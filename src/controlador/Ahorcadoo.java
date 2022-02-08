package ahorcadoo;
import javafx.application.Application;
import javafx.stage.Stage;
import controlador.RootController;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import modelo.Jugador;
/**
 *
 * @author Aridai
 */
public class Ahorcadoo extends Application {
    public static final String PALABRASURL = "src/text/palabras.txt";
    public static final String JUGADORESURL = "src/text/jugadores.txt";
    
    private RootController rootController = new RootController();

    public static void main() {
		launch();
    }
    
    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(rootController.getRootView(), 740, 500);
		primaryStage.setTitle("Juego del ahorcado");
		primaryStage.setScene(scene);
		primaryStage.show();
    }
    
    @Override
    public void init() throws Exception {
	super.init();
	cargarJugadores();
	cargarPalabras();
    }
    
    @Override
    public void stop() throws Exception {	
	super.stop();
	guardarPalabras();
	guardarJugadores();
    }

    private void cargarJugadores() {
		FileReader file = null;
		BufferedReader reader = null;
		ArrayList<Jugador> jList = new ArrayList<>();
		
		try {
			
			file = new FileReader(JUGADORESURL);
			reader = new BufferedReader(file);
			
			String line;
			while( (line = reader.readLine()) != null ) {
				line.trim();
				String[] jArray = line.split(",");
				jList.add(new Jugador(jArray[0], Integer.parseInt(jArray[1])));
			}
			
			rootController.setJugadoresList(jList);
			
		} catch (IOException | NumberFormatException e) {
			sendFileError(JUGADORESURL);
		} finally {
			try {	
				
				if( reader != null )
					reader.close();
				
				if( file != null )
					file.close();
				
			} catch (IOException e) {
				sendFileError(JUGADORESURL);
			}
		}
	}
    
    	private void cargarPalabras() {
		FileReader file = null;
		BufferedReader reader = null;
		ArrayList<String> pList = new ArrayList<>();
		
		try {
			
			file = new FileReader(PALABRASURL);
			reader = new BufferedReader(file);
			
			String line;
			while( (line = reader.readLine()) != null ) {
				line.trim();
				pList.add(line);
			}
			
			rootController.setPalabrasList(pList);
			
		} catch (IOException | NumberFormatException e) {
			sendFileError(PALABRASURL);
		} finally {
			
			try {		
				if( reader != null )
					reader.close();
				if( file != null )
					file.close();
				
			} catch (IOException e) {
				sendFileError(PALABRASURL);
			}
		}
	}


	private void guardarPalabras() {
		
		FileOutputStream file = null;
		OutputStreamWriter out = null;
		BufferedWriter writer = null;
		
		try {
			
			file = new FileOutputStream(PALABRASURL);
			out = new OutputStreamWriter(file, StandardCharsets.UTF_8);
			writer = new BufferedWriter(out);
			for( String str : rootController.getPalabrasList()) {
				writer.write(str.toUpperCase()); 
				writer.newLine();
			}
			
		} catch (IOException e) {
			sendFileError(PALABRASURL);
		} finally {
			
			try {	
				if( writer != null )
					writer.close();
				
				if( out != null )
					out.close();
				
				if( file != null )
					file.close();
				
			} catch (IOException e) {
				sendFileError(PALABRASURL);
			}
		}
	}

	private void guardarJugadores() {
		
		FileOutputStream file = null;
		OutputStreamWriter out = null;
		BufferedWriter writer = null;
		
		try {
			
			file = new FileOutputStream(JUGADORESURL);
			out = new OutputStreamWriter(file, StandardCharsets.UTF_8);
			writer = new BufferedWriter(out);
			
			for( Jugador j : rootController.getJugadoresList()) {
				writer.write(j.getNombre() + "," + j.getPuntuacion()); 
				writer.newLine();
			}
			
		} catch (IOException e) {
			sendFileError(JUGADORESURL);
		} finally {
			
			try {	
				if( writer != null )
					writer.close();
				
				if( out != null )
					out.close();
				
				if( file != null )
					file.close();
				
			} catch (IOException e) {
				sendFileError(JUGADORESURL);
			}
		}
	}
        
	private void sendFileError(String fileName) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("ERROR");
		alert.setHeaderText("Error al guardar el fichero:" + fileName);
		alert.setContentText("Compruebe que el fichero no esté abierto por otro programa");
		alert.showAndWait(); 
		//AridaiOrtega
	}
}
    


