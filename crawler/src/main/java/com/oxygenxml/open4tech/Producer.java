package com.oxygenxml.open4tech;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.oxygenxml.open4tech.interfaces.IProducer;

class Producer implements IProducer{
  private WebDriver driver;
  private BlockingQueue<String> queue;
  private int pageNr;

  public Producer(BlockingQueue<String> queue) {
    this.queue = queue;
    this.driver = new ChromeDriver();
    this.pageNr = 0;
  }

  public void startAndWaitFinnish() throws InterruptedException {
    while(next()) {
      List<String> pagesToConsume = getPagesToConsume();
      for (String pageToConsume : pagesToConsume) {
        queue.put(pageToConsume);
      }
    }
  }

  public boolean next() {
    if (pageNr < 501) {
      this.pageNr++;
      String currentPage = "https://www.olx.ro/auto-masini-moto-ambarcatiuni/autoturisme/?page=" + pageNr;
      this.driver.get(currentPage);
      return true;
    } else {
      this.close();
      return false;
    }
  }

  public List<String> getPagesToConsume() {
    List<String> toReturn = new ArrayList<>();

    List<WebElement> anchors = driver.findElements(By.cssSelector(".offer-wrapper .title-cell a"));
    for (WebElement anchor : anchors) {
      String href = anchor.getAttribute("href");
      if (href.startsWith("https://www.olx.ro/oferta/")) {
        System.out.println("Produce: " + href);
        toReturn.add(href);
      } else {
        // Ignore links to autovit.
      }
    }
    return toReturn;
  }

  @Override
  public void close() {
    driver.close();
  }
}
