package searchDbs;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelBookSurfer.Autor;
import modelBookSurfer.Buch;
import modelBookSurfer.Genre;

public class MainpageSearch {

    private String searchValue;
    private static Statement statement;
    List<Genre> genres = new LinkedList<>();
    List<Buch> buecher = new LinkedList<>();
    List<Autor> autoren = new LinkedList<>();

    public MainpageSearch(String userInput, Statement statement) {
        this.setSearchValue(userInput);
        this.setStatement(statement);
    }
    
    public static MainpageSearch findAll(String userInput, Statement statement) {
        MainpageSearch ms = new MainpageSearch(userInput, statement);
        ms.searchAuthor();
        ms.searchBook();
        ms.searchGenre();
        return ms;
    }
      private void searchAuthor() {
        this.autoren = Autor.getAutorsByUserInput(searchValue, statement);
    }

    private void searchBook() {
        this.buecher = Buch.getBuecherByUserInput(searchValue, statement);
    }

    private void searchGenre() {
        this.genres = Genre.getGenresByUserInput(searchValue, statement);
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Buch> getBuecher() {
        return buecher;
    }

    public void setBuecher(List<Buch> buecher) {
        this.buecher = buecher;
    }

    public List<Autor> getAutoren() {
        return autoren;
    }

    public void setAutoren(List<Autor> autoren) {
        this.autoren = autoren;
    }
    
    

}
