/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookC;

import static bookC.StyleUserInterfaceController.stage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.URL;
import java.sql.Statement;
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
import javafx.stage.Stage;
import loginAndRegisterStuff.CurrentUser;

/**
 * FXML Controller class
 *
 * @author Nexo
 */
public class StyleReadingVisionController implements Initializable {

    private static Stage stage;
    private static Statement statement;
    private static final String VIEWNAME = "StyleUserInterface.fxml";
    private Integer kapitel=1;

    @FXML
    private TextField tfSeite;
    @FXML
    private TextArea tfReadingVision;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setKapitel(Integer kapitel) {
        if((this.kapitel=+kapitel) > 1){
            this.kapitel =+kapitel;
        }else{
            return;
        }
        
    }

    @FXML
    private void actionFirstPage(ActionEvent event) {
        setKapitel(1);
        insertIntoTextBook(this.kapitel);
    }

    @FXML
    private void actionBackPage(ActionEvent event) {
        setKapitel(-1);
        insertIntoTextBook(this.kapitel);
    }

    @FXML
    private void actionForward(ActionEvent event) {
        setKapitel(1);
        insertIntoTextBook(this.kapitel);
    }

    @FXML
    private void actionLastPage(ActionEvent event) {
        
    }

    @FXML
    private void actionMakeFullScreen(ActionEvent event) {
        stage.setFullScreen(true);
    }

    @FXML
    private void actionExit(ActionEvent event) {
         StyleMainPageController.show(stage, statement);
    }

    public static void show(Stage stage, Statement statement) {
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

            ssbController.insertIntoTextBook(1);

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

    public void insertIntoTextBook(int kapitel) {
        BufferedReader br = null;
        try {
            File file = new File("C:\\Users\\Desktop\\test"+kapitel+".txt"); //Pfad noch ändern
            br = new BufferedReader(new FileReader(file));
            String string;
            while ((string = br.readLine()) != null) {
                tfReadingVision.appendText(string);
            }
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

}
