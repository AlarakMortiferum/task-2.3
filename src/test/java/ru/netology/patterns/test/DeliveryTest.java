package ru.netology.patterns.test;

import org.junit.jupiter.api.Test;
import ru.netology.patterns.data.DataGenerator;
import ru.netology.patterns.data.UserInfo;
import ru.netology.patterns.page.DeliveryOrderPage;

import static com.codeborne.selenide.Selenide.open;

public class DeliveryTest {

    @Test
    void shouldReplanMeeting() {
        open("http://localhost:9999");
        DeliveryOrderPage page = new DeliveryOrderPage();

        UserInfo user = DataGenerator.generateUser("ru");
        String firstDate = DataGenerator.generateDate(4);
        String secondDate = DataGenerator.generateDate(10);

        page.fillForm(user, firstDate);
        page.checkSuccessNotificationVisible(firstDate);

        page.clearForm();

        page.fillForm(user, secondDate);
        page.confirmReplan();
        page.checkSuccessNotificationVisible(secondDate);
    }
}
