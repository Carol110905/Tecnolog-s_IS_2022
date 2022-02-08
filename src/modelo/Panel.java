/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 *
 * @author Aridai
 */
public class Panel {
    private ImageView imagen;
    private Boolean usado;
    private String usadoPor;
    public Panel(ImageView imagen, Boolean usado, String usadopor) {
        this.imagen = imagen;
        this.usado = usado;
        this.usadoPor = usadopor;
    }
    
    public String getUsadoPor() {
        return usadoPor;
    }

    public void setUsadoPor(String usadoPor) {
        this.usadoPor = usadoPor;
    }

    public ImageView getImagen() {
        return imagen;
    }
    public void setImagen(Image imagen) {
        this.imagen.setImage(imagen);
    }
    public Boolean getUsado() {
        return usado;
    }
    public void setUsado(Boolean usado) {
        this.usado = usado;
    }

    
}