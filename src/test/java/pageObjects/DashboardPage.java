package pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private SelenideElement dashBoardHeader = $("[data-test-id=dashboard]");
    public DashboardPage() {
        dashBoardHeader.shouldBe(Condition.visible);
    }

}
