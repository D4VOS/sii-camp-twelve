package pages.mystore.order.shipping;

import exceptions.NotFoundMatchingOptionException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.util.List;
import java.util.stream.Collectors;

public class ShippingMethodListPage extends BasePage {
    @FindBy(css = ".delivery-option")
    private List<WebElement> methods;

    public ShippingMethodListPage(WebDriver driver, WebElement element) {
        super(driver, element);
    }

    public ShippingMethodListPage selectByName(String name) {
        getByName(name).select();
        return this;
    }

    public ShippingMethodPage getByName(String name) {
        return getAll().stream()
                .filter(m -> m.getCarrierName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new NotFoundMatchingOptionException("Not found shipping method with name " + name));
    }

    public List<ShippingMethodPage> getAll() {
        return methods.stream()
                .map(m -> new ShippingMethodPage(driver, m))
                .collect(Collectors.toList());
    }
}
