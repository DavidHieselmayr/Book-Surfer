package loginAndRegisterStuff;

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
        if(checkIfUserInDB(Statement statement)){
    
        }
        
        return false;
    }
    private boolean checkIfUserInDB(Statement statement){
        String sql="Select * from User where benutzername ="+this.Username;
        
    }
    

}
