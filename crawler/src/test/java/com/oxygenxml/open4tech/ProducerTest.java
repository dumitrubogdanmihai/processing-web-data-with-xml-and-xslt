package com.oxygenxml.open4tech;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import org.junit.Test;

public class ProducerTest {
  @Test
  public void testGenerateLinks() {
    Crawler.setup();

    ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(1000);
    Producer producer = new Producer(queue);
    try {
      assertTrue(producer.next());
      List<String> pagesToConsume = producer.getPagesToConsume();
      assertNotEquals(0, pagesToConsume.size());
      assertTrue(pagesToConsume.get(0).startsWith("https://www.olx.ro/"));
    } finally {
      producer.close();
    }
  }
}
