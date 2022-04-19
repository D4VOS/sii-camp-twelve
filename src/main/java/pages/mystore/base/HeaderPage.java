package pages.mystore.base;

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
import pages.mystore.CategoryPage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public final class HeaderPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(HeaderPage.class);
    private final WidgetsPage widgetsPage;

    private static List<MenuOption> menu = new ArrayList<>();
    @FindBy(css = "#search_widget .ui-autocomplete-input")
    private WebElement searchInput;

    @FindBy(css = "#search_widget button")
    private WebElement searchSubmitButton;

    @FindBy(css = ".category")
    private List<WebElement> categories;

    public HeaderPage(WebDriver driver) {
        super(driver);
        initMenuStructure();
        widgetsPage = new WidgetsPage(driver);
    }

    public List<MenuOption> getCategories() {
        return menu.stream().filter(o -> o.getLevel() == 0).collect(Collectors.toList());
    }

    public List<MenuOption> getSubCategories() {
        return menu.stream().filter(o -> o.getLevel() == 1).collect(Collectors.toList());
    }

    private void initMenuStructure() {
        menu = categories.stream()
                .map(this::parseToMenuOption)
                .collect(Collectors.toList());
    }

    private MenuOption parseToMenuOption(WebElement element) {
        return new MenuOption(
                getMenuItemText(element),
                getItemId(element),
                getMenuDepth(element),
                getParentOption(element)
        );
    }


    private MenuOption getExistingOptionByTitle(String title) {
        return menu.stream().filter(o -> o.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElseThrow(() -> new NotFoundMatchingOptionException("Menu option with text " + title + " not found"));
    }

    private MenuOption getExistingOptionById(String id) {
        return menu.stream().filter(o -> o.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundMatchingOptionException("Menu option with id: " + id + " not found"));
    }

    private String getMenuItemText(WebElement element) {
        WebElement categoryItem = lookForContainer(element);
        if (getMenuDepth(categoryItem) == 0) {
            return categoryItem.getText();
        }
        categoryItem = lookForContainer(categoryItem);
        hoverOnElement(categoryItem);
        return element.getText();
    }

    private MenuOption getParentOption(WebElement element) {
        WebElement currentItem = lookForContainer(element);
        try {
            WebElement parent = lookForContainer(currentItem);
            if (parent == null) {
                return null;
            }
            currentItem = parent;
        } catch (NoSuchElementException ignored) {
        }
        return new MenuOption(
                element.getText(),
                currentItem.getAttribute("id"),
                0,
                null
        );
    }

    private String getItemId(WebElement element) {
        WebElement itemRoot = lookForContainer(element);
        return itemRoot.getAttribute("id");
    }

    public HeaderPage searchFor(String text) {
        searchInput.clear();
        searchInput.sendKeys(text);
        return this;
    }

    public void submitSearch() {
        searchSubmitButton.click();
    }

    public List<String> getSearchHintTexts() {
        wait.until(ExpectedConditions.visibilityOf(widgetsPage.getSearchDropdown()));
        return widgetsPage.getSearchHints().stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    // Menu methods
    public int getMenuDepth(WebElement element) {
        WebElement menuRoot = lookForMenu(element);
        if (menuRoot == null) {
            return 0;
        }
        return Integer.parseInt(menuRoot.getAttribute("data-depth"));
    }

    public MenuOption getRandomCategory() {
        int random = new Random().nextInt(menu.size());
        return menu.get(random);
    }

    public CategoryPage goToRandomCategory() {
        MenuOption randomCategory = getRandomCategory();
        return goToCategory(randomCategory.getTitle());
    }

    public WebElement getMenuItem(String title) {
        String elementId = menu.stream().filter(o -> o.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElseThrow(() -> new NotFoundMatchingOptionException("Option with text " + title + " not found"))
                .getId();

        return driver.findElement(By.id(elementId));
    }


    public CategoryPage goToCategory(String title) {
        logger.info("Opening " + title + " category..");
        WebElement menuItem = getMenuItem(title);
        hoverHigherLevels(getExistingOptionByTitle(title));
        menuItem.click();
        return new CategoryPage(driver);
    }

    private void hoverHigherLevels(MenuOption menuOption) {
        if (menuOption.getLevel() == 0) {
            return;
        }
        if (menuOption.getLevel() > 0) {
            String parentId = menuOption.getParent().getId();
            hoverHigherLevels(getExistingOptionById(parentId));
            WebElement parent = driver.findElement(By.id(parentId));
            hoverOnElement(parent);
        }
    }

    private WebElement lookForMenu(WebElement element) {
        WebElement container = lookForContainer(element);
        return getParentUntilHaveClass(container, "top-menu");
    }

    private WebElement lookForContainer(WebElement element) {
        return getParentUntilHaveClass(element, "category");
    }
}
