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
    private Statement statement;
    private String sql;
    private ResultSet rSet;

    public mainpageSearch(String userInput, Statement statement) {
        this.setSearchValue(userInput);
        this.setStatement(statement);
    }

    private String searchMain() {

        return "test";
    }

    private void searchAuthor() {
        this.sql = "Select * from APP.\"user\" where vorname like %this.value% or vorname like %this.value%";

        try {
            rSet = statement.executeQuery(sql);
            while (rSet.next()) {
                rSet.getString("name");
                rSet.getString("beschreibung");
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
                rSet.getString("name");
                rSet.getString("beschreibung");
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
                rSet.getString("name");
                rSet.getString("beschreibung");
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
