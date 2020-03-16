/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookC;

import java.io.IOException;
import java.text.NumberFormat;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nexo
 */
public class StyleBookController {
  // Verbindung zur Datenbank
  private Statement statement;

  // Helper
  private final static String VIEWNAME = "styleBook.fxml";
  private static final NumberFormat NUMBERFORMAT_2DEC;


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
   * @param stage     Stage, in der die View angezeigt werden soll; null, wenn
   *                  neue erstellt werden soll.
   * @param statement Datenbankverbindung
   */
  public static void show(Stage stage, Statement statement) {
    try {
      // View & Controller erstellen
      FXMLLoader loader = new FXMLLoader(StyleBookController.class.getResource(VIEWNAME));
      Parent root = (Parent) loader.load();

      // Scene erstellen
      Scene scene = new Scene(root);

      // Stage: Entweder übergebene Stage verwenden (Primary Stage) oder neue erzeugen
      if (stage == null) {
        stage = new Stage();
      }
      stage.setScene(scene);
      stage.setTitle("BookSurfer");

      // Controller ermitteln
      StyleBookController bookSurferC = (StyleBookController) loader.getController();

      // Datenbankzugriff merken
      bookSurferC.statement = statement;

      // View initialisieren

      // lles anzeigen
      stage.show();
    }
    catch (IOException ex) {
      Logger.getLogger(StyleBookController.class.getName()).log(Level.SEVERE, null, ex);
      System.err.println("Something wrong with " + VIEWNAME + "!");
      ex.printStackTrace(System.err);
      System.exit(1);
    }
    catch (Exception ex) {
      Logger.getLogger(StyleBookController.class.getName()).log(Level.SEVERE, null, ex);
      ex.printStackTrace(System.err);
      System.exit(2);
    }
  }
}
