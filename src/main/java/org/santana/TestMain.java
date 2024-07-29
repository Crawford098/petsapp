package org.santana;

import org.santana.service.auth.UserAuth;
import org.santana.service.users.Users;

class TestMain {

    public static void main(String[] args) {

        Users user = new Users("jonathan", "santana", "prueba@gmail.com");
        user.setPassword("123456");

        UserAuth auth = new UserAuth();
        
        System.out.println(auth.register(user));
    }
}
