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
import modelo.Panel;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PrimaryController implements Initializable {
    ArrayList<Panel> paneles = new ArrayList<Panel>();
    @FXML
    private Label labelJugador22;
    @FXML
    private Label labelJugador12;
    @FXML
    private ImageView Panel1;
    @FXML
    private ImageView Panel2;
    @FXML
    private ImageView Panel3;
    @FXML
    private ImageView Panel4;
    @FXML
    private ImageView Panel5;
    @FXML
    private ImageView Panel6;
    @FXML
    private ImageView Panel7;
    @FXML
    private ImageView Panel8;
    @FXML
    private ImageView Panel9;
    @FXML
    Label labelTurno;
    @FXML
    private Button btnReiniciar;
    @FXML
    private Button btnSalir;
    @FXML 
    private Button btnJugar;


    Image imagegatito = new Image("https://cdn.pixabay.com/photo/2020/08/02/18/49/cat-5458336_1280.png");
    Image imageperrito = new Image("https://dbdzm869oupei.cloudfront.net/img/sticker/preview/46888.png");

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        btnJugar.setVisible(false);
        paneles.add(new Panel(Panel1, false,""));
        paneles.add(new Panel(Panel2, false,""));
        paneles.add(new Panel(Panel3, false,""));
        paneles.add(new Panel(Panel4, false,""));
        paneles.add(new Panel(Panel5, false,""));
        paneles.add(new Panel(Panel6, false,""));
        paneles.add(new Panel(Panel7, false,""));
        paneles.add(new Panel(Panel8, false,""));
        paneles.add(new Panel(Panel9, false,""));

        Random random = new Random();
        int turno = random.nextInt(2 + 1) + 1;
        if (turno == 1) {
            labelTurno.setText("Turno de la maquina");
            turnoMaquina();
            turno++;
        } else {
            labelTurno.setText("Es tu turno");
            turno--;
        }
        // TODO Auto-generated method stub
    }

    @FXML
    private void click1() throws IOException {
        if(!turnoUsuario(0)){
            turnoMaquina();
        }
    }

    @FXML
    private void click2() throws IOException {
        if(!turnoUsuario(1)){
            turnoMaquina();
        }
    }

    @FXML
    private void click3() throws IOException {
        if(!turnoUsuario(2)){
            turnoMaquina();
        }
    }

    @FXML
    private void click4() throws IOException {
        if(!turnoUsuario(3)){
            turnoMaquina();
        }
    }

    @FXML
    private void click5() throws IOException {
        if(!turnoUsuario(4)){
            turnoMaquina();
        }
    }

    @FXML
    private void click6() throws IOException {
        if(!turnoUsuario(5)){
            turnoMaquina();
        }
    }

    @FXML
    private void click7() throws IOException {
        if(!turnoUsuario(6)){
            turnoMaquina();
        }
    }

    @FXML
    private void click8() throws IOException {
        if(!turnoUsuario(7)){
            turnoMaquina();
        }
    }

    @FXML
    private void click9() throws IOException {
        if(!turnoUsuario(8)){
            turnoMaquina();
        }
    }

    @FXML
    private void Reiniciar()throws IOException {
        labelJugador12.setText(0+"");
        labelJugador22.setText(0+"");
        for(int i=0;i<9;i++){
            paneles.get(i).setImagen(null);
            paneles.get(i).setUsado(false);
            paneles.get(i).setUsadoPor("");
        }
        Random random = new Random();
        int turno = random.nextInt(2 + 1) + 1;
        if (turno == 1) {
            labelTurno.setText("Turno de la maquina");
            turnoMaquina();
            turno++;
        } else {
            labelTurno.setText("Es tu turno");
            turno--;
        }
    }

    @FXML
    private void Salir()throws IOException {
        
    }
    @FXML
    private void VolverAJugar() throws IOException{
        for(int i=0;i<9;i++){
            paneles.get(i).setImagen(null);
            paneles.get(i).setUsado(false);
            paneles.get(i).setUsadoPor("");
            paneles.get(i).getImagen().setDisable(false);
        }
        Random random = new Random();
        int turno = random.nextInt(2 + 1) + 1;
        if (turno == 1) {
            labelTurno.setText("Turno de la maquina");
            turnoMaquina();
            turno++;
        } else {
            labelTurno.setText("Es tu turno");
            turno--;
        }
        btnJugar.setVisible(false);
    }
    
    public boolean turnoUsuario(int posicion){
        String ganador = "";
            if (!paneles.get(posicion).getUsado()) {
                Panel panel = paneles.get(posicion);
                panel.setImagen(imagegatito);
                paneles.get(posicion).setUsado(true);
                paneles.get(posicion).setUsadoPor("gatito");
                ganador = verificarGanador("gatito");
            }
            labelTurno.setText("Turno de la maquina");
            if(ganador.equals("nadie")){
                labelTurno.setText("Nadie Gano");
                return true;
            }else if(ganador.equals("gatito")){
                labelTurno.setText("Los gatitos Ganaron");
                return true;
            }else if(ganador.equals("perrito")){
                labelTurno.setText("Los Perritos Ganaron");
                return false;
            }
            return false;
    }
    public void turnoMaquina() {
        String ganador;
        Random random = new Random();
        while (true) {
            int posicion = random.nextInt(7 + 1) + 1;
            if (!paneles.get(posicion).getUsado()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Panel panel = paneles.get(posicion);
                panel.setImagen(imageperrito);
                paneles.get(posicion).setUsado(true);
                paneles.get(posicion).setUsadoPor("perrito");
                ganador = verificarGanador("perrito");
                break;
            }
        }
        labelTurno.setText("Es tu turno");
        if(ganador.equals("nadie")){
            labelTurno.setText("Nadie Gano");
        }else if(ganador.equals("gatito")){
            labelTurno.setText("Los gatitos Ganaron");
        }else if(ganador.equals("perrito")){
            labelTurno.setText("Los Perritos Ganaron");
        }
    }
    public String verificarGanador(String usadopor){
        if(paneles.get(0).getUsadoPor().equals(usadopor) && paneles.get(1).getUsadoPor().equals(usadopor) && paneles.get(2).getUsadoPor().equals(usadopor)){
            return asignarPunto(usadopor);
        }
        if(paneles.get(3).getUsadoPor().equals(usadopor) && paneles.get(4).getUsadoPor().equals(usadopor) && paneles.get(5).getUsadoPor().equals(usadopor)){
            return asignarPunto(usadopor);
        }
        if(paneles.get(6).getUsadoPor().equals(usadopor) && paneles.get(7).getUsadoPor().equals(usadopor) && paneles.get(8).getUsadoPor().equals(usadopor)){
            return asignarPunto(usadopor);
        }
        if(paneles.get(0).getUsadoPor().equals(usadopor) && paneles.get(3).getUsadoPor().equals(usadopor) && paneles.get(6).getUsadoPor().equals(usadopor)){
            return asignarPunto(usadopor);
        }
        if(paneles.get(1).getUsadoPor().equals(usadopor) && paneles.get(4).getUsadoPor().equals(usadopor) && paneles.get(7).getUsadoPor().equals(usadopor)){
            return asignarPunto(usadopor);
        }
        if(paneles.get(2).getUsadoPor().equals(usadopor) && paneles.get(5).getUsadoPor().equals(usadopor) && paneles.get(8).getUsadoPor().equals(usadopor)){
            return asignarPunto(usadopor);
        }
        if(paneles.get(0).getUsadoPor().equals(usadopor) && paneles.get(4).getUsadoPor().equals(usadopor) && paneles.get(8).getUsadoPor().equals(usadopor)){
            return asignarPunto(usadopor);
        }
        if(paneles.get(2).getUsadoPor().equals(usadopor) && paneles.get(4).getUsadoPor().equals(usadopor) && paneles.get(6).getUsadoPor().equals(usadopor)){
            return asignarPunto(usadopor);
        }
        if(paneles.get(0).getUsado() && paneles.get(1).getUsado() && paneles.get(2).getUsado() &&
        paneles.get(3).getUsado() && paneles.get(4).getUsado() && paneles.get(5).getUsado() &&
        paneles.get(6).getUsado() && paneles.get(7).getUsado() && paneles.get(8).getUsado()
        ){
            labelTurno.setText("Nadie Gana");
            btnJugar.setVisible(true);
            btnJugar.setDisable(false);
            return "nadie";
        }
        return "";
    }
    public String asignarPunto(String usadopor){
        if(usadopor.equals("perrito")){
            int puntos = Integer.parseInt(labelJugador22.getText())+1; 
            labelJugador22.setText(puntos+"");
            btnJugar.setVisible(true);
            for(int i=0;i<9;i++){
                paneles.get(i).getImagen().setDisable(true);
            }
            return "perrito";
        }else{
            int puntos = Integer.parseInt(labelJugador12.getText())+1; 
            labelJugador12.setText(puntos+"");
            btnJugar.setVisible(true);
            btnJugar.setDisable(false);
            btnJugar.setDisable(false);
            for(int i=0;i<9;i++){
                paneles.get(i).getImagen().setDisable(true);
            }
            return "gatito";
        }
    }
    public ArrayList<Panel> getPaneles() {
        return paneles;
    }

    public void setPaneles(ArrayList<Panel> paneles) {
        this.paneles = paneles;
    }

    public Label getLabelTurno() {
        return labelTurno;
    }

    public void setLabelTurno(Label labelTurno) {
        this.labelTurno = labelTurno;
    }

}

