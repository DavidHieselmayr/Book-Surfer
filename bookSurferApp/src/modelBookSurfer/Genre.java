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
public class Genre {
    /*
    name           varchar(32),
    beschreibung   varchar(1024),
    genreid        decimal(6) NOT NULL
    */
    
    private String name;
    private String beschreibung;
    private int genreid;
    private Statement statement;

    public Genre(String name, String beschreibung, int genreid, Statement statement) {
        this.setBeschreibung(beschreibung);
        this.setGenreid(genreid);
        this.setName(name);
        this.setStatement(statement);
    }
    
    static public List<Genre> getGenresByUserInput(String userInput, Statement statement){
        List<Genre> genres = new LinkedList<>();
        String sql = "Select * from APP.genre where lower(name) like '%"+userInput.toLowerCase()+"%'";

        try {
            ResultSet rSet = statement.executeQuery(sql);

            while (rSet.next()) {
                genres.add(new Genre(rSet.getString("name"),rSet.getString("beschreibung"),rSet.getInt("genreid"),  statement));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Autor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return genres;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public int getGenreid() {
        return genreid;
    }

    public void setGenreid(int genreid) {
        this.genreid = genreid;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }
    
    
}
