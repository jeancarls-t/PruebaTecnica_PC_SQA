package interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;

public class SetDateDirectly implements Interaction {

    private final Target dateInput;
    private final String dateValue;

    public SetDateDirectly(Target dateInput, String dateValue) {
        this.dateInput = dateInput;
        this.dateValue = dateValue;
    }

    public static SetDateDirectly to(Target dateInput, String dateValue) {
        return new SetDateDirectly(dateInput, dateValue);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String script =
                "var input = arguments[0];" +
                        "input.value = arguments[1];" +
                        "input.dispatchEvent(new Event('input', { bubbles: true }));" +
                        "input.dispatchEvent(new Event('change', { bubbles: true }));" +
                        "input.dispatchEvent(new Event('blur', { bubbles: true }));";

        BrowseTheWeb.as(actor).evaluateJavascript(script, dateInput.resolveFor(actor), dateValue);
    }
}