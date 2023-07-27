package pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    //data-test-id="login" - поле логина
    //data-test-id=password - поле пароля
    //data-test-id="action-login" - кнопка "Продолжить"
    //data-test-id="error-notification" - окно ошибки

    private SelenideElement loginField = $("[data-test-id=login] input");
    private SelenideElement passwordField = $("[data-test-id=password] input");
    private SelenideElement loginButton = $("[data-test-id=action-login]");
    private SelenideElement errorMessage = $("[data-test-id=error-notification]");

    public VerifyPage makeLogin(DataHelper.AuthInfo authInfo) {
        loginField.setValue(authInfo.getLogin());
        passwordField.setValue(authInfo.getPassword());
        loginButton.click();
        return new VerifyPage();
    }

    public void makeIncompleteLogin(DataHelper.AuthInfo authInfo) {
        loginField.setValue(authInfo.getLogin());
        passwordField.setValue(authInfo.getPassword());
        loginButton.click();
    }


    public void getErrorMessage(String expectedText) {
        errorMessage.shouldHave(Condition.text(expectedText), Duration.ofSeconds(15)).shouldBe(Condition.visible);
    }

    public void makeFieldsEmpty() {
        loginField.sendKeys(Keys.chord(Keys.SHIFT,Keys.HOME),Keys.DELETE);
        passwordField.sendKeys(Keys.chord(Keys.SHIFT,Keys.HOME),Keys.DELETE);
    }


}