package com.dcgallaga.appconteothreads;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

public class PrimaryController {

    @FXML
    private TextField txtConteo;
    @FXML 
    private ProgressBar progressBar;
    @FXML 
    private Button btnEsperar;
    
    //El contador esta fuera del ciclo RECUERDALO :3
    private int contador = 0;

    @FXML
    //Hilo principal: Aqui el Boton de contar tiene la funcion de incrementar cuantas veces lo toques 
    private void contarClics(ActionEvent event) {
        contador++;
        txtConteo.setText(String.valueOf(contador));
    }

    @FXML
    private void iniciarEspera(ActionEvent event) {
        //Desactiva el boton para que el usuario no lo este pique y pique 
        btnEsperar.setDisable(true);
        //Inicia la progressBar en 0 
        progressBar.setProgress(0.0);
        //Se crea el hilo secundario 
        Thread hiloSecundario = new Thread(() -> {
            //Usamos el try para que el hilo "trate" de hacer su trabajo de la manera que indicamos
            try {
                //El hilo tiene que repetir una misma accion 100 veces que es llenar la progressBar
                for (int i = 1; i <= 100; i++) {
                    //Hace que el Hilo se tarde una fraccion de segundo para que se tarde 10 segundos c:
                    Thread.sleep(100); 
                    
                    //Calcula el progreso actual. La progressBar solo acepta valores entre 0.0 y 1.0 (por eso se divide entre 100)
                    final double progresoActual = i / 100.0;
                    //El hilo secundario no tiene permiso de tocar la pantalla directamemte, asi que le dice al hilo principal que lo haga 
                    Platform.runLater(() -> {
                        progressBar.setProgress(progresoActual);
                    });
                }
                //Si se interrumpe el proceso del hilo ¿que pasaria?
            } catch (InterruptedException e) {
                e.printStackTrace();
                //Sin importar si el hilo termino o no su trabajo, el boton se volvera a activar 
            } finally {
                Platform.runLater(() -> {
                    btnEsperar.setDisable(false);
                });
            }
        });

        hiloSecundario.start();
    }
}