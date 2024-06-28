package org.santana.service.auth;
import org.santana.service.users.Users;

/**User authentication */
public class UserAuth implements IAuth{

    public String login(Users user)
    {
        return "Usuario" + user.getUsersname() + " Logueado";
    }

    public String register(Users user)
    {
        return "Usuario" + user.getUsersname() + " Registrado"; 
    }
}