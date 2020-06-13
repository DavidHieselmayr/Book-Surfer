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
import java.util.LinkedList;
import java.util.List;
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
    private Date releasedatum;
    private int seitenanzahl;
    private int kapitelanzahl;
    private int preis;
    private Statement statement;
    
    public Buch(Statement statement, int buchid,String titel,  String klappentext, Date releasedatum, int seitenanzahl, int kapitelanzahl, int preis){
        this.setBuchid(buchid);
        this.setKapitelanzahl(kapitelanzahl);
        this.setKlappentext(klappentext);
        this.setReleasedatum(releasedatum);
        this.setSeitenanzahl(seitenanzahl);
        this.setStatement(statement);
        this.setTitel(titel);
        this.setPreis(preis);
    }
    
    static public List<Buch> getBuecherByUserInput(String userInput, Statement statement){
        List<Buch> buecher = new LinkedList<>();
        String sql = "Select * from APP.buch where titel like '%"+userInput+"%'";

        try {
            ResultSet rSet = statement.executeQuery(sql);

            while (rSet.next()) {
                buecher.add(new Buch(statement, rSet.getInt("buchid"), rSet.getString("titel"), rSet.getString("klappentext"), rSet.getDate("releasedatum"), rSet.getInt("seitenanzahl"), rSet.getInt("kapitelanzahl"), rSet.getInt("preis")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Autor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return buecher;
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

    public Date getReleasedatum() {
        return releasedatum;
    }

    public void setReleasedatum(Date relasedatum) {
        this.releasedatum = relasedatum;
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

    public int getPreis() {
        return preis;
    }

    public void setPreis(int preis) {
        this.preis = preis;
    }
    
    
}
