package pages.mystore.auth;

import models.entities.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.mystore.base.MyStoreBasePage;
import pages.mystore.home.HomePage;

import static helpers.web.wrappers.InputActions.performSendKeys;

public class LoginPage extends MyStoreBasePage {

    @FindBy(css = "[name=email]")
    private WebElement email;

    @FindBy(css = "[name=password]")
    private WebElement password;

    @FindBy(css = "#submit-login")
    private WebElement submit;

    @FindBy(css = ".no-account a")
    private WebElement registerLink;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public RegisterPage goToRegister() {
        registerLink.click();
        return new RegisterPage(driver);
    }

    public LoginPage fillEmail(String email) {
        performSendKeys(this.email, email);
        return this;
    }

    public LoginPage fillPassword(String password) {
        performSendKeys(this.password, password);
        return this;
    }

    public HomePage submit() {
        submit.click();
        return new HomePage(driver);
    }

    public HomePage loginAs(User user) {
        fillEmail(user.getEmail());
        fillPassword(user.getPassword());

        return submit();
    }
}
