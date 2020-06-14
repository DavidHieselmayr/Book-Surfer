/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookC;

import java.io.IOException;
import java.net.URL;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import loginAndRegisterStuff.CurrentUser;
import modelBookSurfer.Buch;
import searchDbs.MainpageSearch;
//import loginAndRegisterStuff.UserRegister;
/**
 * FXML Controller class
 *
 * @author Nexo
 */
public class StyleMainPageController {
    // Verbindung zur Datenbank

    private Statement statement;
    @FXML
    private TextField tfSearch;
    @FXML
    private Button btSearch;
    
    private Stage stage;

    // Helper
    private final static String VIEWNAME = "styleHausaMain.fxml";

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
    public static void show(Stage stage, Statement statement) {
        try {
            // View & Controller erstellen
            FXMLLoader loader = new FXMLLoader(StyleMainPageController.class.getResource(VIEWNAME));
            Parent root = (Parent) loader.load();

            // Scene erstellen
            Scene scene = new Scene(root);

            // Stage: Entweder übergebene Stage verwenden (Primary Stage) oder neue erzeugen
            if (stage == null) {
                stage = new Stage();
            }
            stage.setScene(scene);
            stage.setTitle("Hauptseite");

            // Controller ermitteln
            StyleMainPageController bookSurferC = (StyleMainPageController) loader.getController();
            bookSurferC.displayGekaufteBuecher();
            // Datenbankzugriff merken
            bookSurferC.statement = statement;
            bookSurferC.stage = stage;
            // View initialisieren
            // lles anzeigen
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(StyleMainPageController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Something wrong with " + VIEWNAME + "!");
            ex.printStackTrace(System.err);
            System.exit(1);
        } catch (Exception ex) {
            Logger.getLogger(StyleMainPageController.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace(System.err);
            System.exit(2);
        }
    }
    @FXML
    private Label tKonto;
    @FXML
    private ImageView ivLogo;
    @FXML
    private GridPane gPGekaufteBuecher;
    
    private void displayGekaufteBuecher(){
        List<Buch> buecher = CurrentUser.getCurrentUser().getGekaufteBuecher();
        int index = 0;
        for(Buch buch : buecher){
            ImageView iv = new ImageView();
            
            Image i = new Image("file:../../data/bilder/buch/" + buch.getBuchid()+".jpg");
            iv.setFitHeight(200);
            iv.setFitWidth(150);
            iv.setImage(i);
            iv.addEventHandler(MouseEvent.MOUSE_CLICKED, e->StyleSpezifischeBuchansichtController.show(stage, statement, buch));
            gPGekaufteBuecher.add(iv, index%2, (int)Math.floor(index/2));
            index++;
        }
        if(buecher.isEmpty()){
            Text t = new Text();
            t.setText("Noch kein Buch gekauft.");
            gPGekaufteBuecher.add(t, 0, 0);
        }
    }

    @FXML
    private void onActionBtSearch(ActionEvent event) {
        MainpageSearch ms = MainpageSearch.findAll(tfSearch.getText(), statement);
        ShopControllerController.show(stage, statement, tfSearch.getText(), ms);
    }

    @FXML
    private void onMouseClickedTKonto(MouseEvent event) {
        StyleUserInterfaceController.show(stage, statement);
    }

    @FXML
    private void onMouseClickedLogo(MouseEvent event) {
    }
}
