import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.ownText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class DeliveryCardTest {

    @Test
    void shouldAddDate() {
        Configuration.holdBrowserOpen=true;
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Рязань");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.DELETE);
        String date = LocalDate.now().plusDays(5).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").setValue(date);
        $("[name='name']").setValue("Дмитрий Дмитров");
        $("[name='phone']").setValue("+79777770707");
        $("[data-test-id='agreement']").click();
        $(".button__text").click();
        $(".notification__content").should(ownText("Встреча успешно забронирована на " + date)).shouldBe((visible), Duration.ofSeconds(15));


    }
}