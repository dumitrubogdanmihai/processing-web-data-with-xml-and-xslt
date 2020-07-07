package com.oxygenxml.open4tech;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.ArrayBlockingQueue;

import org.junit.Test;

public class ConsumerTest {
  @Test
  public void testGetPageContent() {
    Crawler.setup();

    ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(1000);
    Consumer consumer = new Consumer(queue);
    try {
      String pageContent = consumer.getData("https://www.olx.ro/oferta/mini-cooper-s-1-6i-180-cai-accept-schimb-IDcrmtw.html#080d3adf25");
      assertTrue(pageContent.contains("Mini Cooper S,1.6i, 180 cai,accept schimb"));
    } finally {
      consumer.close();
    }
  }
}
