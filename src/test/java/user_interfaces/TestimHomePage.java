package user_interfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class TestimHomePage {
    // Calendarios - usando clase común
    public static final Target DEPARTURE_DATE = Target.the("fecha de partida").located(By.xpath("(//input[contains(@class,'WhiteDatePicker__inputElement')])[1]"));
    public static final Target RETURN_DATE = Target.the("fecha de regreso").located(By.xpath("(//input[contains(@class,'WhiteDatePicker__inputElement')])[2]"));

    // Campos numéricos - buscando por tipo y etiqueta cercana
    public static final Target ADULTS = Target.the("adultos").located(By.xpath("//div[contains(text(),'Adults')]/following::input[1] | //input[contains(@placeholder,'Adult')]"));
    public static final Target CHILDREN = Target.the("niños").located(By.xpath("//div[contains(text(),'Children')]/following::input[1] | //input[contains(@placeholder,'Child')]"));

    // Botón principal
    public static final Target SELECT_DESTINATION_BUTTON = Target.the("botón Select Destination").located(By.xpath("//button[contains(text(),'Select Destination')]"));
}