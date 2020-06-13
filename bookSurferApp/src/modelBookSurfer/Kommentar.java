/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelBookSurfer;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextField;
import java.util.regex.*;
/**
 *
 * @author fabia
 */
public class Kommentar {
    /*
     text          varchar(1000),
    überschrift   varchar(32),
    sterne        decimal(2),
    kommentarid   decimal(6) NOT NULL,
    user_uid      decimal(6) NOT NULL
    */
    
    private Statement statement;
    private String text;
    private String ueberschrift;
    private int sterne;
    private int kommentarid;
    private int userid;

    public Kommentar(Statement statement, String text, String ueberschrift, int sterne, int kommentarid, int userid) {
        this.setStatement(statement);
        this.setKommentarid(kommentarid);
        this.setSterne(sterne);
        this.setText(text);
        this.setUeberschrift(ueberschrift);
        this.setUserid(userid);
    }
    
    

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUeberschrift() {
        return ueberschrift;
    }

    public void setUeberschrift(String ueberschrift) {
        this.ueberschrift = ueberschrift;
    }

    public int getSterne() {
        return sterne;
    }

    public void setSterne(int sterne) {
        this.sterne = sterne;
    }

    public int getKommentarid() {
        return kommentarid;
    }

    public void setKommentarid(int kommentarid) {
        this.kommentarid = kommentarid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
    
    
}
