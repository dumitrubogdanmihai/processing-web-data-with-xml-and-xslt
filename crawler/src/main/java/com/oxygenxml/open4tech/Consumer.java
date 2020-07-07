package com.oxygenxml.open4tech;

import java.io.File;
import java.io.FileWriter;
import java.util.concurrent.BlockingQueue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.oxygenxml.open4tech.interfaces.IConsumer;

class Consumer implements IConsumer {

  private ChromeDriver driver;
  private BlockingQueue<String> queue;

  public Consumer(BlockingQueue<String> queue) {
    this.queue = queue;
    this.driver = new ChromeDriver();
  }

  public String getData(String page) {
    driver.get(page);
    WebElement title = driver.findElement(By.cssSelector(".offer-titlebox h1"));
    System.out.println("Consume: " + page + "  -  \"" + title.getText() + "\"");
    return driver.getPageSource();
  }

  public void start() {
    while(true) {
      try {
        String pageUrl = queue.take();
        String pageContent = this.getData(pageUrl);
        File outputDir = new File("target/pages/");
        outputDir.mkdirs();

        File outputFile = new File("target/pages/" + getPageId(pageUrl) + ".html");
        if (!outputFile.exists()) {
          try (FileWriter fileWriter = new FileWriter(outputFile)) {
            fileWriter.write(pageContent);
          }
        }

        if (Thread.interrupted()) {
          driver.close();
          break;
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  static String getPageId(String url) {
    return url.replaceAll("https://www.olx.ro/oferta/.*-([^-]+)[.]html.*", "$1");
  }

  @Override
  public void close() {
    driver.close();
  }
}
