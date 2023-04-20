/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package General;

/**
 *
 * @author wissam
 */
public class LoginUser {

    String username;
    String type;

    LoginUser(String uuser, String utype) {
        username = uuser;
        type = utype;
    }

    /**
     * Checks if the login user is an admin or not
     *
     * @return true if user is admin, false otherwise
     */
    public boolean isAdmin() {
        return type.equals("0");
    }

}
