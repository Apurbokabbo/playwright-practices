package Testcases;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.testng.annotations.Test;

import java.util.regex.Pattern;

public class textAssertion {

    @Test
    void textAssertion() {
        // This method is a placeholder for text assertion tests.
        // You can implement your text assertion logic here.
        Playwright playwight = Playwright.create();
        Browser browser = playwight.chromium().launch(
                new com.microsoft.playwright.BrowserType.LaunchOptions().setHeadless(false)
        );
        Page page = browser.newPage();

        page.navigate("https://freelance-learn-automation.vercel.app/login");

        page.locator(".submit-btn").click();

        String message = page.locator(".errorMessage").textContent();
        String message2 = page.locator(".errorMessage").innerText();
        String message3 = (String) page.evaluate("document.getElementsByClassName('errorMessage')[0].textContent");

        System.out.println("Message using textContent: " + message);
        System.out.println("Message using innerText: " + message2);
        System.out.println("Message using evaluate: " + message3);

        PlaywrightAssertions.assertThat(page.locator(".errorMessage")).hasText("Email and Password is required");
        PlaywrightAssertions.assertThat(page.locator(".errorMessage")).containsText("Email and Password is required");
        PlaywrightAssertions.assertThat(page.locator(".errorMessage")).containsText(Pattern.compile("required"));

        page.close();
        browser.close();
    }


    @Test
    public void textAssertion2() {
        // This method is a placeholder for additional text assertion tests.
        // You can implement your text assertion logic here.
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new com.microsoft.playwright.BrowserType.LaunchOptions().setHeadless(false)
        );
        Page page = browser.newPage();

        page.navigate("https://freelance-learn-automation.vercel.app/login");
        page.getByPlaceholder("Email").fill("admin@gmail.com");
        page.locator(".submit-btn").click();

        String message = page.locator(".errorMessage").textContent();
        System.out.println("Message: " + message);

        PlaywrightAssertions.assertThat(page.locator(".errorMessage")).hasText("Password is required");

        page.close();
        browser.close();
    }





}
