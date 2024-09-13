package org.santana;

import java.util.HashMap;
import java.util.Map;

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
        Map<String, Object> data = new HashMap();
        data.put("userName", "New name");
        data.put("email", "prueba2@prueba@gmail.com");

        System.out.println(userRepository.updateById(data, 1));
    }
}
