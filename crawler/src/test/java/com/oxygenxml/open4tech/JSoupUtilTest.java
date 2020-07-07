package com.oxygenxml.open4tech;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.google.common.io.Files;

public class JSoupUtilTest {
  @Test
  public void testJsoupProduceWellFormedXhtml() throws IOException, ParserConfigurationException, SAXException {
    File htmlFile = new File("src/test/resources/IDdLHsU.html");
    
    String html = Files.readLines(htmlFile, StandardCharsets.UTF_8)
        .stream()
        .collect(Collectors.joining("\n"));
    String xhtml = JSoupUtil.convertHtmlToXhtml(html);
    
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    dBuilder.parse(new ByteArrayInputStream(xhtml.getBytes()));
  }
  @Test
  public void testJsoupPreserveClasses() throws IOException, ParserConfigurationException, SAXException {
    File htmlFile = new File("src/test/resources/IDdLHsU.html");
    
    String html = Files.readLines(htmlFile, StandardCharsets.UTF_8)
        .stream()
        .collect(Collectors.joining("\n"));
    String xhtml = JSoupUtil.convertHtmlToXhtml(html);
    assertTrue(xhtml.contains("offer-details__value"));
    assertTrue(xhtml.contains("class=\"offer-details__value\""));
  }
}
