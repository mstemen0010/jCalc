/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcalc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author matthew.g.stemen
 */
public class JCalc extends Application {
    
    
    @Override
    public void start(Stage stage)  throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("JCalcFXML.fxml"));
        
        Scene scene = new Scene(root);
        Image systemImage = new Image(getClass().getResourceAsStream("/jcalc/images/calcfx_icon_from_vintage_casio_64.jpg"));
        stage.getIcons().add(systemImage);
        stage.setScene(scene);
        stage.setTitle("CalxFx 2.1");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
