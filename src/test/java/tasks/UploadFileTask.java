package tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import interactions.UploadFile;
import user_interfaces.CheckoutForm;

public class UploadFileTask {

    public static Performable fromPath(String filePath) {
        return Task.where("{0} carga el archivo desde " + filePath,
                actor -> actor.attemptsTo(
                        UploadFile.to(CheckoutForm.FILE_UPLOAD, filePath)
                )
        );
    }

    public static Performable defaultFile() {
        return fromPath("src/test/resources/data/Upload.txt");
    }
}