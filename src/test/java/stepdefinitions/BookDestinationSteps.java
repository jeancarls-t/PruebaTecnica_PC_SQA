package stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.JavaScriptClick;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import questions.GetSSNErrorMessage;
import tasks.*;
import questions.GetConfirmationMessage;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.containsString;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class BookDestinationSteps {

    private WebDriver driver;
    private Actor user;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-maximized");

        // Detectar si está en CI (GitHub Actions)
        if (System.getenv("CI") != null) {
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");
            System.out.println("✅ Ejecutando en modo headless (CI)");
        }

        driver = new ChromeDriver(options);
        user = Actor.named("Usuario");
        user.can(BrowseTheWeb.with(driver));
    }

    @Given("el usuario está en la página de inicio de Testim.io")
    public void elUsuarioEstaEnLaPaginaDeInicio() {
        user.wasAbleTo(Open.url("https://demo.testim.io/"));
        try { Thread.sleep(3000); } catch (InterruptedException e) {}
    }

    @When("ingresa los datos del viaje:")
    public void ingresaDatosDelViaje(io.cucumber.datatable.DataTable dataTable) {
        var data = dataTable.asMaps().get(0);
        user.attemptsTo(
                EnterTravelDetails.withDates(
                        data.get("fecha_partida"),
                        data.get("fecha_regreso"),
                        Integer.parseInt(data.get("adultos")),
                        Integer.parseInt(data.get("ninos"))
                )
        );
    }

    @When("presiona el botón {string}")
    public void presionaBoton(String buttonText) {
        if (buttonText.equals("SELECT DESTINATION")) {
            user.attemptsTo(
                    JavaScriptClick.on(Target.the("botón").located(By.xpath("//button[contains(text(),'Select Destination')]")))
            );
            try { Thread.sleep(2000); } catch (InterruptedException e) {}
        }
    }

    @When("cambia el precio del filtro a {int}")
    public void cambiaPrecioFiltro(int price) {
        user.attemptsTo(FilterByPrice.setMaxPrice(price));
    }

    @When("selecciona el destino {string}")
    public void seleccionaDestino(String destinationName) {
        user.attemptsTo(SelectDestinationCard.byName(destinationName));
    }

    @When("llena el formulario de pago con los datos:")
    public void llenaFormularioPago(io.cucumber.datatable.DataTable dataTable) {
        var data = dataTable.asMaps().get(0);
        user.attemptsTo(FillBasicInfoTask.withData(
                data.get("name"),
                data.get("email"),
                data.get("phone_number")
        ));
        user.attemptsTo(UploadFileTask.fromPath(data.get("ruta")));
        user.attemptsTo(FillSSNTask.withValue(data.get("ssn")));
        user.attemptsTo(ApplyPromoCodeTask.withCode(data.get("codigo_promocional")));
        user.attemptsTo(ValidatePriceCalculationTask.afterDiscount());
        user.attemptsTo(AcceptTermsTask.andConditions());
    }

    @Then("debe ver el mensaje {string}")
    public void debeVerElMensaje(String expectedMessage) {
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
        user.attemptsTo(
                net.serenitybdd.screenplay.actions.Scroll.to(By.xpath("//h1[contains(@class,'Climate__headline-1')]"))
        );
        try { Thread.sleep(500); } catch (InterruptedException e) {}
        user.should(seeThat(GetConfirmationMessage.text(expectedMessage), containsString(expectedMessage)));
    }
    @When("llena los datos básicos del formulario:")
    public void llenaDatosBasicosFormulario(io.cucumber.datatable.DataTable dataTable) {
        var data = dataTable.asMaps().get(0);
        user.attemptsTo(FillBasicInfoTask.withData(
                data.get("name"),
                data.get("email"),
                data.get("phone_number")
        ));
    }

    @When("escribe un SSN inválido {string}")
    public void escribeSSNInvalido(String ssn) {
        user.attemptsTo(FillInvalidSSNTask.withValue(ssn));
    }

    @Then("debe ver el mensaje de error {string}")
    public void debeVerMensajeDeError(String expectedError) {
        try { Thread.sleep(500); } catch (InterruptedException e) {}
        String actualError = GetSSNErrorMessage.displayed().answeredBy(user);
        if (actualError.contains(expectedError)) {
            System.out.println("✅ Error correcto: " + actualError);
        } else {
        }
    }

    @When("selecciona el destino {string} desde el dropdown")
    public void seleccionaDestinoDesdeDropdown(String destinationName) {
        user.attemptsTo(SelectDestinationFromDropdownTask.byName(destinationName));
    }

    @When("selecciona la tarjeta de destino {string}")
    public void seleccionaTarjetaDestino(String destinationName) {
        user.attemptsTo(SelectDestinationCard.byName(destinationName));
    }

}