/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookC;

import java.io.IOException;
import java.text.NumberFormat;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import loginAndRegisterStuff.InputException;
import loginAndRegisterStuff.UserLogin;
import loginAndRegisterStuff.UserLoginRegister;
/**
 * FXML Controller class
 *
 * @author Nexo
 */

public class StyleBookController {
    // Verbindung zur Datenbank

    private Statement statement;
    @FXML
    private TextField tfUsername;
    @FXML
    private TextField tfPassword;
    @FXML
    private Button btLogin;
    @FXML
    private Button btRegister;
    @FXML
    private TextField tfErrorMsg;

    // Helper
    private final static String VIEWNAME = "styleLogin2.fxml";
    private static final NumberFormat NUMBERFORMAT_2DEC;
    private static Statement mystatement;
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
    public static void show(Stage stage, Statement statement) {
        try {
            StyleBookController.mystatement = statement;
            // View & Controller erstellen
            FXMLLoader loader = new FXMLLoader(StyleBookController.class.getResource(VIEWNAME));
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
            StyleBookController bookSurferC = (StyleBookController) loader.getController();

            // Datenbankzugriff merken
            bookSurferC.statement = statement;

            bookSurferC.stage = stage;

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
    private void actionLogin(ActionEvent event) {
        try {
            UserLoginRegister.login(statement, tfUsername.getText(), tfPassword.getText());
        } catch (InputException ex) {
            tfErrorMsg.setText(ex.getMessage());
        }
    }

    @FXML
    private void actionRegister(ActionEvent event) {
        RegisterController.show(stage, statement);
    }
}
