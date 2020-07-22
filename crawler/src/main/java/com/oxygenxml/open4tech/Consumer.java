package com.oxygenxml.open4tech;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.BlockingQueue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.oxygenxml.open4tech.interfaces.IConsumer;

class Consumer implements IConsumer {

  private final ChromeDriver driver;
  private final BlockingQueue<String> queue;
  private final File outputDir;
  
  public Consumer(BlockingQueue<String> queue) {
    this.queue = queue;
    this.driver = new ChromeDriver();
    this.outputDir = new File("target/pages/");
    this.outputDir.mkdirs();
  }

  public String getPageHtml(String page) {
    driver.get(page);
    WebElement title = driver.findElement(By.cssSelector(".offer-titlebox h1"));
    System.out.println("Consume: " + page + "  -  \"" + title.getText() + "\"");
    return driver.getPageSource();
  }

  public void start() {
    while(true) {
      try {
        String pageUrl = queue.take();
        String htmlPageContent = this.getPageHtml(pageUrl);

        writePageContent(pageUrl, htmlPageContent);

        if (Thread.interrupted()) {
          driver.close();
          break;
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  private void writePageContent(String pageUrl, String htmlContent) throws IOException {
    String xhtml = JSoupUtil.convertHtmlToXhtml(htmlContent);
    File outputFile = new File("target/pages/" + getPageId(pageUrl) + ".xhtml");
    if (!outputFile.exists()) {
      try (OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(outputFile), StandardCharsets.UTF_8)) {
        osw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        osw.write(xhtml);
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
