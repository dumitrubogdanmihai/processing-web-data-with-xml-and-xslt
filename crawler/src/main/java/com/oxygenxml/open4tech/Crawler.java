package com.oxygenxml.open4tech;
import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Crawler {

  public static void main(String[] args) throws InterruptedException {
    setup();

    BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
    Producer producer = new Producer(queue);
    Consumer consumer = new Consumer(queue);

    new Thread(() -> {
      consumer.start();
    }).start();
    producer.startAndWaitFinnish();
  }

  private static void setup() {
    File webdriverFile = new File("target/chromedriver.exe");
    if (!webdriverFile.exists()) {
      System.out.println("Missing Chrome driver: " + webdriverFile + ". "
          + "It can be downloaded from https://chromedriver.chromium.org/downloads");
      System.exit(0);
    }
    System.setProperty("webdriver.chrome.driver", webdriverFile.getAbsolutePath());
  }
}
