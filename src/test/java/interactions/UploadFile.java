package interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.WebElement;

import java.nio.file.Path;
import java.nio.file.Paths;

public class UploadFile implements Interaction {
    private final Target fileInput;
    private final String filePath;

    public UploadFile(Target fileInput, String filePath) {
        this.fileInput = fileInput;
        this.filePath = filePath;
    }

    public static UploadFile to(Target fileInput, String filePath) {
        return new UploadFile(fileInput, filePath);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        Path absolutePath = Paths.get(filePath).toAbsolutePath();
        WebElement element = fileInput.resolveFor(actor);
        element.sendKeys(absolutePath.toString());
    }
}