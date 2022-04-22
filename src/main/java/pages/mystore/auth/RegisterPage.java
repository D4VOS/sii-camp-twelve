package pages.mystore.auth;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.mystore.base.MyStoreBasePage;
import pages.mystore.home.HomePage;

import java.util.List;

import static helpers.WebElementHelpers.getOptionByText;

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
        this.firstName.clear();
        this.firstName.sendKeys(firstName);
        return this;
    }

    public RegisterPage fillLastName(String lastName) {
        this.lastName.clear();
        this.lastName.sendKeys(lastName);
        return this;
    }

    public RegisterPage fillEmail(String email) {
        this.email.clear();
        this.email.sendKeys(email);
        return this;
    }

    public RegisterPage fillPassword(String password) {
        this.password.clear();
        this.password.sendKeys(password);
        return this;
    }

    public RegisterPage fillBirthDate(String birthDate) {
        this.birthDate.clear();
        this.birthDate.sendKeys(birthDate);
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
}
