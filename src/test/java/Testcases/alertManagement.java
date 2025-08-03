package Testcases;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.Test;

public class alertManagement {

    //https://playwright.dev/java/docs/dialogs

    @Test
    public void jsAlert() {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(
                    new com.microsoft.playwright.BrowserType.LaunchOptions().setHeadless(false)
            );
            Page page = browser.newPage();
            page.navigate("https://the-internet.herokuapp.com/javascript_alerts");

            // Click the button to trigger the alert
            page.onDialog(dialog -> {
                // Handle the alert dialog
                System.out.println("Alert text: " + dialog.message());
                dialog.accept(); // Accept the alert
            });
            page.click("button:has-text('Click for JS Alert')");

            // Handle the alert


//            System.out.println("Alert text: " + alertText);

            // Close the page and browser
            page.close();
            browser.close();
        }
    }

    @Test
    public void jsConfirmDismiss() {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(
                    new com.microsoft.playwright.BrowserType.LaunchOptions().setHeadless(false)
            );
            Page page = browser.newPage();
            page.navigate("https://the-internet.herokuapp.com/javascript_alerts");

            // Click the button to trigger the confirm dialog
            page.onDialog(dialog -> {
                // Handle the confirm dialog
                System.out.println("Confirm text: " + dialog.message());
                dialog.dismiss(); // Dismiss the confirm dialog
            });
            page.click("button:has-text('Click for JS Confirm')");

            // Close the page and browser
            page.close();
            browser.close();
        }
    }

    @Test
    public void jsConfirmAccept() {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(
                    new com.microsoft.playwright.BrowserType.LaunchOptions().setHeadless(false)
            );
            Page page = browser.newPage();
            page.navigate("https://the-internet.herokuapp.com/javascript_alerts");

            // Click the button to trigger the confirm dialog
            page.onDialog(dialog -> {
                // Handle the confirm dialog
                System.out.println("Confirm text: " + dialog.message());
                dialog.accept(); // Dismiss the confirm dialog
            });
            page.click("button:has-text('Click for JS Confirm')");

            // Close the page and browser
            page.close();
            browser.close();
        }
    }

    @Test
    public void jsPromptAccept() throws InterruptedException{
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(
                    new com.microsoft.playwright.BrowserType.LaunchOptions().setHeadless(false)
            );
            Page page = browser.newPage();
            page.navigate("https://the-internet.herokuapp.com/javascript_alerts");

            // Click the button to trigger the prompt dialog
            page.onDialog(dialog -> {
                // Handle the prompt dialog
                System.out.println("Prompt text: " + dialog.message());
                dialog.accept("Hello, Playwright!"); // Accept the prompt with a value
            });
            Thread.sleep(2000); // Wait for the dialog to be processed
            page.click("button:has-text('Click for JS Prompt')");

            // Close the page and browser
            page.close();
            browser.close();
        }
    }

}
