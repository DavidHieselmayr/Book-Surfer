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

public class UserLogin {

    public UserLogin() {
    }
    private String Username; 
    private String Password; 
    private Statement statement;

    public UserLogin(Statement statement, String Username, String Password) {
        this.statement = statement;
        this.Username = Username;
        this.Password = Password;
    }

    public void login() {
        if (checkUsername()) {
           // change fxml document
        }else{
        // send fehlernachricht
        }
    }

    private boolean checkUsername() {
        if (checkIfUserInDB()) {
            if(checkUserPWD()){
                return true;
            }else{
                return false;
            }
        } else {
            return false;
        }

    }

    private boolean checkIfUserInDB() {
        String sql = "Select * from APP.\"User\" where benutzername =" + this.Username;
        try {
            return statement.execute(sql);
        } catch (SQLException ex) {
            return false;
        }

    }

        private boolean checkUserPWD(){
        String sql = "Select * from APP.\"User\" where passwort ="+this.Password;
        try {
            return statement.execute(sql);
        } catch (SQLException ex) {
            return false;
        }
    }

}
