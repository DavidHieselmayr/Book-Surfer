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
public class CurrentUser {
    
    private static UserLoginRegister currentUser;

    public static UserLoginRegister getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(UserLoginRegister currentUser) {
        CurrentUser.currentUser = currentUser;
    }
}
