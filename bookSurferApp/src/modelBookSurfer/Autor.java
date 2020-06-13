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
public class Autor {
    /*
    vorname      VARCHAR(32),
    nachname     VARCHAR(32),
    gebdatum     DATE,
    authorid     decimal(6) NOT NULL,
    biographie   VARCHAR(4000)
    */
    
    private String vorname;
    private String nachname;
    private Date gebdatum;
    private int autorid;
    private String biographie;
    private Statement statement;
    
    public Autor(Statement statement,int autorid, String vorname, String nachname, Date gebdatum,  String biographie){
        this.setStatement(statement);
        this.setVorname(vorname);
        this.setNachname(nachname);
        this.setGebdatum(gebdatum);
        this.setAutorid(autorid);
        this.setBiographie(biographie);
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public Date getGebdatum() {
        return gebdatum;
    }

    public void setGebdatum(Date gebdatum) {
        this.gebdatum = gebdatum;
    }

    public int getAutorid() {
        return autorid;
    }

    public void setAutorid(int autorid) {
        this.autorid = autorid;
    }

    public String getBiographie() {
        return biographie;
    }

    public void setBiographie(String biographie) {
        this.biographie = biographie;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }
}
