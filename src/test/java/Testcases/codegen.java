package Testcases;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class codegen {

    @Test
    public void fullLoginFlowVerify() {

        //ctrl + shift + o

        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();
            page.navigate("https://freelance-learn-automation.vercel.app/");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add to Cart right arrow")).first().click();
            page.getByRole(AriaRole.DIALOG).getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Log in")).click();
            page.getByPlaceholder("Enter Email").click();
            page.getByPlaceholder("Enter Email").fill("admin@email.com");
            page.getByPlaceholder("Enter Password").click();
            page.getByPlaceholder("Enter Password").fill("admin@123");
            assertThat(page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign in"))).isVisible();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign in")).click();
            assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Welcome Admin Manager to"))).isVisible();
            page.getByRole(AriaRole.IMG, new Page.GetByRoleOptions().setName("menu")).click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign out")).click();
            assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Sign In"))).isVisible();
        }
    }
//ctrl + alt + l

    @Test
    public void loginAuthSave() {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();
            page.navigate("https://freelance-learn-automation.vercel.app/");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add to Cart right arrow")).nth(2).click();
            page.getByRole(AriaRole.DIALOG).getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Log in")).click();
            page.getByPlaceholder("Enter Email").click();
            page.getByPlaceholder("Enter Email").fill("admin@email.com");
            page.getByPlaceholder("Enter Password").click();
            page.getByPlaceholder("Enter Password").fill("admin@123");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign in")).click();
            page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Welcome Admin Manager to")).click();

            context.storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get("auth.json")));
        }
    }

    @Test
    public void addToCartUsingAuth (){
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false));
            BrowserContext context = browser.newContext(new Browser.NewContextOptions()
                    .setStorageStatePath(Paths.get("auth.json")));
            Page page = context.newPage();
            page.navigate("https://freelance-learn-automation.vercel.app/");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add to Cart right arrow")).nth(1).click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add to Cart right arrow")).nth(1).click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Cart 2")).click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Enroll Now")).click();
            assertThat(page.getByRole(AriaRole.DIALOG)).containsText("₹11499");
            page.getByRole(AriaRole.DIALOG).getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Enroll Now")).click();
            page.getByText("Address is empty").click();
        }
    }
/* You can use below commands for codegen

mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="codegen demo.playwright.dev/todomvc"

mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="codegen https://freelance-learn-automation.vercel.app/"

mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="codegen --viewport-size=800,600"

mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args='codegen --device="iPhone 13"'

mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args='codegen --timezone="Europe/Rome" --geolocation="41.890221,12.492348" --lang="it-IT" bing.com/maps'


mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="codegen https://freelance-learn-automation.vercel.app/ --save-storage=login.json"

mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="codegen --load-storage=login.json https://freelance-learn-automation.vercel.app/"
*/
}


