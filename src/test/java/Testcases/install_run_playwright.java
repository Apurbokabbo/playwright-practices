package Testcases;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.Test;

import java.nio.file.Paths;

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
            Browser browser = playwright.webkit().launch();
            new BrowserType.LaunchOptions().setHeadless(false);
            Page page = browser.newPage();
            page.navigate("https://www.saucedemo.com/v1/");
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("example.png")));
        }





}
