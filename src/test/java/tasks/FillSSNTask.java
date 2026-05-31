package tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.JavaScriptClick;
import net.serenitybdd.screenplay.waits.WaitUntil;
import user_interfaces.CheckoutForm;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class FillSSNTask {

    public static Performable withValue(String ssn) {
        return Task.where("{0} escribe el Social Security Number " + ssn,
                actor -> actor.attemptsTo(
                        WaitUntil.the(CheckoutForm.SSN_FIELD, isVisible()).forNoMoreThan(10).seconds(),
                        JavaScriptClick.on(CheckoutForm.SSN_FIELD),
                        Enter.theValue(ssn).into(CheckoutForm.SSN_FIELD)
                )
        );
    }
}