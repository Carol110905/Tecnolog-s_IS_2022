package controlador;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class PartidaInicioFXMLController implements Initializable {

	private RootController rootController;
	
	private static final String HELPURL = "/text/helpText.txt";
	
	@FXML
	private VBox view;
	
	@FXML 
	private Button initBt;
	
	@FXML
	private ImageView helpIcon;
	
	@FXML
	private Label helpText;
	
	private StringProperty help = new SimpleStringProperty();
	
	public PartidaInicioFXMLController(RootController rootController) throws IOException {
		
		this.rootController = rootController;
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/PartidaInicioFXML.fxml"));
		loader.setController(this);
		loader.load();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		helpText.textProperty().bind(help);
		loadTextInfo();
		
		initBt.setOnAction( evt -> onInitAction());
		
	}
	
	private void onInitAction() {
		rootController.iniciarPartida();
	}

	private void loadTextInfo() {
		
		InputStream reader = null;
		InputStreamReader in = null;
		BufferedReader buffer = null;
		
		try {
			
			reader = getClass().getResourceAsStream(HELPURL); 
			in = new InputStreamReader(reader, StandardCharsets.UTF_8);
			buffer = new BufferedReader(in);
			
			String line;
			StringBuilder myText = new StringBuilder();
			
			while( (line = buffer.readLine()) != null ) {
				myText.append(line+"\n");
			}
			
			help.set(myText.toString());
			
		} catch (IOException e) {
			rootController.sendFileError(HELPURL);
		} finally {
			
			try {
				if( buffer != null ) {
					buffer.close();
				}
				
				if( in != null ) {
					in.close();
				}
				
				if( reader != null ) {
					reader.close();
				}
			} catch (IOException e) {
				rootController.sendFileError(HELPURL);
			}
		}
		
	}
	
	public VBox getRootView() {
		return view;
	}

}