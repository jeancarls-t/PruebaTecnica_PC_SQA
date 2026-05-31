package questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class GetSSNErrorMessage implements Question<String> {
    public static GetSSNErrorMessage displayed() {
        return new GetSSNErrorMessage();
    }
    @Override
    public String answeredBy(Actor actor) {
        try {
            WebElement errorElement = BrowseTheWeb.as(actor).find(
                    By.xpath("//span[contains(@class,'theme__error') and contains(text(),'Enter a valid Social Security number')]")
            );
            String errorMessage = errorElement.getText();
            return errorMessage;
        } catch (Exception e) {
            return "";
        }
    }
}
