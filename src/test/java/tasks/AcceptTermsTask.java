package tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import user_interfaces.CheckoutForm;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class AcceptTermsTask {

    public static Performable andConditions() {
        return Task.where("{0} acepta los términos y condiciones",
                actor -> actor.attemptsTo(
                        WaitUntil.the(CheckoutForm.TERMS_CHECKBOX, isVisible()).forNoMoreThan(10).seconds(),
                        Click.on(CheckoutForm.TERMS_CHECKBOX)
                )
        );
    }
}