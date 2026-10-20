package com.dcgallaga.liebreytortuga;

import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;


public class PrimaryController {
    
   
    @FXML 
    private ProgressBar progressBarLiebre;
    @FXML 
    private ProgressBar progressBarTortuga;
    @FXML 
    private Button btnCarrera;
    @FXML 
    private Label lblTortuga; 
    @FXML 
    private Label lblLiebre;    

    @FXML
    private void iniciarCarrera(ActionEvent event) {
        
        btnCarrera.setDisable(true);
        //Inicia las progressBars en 0 
        progressBarLiebre.setProgress(0.0);
        progressBarTortuga.setProgress(0.0);
        Thread hiloLiebre = new Thread(() -> {
            //Usamos el try para que el hilo "trate" de hacer su trabajo de la manera que indicamos
            try {
                //El hilo tiene que repetir una misma accion 100 veces que es llenar la progressBar
                for (int i = 1; i <= 100; i++) {
                    if (i == 40) {
                        Platform.runLater(() -> lblLiebre.setText("La Liebre: ¡Se durmió!"));
                        Thread.sleep(6000); // Se pausa 6s
                        Platform.runLater(() -> lblLiebre.setText("La Liebre: ¡Despertó y está corriendo!"));
                    }
                    
                    Thread.sleep(20); 
                    
                 
                    final double progresoActual = i / 100.0;
                   
                    Platform.runLater(() -> {
                        progressBarLiebre.setProgress(progresoActual);
                    });
                }
               
            } catch (InterruptedException e) {
                e.printStackTrace();
               
            } 
        });

        hiloLiebre.start();
        Thread hiloTortuga = new Thread(() -> {
            
            try {
               
                for (int i = 1; i <= 100; i++) {
                    
                    Thread.sleep(80); 
                    
                    
                    final double progresoActual = i / 100.0;
                   
                    Platform.runLater(() -> {
                        progressBarTortuga.setProgress(progresoActual);
                    });
                }
                
            } catch (InterruptedException e) {
                e.printStackTrace();
               
            } 
        });

        hiloTortuga.start();       

        
    }
    
}
