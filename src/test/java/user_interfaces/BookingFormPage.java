package user_interfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class BookingFormPage {
    public static final Target NAME = Target.the("campo nombre").located(By.cssSelector("input[name='name']"));
    public static final Target EMAIL = Target.the("campo email").located(By.cssSelector("input[name='email']"));
    public static final Target SSN = Target.the("campo social security number").located(By.cssSelector("input[name='ssn']"));
    public static final Target PHONE = Target.the("campo teléfono").located(By.cssSelector("input[name='phone']"));
    public static final Target FILE_UPLOAD = Target.the("cargar archivo").located(By.cssSelector("input[type='file']"));
    public static final Target PROMO_CODE = Target.the("código promocional").located(By.cssSelector("input[name='promo']"));
    public static final Target TERMS_CHECKBOX = Target.the("términos y condiciones").located(By.cssSelector("input[name='terms']"));
    public static final Target PAY_NOW_BUTTON = Target.the("botón Pay Now").located(By.xpath("//button[contains(text(),'Pay Now')]"));
    public static final Target DESTINATION_CONFIRMATION = Target.the("confirmación de destino").located(By.xpath("//*[contains(text(),'Temperatures')]"));
}
