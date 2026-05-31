package tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import user_interfaces.CheckoutForm;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class ApplyPromoCodeTask {

    public static Performable withCode(String promoCode) {
        return Task.where("{0} aplica el código promocional " + promoCode,
                actor -> actor.attemptsTo(
                        WaitUntil.the(CheckoutForm.PROMO_CODE_FIELD, isVisible()).forNoMoreThan(10).seconds(),
                        Enter.theValue(promoCode).into(CheckoutForm.PROMO_CODE_FIELD),
                        WaitUntil.the(CheckoutForm.APPLY_BUTTON, isVisible()).forNoMoreThan(10).seconds(),
                        Click.on(CheckoutForm.APPLY_BUTTON)
                )
        );
    }
}