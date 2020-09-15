import client.model.modelaccount.AccountModelDummyClass;
import client.model.modelaccount.IAccountsModel;
import client.viewmodel.logIn.LoginViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginViewModelTest
{


    IAccountsModel iAccountsModel = new AccountModelDummyClass();
    LoginViewModel loginViewModel = new LoginViewModel(iAccountsModel);
    StringProperty userName = new SimpleStringProperty();
    StringProperty password = new SimpleStringProperty();


    @BeforeEach
    void setUp()
    {
        userName.bindBidirectional(loginViewModel.getUserNameProperty());
        password.bindBidirectional(loginViewModel.getPasswordProperty());

    }
    @AfterEach
    void tearDown()
    {
        userName.setValue("");
        password.setValue("");
    }

    @Test
    void isManager()
    {
        userName.setValue("arturas");
        password.setValue("admin");
        assertEquals(true,loginViewModel.isManager());
    }
    @Test
    void isNotManager()
    {
        userName.setValue("ImNotManager");
        password.setValue("admin");
        assertEquals(false,loginViewModel.isManager());
    }

    @Test
    void isManagerUsername()
    {
        userName.setValue("arturas");
        password.setValue("wrongPassword");
        assertEquals(false,loginViewModel.isManager());
    }

    @Test
    void checkIfExistsTrue()
    {
        userName.setValue("arturas");
        password.setValue("admin");
        assertEquals(true,loginViewModel.checkIfExists());
    }
    @Test
    void checkIfExistsFalse()
    {
        userName.setValue("doesntExists");
        password.setValue("admin");
        assertEquals(false,loginViewModel.checkIfExists());
    }
}