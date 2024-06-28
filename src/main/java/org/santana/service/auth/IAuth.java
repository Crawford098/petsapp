package org.santana.service.auth;
import org.santana.service.users.Users;

public interface IAuth {

    String login(Users user);

    String register(Users user);
}