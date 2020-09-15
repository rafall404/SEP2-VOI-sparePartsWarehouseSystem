package client.model.modelaccount;


import java.io.Serializable;

public class Manager implements Account, Serializable {
    String username;
    String password;

    public Manager(String username, String password)
    {
        this.password= password;
        this.username= username;
    }
    @Override
    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }



}
