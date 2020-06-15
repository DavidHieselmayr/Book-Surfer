/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginAndRegisterStuff;

/**
 *
 * @author fabia
 */
public class InputException extends Exception {

    private String type;

    public InputException(String msg) {
        super(msg);
    }
}
