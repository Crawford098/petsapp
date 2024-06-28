package org.santana;

import java.util.Scanner;

import org.santana.service.auth.UserAuth;
import org.santana.service.users.Users;

class Main {
    public static void main (String [] args)
    {

        System.out.println("Welcome to petShop App");
        System.out.println("Do you what to login the app?");
        System.out.println("Yes or No");

        //create Scanner Object to capture the result
        Scanner scanner = new Scanner(System.in);

        //Capturing the result
        String result = scanner.nextLine();

        if("yes".equals(result))
        {
            System.out.println("Type your username:");
            String username = scanner.nextLine();

            System.out.println("Type your password");
            String password = scanner.nextLine();

            Users user = new Users(username, password);
            UserAuth auth = new UserAuth();

            System.out.println(auth.register(user));
            System.out.println(auth.login(user));
            System.out.println(user.toString());
        }
        else
        {
            System.out.println("See you later.");
        }
    }

} 
