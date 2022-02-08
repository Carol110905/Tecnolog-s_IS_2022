/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

/**
 *
 * @author Aridai
 */

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
public class RootView extends TabPane {

	private Tab tab_game;
	private Tab tab_palabras;
	private Tab tab_puntuaciones;
	
	public RootView() {
		
		tab_game = new Tab("Partida");
		tab_palabras= new Tab("Palabras");
		tab_puntuaciones = new Tab("Puntuaciones");

		// No cerramos las pestañas
		setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
		getTabs().addAll(tab_game, tab_palabras, tab_puntuaciones);
	}

	public Tab getTab_game() {
		return tab_game;
	}

	public Tab getTab_palabras() {
		return tab_palabras;
	}

	public Tab getTab_puntuaciones() {
		return tab_puntuaciones;
	}
}
