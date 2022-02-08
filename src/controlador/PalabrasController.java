/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
/**
 * FXML Controller class
 *
 * @author Aridai
 */
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;

public class PalabrasController implements Initializable {

	@FXML
	private Button addBt, removeBt;
	
	@FXML
	private BorderPane borderRoot;

	@FXML
	private ListView<String> palabrasList;	

	private ObservableList<String> oList =  FXCollections.observableArrayList(new ArrayList<String>());
	private ListProperty<String> list = new SimpleListProperty<>(oList); 
	
	private StringProperty palabraSelected = new SimpleStringProperty();
	
	public PalabrasController() throws IOException {
				
		FXMLLoader loader = new FXMLLoader( getClass().getResource("/vista/PalabrasFXML.fxml"));
		loader.setController(this);
		loader.load();
	
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		palabrasList.itemsProperty().bind(list);
		removeBt.disableProperty().bind( palabrasList.getSelectionModel().selectedItemProperty().isNull() );
		palabraSelected.bind( palabrasList.getSelectionModel().selectedItemProperty());
		addBt.setOnAction( evt -> insertarPalabra() );
		removeBt.setOnAction( evt -> quitarPalabra() );
	}
	

	private void insertarPalabra() {
		
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Insertar palabra");
		dialog.setHeaderText("Inserte una nueva palabra");
		dialog.setContentText("La palabra debe tener al menos 3 caracteres");
		
		Optional<String> palabra = dialog.showAndWait();

		if( palabra.isPresent() && palabra.get().length() < 3) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Palabra erronea");
			alert.setHeaderText("La palabra debe tener al menos 3 caracteres");
			alert.showAndWait();
			
		} else if( palabra.isPresent() ) {
			list.add(palabra.get().toUpperCase()); 
		}
	}
	
	private void quitarPalabra() {
		
		if( palabraSelected.get() != null ) {
			list.remove(palabraSelected.get());
		}
	}
	
	public BorderPane getRootView() {
		return borderRoot;
	}

	public final ListProperty<String> listProperty() {
		return this.list;
	}
	

	public final ObservableList<String> getList() {
		return this.listProperty().get();
	}
	

	public final void setList(final ObservableList<String> list) {
		this.listProperty().set(list);
	}
	
	

}

