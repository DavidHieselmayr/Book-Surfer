/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelBookSurfer;

import ClientThread.ClientThread;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
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

    public Buch(Statement statement, int buchid, String titel, String klappentext, Date releasedatum, int seitenanzahl, int kapitelanzahl, int preis) {
        this.setBuchid(buchid);
        this.setKapitelanzahl(kapitelanzahl);
        this.setKlappentext(klappentext);
        this.setReleasedatum(releasedatum);
        this.setSeitenanzahl(seitenanzahl);
        this.setStatement(statement);
        this.setTitel(titel);
        this.setPreis(preis);
    }

    public void storageBuchLocal() {
        List<Kapitel> kapitel = Kapitel.getKapitelToBuch(buchid, statement);
        Socket echoServerSocket = null;
        String hostName = "127.0.0.1";
        int portNumber = 6014;

       
            
            for (Kapitel k : kapitel) {
                Thread t = new Thread(new ClientThread(k));
                t.start();
            }

    }

    static public List<Buch> getBuecherOfAutor(Statement statement, int autorid) {
        List<Buch> buecher = new LinkedList<>();
        List<Integer> buecherid = new LinkedList<>();
        String sql = "Select buch_buchid from APP.relation_3 where autor_autorid = " + autorid;

        try {
            ResultSet rSet = statement.executeQuery(sql);

            while (rSet.next()) {
                buecherid.add(rSet.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Autor.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int buchid : buecherid) {
            buecher.add(Buch.getBuchByID(statement, buchid));
        }
        return buecher;
    }

    static public List<Buch> getBuecherOfGenre(Statement statement, int genreid) {
        List<Buch> buecher = new LinkedList<>();
        List<Integer> buecherids = new LinkedList<>();
        String sql = "Select buch_buchid from APP.relation_2 where genre_genreid = " + genreid;

        try {
            ResultSet rSet = statement.executeQuery(sql);

            while (rSet.next()) {
                buecherids.add(rSet.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Autor.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int buchid : buecherids) {
            buecher.add(Buch.getBuchByID(statement, buchid));
        }
        return buecher;
    }

    static public Buch getBuchByID(Statement statement, int buchid) {
        String sql = "Select * from APP.buch where buchid = " + buchid;
        Buch buch;
        try {
            ResultSet rSet = statement.executeQuery(sql);

            while (rSet.next()) {
                buch = new Buch(statement, rSet.getInt("buchid"), rSet.getString("titel"), rSet.getString("klappentext"), rSet.getDate("releasedatum"), rSet.getInt("seitenanzahl"), rSet.getInt("kapitelanzahl"), rSet.getInt("preis"));
                return buch;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Autor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    static public List<Buch> getBuecherByUserInput(String userInput, Statement statement) {
        List<Buch> buecher = new LinkedList<>();
        String sql = "Select * from APP.buch where lower(titel) like '%" + userInput.toLowerCase() + "%'";

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
