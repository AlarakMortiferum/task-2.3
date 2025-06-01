package ru.netology.patterns.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.patterns.data.UserInfo;
import org.openqa.selenium.Keys;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;

public class DeliveryOrderPage {

    private SelenideElement cityField = $("[data-test-id=city] input");
    private SelenideElement dateField = $("[data-test-id=date] input");
    private SelenideElement nameField = $("[data-test-id=name] input");
    private SelenideElement phoneField = $("[data-test-id=phone] input");
    private SelenideElement agreeCheckbox = $("[data-test-id=agreement]");
    private SelenideElement submitButton = $x("//span[text()='Запланировать']/ancestor::button");
    private SelenideElement successNotification = $(".notification.notification_visible[data-test-id=success-notification]");
    private SelenideElement replanButton = $("[data-test-id=replan-notification] button.button");

    public void fillForm(UserInfo user, String date) {
        cityField.setValue(user.getCity());
        dateField.doubleClick().sendKeys(Keys.BACK_SPACE);
        dateField.setValue(date);
        nameField.setValue(user.getName());
        phoneField.setValue(user.getPhone());
        agreeCheckbox.click();
        $x("//span[text()='Запланировать']/ancestor::button").click();
    }

    public void checkSuccessNotificationVisible(String expectedDate) {
        successNotification
                .shouldBe(visible, Duration.ofSeconds(20))
                .shouldHave(text("Встреча успешно запланирована на " + expectedDate));
    }

    public void clearForm() {
        cityField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
        dateField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
        nameField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
        phoneField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
    }

    public void confirmReplan() {
        replanButton.shouldBe(visible, Duration.ofSeconds(10)).click();
    }
}
