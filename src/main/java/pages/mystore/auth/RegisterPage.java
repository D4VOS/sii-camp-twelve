package pages.mystore.auth;

import models.entities.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.mystore.base.MyStoreBasePage;
import pages.mystore.home.HomePage;

import java.util.List;

import static helpers.web.WebElementHelpers.getOptionByText;
import static helpers.web.wrappers.InputActions.performSendKeys;

public class RegisterPage extends MyStoreBasePage {

    @FindBy(css = ".radio-inline")
    private List<WebElement> socialTitles;

    @FindBy(css = "[name=firstname]")
    private WebElement firstName;

    @FindBy(css = "[name=lastname]")
    private WebElement lastName;

    @FindBy(css = "[name=email]")
    private WebElement email;

    @FindBy(css = "[name=password]")
    private WebElement password;

    @FindBy(css = "[name=birthday]")
    private WebElement birthDate;

    @FindBy(css = ".register-form .btn-primary")
    private WebElement submit;

    @FindBy(css = "[name=customer_privacy]")
    private WebElement customerPrivacyCheckbox;

    @FindBy(css = "[name=psgdpr]")
    private WebElement tosCheckbox;

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public RegisterPage setSocialTitle(String socialTitle) {
        getOptionByText(socialTitles, socialTitle).click();
        return this;
    }

    public RegisterPage fillFirstName(String firstName) {
        performSendKeys(this.firstName, firstName);
        return this;
    }

    public RegisterPage fillLastName(String lastName) {
        performSendKeys(this.lastName, lastName);
        return this;
    }

    public RegisterPage fillEmail(String email) {
        performSendKeys(this.email, email);
        return this;
    }

    public RegisterPage fillPassword(String password) {
        performSendKeys(this.password, password);
        return this;
    }

    public RegisterPage fillBirthDate(String birthDate) {
        performSendKeys(this.birthDate, birthDate);
        return this;
    }

    public RegisterPage acceptTOS() {
        if (!tosCheckbox.isSelected()) {
            tosCheckbox.click();
        }
        return this;
    }

    public RegisterPage acceptCustomerPrivacy() {
        if (!customerPrivacyCheckbox.isSelected()) {
            customerPrivacyCheckbox.click();
        }
        return this;
    }

    public HomePage submit() {
        submit.click();
        return new HomePage(driver);
    }

    public HomePage registerUser(User user) {
        setSocialTitle(user.getSocialTitle());
        fillFirstName(user.getFirstName());
        fillLastName(user.getLastName());
        fillEmail(user.getEmail());
        fillPassword(user.getPassword());
        fillBirthDate(user.getBirthDate());
        acceptCustomerPrivacy()
                .acceptTOS();

        return submit();
    }
}
