package loginAndRegisterStuff;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextField;
import java.util.regex.*;

public class UserLoginRegister {

    private String username; // Zu zahlender Preis
    private String password; // nächste freie id
    private Statement statement;
    private double guthaben;

    public UserLoginRegister(Statement statement, String username, String password) throws InputException {
        this.setStatement(statement);
        this.setPassword(password);
        this.setUsername(username);
    }

    public static void register(Statement statement, String username, String password1, String password2) throws InputException {
        if (password1.equals(password2)) {
            UserLoginRegister register = new UserLoginRegister(statement, username, password1);
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
        UserLoginRegister login = new UserLoginRegister(statement, username, password);
        if (login.checkUsername()) {
            // change fxml document
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
        if (username != null && !username.trim().equals("") && username.length() > 1 && checkRegularExpressionUsername()) {
            this.username = username;
        } else {
            throw new InputException("Der Username muss eingegeben werden.");
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws InputException {
        if (password != null && !password.trim().equals("") && password.length() > 9 && checkRegularExpressionPwd()) {
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

    private boolean checkRegularExpressionPwd() {
        Pattern pattern = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-])");
        /* 
            At least one upper case English letter, (?=.*?[A-Z])
            At least one lower case English letter, (?=.*?[a-z])
            At least one digit, (?=.*?[0-9])
            At least one special character, (?=.*?[#?!@$%^&*-])
            Minimum eight in length .{8,} (with the anchors)
         */
        Matcher mat = pattern.matcher(this.password);
        return mat.matches();

    }

    private boolean checkRegularExpressionUsername() {
        Pattern pattern = Pattern.compile("[^A-Za-z0-9]");
        // check if String include special letters 
        Matcher mat = pattern.matcher(this.username);
        return !mat.matches();
    }

    public void setGuthaben(double abzug) throws InputException {
        this.guthaben = guthaben - abzug;
        String sql = "Update into APP.\"USER\" set geld = " + this.guthaben + " where username = " + this.username;

        try {
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println("Exception Message: " + ex.getMessage());
            throw new InputException("Guthaben konnte nicht aktualisiert werden!");
        }
    }

    public double getGuthaben() throws InputException {
        String sql = "Select geld from APP.\"USER\" where username = " + this.username;
        try {
            ResultSet rs = statement.executeQuery(sql);
            return rs.getDouble("geld");
        } catch (SQLException ex) {
            throw new InputException("Guthabenabfrage hat nicht funktioniert!");

        }
    }
}
