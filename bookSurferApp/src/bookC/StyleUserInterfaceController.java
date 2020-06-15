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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import loginAndRegisterStuff.CurrentUser;
import loginAndRegisterStuff.InputException;
import modelBookSurfer.Genre;

/**
 * FXML Controller class
 *
 * @author Nexo
 */
public class StyleUserInterfaceController implements Initializable {

    static Stage stage;
    static Statement statement;
    private static final String VIEWNAME = "StyleUserInterface.fxml";

    public static void show(Stage stage, Statement statement) {
        try {
            // View & Controller erstellen
            FXMLLoader loader = new FXMLLoader(StyleSpezifischeGenreAnsichtController.class.getResource(VIEWNAME));
            Parent root = (Parent) loader.load();

            // Scene erstellen
            Scene scene = new Scene(root);

            // Stage: Entweder übergebene Stage verwenden (Primary Stage) oder neue erzeugen
            if (stage == null) {
                stage = new Stage();
            }
            stage.setScene(scene);
            stage.setTitle(CurrentUser.getCurrentUser().getUsername());

            // Controller ermitteln
            StyleUserInterfaceController ssbController = (StyleUserInterfaceController) loader.getController();

            // Datenbankzugriff merken
            StyleUserInterfaceController.statement = statement;

            StyleUserInterfaceController.stage = stage;

            ssbController.displayInformation();

            // View initialisieren
            // lles anzeigen
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Something wrong with " + VIEWNAME + "!");
            ex.printStackTrace(System.err);
            System.exit(1);
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace(System.err);
            System.exit(2);
        }
    }
    @FXML
    private Text tBenutzername;
    @FXML
    private Text tGuthaben;

    public void displayInformation() {
        tBenutzername.setText(CurrentUser.getCurrentUser().getUsername());
        try {
            tGuthaben.setText(String.valueOf(CurrentUser.getCurrentUser().getGuthaben()));
        } catch (InputException ex) {
            Logger.getLogger(StyleUserInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void onMouseClickedLogo(MouseEvent event) {
        StyleMainPageController.show(stage, statement);
    }

}
