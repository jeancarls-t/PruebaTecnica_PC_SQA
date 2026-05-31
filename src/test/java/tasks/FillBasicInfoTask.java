package tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import user_interfaces.CheckoutForm;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class FillBasicInfoTask {

    public static Performable withData(String name, String email, String phone) {
        return Task.where("{0} llena nombre, email y teléfono",
                actor -> actor.attemptsTo(
                        WaitUntil.the(CheckoutForm.NAME_FIELD, isVisible()).forNoMoreThan(10).seconds(),
                        Enter.theValue(name).into(CheckoutForm.NAME_FIELD),
                        Enter.theValue(email).into(CheckoutForm.EMAIL_FIELD),
                        Enter.theValue(phone).into(CheckoutForm.PHONE_FIELD)
                )
        );
    }
}