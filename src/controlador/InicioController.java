package controlador;

import ahorcadoo.Ahorcadoo;
import ahorcadoo.Snake;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author Carol
 */
public class InicioController implements Initializable {

     @FXML
    private Button btnAhorcado;

    @FXML
    private Button btnGato;

    @FXML
    private Button btnSerpiente;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    void ahorcado(ActionEvent event)  {
        this.cerrarVentanaInicio(); 
        Ahorcadoo.main();
         try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/PartidaInicioFXML.fxml"));
            Parent ventanaPrincipal = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(ventanaPrincipal));
            stage.show();
            
        }catch(IOException e){
            System.out.println("Error");
        }
       
    }
    
     @FXML
    void gato(ActionEvent event) {
        this.cerrarVentanaInicio();
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/primary.fxml"));
            Parent ventanaPrincipal = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(ventanaPrincipal));
            stage.show();
            
        }catch(IOException e){
            System.out.println("Error");
        }
    }

    @FXML
    void serpiente(ActionEvent event) {
        this.cerrarVentanaInicio();
        Snake.main();
    }
    
    public void cerrarVentanaInicio(){
        Stage stage = (Stage) this.btnAhorcado.getScene().getWindow();
        stage.close();
    }
  
}
