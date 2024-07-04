package org.santana.service.users;

//* Define Users*/
public class Users {

    private String username;
    private String password;

    public Users(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsersname() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Users{");
        sb.append("username=").append(username);
        sb.append(", password=").append(password);
        sb.append('}');
        return sb.toString();
    }
}
