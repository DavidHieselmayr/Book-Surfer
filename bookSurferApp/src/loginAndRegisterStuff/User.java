package loginAndRegisterStuff;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextField;
import java.util.regex.*;
import modelBookSurfer.Autor;
import modelBookSurfer.Buch;
import modelBookSurfer.Kommentar;

public class User {

    private String username; // Zu zahlender Preis
    private String password; // nächste freie id
    private Statement statement;
    private double guthaben;
    private int userid;

    public User(Statement statement, String username, String password) throws InputException {
        this.setStatement(statement);
        this.setPassword(password);
        this.setUsername(username);
    }
    
    public boolean isAlreadyBuyed(int buchid){
        String sql = "SELECT buch_buchid FROM relation_1 where user_uid = " + this.userid;
        
        try {
            ResultSet rSet = statement.executeQuery(sql);
            while(rSet.next()){
                if(rSet.getInt(1) == buchid){
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public List<Buch> getGekaufteBuecher(){
        List <Buch> buecher = new LinkedList<>();
        List <Integer> buchids = new LinkedList<>(); 
        
        String sql = "SELECT buch_buchid FROM relation_1 where user_uid = " + this.userid;
        
        try {
            ResultSet rSet = statement.executeQuery(sql);
            while(rSet.next()){
                buchids.add(rSet.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for(int buchid: buchids){
            buecher.add(Buch.getBuchByID(statement, buchid));
        }
        return buecher;
    }
    
    public void buyBook(int buchid, double kaufbetrag){
        try {
            /*
            user_uid      decimal(6) NOT NULL,
            buch_buchid   decimal(6) NOT NULL,
            kaufdatum     timestamp
            */
            CurrentUser.getCurrentUser().setGuthaben(kaufbetrag);
        } catch (InputException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Timestamp t = new Timestamp(System.currentTimeMillis());
        
        String sql = "INSERT INTO relation_1(user_uid, buch_buchid, kaufdatum) VALUES ("+CurrentUser.getCurrentUser().getUserid()+", "+buchid+", '"+t.toString()+"')";

        try {
            statement.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Autor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static User getUserByUserID(Statement statement, int userid){
        User user;
        String sql = "Select * from APP.\"User\" where \"UID\" = "+userid;

        try {
            ResultSet rSet = statement.executeQuery(sql);

            while (rSet.next()) {
                user = new User(statement, rSet.getString("benutzername"), rSet.getString("passwort"));
                return user;  
            }
        } catch (SQLException ex) {
            Logger.getLogger(Autor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InputException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void register(Statement statement, String username, String password1, String password2) throws InputException {
        if (password1.equals(password2)) {
            User register = new User(statement, username, password1);
            if (register.checkIfUserInDB()) {
                throw new InputException("User exisitert bereits.");
            } else {
                if (!register.registerUser()) {
                    throw new InputException("Verbindung zur Datenbank konnte nicht hergestellt werden.");
                }
            }
        } else {
            throw new InputException("Passwortbestätigung ungleich Passwort!");
        }
    }

    public boolean registerUser() {
        String sql = "Insert into APP.\"User\"(\"UID\",benutzername, passwort, email) values (next value for seq_user, '" + this.username + "', '" + this.password + "', null)";

        try {
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println("Exception Message: " + ex.getMessage());
            return false;
        }

        System.out.println("User wurde zu Datenbank hinzugefügt");
        return true;
    }

    public static void login(Statement statement, String username, String password) throws InputException {
        User login = new User(statement, username, password);
        if (login.checkUsername()) {
            // change fxml document
            login.setUserid();
            CurrentUser.setCurrentUser(login);
            // set guthaben 
            System.out.println("Login succesful");
        }
    }

    public boolean checkUsername() throws InputException {
        if (checkIfUserInDB()) {
            if (checkUserPWD()) {
                return true;
            } else {
                throw new InputException("Benutzer oder Passwort falsch!");
            }
        } else {
            throw new InputException("Benutzer nicht gefunden!");
        }
    }

    public boolean checkIfUserInDB() {
        String sql = "Select * from APP.\"User\" where benutzername = '" + this.username + "'";
        try {
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("Exception: " + ex.getMessage());
            return false;
        }

    }

    public boolean checkUserPWD() {
        String sql = "Select * from APP.\"User\" where passwort = '" + this.password + "'";
        try {
            ResultSet rs = statement.executeQuery(sql);
            if (rs != null) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            return false;
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) throws InputException {
        if (username != null && !username.trim().equals("") && username.length() > 1 && checkRegularExpressionUsername(username)) {
            this.username = username;
        } else {
            throw new InputException("Der Username muss eingegeben werden.");
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws InputException {
        if (password != null && !password.trim().equals("") && password.length() > 1 && checkRegularExpressionPwd(password)) {
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

    private boolean checkRegularExpressionPwd(String password) {
//        Pattern pattern = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-])");
//        /* 
//            At least one upper case English letter, (?=.*?[A-Z])
//            At least one lower case English letter, (?=.*?[a-z])
//            At least one digit, (?=.*?[0-9])
//            At least one special character, (?=.*?[#?!@$%^&*-])
//            Minimum eight in length .{8,} (with the anchors)
//         */
//        Matcher mat = pattern.matcher(password);
//        System.out.println(mat.matches());
//        return mat.matches();

    return true;

    }

    private boolean checkRegularExpressionUsername(String username) {
        Pattern pattern = Pattern.compile("[^A-Za-z0-9]");
        // check if String include special letters 
        Matcher mat = pattern.matcher(username);
        return !mat.matches();
    }

    public void setGuthaben(double abzug) throws InputException {
        this.guthaben = guthaben - abzug;
        String sql = "UPDATE APP.\"User\" SET geld = " + this.guthaben + " where benutzername = '" + this.username+"'";

        try {
            statement.execute(sql);
        } catch (SQLException ex) {
            System.out.println("Exception Message: " + ex.getMessage());
            throw new InputException("Guthaben konnte nicht aktualisiert werden!");
        }
    }

    public double getGuthaben() throws InputException {
        String sql = "Select geld from APP.\"User\" where benutzername = '" + this.username+"'";
        try {
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next())
                return rs.getDouble(1);
        } catch (SQLException ex) {
            throw new InputException("Guthabenabfrage hat nicht funktioniert!");
        }
        return -1;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid() {
        String sql = "Select \"UID\" from APP.\"User\" where benutzername = '" + this.username+"'";

        try {
            ResultSet rSet = statement.executeQuery(sql);

            while (rSet.next()) {
                this.userid = rSet.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Autor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
