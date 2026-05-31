package questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class GetConfirmationMessage implements Question<String> {

    private final String expectedMessage;

    public GetConfirmationMessage(String expectedMessage) {
        this.expectedMessage = expectedMessage;
    }

    public static GetConfirmationMessage text(String expectedMessage) {
        return new GetConfirmationMessage(expectedMessage);
    }

    @Override
    public String answeredBy(Actor actor) {
        try {
            WebElement messageElement = BrowseTheWeb.as(actor).find(
                    By.xpath("//h1[contains(@class,'Climate__headline-1')]")
            );
            String actualMessage = messageElement.getText().trim();
            return actualMessage;
        } catch (Exception e) {
            return "";
        }
    }
}
