package user_interfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CheckoutForm {

    public static final Target NAME_FIELD = Target.the("campo nombre").located(By.xpath("//input[@type='text' and @maxlength='30']"));
    public static final Target EMAIL_FIELD = Target.the("campo email").located(By.xpath("//input[@type='email']"));
    public static final Target SSN_FIELD = Target.the("campo SSN").located(By.xpath("//input[@type='email']/following::input[@type='text'][1]"));
    public static final Target PHONE_FIELD = Target.the("campo teléfono").located(By.xpath("//input[@type='tel']"));
    public static final Target FILE_UPLOAD = Target.the("cargar archivo").located(By.xpath("//input[@type='file']"));
    public static final Target PROMO_CODE_FIELD = Target.the("código promocional").located(By.xpath("//input[@name='promo']"));
    public static final Target APPLY_BUTTON = Target.the("botón Apply").located(By.xpath("//button[contains(text(),'Apply')]"));
    public static final Target TERMS_CHECKBOX = Target.the("términos y condiciones").located(By.xpath("//div[@data-react-toolbox='check']"));

    public static final String CONFIRMATION_MESSAGE = "//h1[contains(@class,'Climate__headline-1')]";

    public static final Target UNIT_PRICE = Target.the("precio unitario").located(By.xpath("//div[contains(@class,'flexboxgrid__col-xs-5')]"));
    public static final Target TRAVELERS = Target.the("número de viajeros").located(By.xpath("//*[contains(text(),'travelers')]"));
    public static final Target TOTAL_PRICE = Target.the("precio total").located(By.xpath("//strong[contains(@class,'OrderSummary__headline-1')]"));
}