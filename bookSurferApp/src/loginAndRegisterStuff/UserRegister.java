/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
package loginAndRegisterStuff;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UserRegister {
    
    private String username; // Zu zahlender Preis
    private String password; // nächste freie id
    private Statement statement;

    
    

    public UserRegister(Statement statement, String username, String password) throws InputException {
        this.setStatement(statement);
        this.setPassword(password);
        this.setUsername(username);
    }
    
    public static String register(Statement statement, String username, String password1, String password2){
        if(password1.equals(password2)){
            try{
                UserLogin register = new UserLogin(statement, username, password1);
            } catch (InputException ex){
                return ex.getMessage();
            }
            
            
            if(register.checkIfUserInDB())
                return "User exisitert bereits.";
            else {
                if(!register.registerUser())
                    return  "Verbindung zur Datenbank konnte nicht hergestellt werden.";
                else
                    return null;
            }
        } else {
            return "Passwortbestätigung ungleich Passwort!";
        }
    }
    
    public boolean registerUser(){
        String sql = "Insert into APP.\"User\"(\"uid\",benutzername, passwort, email) values (next value for seq_user, "+this.Username+", "+this.Password+", null)";
        
        try {
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UserRegister.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
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
        String sql="Select * from User where benutzername ="+this.username;
        try {
            statement.execute(sql);
        } catch (SQLException ex) {
           
        }
        
        return true;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) throws InputException {
        if(username != null && !username.trim().equals("")){
            this.username = username;
        } else {
            throw new InputException("Der Username muss eingegeben werden.");
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws InputException {
        if(password != null && !password.trim().equals("")){
            this.password = password;
        } else {
            throw new InputException("Das Passwort muss eingegeben werden.");
        }
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }
    
}
*/
