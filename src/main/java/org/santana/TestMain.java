package org.santana;

import org.santana.model.UsersModel;
import org.santana.repository.UserRepository;
import org.santana.service.auth.UserAuth;
import org.santana.service.users.Users;

class TestMain {

    public static void main(String[] args) {

        Users user = new Users();
        user.setUsername("Jonathan");
        user.setEmail("prueba@gmail.com");
        user.setPassword("123456");

        UserAuth auth = new UserAuth();

        // System.out.println(auth.register(user));
        UserRepository userRepository = new UserRepository();
        // Map<String, Object> data = new HashMap();
        // data.put("userName", "New name");
        // data.put("email", "prueba2@prueba@gmail.com");

        UsersModel userModel = new UsersModel();

        userModel.setUsername("prueba");
        userModel.setEmail("email@gmail.com");

        userModel.getPropertiesWithValue();

        // System.out.println(userRepository.updateById(userModel, 1));
    }
}
