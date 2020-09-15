import client.model.modelaccount.IAccountsModel;
import client.model.modelaccount.AccountModelDummyClass;
import client.viewmodel.createAccount.CreateAccountViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class CreateAccountViewModelTest
{
    IAccountsModel iAccountsModel ;
    CreateAccountViewModel createAccountViewModel;
    StringProperty username;
    StringProperty password;
    StringProperty confPassword;

    @BeforeEach
    void setUp()
    {
        //arrange
        iAccountsModel = new AccountModelDummyClass();
        createAccountViewModel = new CreateAccountViewModel(iAccountsModel);
        username = new SimpleStringProperty();
        password = new SimpleStringProperty();
        confPassword = new SimpleStringProperty();

        username.bindBidirectional(createAccountViewModel.getUsername());
        password.bindBidirectional(createAccountViewModel.getPassword());
        confPassword.bindBidirectional(createAccountViewModel.getConfirmPassword());
    }

    @Test
    void createAccountPass()
    {
            //act
            username.setValue("shouldBeCreatedAcc");
            password.setValue("password");
            confPassword.setValue("password");
            boolean isCreated =createAccountViewModel.createAccount();
            //assert
            assertEquals(true,isCreated);
    }

    @Test void createAccountFailAlreadyExists()
    {
        username.setValue("arturas");
        password.setValue("password");
        confPassword.setValue("password");
        boolean isCreated =createAccountViewModel.createAccount();
        assertEquals(false,isCreated);
    }

    @Test void createAccountFailEmpty()
    {
        username.setValue("");
        password.setValue("password");
        confPassword.setValue("password");
        boolean isCreated =createAccountViewModel.createAccount();
        assertEquals(false,isCreated);
    }

    @Test void createAccountFailPasswDoesntMatch()
    {
        username.setValue("newusername1");
        password.setValue("notpassword");
        confPassword.setValue("password");
        boolean isCreated =createAccountViewModel.createAccount();
        assertEquals(false,isCreated);
    }

    @Test void createAccountFail()
    {
        username.setValue("arturas");
        password.setValue("password");
        confPassword.setValue("password");
        boolean isCreated =createAccountViewModel.createAccount();
        assertEquals(false,isCreated);
    }
    //Boundry partitioning
    @Test void createAccountUsernameToLong()
    {
        //21 chars
        username.setValue("aaaaaaaaaaaaaaaaaaaaa");
        password.setValue("aaaaaaaaaaaaaaaaaaaaa");
        confPassword.setValue("aaaaaaaaaaaaaaaaaaaaa");
        boolean isCreated =createAccountViewModel.createAccount();
        assertEquals(false,isCreated);
    }

    @Test void createAccountUsername()
    {
        //19 chars
        username.setValue("aaaaaaaaaaaaaaaaaaa");
        password.setValue("aaaaaaaaaaaaaaaaaaa");
        confPassword.setValue("aaaaaaaaaaaaaaaaaaa");
        boolean isCreated =createAccountViewModel.createAccount();
        assertEquals(true,isCreated);
    }

    @Test void createAccountUsernameTest()
    {
        //20 chars
        username.setValue("aaaaaaaaaaaaaaaaaaaa");
        password.setValue("aaaaaaaaaaaaaaaaaaaa");
        confPassword.setValue("aaaaaaaaaaaaaaaaaaaa");
        boolean isCreated =createAccountViewModel.createAccount();
        assertEquals(true,isCreated);
    }

    @Test()
    void createAccountUsernameDigitAndSymbols()
    {
        //characters
        username.setValue("...1");
        password.setValue("...1");
        confPassword.setValue("...1");
        boolean isCreated =createAccountViewModel.createAccount();
        assertEquals(true,isCreated);
    }


    }