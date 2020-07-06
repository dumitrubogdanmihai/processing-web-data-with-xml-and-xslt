package com.oxygenxml.open4tech;

import java.util.concurrent.BlockingQueue;

import org.openqa.selenium.By;
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
    System.out.println("Consume: " + page);
    driver.get(page);
    driver.findElement(By.cssSelector(".offer-titlebox h1"));
    return driver.getPageSource();
  }

  public void start() {
    while(true) {
      try {
        String page = queue.take();
        this.getData(page);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
