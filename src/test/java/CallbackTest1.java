
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.DevTools;

import javax.swing.text.Style;

import java.awt.*;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CallbackTest1 {

    @Test
    void shouldTest() throws InterruptedException {
        open("http://localhost:9999");
//        SelenideElement form = $("[data-test-id=callback-form]");
//        $(By.xpath("//*[@id=\"root\"]/div/form/div[3]//input")).setValue("Vasya");
//        form.$x("//*[@id=\"root\"]/div/form/div[3]//input")
        $("[data-test-id=name] input").setValue("Василий");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement]").click();
//        Thread.sleep(6000);
        $("button").click();//
        Thread.sleep(1000);
        $("[data-test-id=\"order-success\"]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }


    @Test
    void shouldTurnRedInPhone() throws InterruptedException {
        open("http://localhost:9999");
//        SelenideElement form = $("[data-test-id=callback-form]");
//        $(By.xpath("//*[@id=\"root\"]/div/form/div[3]//input")).setValue("Vasya");
//        form.$x("//*[@id=\"root\"]/div/form/div[3]//input")
        $("[data-test-id=name] input").setValue("Василий");
        $("[data-test-id=phone] input").setValue("+79270000");
        $("[data-test-id=agreement]").click();
//        Thread.sleep(6000);
        $("button").click();//
        Thread.sleep(1000);
        $("[data-test-id=phone] span.input__sub").shouldHave(exactText("Телефон указан неверно." +
                " Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldTurnRedInName() throws InterruptedException {
        open("http://localhost:9999");
//        SelenideElement form = $("[data-test-id=callback-form]");
//        $(By.xpath("//*[@id=\"root\"]/div/form/div[3]//input")).setValue("Vasya");
//        form.$x("//*[@id=\"root\"]/div/form/div[3]//input")
        $("[data-test-id=name] input").setValue("123");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement]").click();
//        Thread.sleep(6000);
        $("button").click();//
        Thread.sleep(1000);
        $("span.input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно." +
                " Допустимы только русские буквы, пробелы и дефисы."));
//        $("[data-test-id=agreement] span.checkbox__text").shouldBe(Color.getColor("#ff5c5c");
    }
}
