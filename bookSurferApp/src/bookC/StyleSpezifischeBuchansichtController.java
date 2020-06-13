/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookC;

import java.io.IOException;
import java.net.URL;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modelBookSurfer.Buch;

/**
 * FXML Controller class
 *
 * @author Nexo
 */
public class StyleSpezifischeBuchansichtController implements Initializable {

    @FXML
    private TextField tfSearch;
    @FXML
    private Button btSearch;
    private Buch buch;
    
    private final static String VIEWNAME = "StyleSpezifischeBuchansicht.fxml";
    private static final NumberFormat NUMBERFORMAT_2DEC;
    private static Statement statement;
    private static Stage stage;

    static {
        NUMBERFORMAT_2DEC = NumberFormat.getNumberInstance();
        NUMBERFORMAT_2DEC.setMaximumFractionDigits(2);
        NUMBERFORMAT_2DEC.setMinimumFractionDigits(2);
    }

    /**
     * Anzeige der View.
     * <br>
     * Diese Methode erstellt eine Instanz der View und dieses Controllers
     * (FXML-Loader) und richtet alles (also vor allem den Controller) so weit
     * ein, dass es angezeigt werden kann.
     *
     * @param stage Stage, in der die View angezeigt werden soll; null, wenn
     * neue erstellt werden soll.
     * @param statement Datenbankverbindung
     */
    public static void show(Stage stage, Statement statement, Buch buch) {
        try {
            // View & Controller erstellen
            FXMLLoader loader = new FXMLLoader(StyleSpezifischeBuchansichtController.class.getResource(VIEWNAME));
            Parent root = (Parent) loader.load();

            // Scene erstellen
            Scene scene = new Scene(root);

            // Stage: Entweder übergebene Stage verwenden (Primary Stage) oder neue erzeugen
            if (stage == null) {
                stage = new Stage();
            }
            stage.setScene(scene);
            stage.setTitle(buch.getTitel());

            // Controller ermitteln
            StyleSpezifischeBuchansichtController ssbController = (StyleSpezifischeBuchansichtController) loader.getController();

            // Datenbankzugriff merken
            StyleSpezifischeBuchansichtController.statement = statement;

            StyleSpezifischeBuchansichtController.stage = stage;
            
            ssbController.buch = buch;
            
            ssbController.displayInformation();

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
    private Text lbTitel;
    @FXML
    private Text lbReleaseDatum;
    @FXML
    private Text lbKapitelanzahl;
    @FXML
    private Text lbPreis;
    @FXML
    private TextField tfKommentar;
    @FXML
    private Button btKommentar;
    @FXML
    private ImageView ivCover;
    
    public void displayInformation(){
        lbTitel.setText(buch.getTitel());
        lbReleaseDatum.setText(buch.getReleasedatum().toString());
        lbKapitelanzahl.setText(String.valueOf(buch.getKapitelanzahl()));
        lbPreis.setText(String.valueOf(buch.getPreis()));
        System.out.println(buch.getBuchid());
        Image i = new Image("file:../../data/bilder/buch/" + buch.getBuchid()+".jpg");
        //Image i = new Image("file:../../data/bilder/buch/2.jpg");
        ivCover.setImage(i);
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
    }

    @FXML
    private void onActionBtKommentar(ActionEvent event) {
    }
    
}
