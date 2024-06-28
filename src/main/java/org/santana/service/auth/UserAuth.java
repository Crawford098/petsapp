package org.santana.service.auth;

import java.util.ArrayList;
import java.util.HashMap;

import org.santana.service.users.Users;

/**
 * User authentication
 */
public class UserAuth implements IAuth {

    private ArrayList<Users> userList;

    public UserAuth() {
        this.userList = new ArrayList();
    }

    public String login(Users user) {

        HashMap<String,String> result = new HashMap();

        for (Users bdUser : this.userList) {

            String bdUsername = bdUser.getUsersname();
            String bdPassword = bdUser.getPassword();

            if (bdUsername.equals(user.getUsersname()) && bdPassword.equals(user.getUsersname())) {

                result.put("Resultado:", "Usuario" + user.getUsersname() + " Logueado");
                break;
            }
        }

        if(result.isEmpty())
        {
            return "Usuario no encontrado";
        }

        return result.toString();
    }

    public String register(Users user) {
        this.userList.add(user);

        return "Usuario" + user.getUsersname() + " ha sido Registrado";
    }
}
