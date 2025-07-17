package Testcases;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;

import java.nio.file.Paths;
import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class install_run_playwright {


    @Test
    public void pageTittle() throws InterruptedException {
            Playwright playwright = Playwright.create();
            Browser browser = playwright.chromium().launch();
            new BrowserType.LaunchOptions().setHeadless(false);
            Page page = browser.newPage();
            page.navigate("https://www.saucedemo.com/v1/");
            System.out.println("Page tittle is : "+page.title());
            Thread.sleep(1000); // Wait for 5 seconds to see the page

    }

    @Test
    public void takeScreenShot() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.webkit().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
        Page page = browser.newPage();
        page.navigate("https://www.saucedemo.com/v1/");
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("example.png")));
        browser.close();
        playwright.close();
    }

    @Test
    public void assertion() {
            Playwright playwright = Playwright.create();
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();
            page.navigate("https://playwright.dev");

            // Expect a title "to contain" a substring.
            assertThat(page).hasTitle(Pattern.compile("Playwright"));

            // create a locator
            Locator getStarted = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Get Started"));

            // Expect an attribute "to be strictly equal" to the value.
            assertThat(getStarted).hasAttribute("href", "/docs/intro");

            // Click the get started link.
            getStarted.click();

            // Expects page to have a heading with the name of Installation.
            assertThat(page.getByRole(AriaRole.HEADING,
                    new Page.GetByRoleOptions().setName("Installation"))).isVisible();

        browser.close();
        playwright.close();
    }

    @Test
    public void loginmethod(){
         Playwright playwright = Playwright.create();
         Browser browser =playwright.chromium().launch(
                 new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500)
         );
         Page page = browser.newPage();
         page.navigate("https://freelance-learn-automation.vercel.app/login");
         PlaywrightAssertions.assertThat(page).hasTitle("Learn Automation Courses");
         page.locator("#email1").fill("admin@email.com");
         page.locator("xpath =//input[@id='password1']").fill("admin@123");
         page.getByText("Sign in").nth(1).click();
         PlaywrightAssertions.assertThat(page.locator(".welcomeMessage")).containsText("Welcome");
         page.waitForTimeout(3000);
         //sign out
         page.getByAltText("menu").click();
         page.locator("xpath=//button[normalize-space()='Sign out']").click();
         PlaywrightAssertions.assertThat(page).hasURL(Pattern.compile("login"));

         page.close();
         browser.close();
    }






}
