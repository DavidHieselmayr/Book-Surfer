package searchDbs;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class mainpageSearch {

    private String searchValue;
    private static Statement statement;
    private String sql;
    private ResultSet rSet;
    List<String> eintraegeGenre = new LinkedList<>();
    List<String> eintraegeBuecher = new LinkedList<>();
    List<String> eintraegAuthor = new LinkedList<>();

    public mainpageSearch(String userInput, Statement statement) {
        this.setSearchValue(userInput);
        this.setStatement(statement);
    }

    public List<String> findAll() {
        searchAuthor();
        searchBook();
        searchGenre();

        return eintraege;
    }

    private void searchAuthor() {
        sql = "Select * from APP.\"user\" where vorname like %this.value% or vorname like %this.value%";

        try {
            rSet = statement.executeQuery(sql);

            while (rSet.next()) {
                eintraegAuthor.add(rSet.getString("vorname"));
                eintraegAuthor.add(rSet.getString("nachname"));
                eintraegAuthor.add(rSet.getString("gebdatum"));
                eintraegAuthor.add(rSet.getString("biographie"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(mainpageSearch.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void searchBook() {
        sql = "Select * from APP.\"buch\" where titel like %this.value%";
        try {
            rSet = statement.executeQuery(sql);
            while (rSet.next()) {
                eintraegeBuecher.add(rSet.getString("titel"));
                eintraegeBuecher.add(rSet.getString("klappentext"));
                eintraegeBuecher.add(rSet.getString("releasedatum"));
                eintraegeBuecher.add(rSet.getString("seitenanzahl"));
                eintraegeBuecher.add(rSet.getString("kapitelanzahl"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(mainpageSearch.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void searchGenre() {
        sql = "Select * from APP.\"genre\" where name like '%this.searchValue%'";

        try {
            rSet = statement.executeQuery(sql);
            while (rSet.next()) {
                eintraegeGenre.add(rSet.getString("name"));
                eintraegeGenre.add(rSet.getString("beschreibung"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(mainpageSearch.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

}
