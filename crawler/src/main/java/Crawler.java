import java.io.File;

import org.openqa.selenium.chrome.ChromeDriver;

public class Crawler {

  private static void setup() {
    File webdriverFile = new File("target/chromedriver.exe");
    if (!webdriverFile.exists()) {
      System.out.println("Missing Chrome driver: " + webdriverFile + ". "
          + "It can be downloaded from https://chromedriver.chromium.org/downloads");
      System.exit(0);
    }
    System.setProperty("webdriver.chrome.driver", webdriverFile.getAbsolutePath());
  }

  public static void main(String[] args) throws InterruptedException {
    setup();

    ChromeDriver driver = new ChromeDriver();
    try {
      driver.get("https://www.google.com");
      driver.get("https://www.olx.ro");
      Thread.sleep(1000);
    } finally {
      driver.close();
    }
  }
}
