/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 *
 * @author Aridai
 */
public class Jugador {
    private StringProperty nombre = new SimpleStringProperty();
	private IntegerProperty puntuacion = new SimpleIntegerProperty();
	
	public Jugador( String nombre, int puntuacion ) {
		
		setNombre(nombre);
		setPuntuacion(puntuacion);
	}

	public final StringProperty nombreProperty() {
		return this.nombre;
	}
	

	public final String getNombre() {
		return this.nombreProperty().get();
	}
	

	public final void setNombre(final String nombre) {
		this.nombreProperty().set(nombre);
	}
	

	public final IntegerProperty puntuacionProperty() {
		return this.puntuacion;
	}
	

	public final int getPuntuacion() {
		return this.puntuacionProperty().get();
	}
	

	public final void setPuntuacion(final int puntuacion) {
		this.puntuacionProperty().set(puntuacion);
	}
}
