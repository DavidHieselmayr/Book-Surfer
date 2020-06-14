/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookC;

import java.io.IOException;
import java.net.URL;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import modelBookSurfer.Autor;
import modelBookSurfer.Buch;
import modelBookSurfer.Genre;
import searchDbs.MainpageSearch;

/**
 * FXML Controller class
 *
 * @author Nexo
 */
public class ShopControllerController implements Initializable {

    @FXML
    private TextField tfSearch;
    @FXML
    private Button btSearch;
    
    private static Stage stage;
    private static Statement statement;
    private static final String VIEWNAME = "shopController.fxml";
    
    private MainpageSearch ms;
    
    public static void show(Stage stage, Statement statement, String userInput, MainpageSearch ms) {
        try {
            // View & Controller erstellen
            FXMLLoader loader = new FXMLLoader(MainPageController.class.getResource(VIEWNAME));
            Parent root = (Parent) loader.load();

            // Scene erstellen
            Scene scene = new Scene(root);

            // Stage: Entweder übergebene Stage verwenden (Primary Stage) oder neue erzeugen
            if (stage == null) {
                stage = new Stage();
            }
            stage.setScene(scene);
            stage.setTitle("BookSurfer-Suchergebnisse");

            // Controller ermitteln
            ShopControllerController shopController = (ShopControllerController) loader.getController();
            
            
            shopController.ms = ms;
            
            shopController.tfSearch.setText(userInput);
            
            shopController.printSearchResult();
            // Datenbankzugriff merken
            ShopControllerController.statement = statement;

            ShopControllerController.stage = stage;

            // View initialisieren
            // lles anzeigen
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(StyleBookController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Something wrong with " + VIEWNAME + "!");
            ex.printStackTrace(System.err);
            System.exit(1);
        } catch (Exception ex) {
            Logger.getLogger(StyleBookController.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace(System.err);
            System.exit(2);
        }
    }
    @FXML
    private GridPane contentBuecher;
    @FXML
    private GridPane contentGenres;
    @FXML
    private GridPane contentAutoren;
    
    public void printSearchResult(){
        int index = 0;
        
        for(Autor autor : ms.getAutoren()){
            
            Button bt = new Button(autor.getVorname() +" "+ autor.getNachname());
            bt.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                //weiter zur Detailseite
            }
            });
            contentAutoren.addRow(index, bt);
            index++;
        }
        index = 0;
        for(Buch buch : ms.getBuecher()){
            
            Button bt = new Button(buch.getTitel());
            
            bt.setOnAction(e -> StyleSpezifischeBuchansichtController.show(stage, statement, buch));
            
            contentBuecher.addRow(index, bt);
            index++;
        }
        index = 0;
        for(Genre genre : ms.getGenres()){
            
            Button bt = new Button(genre.getName());
            bt.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                //weiter zur Detailseite
            }
            });
            
            contentGenres.addRow(index, bt);
            index++;
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onActionBtSearch(ActionEvent event) {
        MainpageSearch ms = MainpageSearch.findAll(tfSearch.getText(), statement);
        ShopControllerController.show(stage, statement, tfSearch.getText(), ms);
    }
}
