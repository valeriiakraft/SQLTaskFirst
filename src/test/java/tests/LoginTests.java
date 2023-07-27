package tests;

import data.DataHelper;
import data.SQLHelper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pageObjects.DashboardPage;
import pageObjects.LoginPage;
import pageObjects.VerifyPage;

import static com.codeborne.selenide.Selenide.open;
import static data.SQLHelper.cleanDataBase;

public class LoginTests {
    static LoginPage loginPage;
    static VerifyPage verifyPage;
    static DashboardPage dashboardPage;


    @BeforeEach
    void setup() {
        loginPage = open("http://localhost:9999/", LoginPage.class);
    }

   @AfterAll
    static void teardown(){
       cleanDataBase();
   }

    @Test
    public void positiveLogin(){
        var validUser = DataHelper.getValidAuthInfoWithTestData();
        verifyPage=loginPage.makeLogin(validUser);
        DataHelper.VerifyCode validCode = SQLHelper.getVerifyCode();
        dashboardPage=verifyPage.validVerify(validCode);
    }

    @Test
    public void InvalidLoginWithRandomUser(){
        var randomUser = DataHelper.getRandomUser();
        loginPage.makeIncompleteLogin(randomUser);
        loginPage.getErrorMessage("Неверно указан логин или пароль");


    }

    @Test
    public void LoginWithWrongVerifyCode(){
        var validUser = DataHelper.getValidAuthInfoWithTestData();
        verifyPage=loginPage.makeLogin(validUser);
        var wrongCode = DataHelper.getRandomVerifyCode();
        verifyPage.makeVerify(wrongCode);
        verifyPage.getErrorMessage("Неверно указан код");

    }



}
