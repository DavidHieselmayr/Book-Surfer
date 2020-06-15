/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookC;

import static bookC.StyleUserInterfaceController.stage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.URL;
import java.sql.Statement;
import java.util.Base64;
import java.util.LinkedList;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import loginAndRegisterStuff.CurrentUser;
import modelBookSurfer.Buch;
import modelBookSurfer.Kapitel;

/**
 * FXML Controller class
 *
 * @author Nexo
 */
public class StyleReadingVisionController implements Initializable {

    private static Stage stage;
    private static Statement statement;
    private static final String VIEWNAME = "styleReadingVision.fxml";
    private Integer kapitel = 1;
    private static Buch buch;

    @FXML
    private TextField tfSeite;
    @FXML
    private Text tfReadingVision;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setKapitel(Integer kapitel) {
        if ((this.kapitel = +kapitel) > 1 && buch.getKapitelanzahl() <= (this.kapitel = +kapitel)) {
            this.kapitel = +kapitel;
        } else {
            return;
        }

    }

    @FXML
    private void actionFirstPage(ActionEvent event) {
        setKapitel(1);
        insertIntoTextBook();
    }

    @FXML
    private void actionBackPage(ActionEvent event) {
        setKapitel(this.kapitel-1);
        insertIntoTextBook();
    }

    @FXML
    private void actionForward(ActionEvent event) {
        setKapitel(this.kapitel+1);
        insertIntoTextBook();
    }

    @FXML
    private void actionLastPage(ActionEvent event) {
        setKapitel(buch.getKapitelanzahl());
        insertIntoTextBook();
    }

    @FXML
    private void actionMakeFullScreen(ActionEvent event) {
        if (stage.isFullScreen()) {
            stage.setFullScreen(false);
        } else {
            stage.setFullScreen(true);
        }

    }

    @FXML
    private void actionExit(ActionEvent event) {
        StyleMainPageController.show(stage, statement);
    }

    public static void show(Stage stage, Statement statement, Buch buch) {
        try {
            // View & Controller erstellen
            FXMLLoader loader = new FXMLLoader(StyleReadingVisionController.class.getResource(VIEWNAME));
            Parent root = (Parent) loader.load();

            // Scene erstellen
            Scene scene = new Scene(root);

            // Stage: Entweder übergebene Stage verwenden (Primary Stage) oder neue erzeugen
            if (stage == null) {
                stage = new Stage();
            }
            stage.setScene(scene);
            stage.setTitle(CurrentUser.getCurrentUser().getUsername());

            // Controller ermitteln
            StyleReadingVisionController ssbController = (StyleReadingVisionController) loader.getController();

            // Datenbankzugriff merken
            StyleReadingVisionController.statement = statement;

            StyleReadingVisionController.stage = stage;

            ssbController.setBuch(buch);
            ssbController.setKapitel(1);
            ssbController.insertIntoTextBook();

            // View initialisieren
            // lles anzeigen
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(StyleReadingVisionController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Something wrong with " + VIEWNAME + "!");
            ex.printStackTrace(System.err);
            System.exit(1);
        } catch (Exception ex) {
            Logger.getLogger(StyleReadingVisionController.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace(System.err);
            System.exit(2);
        }
    }

    public void insertIntoTextBook() {
        InputStream br = null;
        List<Kapitel> kapitel = new LinkedList<>();

        kapitel = Kapitel.getKapitelToBuch(buch.getBuchid(), statement);

        Kapitel richtigesKapitel = null;
        for (Kapitel k : kapitel) {
            if (k.getNummer() == this.kapitel) {
                richtigesKapitel = k;
            }
        }

        String url = richtigesKapitel.getTextdateiurl();

        try {
            File file = new File("data/buecher/"+url); //Pfad noch ändern
            br = new FileInputStream(file);
            String string = "";
            int b;
            while ((b = br.read()) != -1) {
                string += toASCII(b);
            }
            System.out.println(string);
            tfReadingVision.setText(string);
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
        } catch (IOException ex) {
            System.out.println("File reading error!");
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(StyleReadingVisionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private static String toASCII(int value){
        int length = 4;
        StringBuilder builder = new StringBuilder(length);
        for(int i = length - 1; i >= 0; i--){
            builder.append((char)((value >> (8*i)) & 0xFF));
        }
        return builder.toString();
    }

    public void setBuch(Buch buch) {
        this.buch = buch;
    }

}
