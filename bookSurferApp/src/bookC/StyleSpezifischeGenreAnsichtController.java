/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookC;

import java.io.IOException;
import java.net.URL;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modelBookSurfer.Buch;
import modelBookSurfer.Genre;
import searchDbs.MainpageSearch;

/**
 * FXML Controller class
 *
 * @author Nexo
 */
public class StyleSpezifischeGenreAnsichtController implements Initializable {

    @FXML
    private TextField tfSearch;
    @FXML
    private Button btSearch;
    private Genre genre;
   
    
    private final static String VIEWNAME = "StyleSpezifischeGenreAnsicht.fxml";
    private static final NumberFormat NUMBERFORMAT_2DEC;
    private static Statement statement;
    private static Stage stage;

    static {
        NUMBERFORMAT_2DEC = NumberFormat.getNumberInstance();
        NUMBERFORMAT_2DEC.setMaximumFractionDigits(2);
        NUMBERFORMAT_2DEC.setMinimumFractionDigits(2);
    }

    /**
     * Anzeige der View.
     * <br>
     * Diese Methode erstellt eine Instanz der View und dieses Controllers
     * (FXML-Loader) und richtet alles (also vor allem den Controller) so weit
     * ein, dass es angezeigt werden kann.
     *
     * @param stage Stage, in der die View angezeigt werden soll; null, wenn
     * neue erstellt werden soll.
     * @param statement Datenbankverbindung
     */
    public static void show(Stage stage, Statement statement, Genre genre) {
        try {
            // View & Controller erstellen
            FXMLLoader loader = new FXMLLoader(StyleSpezifischeGenreAnsichtController.class.getResource(VIEWNAME));
            Parent root = (Parent) loader.load();

            // Scene erstellen
            Scene scene = new Scene(root);

            // Stage: Entweder übergebene Stage verwenden (Primary Stage) oder neue erzeugen
            if (stage == null) {
                stage = new Stage();
            }
            stage.setScene(scene);
            stage.setTitle(genre.getName());

            // Controller ermitteln
            StyleSpezifischeGenreAnsichtController ssbController = (StyleSpezifischeGenreAnsichtController) loader.getController();

            // Datenbankzugriff merken
            StyleSpezifischeGenreAnsichtController.statement = statement;

            StyleSpezifischeGenreAnsichtController.stage = stage;
            
            ssbController.genre = genre;
            
            ssbController.displayInformation();

            // View initialisieren
            // lles anzeigen
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(StyleBookController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Something wrong with " + VIEWNAME + "!");
            ex.printStackTrace(System.err);
            System.exit(1);
        } catch (Exception ex) {
            Logger.getLogger(StyleBookController.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace(System.err);
            System.exit(2);
        }
    }
    @FXML
    private Text tTitel;
    @FXML
    private Text tBeschreibung;
    @FXML
    private GridPane gPGenre;
    
    public void displayInformation(){
        tTitel.setText(this.genre.getName());
        this.tBeschreibung.setText(genre.getBeschreibung());
        List<Buch> buecher = Buch.getBuecherOfGenre(statement, genre.getGenreid());
        int index = 0;
        for(Buch buch:buecher){
            ImageView iv = new ImageView();
            
            Image i = new Image("file:../../data/bilder/buch/" + buch.getBuchid()+".jpg");
            iv.setFitHeight(200);
            iv.setFitWidth(150);
            iv.setImage(i);
            iv.addEventHandler(MouseEvent.MOUSE_CLICKED, e->StyleSpezifischeBuchansichtController.show(stage, statement, buch));
            gPGenre.add(iv, index%2, (int)Math.floor(index/2));
            //gPGenre.add(iv, 0, index);
            index++;
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onActionBtSearch(ActionEvent event) {
        MainpageSearch ms = MainpageSearch.findAll(tfSearch.getText(), statement);
        ShopControllerController.show(stage, statement, tfSearch.getText(), ms);
    }

    @FXML
    private void onMouseClickedLogo(MouseEvent event) {
        StyleMainPageController.show(stage, statement);
    }
}
