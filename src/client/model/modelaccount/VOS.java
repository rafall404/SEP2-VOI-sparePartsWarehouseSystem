package client.model.modelaccount;

import java.io.Serializable;

public class VOS implements Account, Serializable {

    private String username;
    private String password;

    public VOS(String username, String password)
    {
        this.username= username;
        this.password= password;
    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
