package Testcases;

import com.microsoft.playwright.*;
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

    @Test
    public void createCourseUsingAlertPrompt() throws InterruptedException {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500)
            );
            Page page = browser.newPage();
            page.navigate("https://freelance-learn-automation.vercel.app/login");

            // Login
            page.getByPlaceholder("Email").fill("admin@email.com");
            page.getByPlaceholder("Password").fill("admin@123");
            page.locator("//button[normalize-space()='Sign in']").click();

            // Navigate to Manage Categories
            page.locator("//span[normalize-space()='Manage']").click();
            page.locator("//a[normalize-space()='Manage Categories']").click();

            // Handle prompt before triggering it
            page.onDialog(dialog -> {
                System.out.println("Prompt text: " + dialog.message());
                dialog.accept("Java Automation Course");
            });

            // Trigger the prompt
            Locator addCategoryBtn = page.locator("//button[normalize-space()='Add New Category']");
            addCategoryBtn.click();


            // Optional: assert success message appears (UI feedback)
            page.waitForTimeout(2000); // Give time to reflect changes

            // Clean up
            page.close();
            browser.close();
        }
    }

}
