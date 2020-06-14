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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import loginAndRegisterStuff.CurrentUser;
import loginAndRegisterStuff.User;
import modelBookSurfer.Buch;
import modelBookSurfer.Kommentar;
import searchDbs.MainpageSearch;

/**
 * FXML Controller class
 *
 * @author Nexo
 */
public class StyleSpezifischeBuchansichtController implements Initializable {

    @FXML
    private TextField tfSearch;
    @FXML
    private Button btSearch;
    private Buch buch;

    private final static String VIEWNAME = "StyleSpezifischeBuchansicht.fxml";
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
    public static void show(Stage stage, Statement statement, Buch buch) {
        try {
            // View & Controller erstellen
            FXMLLoader loader = new FXMLLoader(StyleSpezifischeBuchansichtController.class.getResource(VIEWNAME));
            Parent root = (Parent) loader.load();

            // Scene erstellen
            Scene scene = new Scene(root);

            // Stage: Entweder übergebene Stage verwenden (Primary Stage) oder neue erzeugen
            if (stage == null) {
                stage = new Stage();
            }
            stage.setScene(scene);
            stage.setTitle(buch.getTitel());

            // Controller ermitteln
            StyleSpezifischeBuchansichtController ssbController = (StyleSpezifischeBuchansichtController) loader.getController();

            // Datenbankzugriff merken
            StyleSpezifischeBuchansichtController.statement = statement;

            StyleSpezifischeBuchansichtController.stage = stage;

            ssbController.buch = buch;

            ssbController.displayInformation();
            
            ssbController.displayKommentare();

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
    private Text lbTitel;
    @FXML
    private Text lbReleaseDatum;
    @FXML
    private Text lbKapitelanzahl;
    @FXML
    private Text lbPreis;
    @FXML
    private TextArea tfKommentar;
    @FXML
    private Button btKommentar;
    @FXML
    private ImageView ivCover;
    @FXML
    private GridPane gPKommentare;
    @FXML
    private Button btKaufen;

    public void displayKommentare() {
        List<Kommentar> kommentare = Kommentar.getKommentareVonBuch(this.buch.getBuchid(), statement);
        if (kommentare.size() > 0) {
            gPKommentare.getChildren().remove(0, gPKommentare.getChildren().size());
            int index = 0;
            for (Kommentar kommentar : kommentare) {
                GridPane gP = new GridPane();
                User user = User.getUserByUserID(statement, kommentar.getUserid());
                Label lbUser = new Label();
                lbUser.setFont(Font.font("verdana", FontWeight.BOLD, 24));
                lbUser.setText("  "+user.getUsername()+": ");
                Text tKommentar = new Text();
                tKommentar.setFont(Font.font("verdana", FontWeight.MEDIUM, 20));
                tKommentar.setText(kommentar.getText());
                //tKommentar.autosize();
                gP.add(lbUser, 0, 0);
                gP.add(tKommentar, 1, 0);
                gP.setPrefWidth(this.gPKommentare.getWidth());
                gP.getChildren().forEach(cnsmr->{
                    cnsmr.autosize();
                });
                gP.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                gP.autosize();
                this.gPKommentare.add(gP, 0, index);
                //this.gPKommentare.getColumnConstraints().add(alwaysGrow);
                this.gPKommentare.autosize();
                this.gPKommentare.getChildren().forEach(cnsmr->{
                    cnsmr.autosize();
                });
                gP.getChildren().forEach(cnsmr->{
                    cnsmr.autosize();
                });
                index++;
            }
        }
    }

    public void displayInformation() {
        if(CurrentUser.getCurrentUser().isAlreadyBuyed(buch.getBuchid())){
            btKaufen.setText("Lesen");
        }
        lbTitel.setText(buch.getTitel());
        lbReleaseDatum.setText(buch.getReleasedatum().toString());
        lbKapitelanzahl.setText(String.valueOf(buch.getKapitelanzahl()));
        lbPreis.setText(String.valueOf(buch.getPreis()));
        System.out.println(buch.getBuchid());
        Image i = new Image("file:../../data/bilder/buch/" + buch.getBuchid() + ".jpg");
        //Image i = new Image("file:../../data/bilder/buch/2.jpg");
        ivCover.setImage(i);
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
    private void onActionBtKommentar(ActionEvent event) {
        Kommentar.addKommentarToDB(statement, this.tfKommentar.getText(), this.buch.getBuchid());
        tfKommentar.setText("");
        this.displayKommentare();
    }

    @FXML
    private void onMouseClickedLogo(MouseEvent event) {
        StyleMainPageController.show(stage, statement);
    }

    @FXML
    private void onActionBtKaufen(ActionEvent event) {
        CurrentUser.getCurrentUser().buyBook(buch.getBuchid(), buch.getPreis());
        btKaufen.setText("Lesen");
        btKaufen.setDisable(false);
        buch.storageBuchLocal();
        btKaufen.setDisable(true);
    }

}
