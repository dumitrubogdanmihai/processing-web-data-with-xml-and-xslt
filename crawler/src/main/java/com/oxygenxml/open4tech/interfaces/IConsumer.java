package com.oxygenxml.open4tech.interfaces;

public interface IConsumer {
  String getData(String page);
  void start();
  void close();
}
