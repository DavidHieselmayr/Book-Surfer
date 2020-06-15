/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookC;

import java.io.IOException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import loginAndRegisterStuff.InputException;
import loginAndRegisterStuff.User;

/**
 *
 * @author fabia
 */
public class RegisterController {

    private final static String VIEWNAME = "styleRegister.fxml";
    private static final NumberFormat NUMBERFORMAT_2DEC;
    private static Statement mystatement;
    private static Stage stage;
    private static Statement statement;

    @FXML
    private TextField tfErrorMsg;
    @FXML
    private Button btLogin;
    @FXML
    private Button btRegister;
    @FXML
    private TextField tfUsername;
    @FXML
    private TextField tfPassword;
    @FXML
    private TextField tfPassword1;

    static {
        NUMBERFORMAT_2DEC = NumberFormat.getNumberInstance();
        NUMBERFORMAT_2DEC.setMaximumFractionDigits(2);
        NUMBERFORMAT_2DEC.setMinimumFractionDigits(2);
    }

    public static void show(Stage stage, Statement statement) {
        try {
            RegisterController.mystatement = statement;
            // View & Controller erstellen
            FXMLLoader loader = new FXMLLoader(RegisterController.class.getResource(VIEWNAME));
            Parent root = (Parent) loader.load();

            // Scene erstellen
            Scene scene = new Scene(root);

            // Stage: Entweder übergebene Stage verwenden (Primary Stage) oder neue erzeugen
            if (stage == null) {
                stage = new Stage();
            }
            stage.setScene(scene);
            stage.setTitle("BookSurfer-Register");

            // Controller ermitteln
            RegisterController bookSurferRegisterC = (RegisterController) loader.getController();

            // Datenbankzugriff merken
            bookSurferRegisterC.statement = statement;

            bookSurferRegisterC.stage = stage;

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
    private void actionLogin(ActionEvent event) {
        LoginController.show(stage, statement);
    }

    @FXML
    private void actionRegister(ActionEvent event) {
        try {
            User.register(statement, tfUsername.getText(), tfPassword.getText(), tfPassword1.getText());
            LoginController.show(stage, statement);
        } catch (InputException ex) {
            tfErrorMsg.setText(ex.getMessage());
        }
    }
}
