/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginAndRegisterStuff;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nexo
 */
public class UserRegister {
    
    private String Username; // Zu zahlender Preis
    private String Password; // nächste freie id
    private Statement statement;

    public UserRegister(Statement statement, String Username, String Password) {
        this.statement=statement;
        this.Username = Username;
        this.Password = Password;
    }
    
    public static String register(Statement statement, String username, String password1, String password2){
        if(password1.equals(password2)){
            UserLogin register = new UserLogin(statement, username, password1);
            
            if(register.checkIfUserInDB())
                return "User exisitert bereits.";
            else {
                
            }
            
            return null;
        } else {
            return "Passwortbestätigung ungleich Passwort!";
        }
    }
    
    public void registerUser(){
        String sql = "Insert into APP.\"User\"(\"uid\",benutzername, passwort, email) values (seq_user.nextval, "+this.Username+", "+this.Password+", null)";
        
        try {
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UserRegister.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void login(Statement statement) {
            if(checkUsername(statement)){
            
            }
    }
    
    
    private  boolean checkUsername(Statement statement){
        if(checkIfUserInDB(statement)){
    
        }
        
        return false;
    }
    private boolean checkIfUserInDB(Statement statement){
        String sql="Select * from User where benutzername ="+this.Username;
        try {
            statement.execute(sql);
        } catch (SQLException ex) {
           
        }
        
        return true;
    }
    
}
