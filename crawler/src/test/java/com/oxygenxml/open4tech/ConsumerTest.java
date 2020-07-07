package com.oxygenxml.open4tech;
import static org.junit.Assert.assertEquals;
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
      String pageContent = consumer.getPageHtml("https://www.olx.ro/oferta/mini-cooper-s-1-6i-180-cai-accept-schimb-IDcrmtw.html#080d3adf25");
      assertTrue(pageContent.contains("Mini Cooper S,1.6i, 180 cai,accept schimb"));
    } finally {
      consumer.close();
    }
  }

  @Test
  public void testGetPageId() {
    String pageId = Consumer.getPageId("https://www.olx.ro/oferta/renault-twingo-IDdPgpu.html#aa");
    assertEquals("IDdPgpu", pageId);

    pageId = Consumer.getPageId("https://www.olx.ro/oferta/audi-q7-s-line-ultra-extra-full-IDdO2NO.html#6256e9ac30;promoted");
    assertEquals("IDdO2NO", pageId);
  }
}
