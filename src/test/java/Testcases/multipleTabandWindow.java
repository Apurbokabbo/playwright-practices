package Testcases;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.Test;

public class multipleTabandWindow {


    // This class is a placeholder for tests involving multiple tabs and windows.
    // You can implement your test logic here using Playwright's API for handling multiple tabs and windows.

    // Example methods could include:
    // - Opening a new tab
    // - Switching between tabs
    // - Handling pop-up windows
    // - Verifying content in different tabs/windows

    //https://playwright.dev/docs/api/class-browsercontext

    @Test
    public void manageMultipleTabs() throws  InterruptedException {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new com.microsoft.playwright.BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500)
        );
        BrowserContext context = browser.newContext();
        Page page = context.newPage();
        page.navigate("https://www.apurbokabbo.com/");

        Page newPage = context.waitForPage(()->
        {
            page.locator("//div[@id='colorlib-page']//li[1]//a[1]").click();
        });

//        newPage.locator("xpath = //*[local-name()='path' and @fill='currentColor']/parent::*[local-name()='svg']").click();
        page.bringToFront();
        Thread.sleep(2000);
        newPage.bringToFront();
        Thread.sleep(2000);


    }


}
