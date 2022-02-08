/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import controlador.RootController;
import modelo.Jugador;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.converter.NumberStringConverter;
import modelo.PartidaModel;

/**
 * FXML Controller class
 *
 * @author Aridai
 */
public class PartidaController implements Initializable {

	private RootController rootController;

	private static final int VIDAS = 9;

	private static final int T_PEQUEÑO = 1;
	private static final int T_MEDIO = 2;
	private static final int T_GRANDE = 3;
	
	private static final int ADIVINAR_PUNTOS = 10;
	
	private int palabraActual = 0;
	
	private ArrayList<String> listaPalabras;
	private Jugador jugadorActual;
        
	@FXML
	private GridPane view;
	
	@FXML
	private ImageView ahorcadoImg;
	
	@FXML
	private Text puntosTxt, vidasTxt;
	
	@FXML
	private Label letrasTxt, finalTxt;
	
	@FXML
	private TextField intentoTxt;
	
	@FXML
	private Button letraBt, respuestaBt;
	
	private PartidaModel model = new PartidaModel();
	
	public PartidaController(RootController rootController) throws IOException {
		
		this.rootController = rootController;
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/PartidaFXML.fxml"));
		loader.setController(this);
		loader.load();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		ahorcadoImg.imageProperty().bind(model.imageProperty());
		puntosTxt.textProperty().bindBidirectional(model.puntosProperty(), new NumberStringConverter());
		vidasTxt.textProperty().bindBidirectional(model.vidasProperty(), new NumberStringConverter());
		letrasTxt.textProperty().bind(model.usadoProperty());
		finalTxt.textProperty().bind(model.adivinarProperty());
		model.intentoProperty().bind(intentoTxt.textProperty());
		
		model.vidasProperty().addListener( (v, ov, nv) -> changeImage());

		letraBt.setOnAction( evt -> addLetra() );
		respuestaBt.setOnAction( evt -> intentoAdivinar() );
		
		iniciarPartida();
	}
	
	private void changeImage() {
		
		if( model.getVidas() > 0 ) {
			model.setImage(new Image(getClass().getResource(String.format("/images/%d.png",10-model.getVidas())).toString()));
		}
	}
	

	private void iniciarPartida() {
		model.setVidas(VIDAS);
		model.setPuntos(0);
		
		listaPalabras = rootController.getPalabrasList();
		palabraActual = (int) (Math.random() * listaPalabras.size());
		iniciarPalabra();
	}
	

	private void iniciarPalabra() {
		
		final String palabraOriginal =  listaPalabras.get(palabraActual);
		

		StringBuilder palabra = new StringBuilder(palabraOriginal.replaceAll("[a-z,A-Z,0-9]", "-"));
		
		int len = palabra.length();
		int n_letras;

		if( len < 5 ) {
			n_letras = T_PEQUEÑO;
		} else if( len < 8 ) {
			n_letras = T_MEDIO;
		} else {
			n_letras = T_GRANDE;
		}
		
		while( n_letras > 0 ) {
			
			int r = (int) (Math.random() * palabra.length());
			palabra.setCharAt(r, palabraOriginal.charAt(r));
			n_letras--;
		}
		
		model.setAdivinar(palabra.toString());
	}

	private void addLetra() {
		
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Letra");
		dialog.setHeaderText("Introduce una letra");
		
		Optional<String> str = dialog.showAndWait();
		Character c = ' ';
		if( str.isPresent() && !str.get().isEmpty() && !str.get().isBlank() ) {
			

			c = str.get().charAt(0);
			c = Character.toUpperCase(c); 
			
			if( !Character.isLetter(c) && !Character.isDigit(c) ) {
				sendLetterWarning();
				return;
			}
		}
		
		else {
			sendLetterWarning();
			return;
		}
		
		final String palabraOriginal = listaPalabras.get(palabraActual);
		
		// Hay que evitar las letras repetidas
		if( model.getUsado().indexOf(c) != -1 ) {
			return;
		}
		
		if( model.getAdivinar().indexOf(c) != -1 ) {
			
			int n, j, i, m;
			n = j = m = 0;

			while( (i = model.getAdivinar().indexOf(c,j)) != -1 ) {
				j = i+1;
				n++;
			}
			
			j = 0;
			while( (i = palabraOriginal.indexOf(c,j)) != -1 ) {
				j = i+1;
				m++;
			}

			if( m == n ) {
				return;
			}
			
		}
		
		if( palabraOriginal.indexOf(c) != -1) {
			
			model.setPuntos(model.getPuntos()+1);
			StringBuilder palabra = new StringBuilder(model.getAdivinar());
			int i, j;
			j = 0;
			while( (i = palabraOriginal.indexOf(c,j)) != -1 ) {
				palabra.setCharAt(i, c);
				j = i+1;
			}
			
			model.setAdivinar(palabra.toString());
			
			if( palabra.toString().compareTo(palabraOriginal) == 0 ) {
				acabarJuego(false);
			}
			
		} else {

			model.setVidas(model.getVidas()-1);
			
			if( model.getVidas() <= 0 ) { 
				fin();
			} else {
				model.setUsado(model.getUsado()+c);
			}
		}
		
	}
	

	private void acabarJuego(boolean esAdivinado) {
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Fin");
		
		if( !esAdivinado ) {
			alert.setHeaderText("Palabra completada");
		} else {
			alert.setHeaderText("Palabra adivinada");
			model.setPuntos(model.getPuntos()+ADIVINAR_PUNTOS); // 10 puntos extras por adivinar la palabra
		}
		
		alert.showAndWait();

		listaPalabras.remove(listaPalabras.get(palabraActual));
		
		if( listaPalabras.size() == 0 ) {
			fin();
		} else {
			palabraActual = (int) (Math.random() * listaPalabras.size());
			model.setUsado(""); // Limpiamos las letras usadas
			iniciarPalabra(); // Volvemos a jugar
		}
	}
	

	private void fin() {
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Fin de juego");
		alert.setHeaderText("Se ha acabado el juego");
                
		if( jugadorActual.getPuntuacion() < model.getPuntos() ) {
			
			alert.setContentText("Su puntación total ha sido de " + model.getPuntos() + "." +
								 "\nHa superado su anterior puntuación");
			
			jugadorActual.setPuntuacion(model.getPuntos());
		}
		
		else {
			alert.setContentText("Su puntación total ha sido de " + model.getPuntos() + ".");
		}
		
		alert.showAndWait();
		
		// Avisamos de que la partida ha terminado
		rootController.finPartida();
	}
	
	private void intentoAdivinar() {
		
		if( model.getIntento().equalsIgnoreCase(listaPalabras.get(palabraActual))) {
			acabarJuego(true);
			
		} else {
			
			model.setVidas(model.getVidas()-1);
			if( model.getVidas() <= 0 ) { 
				fin();
			} 
		}
	}
	
	private void sendLetterWarning() {
		
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Letra");
		alert.setHeaderText("Letra no válida");
		alert.setContentText("La letra introducida no es válida, vuelva a intentarlo");	
		alert.showAndWait();
	}
	
	public void setJugadorActual(Jugador jugadorActual) {
		this.jugadorActual = jugadorActual;
	}
	
	public GridPane getRootView() {
		return view;
	}

}