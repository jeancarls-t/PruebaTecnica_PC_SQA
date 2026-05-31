package tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.JavaScriptClick;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.WebElement;
import user_interfaces.DestinationsPage;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class SelectDestinationCard {

    private static String selectedDestinationPrice = "";

    public static String getSelectedDestinationPrice() {
        return selectedDestinationPrice;
    }

    public static Performable byName(String destinationName) {
        return Task.where("{0} selecciona la tarjeta de " + destinationName,
                actor -> {
                    Target destinationCard = DestinationsPage.DESTINATION_CARD.of(destinationName);
                    actor.attemptsTo(
                            WaitUntil.the(destinationCard, isVisible()).forNoMoreThan(15).seconds(),
                            Scroll.to(destinationCard)
                    );
                    WebElement priceElement = DestinationsPage.PRICE_TAG.resolveFor(actor);
                    String priceText = priceElement.getText();
                    selectedDestinationPrice = priceText.replace("$", "").trim();
                    System.out.println("✅ Precio guardado para " + destinationName + ": " + selectedDestinationPrice);
                    actor.attemptsTo(
                            JavaScriptClick.on(DestinationsPage.BOOK_BUTTON)
                    );

                    System.out.println("✅ Tarjeta de " + destinationName + " seleccionada");
                    try { Thread.sleep(2000); } catch (InterruptedException e) {}
                }
        );
    }
}