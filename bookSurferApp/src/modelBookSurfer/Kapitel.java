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
public class Kapitel {
    /*
     ueberschrift   varchar(100),
    nummer        decimal(2) NOT NULL,
    kapitelid     decimal(6) NOT NULL,
    buch_buchid   decimal(6) NOT NULL,
    textdateiurl   varchar(100)
    */
    
    private String ueberschrift;
    private int nummer;
    private int kapitelid;
    private int buchid;
    private String textdateiurl;
    private Statement statement;

    public Kapitel(String ueberschrift, int nummer, int kapitelid, int buchid, String textdateiurl, Statement statement) {
        this.setBuchid(buchid);
        this.setKapitelid(kapitelid);
        this.setNummer(nummer);
        this.setStatement(statement);
        this.setTextdateiurl(textdateiurl);
        this.setUeberschrift(ueberschrift);
    }
    

    public String getUeberschrift() {
        return ueberschrift;
    }

    public void setUeberschrift(String ueberschrift) {
        this.ueberschrift = ueberschrift;
    }

    public int getNummer() {
        return nummer;
    }

    public void setNummer(int nummer) {
        this.nummer = nummer;
    }

    public int getKapitelid() {
        return kapitelid;
    }

    public void setKapitelid(int kapitelid) {
        this.kapitelid = kapitelid;
    }

    public int getBuchid() {
        return buchid;
    }

    public void setBuchid(int buchid) {
        this.buchid = buchid;
    }

    public String getTextdateiurl() {
        return textdateiurl;
    }

    public void setTextdateiurl(String textdateiurl) {
        this.textdateiurl = textdateiurl;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }
    
    
    
}
