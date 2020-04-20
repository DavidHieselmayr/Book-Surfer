/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginAndRegisterStuff;

/**
 *
 * @author fabia
 */
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextField;

public class UserLogin {

    public UserLogin() {
    }
    private String username;
    private String password;
    private Statement statement;
    private TextField tfUsername;
    private TextField tfPasswort;

    public UserLogin(Statement statement, TextField tfUsername, TextField tfPasswort) {
        this.statement = statement;
        this.username = tfUsername.getText();
        this.password = tfPasswort.getText();
        this.tfUsername = tfUsername;
        this.tfPasswort = tfPasswort;
    }

    public void login() {
        if (checkIfUserInDB()) {
            if (checkUserPWD()) {

            } else {
                sendErrorMessage(tfUsername, "Benutzer oder Passwort falsch!");

            }
        } else {
            sendErrorMessage(tfUsername, "Benutzer nicht gefunden!");

        }

    }

    private boolean checkIfUserInDB() {
        String sql = "Select * from APP.\"User\" where benutzername =" + this.username;
        try {
            return statement.execute(sql);
        } catch (SQLException ex) {
            return false;
        }

    }

    private boolean checkUserPWD() {
        String sql = "Select * from APP.\"User\" where passwort =" + this.password;
        try {
            return statement.execute(sql);
        } catch (SQLException ex) {
            return false;
        }
    }

    private void sendErrorMessage(TextField field, String errorMessage) {
        field.setPromptText(errorMessage);
        field.setText("");
    }

}
