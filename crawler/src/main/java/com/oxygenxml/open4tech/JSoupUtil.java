package com.oxygenxml.open4tech;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Entities.EscapeMode;
import org.jsoup.safety.Whitelist;

class JSoupUtil {
  static String convertHtmlToXhtml(final String html) {
    Whitelist whitelist = Whitelist.relaxed();
    whitelist.addAttributes(":all", "class");
    whitelist.addAttributes(":all", "id");
    final Document document = Jsoup.parse(Jsoup.clean(html, whitelist));
    document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
    document.outputSettings().escapeMode(EscapeMode.xhtml);
    document.outputSettings().prettyPrint(false);
    return document.html();
  }
}
