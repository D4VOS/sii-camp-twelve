package pages.mystore.home;

import exceptions.NotFoundMatchingOptionException;
import models.shop.MenuOption;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.BasePage;
import pages.mystore.account.AccountPage;
import pages.mystore.auth.LoginPage;
import pages.mystore.base.WidgetsPage;
import pages.mystore.basket.BasketPage;
import pages.mystore.product.CategoryPage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static helpers.web.wrappers.InputActions.performSendKeys;

public class HeaderPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(HeaderPage.class);
    private final WidgetsPage widgets;

    private static List<MenuOption> menu = new ArrayList<>();

    @FindBy(css = ".user-info a")
    private WebElement loginButton;

    @FindBy(css = ".account")
    private WebElement account;

    @FindBy(css = "#search_widget .ui-autocomplete-input")
    private WebElement searchInput;

    @FindBy(css = "#search_widget button")
    private WebElement searchSubmitButton;

    @FindBy(css = "#top-menu")
    private WebElement mainMenu;

    @FindBy(css = ".cart-preview .header")
    private WebElement cartPreviewButton;

    public HeaderPage(WebDriver driver, WebElement element) {
        super(driver, element);
        initMenuStructure();
        widgets = new WidgetsPage(driver);
    }

    public List<MenuOption> getCategories() {
        return menu.stream().filter(o -> o.getLevel() == 0).collect(Collectors.toList());
    }

    public List<MenuOption> getSubCategories() {
        return menu.stream().filter(o -> o.getLevel() == 1).collect(Collectors.toList());
    }

    private void initMenuStructure() {
        if (menu != null) {
            createMenuLevelOptions(mainMenu, null);
        }
    }

    private void createMenuLevelOptions(WebElement menuLevel, MenuOption parent) {
        int depth = getMenuDepthLevel(menuLevel);
        List<WebElement> menuOptions = getLevelOptions(menuLevel);

        menuOptions.forEach(option -> {
            String id = option.getAttribute("id");
            if (getExistingMenuOptionById(id) == null) {
                String text = option.findElement(By.tagName("a")).getText();
                MenuOption menuOption = new MenuOption(text, id, depth, parent);
                menu.add(menuOption);

                WebElement lowerMenu = getLowerMenu(option);
                if (lowerMenu != null) {
                    hoverOnElement(option);
                    createMenuLevelOptions(lowerMenu, menuOption);
                }
            }
        });
    }

    private int getMenuDepthLevel(WebElement menuLevel) {
        return Integer.parseInt(menuLevel.getAttribute("data-depth"));
    }

    private List<WebElement> getLevelOptions(WebElement menuLevel) {
        return menuLevel.findElements(By.xpath("./*[@class='category']"));
    }


    private WebElement getLowerMenu(WebElement element) {
        try {
            return element.findElement(By.className("top-menu"));
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public HeaderPage typeInSearchBar(String text) {
        performSendKeys(searchInput, text);
        return this;
    }

    public void submitSearch() {
        searchSubmitButton.click();
    }

    public List<String> getSearchHintTexts() {
        wait.until(ExpectedConditions.visibilityOf(widgets.getSearchDropdown()));
        return widgets.getSearchHints().stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    // Menu methods

    public MenuOption getRandomCategory() {
        int random = new Random().nextInt(menu.size());
        return menu.get(random);
    }

    public CategoryPage selectRandomCategory() {
        MenuOption randomCategory = getRandomCategory();
        return selectCategory(randomCategory.getTitle());
    }

    public WebElement getMenuItem(String title) {
        String elementId = menu.stream().filter(o -> o.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElseThrow(() -> new NotFoundMatchingOptionException("Option with text " + title + " not found"))
                .getId();

        return driver.findElement(By.id(elementId));
    }


    public CategoryPage selectCategory(String title) {
        logger.info("Opening " + title + " category..");
        WebElement menuItem = getMenuItem(title);
        hoverHigherLevels(getExistingMenuOptionByText(title));
        wait.until(ExpectedConditions.elementToBeClickable(menuItem));
        menuItem.click();
        return new CategoryPage(driver);
    }

    private MenuOption getExistingMenuOptionById(String id) {
        return menu.stream().filter(i -> i.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    private MenuOption getExistingMenuOptionByText(String text) {
        return menu.stream().filter(i -> i.getTitle().equalsIgnoreCase(text))
                .findFirst()
                .orElse(null);
    }

    private void hoverHigherLevels(MenuOption menuOption) {
        if (menuOption.getLevel() == 0) {
            return;
        }
        if (menuOption.getLevel() > 0) {
            MenuOption parentOption = menuOption.getParent();
            hoverHigherLevels(parentOption);
            String parentId = parentOption.getId();
            WebElement parent = driver.findElement(By.id(parentId));
            hoverOnElement(parent);
        }
    }

    public BasketPage goToBasket() {
        cartPreviewButton.click();
        return new BasketPage(driver);
    }

    public LoginPage goToLogin() {
        loginButton.click();
        return new LoginPage(driver);
    }

    public AccountPage goToAccount() {
        account.click();
        return new AccountPage(driver);
    }
}
