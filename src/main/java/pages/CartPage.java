package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import products.Product;

public class CartPage {
    WebDriver driver;

    @FindBy(xpath = "//div[contains(text(),'Итого')]/..//span[@class='price__current']")
    public WebElement priceSum;
    @FindBy(xpath = "//span[@class='restore-last-removed']")
    public WebElement restoreButton;

    public CartPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public WebElement getPriceElementForSum(){
        return priceSum;
    }
    public WebElement getPriceElementForProduct(Product product){
        String productName = product.getName();
        return driver.findElement(By.xpath("//a[contains(text(),'" +
                productName +
                "')]/../../../..//span[@class='price__current']"));
    }
    public void deleteProduct(Product product){
        String productName = product.getName();
        driver.findElement(By.xpath("//a[contains(text(),'" + productName +"')]/../..//button[text()='Удалить']")).click();
    }
    public void changeProductQuantity(Product product, int count){
        String productName = product.getName();
        WebElement element = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[contains(text(),'" +
                productName +
                "')]/../../../..//input[@class='count-buttons__input']"))));
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
        element.sendKeys("" + count);
        element.sendKeys(Keys.ENTER);
    }
    public void restoreDeletedProduct(){
        restoreButton.click();
    }
    public boolean checkWarranty(Product product){
        String productName = product.getName();
        return driver.findElement(By.xpath("//a[contains(text(),'" + productName +"')]/../..//span[contains(text(),'гарантия на 24 мес')]")).isDisplayed();
    }
}
