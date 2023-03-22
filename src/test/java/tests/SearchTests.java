package tests;

import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.qameta.allure.Allure.step;
import static java.lang.String.format;
import static org.openqa.selenium.By.id;

public class SearchTests extends TestBase {
    @DisplayName("Checking search of java")
    @Test
    @Tag("android")
    void successfulSearchTest() {
        step("Type search", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("java");
        });
        step("Verify content found", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
    }
    @Tag("ios")
    @DisplayName("Проверка на вывод текста")
    @Test
    void outputTextTest() {

        String text = "Hello";

        step("Click text button", () ->
                $(id("Text Button")).click());

        step("Check initial state for output text", () ->
                $(id("Text Output")).shouldHave(text("Waiting for text input")));

        step(format("Set value %s in input and press enter", text), () -> {
            $(id("Text Input")).click();
            $(id("Text Input")).sendKeys(text);
            $(id("Text Input")).pressEnter();
        });

        step("Verify output text", () ->
                $(id("Text Output")).shouldHave(text(text)));
    }

    @DisplayName("Checking text in due order")
    @Test
    @Tag("android")
    void CheckButtonLogIn() {

        step("Click on the NavBar Menu button", () -> {
            $(id("org.wikipedia.alpha:id/menu_overflow_button")).click();
        });
        step("Check button log in", () -> {
            $(id("org.wikipedia.alpha:id/explore_overflow_account_name")).shouldHave(text("Log in to Wikipedia"));
            $(id("org.wikipedia.alpha:id/explore_overflow_account_name")).click();
        });

        step("Check text on button at login form", () -> {
            $(id("org.wikipedia.alpha:id/login_button")).shouldHave(text("Log in"));
        });
    }
}
