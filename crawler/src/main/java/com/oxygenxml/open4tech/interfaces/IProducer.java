package com.oxygenxml.open4tech.interfaces;

import java.util.List;

public interface IProducer {
  List<String> getPagesToConsume();
  boolean next();
  void startAndWaitFinnish() throws InterruptedException;
  void close();
}
