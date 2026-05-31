package interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class EnterInvalidSSN implements Interaction {

    private final Target ssnField;
    private final String ssnValue;

    public EnterInvalidSSN(Target ssnField, String ssnValue) {
        this.ssnField = ssnField;
        this.ssnValue = ssnValue;
    }

    public static EnterInvalidSSN into(Target ssnField, String ssnValue) {
        return new EnterInvalidSSN(ssnField, ssnValue);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                WaitUntil.the(ssnField, isVisible()).forNoMoreThan(10).seconds(),
                Enter.theValue(ssnValue).into(ssnField)
        );

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {}

        WebDriver driver = net.serenitybdd.screenplay.abilities.BrowseTheWeb.as(actor).getDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("arguments[0].dispatchEvent(new Event('blur', { bubbles: true }));", ssnField.resolveFor(actor));

        System.out.println("✅ SSN inválido ingresado: " + ssnValue);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {}
    }
}