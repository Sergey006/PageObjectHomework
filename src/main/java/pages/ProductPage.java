package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ProductPage extends BasePage {
    WebDriver driver;

    @FindBy(xpath = "//div[contains(@class, 'desktop')]//span[@class='current-price-value']")
    public WebElement price;
    @FindBy(xpath = "//select[@class='form-control select']/option[@value='2']")
    public WebElement warrantyTwoYearsSelector;
    @FindBy(xpath = "//select[@class='form-control select']")
    public WebElement warranty;
    @FindBy(xpath = "//div[contains(@class, 'without')]/button//span[text()='Купить']/..")
    public WebElement buyButton;
    @FindBy(xpath = "//span[@class='cart-link__price']/..")
    public WebElement cartPrice;


    public ProductPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public int getProductPrice(){
        return Integer.parseInt(price.getText().replaceAll("\\s",""));
    }
    public int getCartPrice(){
        return Integer.parseInt(cartPrice.getText().replaceAll("\\s",""));
    }

    public void selectWarranty(int years){
        if (years >= 0 && years < 3) {
            /*((Select) warranty).selectByIndex(years);*/
            driver.findElement(By.xpath("//select[@class='form-control select']/option[@value='" + years + "']")).click();
        }
    }
    public void buy(){
        buyButton.click();
    }
    public void goToCart(){
        cartPrice.click();
    }

}
