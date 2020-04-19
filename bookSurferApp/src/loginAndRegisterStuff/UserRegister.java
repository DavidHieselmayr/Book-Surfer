/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginAndRegisterStuff;

import java.sql.SQLException;
import java.sql.Statement;

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
        String sql = "Insert into APP.\"User\"(benutzername, passwort, email, uid) as (seq_user.nextval, "+this.Username+", "+this.Password+", null)";
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
