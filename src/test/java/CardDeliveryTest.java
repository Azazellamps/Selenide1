import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.Keys.BACK_SPACE;

public class CardDeliveryTest {
    String generateDate() {
        return LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }


    @Test
    void shouldRegister() {
        String data = generateDate();
        open("http://localhost:9999");
        $("[data-test-id= 'city'] input").setValue("Москва");
        $("[data-test-id = 'date'] input").sendKeys(Keys.chord(Keys.CONTROL, "a"), BACK_SPACE);
        $("[data-test-id= 'date'] input").setValue(generateDate()).sendKeys(Keys.chord(Keys.ESCAPE));
        $("[data-test-id='name'] input").setValue("Метелева Светлана");
        $("[data-test-id= 'phone'] input").setValue("+79816844874");
        $("[data-test-id= 'agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id= 'notification']").shouldBe(appear, Duration.ofSeconds(15)).shouldHave(Condition.text(data), Condition.visible);
    }
}
