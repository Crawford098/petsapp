package org.santana.service.users;

//* Define Users*/
public class Users {

    private String username;
    private String password;
    private String email;

    public Users() {
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Users{");
        sb.append("username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", email=").append(email);
        sb.append('}');
        return sb.toString();
    }

}
