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
import java.sql.Statement;

public class UserLogin {

    public UserLogin(){
    
    }
    private String Username; // Zu zahlender Preis
    private String Password; // nächste freie id
    private Statement statement;

    public UserLogin(Statement statement, String Username, String Password) {
        this.statement=statement;
        this.Username = Username;
        this.Password = Password;
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
        
        return true;
    }
    

}

