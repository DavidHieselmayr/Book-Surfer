/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookC;

import java.io.IOException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import searchDbs.MainpageSearch;
//import loginAndRegisterStuff.UserRegister;
/**
 * FXML Controller class
 *
 * @author Nexo
 */
public class StyleMainPageController {
    // Verbindung zur Datenbank

    Statement statement;
    @FXML
    private TextField tfSearchUser;

    // Helper
    private final static String VIEWNAME = "styleHausaMain.fxml";
    static Statement mystatement;

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
            StyleMainPageController.mystatement = statement;
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
            stage.setTitle("BookSurfer");

            // Controller ermitteln
            StyleMainPageController bookSurferC = (StyleMainPageController) loader.getController();

            // Datenbankzugriff merken
            bookSurferC.statement = statement;

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
    private void actionSearchUserInput(ActionEvent event) {
        MainpageSearch  search= new MainpageSearch(tfSearchUser.getText(), statement);

        for (String entry : search.findAll()) {

        }

    }
}
