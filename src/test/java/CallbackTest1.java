
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.DevTools;

import javax.swing.text.Style;

import java.awt.*;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CallbackTest1 {

    @BeforeEach
    public void be(){
        open("http://localhost:9999");
    }

    @Test
    void shouldTest() {
        $("[data-test-id=name] input").setValue("Василий");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement]").click();
        $("button").click();//
        $("[data-test-id=\"order-success\"]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }


    @Test
    void shouldTurnRedInPhone() {
        $("[data-test-id=name] input").setValue("Василий");
        $("[data-test-id=phone] input").setValue("+79270000");
        $("[data-test-id=agreement]").click();
        $("button").click();//
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно." +
                " Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void emptyShouldTurnRedInPhone() {
        $("[data-test-id=name] input").setValue("Василий");
//        $("[data-test-id=phone] input").setValue("+79270000");
        $("[data-test-id=agreement]").click();
        $("button").click();//
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void emptyShouldTurnRedInName() {
//        $("[data-test-id=name] input").setValue("Василий");
        $("[data-test-id=phone] input").setValue("+79270000");
        $("[data-test-id=agreement]").click();
        $("button").click();//
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldTurnRedInName() {
        $("[data-test-id=name] input").setValue("123");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement]").click();
        $("button").click();//
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно." +
                " Допустимы только русские буквы, пробелы и дефисы."));
    }
    @Test
    void shouldTurnRedInAgreement() {
        $("[data-test-id=name] input").setValue("Вася");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("button").click();//
        $("[data-test-id=agreement].input_invalid").shouldBe(visible);
    }
}
