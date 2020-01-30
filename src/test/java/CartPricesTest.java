import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import pages.*;
import products.Product;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class CartPricesTest extends BaseTest {

    @Test
    public void cartPricesTest(){
        /*
        1.открыть dns-shop
        2.в поиске найти playstation
        3.кликнуть поplaystation4 slim black
        4.запомнить цену
        5.Доп.гарантия-выбрать2 года
        6.дождаться изменения цены и запомнить цену с гарантией
        7.Нажать Купить
        8.выполнить поиск Detroit
        9.запомнить цену
        10.нажать купить
        11.проверить что цена корзины стала равна сумме покупок
        12.перейти в корзину
        13.проверить, что для приставки выбрана гарантия на 2 года
        14.проверить цену каждого из товаров и сумму
        15.удалитьиз корзины Detroit
        16.проверить что Detroit нет больше в корзине и что сумма уменьшилась на цену Detroit
        17.добавить еще 2 playstation(кнопкой +) и проверить что сумма верна (равна трем ценам playstation)
        18.нажать вернуть удаленный товар, проверить что Detroit появился в корзине и сумма увеличилась на его значение
                 */
        driver.navigate().to(properties.getProperty("app.url"));
        MainPage mainPage = new MainPage(driver);
        SearchPage searchPage = new SearchPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);

        Product playstation = new Product("Игровая приставка PlayStation 4 Slim Black 1 TB + 3 игры");
        Product detroit = new Product("Detroit");
        mainPage.searchProduct("playstation");
        //2, 3
        searchPage.chooseProduct(playstation);
        //4
        int pricePS = productPage.getProductPrice();
        //5
        productPage.selectWarranty(2);
        //6
        int pricePSWithWarranty = productPage.getProductPrice();
        //7
        productPage.buy();
        //8
        mainPage.searchProduct(detroit);
        //9
        int gamePrice = productPage.getProductPrice();
        //10
        productPage.buy();
        //11
        int cartPrice = productPage.getCartPrice();
        Assert.assertEquals(cartPrice, (pricePSWithWarranty + gamePrice));
        //12
        productPage.goToCart();
        //13
        Assert.assertTrue(cartPage.checkWarranty(playstation.getName()));
        //14
        Assert.assertEquals(pricePS, formatPrice(cartPage.getPriceElementForProduct(playstation.getName())));
        Assert.assertEquals(gamePrice, formatPrice(cartPage.getPriceElementForProduct(detroit.getName())));
        Assert.assertEquals(pricePSWithWarranty + gamePrice, formatPrice(cartPage.getPriceSumElement()));
        //15
        cartPage.deleteProduct(detroit.getName());
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='restore-last-removed']")));
        //16
        Assert.assertEquals(pricePSWithWarranty, formatPrice(cartPage.getPriceSumElement()));
        //17
        cartPage.changeProductQuantity(playstation.getName(),3);
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Итого')]/..//span[@class='price__current']")));
        Assert.assertEquals(pricePSWithWarranty*3, productPage.getCartPrice());
        //18
        cartPage.restoreDeletedProduct();
        new WebDriverWait(driver, 5).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[@class='restore-last-removed']")));
        Assert.assertEquals((pricePSWithWarranty*3) + gamePrice, formatPrice(cartPage.getPriceSumElement()));
    }
}
