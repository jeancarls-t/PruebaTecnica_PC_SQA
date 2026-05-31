package tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SelectDestinationFromDropdownTask {

    public static Performable byName(String destinationName) {
        return Task.where("{0} selecciona el destino " + destinationName + " del dropdown",
                actor -> {
                    var driver = BrowseTheWeb.as(actor).getDriver();
                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                    JavascriptExecutor js = (JavascriptExecutor) driver;

                    try { Thread.sleep(1000); } catch (InterruptedException e) {}
                    WebElement dropdown = wait.until(ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//input[contains(@class,'BlackDropDown__inputInputElement')]")
                    ));

                    js.executeScript("arguments[0].click();", dropdown);
                    try { Thread.sleep(500); } catch (InterruptedException e) {}
                    WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
                            By.xpath("//li[contains(text(),'" + destinationName + "')]")
                    ));
                    js.executeScript("arguments[0].click();", option);
                    try { Thread.sleep(1000); } catch (InterruptedException e) {}
                }
        );
    }
}
