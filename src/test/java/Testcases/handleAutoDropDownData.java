package Testcases;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Locale;

import static org.testng.Assert.assertTrue;

public class handleAutoDropDownData {

    @Test
    public void handleAutoDropDownData() throws InterruptedException {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500)
        );
        Page page = browser.newPage();

        page.navigate("https://www.startech.com.bd/");

        // Step 1: Click and type in the search box
        Locator searchBox = page.getByPlaceholder("Search", new Page.GetByPlaceholderOptions().setExact(true));
        searchBox.click();
        searchBox.fill("keyboard");
        searchBox.press("ArrowDown");

        // Step 2: Wait a bit to let suggestions load
        Thread.sleep(5000); // Not ideal in real tests – better to use page.waitForSelector()

        // Step 3: Capture all suggestions
        Locator suggestions = page.locator("xpath=/html/body/header/div/div/div[2]/div/div/div[1]/div");
        int count = suggestions.count();
        System.out.println("Total number of suggestions: " + count);

        // Step 4: Loop through suggestions
        for (int i = 0; i < count; i++) {
            Locator suggestionText = suggestions.nth(i).locator("a > div:nth-child(2)");
            String text = suggestionText.textContent().toLowerCase(Locale.ROOT).trim();

            System.out.println("Suggestion " + i + ": " + text);

            if (text.contains("havit kb376 usb multimedia keyboard")) {
                suggestions.nth(i).click(); // click the full suggestion div
                break;
            }
        }

        // Clean up
        page.close();
        browser.close();
        playwright.close();
    }

}

