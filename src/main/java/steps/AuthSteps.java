package steps;

import models.entities.User;
import org.openqa.selenium.WebDriver;
import pages.mystore.home.HomePage;

public class AuthSteps extends HomePage {
    public AuthSteps(WebDriver driver) {
        super(driver);
    }

    public void registerUser(User user) {
        inHeader()
                .goToLogin()
                .goToRegister()
                .registerUser(user);
    }
}
