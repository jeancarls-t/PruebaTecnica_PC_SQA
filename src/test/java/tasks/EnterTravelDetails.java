package tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.JavaScriptClick;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class EnterTravelDetails {

    private static final Target CALENDAR_1 = Target.the("calendario partida").located(By.xpath("(//input[contains(@class,'WhiteDatePicker__inputElement')])[1]"));
    private static final Target CALENDAR_2 = Target.the("calendario regreso").located(By.xpath("(//input[contains(@class,'WhiteDatePicker__inputElement')])[2]"));
    private static final Target BTN_NEXT = Target.the("siguiente mes").located(By.xpath("//button[contains(@aria-label,'Next') or contains(@class,'next')]"));
    private static final Target BTN_OK = Target.the("botón OK").located(By.xpath("//button[text()='Ok']"));

    public static Performable withDates(String departureDate, String returnDate, int adults, int children) {
        return Task.where("{0} ingresa los datos del viaje",
                actor -> {
                    selectDate(actor, CALENDAR_1, departureDate);
                    selectDate(actor, CALENDAR_2, returnDate);
                    selectAdults(actor, adults);
                    selectChildren(actor, children);
                }
        );
    }

    private static void selectDate(net.serenitybdd.screenplay.Actor actor, Target calendar, String date) {
        String[] parts = date.split("-");
        int targetMonth = Integer.parseInt(parts[1]);
        int targetDay = Integer.parseInt(parts[2]);
        int targetYear = Integer.parseInt(parts[0]);

        actor.attemptsTo(
                WaitUntil.the(calendar, isVisible()).forNoMoreThan(10).seconds(),
                JavaScriptClick.on(calendar)
        );
        sleep(500);

        while (true) {
            String currentMonthYear = getCurrentMonthYear(actor);
            if (currentMonthYear.equalsIgnoreCase(getMonthName(targetMonth) + " " + targetYear)) {
                break;
            }
            actor.attemptsTo(JavaScriptClick.on(BTN_NEXT));
            sleep(500);
        }

        sleep(500);
        selectDay(actor, targetDay);

        sleep(500);
        actor.attemptsTo(JavaScriptClick.on(BTN_OK));
        sleep(500);
    }

    private static void selectDay(net.serenitybdd.screenplay.Actor actor, int targetDay) {
        JavascriptExecutor js = (JavascriptExecutor) BrowseTheWeb.as(actor).getDriver();
        boolean dayFound = false;

        String[] selectors = {
                "//button[text()='" + targetDay + "']",
                "//span[text()='" + targetDay + "']",
                "//div[text()='" + targetDay + "']",
                "//*[text()='" + targetDay + "' and not(contains(@class,'header'))]"
        };

        for (String selector : selectors) {
            try {
                WebElement dayElement = BrowseTheWeb.as(actor).find(By.xpath(selector));
                if (dayElement != null && dayElement.isDisplayed()) {
                    js.executeScript("arguments[0].click();", dayElement);
                    dayFound = true;
                    break;
                }
            } catch (Exception e) {
            }
        }

        if (!dayFound) {
            System.out.println("No se encontró el día " + targetDay);
        }
    }

    private static String getCurrentMonthYear(net.serenitybdd.screenplay.Actor actor) {
        try {
            WebElement span = BrowseTheWeb.as(actor).find(By.xpath("//span[contains(@class,'theme__title')]"));
            return span.getText().trim();
        } catch (Exception e) {
            return "June 2026";
        }
    }

    private static String getMonthName(int month) {
        String[] months = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};
        return months[month - 1];
    }

    private static void selectAdults(net.serenitybdd.screenplay.Actor actor, int value) {
        try {
            Target adultsInput = Target.the("input Adults").located(By.xpath("//input[@value='Adults (18+)']"));

            actor.attemptsTo(
                    WaitUntil.the(adultsInput, isVisible()).forNoMoreThan(10).seconds(),
                    JavaScriptClick.on(adultsInput)
            );
            sleep(800);

            Target option = Target.the("opción adults").located(By.xpath("//li[text()='" + value + "']"));
            actor.attemptsTo(
                    WaitUntil.the(option, isVisible()).forNoMoreThan(10).seconds(),
                    JavaScriptClick.on(option)
            );
            sleep(300);

        } catch (Exception e) {
        }
    }

    private static void selectChildren(net.serenitybdd.screenplay.Actor actor, int value) {
        try {
            Target childrenInput = Target.the("input Children").located(By.xpath("//input[@value='Children (0-7)']"));

            actor.attemptsTo(
                    WaitUntil.the(childrenInput, isVisible()).forNoMoreThan(10).seconds(),
                    JavaScriptClick.on(childrenInput)
            );
            sleep(800);

            String optionXPath = "//*[@id='app']/div/section[1]/div[3]/div/div[4]/ul/li[" + (value + 1) + "]";

            Target option = Target.the("opción children " + value).located(By.xpath(optionXPath));
            actor.attemptsTo(
                    WaitUntil.the(option, isVisible()).forNoMoreThan(10).seconds(),
                    JavaScriptClick.on(option)
            );
            sleep(300);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}