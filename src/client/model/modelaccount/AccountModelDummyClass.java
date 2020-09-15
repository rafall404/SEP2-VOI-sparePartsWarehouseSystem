package client.model.modelaccount;

import client.model.modelaccount.IAccountsModel;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

//************A dummy class for testing*********


public class AccountModelDummyClass implements IAccountsModel
{

    private ArrayList<String> usernames;
    private ArrayList<String> passwords;
    public AccountModelDummyClass()
    {
        //our "DBS"
        usernames = new ArrayList<>();
        passwords = new ArrayList<>();
        usernames.add("simon");
        usernames.add("rafal");
        usernames.add("arturas");
        usernames.add("sasha");
        passwords.add("admin");
        passwords.add("password");
        passwords.add("we will get 12 from SEP");

    }

    @Override
    public boolean accountExists(String username, String password)
    {
        //the issue might be here that username can login here with others username password better would be to use hashmap
        return usernames.contains(username)&& password.contains(password);
    }

    @Override
    public boolean checkUsername(String username)
    {
        return usernames.contains(username);
    }

    @Override
    public void createAccount(String username, String password, boolean isManager)
    {
        System.out.println("account created in dummy class!");
    }


    public boolean accountIsManager(String username, String password) {
        if (username.equals("arturas") && password.equals("admin"))
        {
            System.out.println("the user " + username + " is manager");
            return true;

        }
        System.out.println("the user " + username + " is not a manager");
        return false;
    }
}
