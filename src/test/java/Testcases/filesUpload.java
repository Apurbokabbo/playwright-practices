package Testcases;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

public class filesUpload {


  //https://playwright.dev/java/docs/api/class-page
  @Test
  public void singleFileUpload() {
    try (Playwright playwright = Playwright.create()) {
      Browser browser = playwright.chromium().launch(
              new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500)
      );
      Page page = browser.newPage();
      page.setDefaultNavigationTimeout(30000);
      page.navigate("https://the-internet.herokuapp.com/upload");

      // Upload a file
      page.setInputFiles("#file-upload", Paths.get("files/1753706901022.jpg"));
//      page.setInputFiles("#file-upload", Paths.get((System.getProperty("user.dir")+"files/1753706901022.jpg"));
      page.click("#file-submit");

      // Verify the upload
      String uploadedText = page.textContent("#uploaded-files");
      System.out.println("Uploaded file text: " + uploadedText);

      // Clean up
      page.close();
      browser.close();
    }
  }

  @Test
  public void multipleFilesUpload() {
    try (Playwright playwright = Playwright.create()) {
      Browser browser = playwright.chromium().launch(
              new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500)
      );
      Page page = browser.newPage();
      page.setDefaultNavigationTimeout(30000);
      page.navigate("https://the-internet.herokuapp.com/upload");

      // Upload multiple files
      Path[] files = {
              Paths.get("files/1753706901022.jpg"),
              Paths.get("files/1753683034617.jpg")
      };
      page.setInputFiles("#file-upload", files);
      page.click("#file-submit");

      // Verify the upload
      String uploadedText = page.textContent("#uploaded-files");
      System.out.println("Uploaded files text: " + uploadedText);

      // Clean up
      page.close();
      browser.close();
    }
  }
  @Test
  public void multipleFilesUploadByDirectArrayPass() {
    try (Playwright playwright = Playwright.create()) {
      Browser browser = playwright.chromium().launch(
              new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500)
      );
      Page page = browser.newPage();
      page.setDefaultNavigationTimeout(30000);
      page.navigate("https://the-internet.herokuapp.com/upload");
        // Upload multiple files by passing an array directly
      page.setInputFiles("#file-upload", new Path[]{
//              Paths.get("files/1753706901022.jpg"),
              Paths.get("files/1753683034617.jpg")
      });
      page.click("#file-submit");

      // Verify the upload
      String uploadedText = page.textContent("#uploaded-files");
      System.out.println("Uploaded files text: " + uploadedText);

      // Clean up
      page.close();
      browser.close();
    }
  }

  @Test
  public void multipleFilesUploadByDirectArrayPassRemoveFiles() throws InterruptedException {
    try (Playwright playwright = Playwright.create()) {
      Browser browser = playwright.chromium().launch(
              new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500)
      );
      Page page = browser.newPage();
      page.setDefaultNavigationTimeout(30000);
      page.navigate("https://the-internet.herokuapp.com/upload");
      // Upload multiple files by passing an array directly
      page.setInputFiles("#file-upload", new Path[]{
//              Paths.get("files/1753706901022.jpg"),
              Paths.get("files/1753683034617.jpg")
      });

      Thread.sleep(2000); // Wait for the file input to process the files
      page.setInputFiles("#file-upload", new Path[0]);
      page.click("#file-submit");

      // Verify the upload
      String uploadedText = page.textContent("#uploaded-files");
      System.out.println("Uploaded files text: " + uploadedText);

      // Clean up
      page.close();
      browser.close();
    }
  }






}
