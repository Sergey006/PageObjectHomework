package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import products.Product;

import java.util.List;

public class SearchPage extends BasePage {

    WebDriver driver;

    @FindBy(xpath = "//div[@class='product-info__title-link']/a")
    public List<WebElement> products;

    public SearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void chooseProduct(Product product){
        for (WebElement element:products){
            if (product.getName().equals(element.getText())){
                element.click();
                break;
            }
        };
    }

}
