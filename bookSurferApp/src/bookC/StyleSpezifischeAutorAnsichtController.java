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
import java.util.List;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modelBookSurfer.Autor;
import modelBookSurfer.Buch;
import searchDbs.MainpageSearch;

/**
 * FXML Controller class
 *
 * @author Nexo
 */
public class StyleSpezifischeAutorAnsichtController implements Initializable {

    @FXML
    private TextField tfSearch;
    @FXML
    private Button btSearch;
    private Autor autor;
    
    private final static String VIEWNAME = "StyleSpezifischeAutorAnsicht.fxml";
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
    public static void show(Stage stage, Statement statement, Autor autor) {
        try {
            // View & Controller erstellen
            FXMLLoader loader = new FXMLLoader(StyleSpezifischeAutorAnsichtController.class.getResource(VIEWNAME));
            Parent root = (Parent) loader.load();

            // Scene erstellen
            Scene scene = new Scene(root);

            // Stage: Entweder übergebene Stage verwenden (Primary Stage) oder neue erzeugen
            if (stage == null) {
                stage = new Stage();
            }
            stage.setScene(scene);
            stage.setTitle(autor.getVorname() + " " + autor.getNachname());

            // Controller ermitteln
            StyleSpezifischeAutorAnsichtController ssbController = (StyleSpezifischeAutorAnsichtController) loader.getController();

            // Datenbankzugriff merken
            StyleSpezifischeAutorAnsichtController.statement = statement;

            StyleSpezifischeAutorAnsichtController.stage = stage;
            
            ssbController.autor = autor;
            
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
    private ImageView ivCover;
    @FXML
    private GridPane gPBuecher;
    
    public void displayInformation(){
        lbTitel.setText(autor.getVorname());
        lbReleaseDatum.setText(autor.getNachname());
        lbKapitelanzahl.setText(autor.getGebdatum().toString());
        lbPreis.setText(autor.getBiographie());
        
        Image i = new Image("file:../../data/bilder/buch/" + autor.getAutorid()+".jpg");
        ivCover.setImage(i);
        
        List<Buch> buecher= Buch.getBuecherOfAutor(statement, autor.getAutorid());
        int index = 0;
        for(Buch buch : buecher){
            Button b = new Button(buch.getTitel());
                b.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                //weiter zur Detailseite
                    StyleSpezifischeBuchansichtController.show(stage, statement, buch);
                }
            });
            gPBuecher.add(b, 0, index);
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
