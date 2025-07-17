package Testcases;

import com.github.javafaker.Faker;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.testng.annotations.Test;

public class registration_testcases {

    @Test
    public void registrationWithValidData() {
        Faker faker = new Faker();
        String name = faker.name().fullName();

        Playwright playwright = Playwright.create();
        Browser browser =playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500)
        );
        Page page = browser.newPage();
        page.navigate("https://freelance-learn-automation.vercel.app/login");
        //https://freelance-learn-automation.vercel.app/signup

        page.getByText("New User? Signup").click();
        PlaywrightAssertions.assertThat(page.getByText("Sign up").last()).isDisabled();

        page.locator("#name").fill(name);
        page.getByPlaceholder("Email").fill(name+System.currentTimeMillis()+"@gmail.com");
        page.getByPlaceholder("Password").fill("test@123");

        //checked
        page.getByText("Java").nth(0).click();
        PlaywrightAssertions.assertThat(page.locator("xpath=//label[text()='Java']//preceding::input[1]")).isChecked();

        //radio button
        page.locator("xpath =//input[@value='Female']").click();
        PlaywrightAssertions.assertThat(page.locator("xpath =//input[@value='Female']")).isChecked();

        //dropdown
         page.locator("#state").selectOption("Maharashtra");

         String hobbies[] = {"Playing", "Reading", "Swimming"};
         page.locator("#hobbies").selectOption(hobbies);



        PlaywrightAssertions.assertThat(page.getByText("Sign up").last()).isEnabled();
        page.getByText("Sign up").last().click();
        page.waitForTimeout(3000);


    }

}
