package data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

public class DataHelper {
    private static Faker faker = new Faker(new Locale("en"));

    private DataHelper() {
    }

    public static AuthInfo getValidAuthInfoWithTestData() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getAuthInfoWithValidLoginDataAndWrongPassword() {
        return new AuthInfo("vasya",faker.internet().password() );
    }

    public static String getRandomLogin() {
        return faker.name().username();
    }

    public static String getRandomPassword() {
        return faker.internet().password();
    }

    public static AuthInfo getRandomUser() {
        return new AuthInfo(getRandomLogin(), getRandomPassword());
    }

    public static VerifyCode getRandomVerifyCode() {
        return new VerifyCode(faker.numerify("#####"));
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;

    }

    @Value
    public static class VerifyCode {
        String code;
    }

}