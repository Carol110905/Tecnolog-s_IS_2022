/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import modelo.Jugador;

/**
 *
 * @author Aridai
 */
public class JugadoresController implements Initializable {

	@FXML
	private BorderPane view;
	
	@FXML
	private TableView<Jugador> playersTable;
	private ObservableList<Jugador> jList = FXCollections.observableArrayList(new ArrayList<Jugador>());
	private ListProperty<Jugador> list =  new SimpleListProperty<>(jList);;
	
	public JugadoresController() throws IOException {
				
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/JugadoresFXML.fxml"));
		loader.setController(this);
		loader.load();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		playersTable.itemsProperty().bind(list);
	}

	public final ListProperty<Jugador> listaJugadorProperty() {
		return this.list;
	}
	

	public final ObservableList<Jugador> getListaJugador() {
		return this.listaJugadorProperty().get();
	}
	

	public final void setListaJugador(final ObservableList<Jugador> listaJugador) {
		this.listaJugadorProperty().set(listaJugador);
	}
	
	public BorderPane getRootView() {
		return view;
	}
	

}
