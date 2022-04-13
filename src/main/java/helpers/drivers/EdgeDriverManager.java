package helpers.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class EdgeDriverManager extends WebDriverManger {
    @Override
    public WebDriver newDriver() {
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver(getOptions());
    }

    @Override
    public EdgeOptions getOptions() {
        return new EdgeOptions();
    }
}
