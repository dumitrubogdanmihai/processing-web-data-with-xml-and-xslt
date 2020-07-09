<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:xs="http://www.w3.org/2001/XMLSchema"
  exclude-result-prefixes="xs"
  version="2.0">
  
  <xsl:output method="xml" indent="yes"/>
  
  <xsl:template match="//title/text()">
    <xsl:value-of select="normalize-space(.)"/>
  </xsl:template>
  
  <xsl:template match="//spec/@type">
    <xsl:attribute name="type">
      <xsl:value-of select="replace(lower-case(.), ' ', '-')"/>
    </xsl:attribute>
  </xsl:template>
  
  <xsl:template match="//spec[@type='Rulaj']/text()">
    <xsl:value-of select="replace(., '[^0-9]', '')"/>
  </xsl:template>
  <xsl:template match="//spec[@type='Capacitate motor']/text()">
    <xsl:value-of select="replace(., '[^0-9]', '')"/>
  </xsl:template>
  <xsl:template match="//spec[@type='An de fabricatie']/text()">
    <xsl:value-of select="replace(., '[^0-9]', '')"/>
  </xsl:template>
  
  <xsl:template match="//price">
    <price>
      <xsl:attribute name="currency">
        <xsl:value-of select="replace(./text(), '[0-9 ]', '')"/>
      </xsl:attribute>
      <xsl:value-of select="replace(./text(), '[^0-9]', '')"/>
    </price>
  </xsl:template>

  <xsl:template match="@*|node()">
    <xsl:copy>
      <xsl:apply-templates select="@*|node()"/>
    </xsl:copy>
  </xsl:template>
</xsl:stylesheet>