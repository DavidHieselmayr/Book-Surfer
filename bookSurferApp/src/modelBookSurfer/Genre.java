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
}
