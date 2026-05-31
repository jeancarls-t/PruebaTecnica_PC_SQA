package tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FilterByPrice {

    public static Performable setMaxPrice(int targetPrice) {
        return Task.where("{0} cambia el precio del filtro a " + targetPrice,
                actor -> {
                    var driver = BrowseTheWeb.as(actor).getDriver();
                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
                    JavascriptExecutor js = (JavascriptExecutor) driver;

                    try { Thread.sleep(1500); } catch (InterruptedException e) {}

                    WebElement priceInput = wait.until(ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//input[@role='input' and contains(@class,'theme__inputElement') and @value='1800']")
                    ));

                    js.executeScript("arguments[0].scrollIntoView(true);", priceInput);
                    js.executeScript("arguments[0].click();", priceInput);
                    js.executeScript("arguments[0].value = arguments[1];", priceInput, String.valueOf(targetPrice));
                    js.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", priceInput);
                    js.executeScript("arguments[0].dispatchEvent(new Event('change', { bubbles: true }));", priceInput);
                    js.executeScript("arguments[0].dispatchEvent(new Event('blur', { bubbles: true }));", priceInput);

                    try {
                        WebElement confirmField = driver.findElement(By.xpath("//input[@role='input' and @name='name' and @value='$100']"));
                        js.executeScript("arguments[0].click();", confirmField);
                    } catch (Exception e) {
                    }
                    try { Thread.sleep(3000); } catch (InterruptedException e) {}
                }
        );
    }
}