package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import products.Product;

public class MainPage extends BasePage {
    WebDriver driver;
    @FindBy(xpath = "//input[@type='search' and contains(@placeholder,'товаров')]")
    public WebElement searchField;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    public void searchProduct(Product product){
        searchField.sendKeys(product.getName());
        searchField.sendKeys(Keys.ENTER);
    }
    public void searchProduct(String productName){
        searchField.sendKeys(productName);
        searchField.sendKeys(Keys.ENTER);
    }

}
