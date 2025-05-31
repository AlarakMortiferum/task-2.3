package ru.netology.patterns.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.patterns.data.DataGenerator.UserInfo;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.*;

public class DeliveryOrderPage {

    private SelenideElement cityField = $("[data-test-id=city] input");
    private SelenideElement dateField = $("[data-test-id=date] input");
    private SelenideElement nameField = $("[data-test-id=name] input");
    private SelenideElement phoneField = $("[data-test-id=phone] input");
    private SelenideElement agreeCheckbox = $("[data-test-id=agreement]");
    private SelenideElement submitButton = $("[type=button]");
    private SelenideElement successNotification = $("[data-test-id=success-notification]");
    private SelenideElement replanNotification = $("[data-test-id=replan-notification]");
    private SelenideElement replanButton = $("[data-test-id=replan-notification] button.button");

    public void fillForm(UserInfo user, String date) {
        cityField.setValue(user.getCity());
        dateField.doubleClick().sendKeys(Keys.BACK_SPACE);
        dateField.setValue(date);
        nameField.setValue(user.getName());
        phoneField.setValue(user.getPhone());
        agreeCheckbox.click();
        submitButton.click();
    }

    public void checkSuccessNotificationVisible(String expectedDate) {
        successNotification
                .shouldBe(visible, Duration.ofSeconds(20))
                .shouldHave(cssClass("notification_visible"))
                .shouldHave(text("Встреча успешно забронирована на " + expectedDate));
    }

    public void confirmReplan() {
        replanNotification.shouldBe(visible);
        replanButton.shouldBe(visible).click();
    }
}
