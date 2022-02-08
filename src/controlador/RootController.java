/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import modelo.Jugador;
import vista.RootView;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author Aridai
 */
public class RootController {
	
	private RootView view;
        private PalabrasController pController;
	private JugadoresController jController;
	private PartidaInicioFXMLController iController;
	private PartidaController playController;
	
	private ObjectProperty<Node> gameNode = new SimpleObjectProperty<>(); 
	private ObjectProperty<Node> palabrasNode = new SimpleObjectProperty<>(); 
	private ObjectProperty<Node> jugadoresNode = new SimpleObjectProperty<>(); 
	private BooleanProperty enJuego = new SimpleBooleanProperty(false);
	
	public RootController() {

		try {
			pController = new PalabrasController();
			
			jController = new JugadoresController();
			iController = new PartidaInicioFXMLController(this);
			
		} catch (IOException e) {
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR FATAL");
			alert.setHeaderText("No se pudo inicializar correctamente la aplicación");
			alert.showAndWait();
			Platform.exit();
		}
		
		view = new RootView();
		view.getTab_game().contentProperty().bind(gameNode);
		view.getTab_puntuaciones().contentProperty().bind(jugadoresNode);
		view.getTab_palabras().contentProperty().bind(palabrasNode);
		
		view.getTab_puntuaciones().disableProperty().bind(enJuego);
		view.getTab_palabras().disableProperty().bind(enJuego);
		
		gameNode.set(iController.getRootView()); // Empezamos con la vista inicial
		jugadoresNode.set(jController.getRootView());
		palabrasNode.set(pController.getRootView());
	}
	
	public void sendFileError(String fileName) {
		
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("ERROR");
		alert.setHeaderText("Error al procesar el fichero: " + fileName);
		alert.setContentText("Compruebe que el fichero está en la subcarpeta resources y no esté abierto por ningún otro programa");
		alert.showAndWait();
		Platform.exit();
	}

	public void iniciarPartida() {
		
		SeleccionJugadorDialog dialog = new SeleccionJugadorDialog(jController.listaJugadorProperty());
		Optional<Jugador> j = dialog.showAndWait();
		
		if( j.isPresent() ) {
			
			try {
				playController = new PartidaController(this);
				gameNode.set(playController.getRootView());
				playController.setJugadorActual(j.get());
				
				enJuego.set(true);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void finPartida() {
		
		enJuego.set(false);
		
		try {
			iController = new PartidaInicioFXMLController(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		gameNode.set(iController.getRootView());
	}
	
	public RootView getRootView() {
		return view;
	}


	public ArrayList<String> getPalabrasList() {
		return new ArrayList<>(pController.getList());
	}

	public ArrayList<Jugador> getJugadoresList() {
		return new ArrayList<>(jController.getListaJugador());
	}

	public final ObjectProperty<Node> gameNodeProperty() {
		return this.gameNode;
	}
	

	public void setJugadoresList(ArrayList<Jugador> jList) {
		jController.setListaJugador(FXCollections.observableArrayList(jList));
	}
	

	public void setPalabrasList(ArrayList<String> pList) {
		pController.setList(FXCollections.observableArrayList(pList));
	}

	public final Node getGameNode() {
		return this.gameNodeProperty().get();
	}
	

	public final void setGameNode(final Node gameNode) {
		this.gameNodeProperty().set(gameNode);
	}
	

	public final ObjectProperty<Node> palabrasNodeProperty() {
		return this.palabrasNode;
	}
	

	public final Node getPalabrasNode() {
		return this.palabrasNodeProperty().get();
	}
	

	public final void setPalabrasNode(final Node palabrasNode) {
		this.palabrasNodeProperty().set(palabrasNode);
	}
	

	public final ObjectProperty<Node> jugadoresNodeProperty() {
		return this.jugadoresNode;
	}
	

	public final Node getJugadoresNode() {
		return this.jugadoresNodeProperty().get();
	}
	

	public final void setJugadoresNode(final Node jugadoresNode) {
		this.jugadoresNodeProperty().set(jugadoresNode);
	}
	
	
}
