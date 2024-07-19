package org.santana.service.auth;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import org.santana.model.UsersModel;
import org.santana.repository.UserRepository;
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

        HashMap<String, String> result = new HashMap();

        for (Users bdUser : this.userList) {

            String bdUsername = bdUser.getUsername();
            String bdPassword = bdUser.getPassword();

            if (bdUsername.equals(user.getUsername()) && bdPassword.equals(user.getPassword())) {

                result.put("Resultado: ", "Usuario" + user.getUsername() + " Logueado");
                break;
            }
        }

        if (result.isEmpty()) {
            return "Usuario no encontrado";
        }

        return result.toString();
    }

    public String register(Users user) {
        //this.userList.add(user);

        UserRepository repository = new UserRepository();
        UsersModel userModel = new UsersModel();

        userModel.setUsername(user.getUsername());
        userModel.setEmail(user.getEmail());
        userModel.setPassword(user.getPassword());
        userModel.setCreatedDate(LocalDate.now());

        boolean result = repository.save(userModel);

        return (result) ? "Usuario" + user.getUsername() + " ha sido Registrado" : "El hubo un error al registrar el usuario";
    }
}
