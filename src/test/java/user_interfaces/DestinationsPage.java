package user_interfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class DestinationsPage {
    public static final Target DROPDOWN_OPTION = Target.the("opción {0} del dropdown").locatedBy("//li[contains(text(),'{0}')]");
    public static final Target LOAD_MORE_BUTTON = Target.the("botón Load More").located(By.xpath("//button[contains(text(),'Load More')]"));
    public static final Target PRICE_FILTER = Target.the("control deslizante de precio").located(By.cssSelector(".price-slider .slider"));
    public static final Target COLOR_DROPDOWN = Target.the("color dropdown").located(By.xpath("//input[@value='Red']"));
    public static final Target PRICE_SLIDER_BAR = Target.the("barra de progreso").located(By.xpath("//div[contains(@class,'PurpleSlider__progress')]"));
    public static final Target PRICE_INPUT = Target.the("input de precio").located(By.xpath("//input[@name='name' and @value='$100']"));
    public static final Target PRICE_MAX_INPUT = Target.the("input precio máximo").located(By.xpath("//input[@value='1800']"));
    public static final Target DESTINATION_DROPDOWN = Target.the("dropdown destino").located(By.xpath("//input[@value='Shenji']"));
    public static final Target BOOK_BUTTON = Target.the("botón Book").located(By.xpath("//button[contains(text(),'Book')]"));
    public static final Target DESTINATION_CARD = Target.the("tarjeta de destino {0}").locatedBy("//h5[contains(@class,'theme__title') and contains(text(),'{0}')]/ancestor::div[contains(@class,'theme__card')]");
    public static final Target PRICE_TAG = Target.the("precio del destino").located(By.xpath(".//span[contains(@class,'price-tag')]"));

}