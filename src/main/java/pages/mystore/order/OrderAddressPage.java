package pages.mystore.order;

import models.entities.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.mystore.order.shipping.OrderShippingPage;

public class OrderAddressPage extends OrderPage {

    @FindBy(css = "[name=firstname]")
    private WebElement firstNameField;

    @FindBy(css = "[name=lastname]")
    private WebElement lastNameField;

    @FindBy(css = "[name=company]")
    private WebElement companyNameField;

    @FindBy(css = "[name=address1]")
    private WebElement addressField;

    @FindBy(css = "[name=address2]")
    private WebElement addressComplementField;

    @FindBy(css = "[name=city]")
    private WebElement cityField;

    @FindBy(css = "[name=id_state]")
    private WebElement stateSelectField;

    @FindBy(css = "[name=postcode]")
    private WebElement zipPostalCodeField;

    @FindBy(css = "[name=id_country]")
    private WebElement countrySelectField;

    @FindBy(css = "[name=phone]")
    private WebElement phoneField;

    @FindBy(css = "#use_same_address")
    private WebElement useThisAddressForInvoiceCheckbox;

    @FindBy(css = "[name=confirm-addresses]")
    private WebElement continueButton;

    public OrderAddressPage(WebDriver driver) {
        super(driver);
    }

    public OrderAddressPage fillFirstName(String firstName) {
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
        return this;
    }

    public OrderAddressPage fillLastName(String lastName) {
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
        return this;
    }

    public OrderAddressPage fillCompany(String company) {
        companyNameField.clear();
        companyNameField.sendKeys(company);
        return this;
    }

    public OrderAddressPage fillAddress(String address) {
        addressField.clear();
        addressField.sendKeys(address);
        return this;
    }

    public OrderAddressPage fillAddressComplement(String addressComplement) {
        addressComplementField.clear();
        addressComplementField.sendKeys(addressComplement);
        return this;
    }

    public OrderAddressPage fillCity(String city) {
        cityField.clear();
        cityField.sendKeys(city);
        return this;
    }

    public OrderAddressPage selectState(String state) {
        Select countrySelect = new Select(stateSelectField);
        countrySelect.selectByVisibleText(state);
        return this;
    }

    public OrderAddressPage fillZipCode(String zipCode) {
        zipPostalCodeField.clear();
        zipPostalCodeField.sendKeys(zipCode);
        return this;
    }

    public OrderAddressPage selectCountry(String country) {
        Select countrySelect = new Select(countrySelectField);
        countrySelect.selectByVisibleText(country);
        return this;
    }

    public OrderShippingPage goToShipping() {
        continueButton.click();
        return new OrderShippingPage(driver);
    }

    public OrderAddressPage fillAddress(User user) {
        fillFirstName(user.getFirstName());
        fillLastName(user.getLastName());
        fillAddress(user.getAddress().getFirstLine());
        fillCity(user.getAddress().getCity());
        selectState(user.getAddress().getState());
        fillZipCode(user.getAddress().getZipPostalCode());
        selectCountry(user.getAddress().getCountry());
        return this;
    }
}
