package tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import interactions.EnterInvalidSSN;
import user_interfaces.CheckoutForm;

public class FillInvalidSSNTask {

    public static Performable withValue(String ssn) {
        return Task.where("{0} escribe un SSN inválido " + ssn,
                actor -> actor.attemptsTo(
                        EnterInvalidSSN.into(CheckoutForm.SSN_FIELD, ssn)
                )
        );
    }
}