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
public class Buch {
    /*
     titel           varchar(64),
    buchid          decimal(6) NOT NULL,
    klappentext     varchar(1500),
    releasedatum    DATE,
    seitenanzahl    decimal(4),
    kapitelanzahl   decimal(2)
    */
    
    private String titel;
    private int buchid;
    private String klappentext;
    private Date relasedatum;
    private int seitenanzahl;
    private int kapitelanzahl;
    private Statement statement;
    
    public Buch(Statement statement, int buchid,String titel,  String klappentext, Date releasedatum, int seitenanzahl, int kapitelanzahl){
        
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public int getBuchid() {
        return buchid;
    }

    public void setBuchid(int buchid) {
        this.buchid = buchid;
    }

    public String getKlappentext() {
        return klappentext;
    }

    public void setKlappentext(String klappentext) {
        this.klappentext = klappentext;
    }

    public Date getRelasedatum() {
        return relasedatum;
    }

    public void setRelasedatum(Date relasedatum) {
        this.relasedatum = relasedatum;
    }

    public int getSeitenanzahl() {
        return seitenanzahl;
    }

    public void setSeitenanzahl(int seitenanzahl) {
        this.seitenanzahl = seitenanzahl;
    }

    public int getKapitelanzahl() {
        return kapitelanzahl;
    }

    public void setKapitelanzahl(int kapitelanzahl) {
        this.kapitelanzahl = kapitelanzahl;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }
    
    
}
