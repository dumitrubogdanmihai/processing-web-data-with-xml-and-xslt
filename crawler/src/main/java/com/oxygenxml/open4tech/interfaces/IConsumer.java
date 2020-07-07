package com.oxygenxml.open4tech.interfaces;

public interface IConsumer {
  String getPageHtml(String page);
  void start();
  void close();
}
