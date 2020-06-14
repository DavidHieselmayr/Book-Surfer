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
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextField;
import java.util.regex.*;
import loginAndRegisterStuff.CurrentUser;
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
    private int buchid;
    private Timestamp erstelldatum;

    public Kommentar(Statement statement, String text, String ueberschrift, int sterne, int kommentarid, int userid, int buchid, Timestamp erstelldatum) {
        this.setStatement(statement);
        this.setKommentarid(kommentarid);
        this.setSterne(sterne);
        this.setText(text);
        this.setUeberschrift(ueberschrift);
        this.setUserid(userid);
        this.setBuchid(buchid);
    }
    
    public static void addKommentarToDB(Statement statement, String kommentar, int buchid){
        Timestamp t = new Timestamp(System.currentTimeMillis());
        String sql = "INSERT INTO APP.kommentar(text, ueberschrift, sterne, user_uid, buchid, erstelldatum, kommentarid) VALUES ('"+kommentar+"', null, 0, "+CurrentUser.getCurrentUser().getUserid()+", "+buchid+", '"+t.toString()+"', next value for seq_kommentar)";

        try {
            statement.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Autor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static List<Kommentar> getKommentareVonBuch(int buchid, Statement statement){
        List<Kommentar> kommentare = new LinkedList<>();
        String sql = "Select * from APP.kommentar where buchid = " + buchid + " ORDER BY erstelldatum DESC";

        try {
            ResultSet rSet = statement.executeQuery(sql);

            while (rSet.next()) {
                kommentare.add(new Kommentar(statement, rSet.getString("text"), rSet.getString("ueberschrift"), rSet.getInt("sterne"), rSet.getInt("kommentarid"), rSet.getInt("user_uid"), rSet.getInt("buchid"),rSet.getTimestamp("erstelldatum")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Autor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kommentare;
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

    public int getBuchid() {
        return buchid;
    }

    public void setBuchid(int buchid) {
        this.buchid = buchid;
    }

    public Timestamp getErstelldatum() {
        return erstelldatum;
    }

    public void setErstelldatum(Timestamp erstelldatum) {
        this.erstelldatum = erstelldatum;
    }
    
    
}
