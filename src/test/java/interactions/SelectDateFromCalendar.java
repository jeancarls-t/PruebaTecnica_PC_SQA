package interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.JavaScriptClick;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class SelectDateFromCalendar implements Interaction {

    private final Target dateInput;
    private final String targetDate;

    public SelectDateFromCalendar(Target dateInput, String targetDate) {
        this.dateInput = dateInput;
        this.targetDate = targetDate;
    }

    public static SelectDateFromCalendar on(Target dateInput, String targetDate) {
        return new SelectDateFromCalendar(dateInput, targetDate);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(dateInput, isVisible()).forNoMoreThan(10).seconds(),
                JavaScriptClick.on(dateInput)
        );

        actor.attemptsTo(
                WaitUntil.the(By.xpath("//button[text()='Ok']"), isVisible()).forNoMoreThan(5).seconds()
        );

        LocalDate fechaObjetivo = LocalDate.parse(targetDate);
        int añoObjetivo = fechaObjetivo.getYear();
        int mesObjetivo = fechaObjetivo.getMonthValue();
        int diaObjetivo = fechaObjetivo.getDayOfMonth();

        selectMonthAndYear(actor, añoObjetivo, mesObjetivo);

        selectDay(actor, diaObjetivo);

        actor.attemptsTo(
                JavaScriptClick.on(By.xpath("//button[text()='Ok']"))
        );
    }

    private <T extends Actor> void selectMonthAndYear(T actor, int añoObjetivo, int mesObjetivo) {
        int maxIteraciones = 24;
        int iteraciones = 0;

        while (iteraciones < maxIteraciones) {
            String currentMonthYear = getCurrentMonthYear(actor);
            if (currentMonthYear == null || currentMonthYear.isEmpty()) {
                break;
            }

            LocalDate currentDate = parseMonthYear(currentMonthYear);
            if (currentDate == null) {
                break;
            }

            int añoActual = currentDate.getYear();
            int mesActual = currentDate.getMonthValue();

            if (añoActual == añoObjetivo && mesActual == mesObjetivo) {
                break;
            }

            if (añoActual < añoObjetivo || (añoActual == añoObjetivo && mesActual < mesObjetivo)) {
                actor.attemptsTo(
                        Click.on(By.xpath("//button[contains(@aria-label,'Next') or contains(@class,'next') or .//*[contains(@class,'chevron_right')]]"))
                );
            } else {
                actor.attemptsTo(
                        Click.on(By.xpath("//button[contains(@aria-label,'Previous') or contains(@class,'prev') or .//*[contains(@class,'chevron_left')]]"))
                );
            }

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            iteraciones++;
        }
    }

    private <T extends Actor> String getCurrentMonthYear(T actor) {
        try {
            WebElement header = BrowseTheWeb.as(actor).find(By.xpath("//div[contains(@class,'calendar-header') or contains(@class,'datepicker__header') or contains(@class,'DayPicker-Caption')]"));
            return header.getText();
        } catch (Exception e) {
            return null;
        }
    }

    private LocalDate parseMonthYear(String text) {
        if (text == null || text.isEmpty()) {
            return null;
        }
        try {
            String[] partes = text.split(" ");
            if (partes.length == 2) {
                int mes = getMonthNumber(partes[0]);
                int año = Integer.parseInt(partes[1]);
                return LocalDate.of(año, mes, 1);
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    private <T extends Actor> void selectDay(T actor, int dia) {
        actor.attemptsTo(
                Click.on(By.xpath("//button[text()='" + dia + "' or normalize-space()='" + dia + "']"))
        );
    }

    private int getMonthNumber(String mesNombre) {
        switch (mesNombre.toLowerCase()) {
            case "enero": case "january": case "jan": return 1;
            case "febrero": case "february": case "feb": return 2;
            case "marzo": case "march": case "mar": return 3;
            case "abril": case "april": case "apr": return 4;
            case "mayo": case "may": return 5;
            case "junio": case "june": case "jun": return 6;
            case "julio": case "july": case "jul": return 7;
            case "agosto": case "august": case "aug": return 8;
            case "septiembre": case "september": case "sep": return 9;
            case "octubre": case "october": case "oct": return 10;
            case "noviembre": case "november": case "nov": return 11;
            case "diciembre": case "december": case "dec": return 12;
            default: return 5;
        }
    }
}