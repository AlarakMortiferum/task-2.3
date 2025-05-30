package ru.netology.patterns.test;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import ru.netology.patterns.data.DataGenerator;
import ru.netology.patterns.data.DataGenerator.UserInfo;
import ru.netology.patterns.page.DeliveryOrderPage;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Condition.cssClass;

public class DeliveryTest {

    @Test
    void shouldReplanMeeting() {
        open("http://localhost:9999");
        DeliveryOrderPage page = new DeliveryOrderPage();

        UserInfo user = DataGenerator.generateUser("ru");
        String firstDate = DataGenerator.generateDate(4);
        String secondDate = DataGenerator.generateDate(10);

        page.fillForm(user, firstDate);
        page.checkSuccessNotificationVisible();
        $("[data-test-id=success-notification]").shouldBe(visible).shouldHave(cssClass("notification_visible"));

        page.fillForm(user, secondDate);
        page.confirmReplan();
        page.checkSuccessNotificationVisible();
        $("[data-test-id=success-notification]").shouldBe(visible).shouldHave(cssClass("notification_visible"));
    }
}

