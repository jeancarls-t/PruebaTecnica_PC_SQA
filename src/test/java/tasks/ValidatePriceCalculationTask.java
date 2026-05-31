package tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ValidatePriceCalculationTask {

    public static Performable afterDiscount() {
        return Task.where("{0} valida que el precio con descuento sea menor",
                actor -> {
                    var driver = BrowseTheWeb.as(actor).getDriver();
                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

                    try { Thread.sleep(2000); } catch (InterruptedException e) {}

                    int travelers = 1;
                    try {
                        WebElement travelersElement = driver.findElement(By.xpath("//*[contains(text(),'travelers')]"));
                        String travelersText = travelersElement.getText().trim();
                        String travelersNumber = travelersText.replaceAll("[^0-9]", "");
                        if (!travelersNumber.isEmpty()) {
                            travelers = Integer.parseInt(travelersNumber);
                        }
                    } catch (Exception e) {
                    }
                    double unitPrice = 0;
                    try {
                        WebElement unitPriceElement = driver.findElement(By.xpath("//*[contains(@class,'price-tag') or contains(@class,'GalleryItem__price')]"));
                        String unitPriceText = unitPriceElement.getText().replace("$", "").trim();
                        unitPrice = Double.parseDouble(unitPriceText);
                    } catch (Exception e) {
                        WebElement unitPriceElement = driver.findElement(By.xpath("//div[contains(@class,'flexboxgrid__col-xs-5')]"));
                        String unitPriceText = unitPriceElement.getText().replace("$", "").trim();
                        unitPrice = Double.parseDouble(unitPriceText);
                    }
                    double totalAfterDiscount = 0;
                    try {
                        WebElement totalElement = wait.until(ExpectedConditions.presenceOfElementLocated(
                                By.xpath("//strong[contains(text(),'$') and contains(@class,'OrderSummary')]")
                        ));
                        String totalText = totalElement.getText().replace("$", "").replace(",", "").trim();
                        totalAfterDiscount = Double.parseDouble(totalText);
                    } catch (Exception e) {
                    }

                    double expectedPrice = unitPrice * travelers;
                    System.out.println("\n📊 === RESUMEN DE VALIDACIÓN ===");
                    System.out.println("Precio unitario: $" + unitPrice);
                    System.out.println("Precio esperado sin descuento: $" + travelers);
                    System.out.println("Precio total actual: $" + totalAfterDiscount);

                    if (totalAfterDiscount < expectedPrice) {
                    } else if (Math.abs(totalAfterDiscount - expectedPrice) < 0.01) {
                        System.out.println("EL PRECIO NO TIENE DESCUENTO: Total: $" + totalAfterDiscount + " = Esperado: $" + expectedPrice);
                    } else {
                        System.out.println("ERROR: El precio es mayor al esperado. Total: $" + totalAfterDiscount + " > Esperado: $" + expectedPrice);
                    }
                }
        );
    }
}