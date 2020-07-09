<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:xs="http://www.w3.org/2001/XMLSchema"
  exclude-result-prefixes="xs"
  version="2.0">
  
  <xsl:output method="xml" indent="yes"/>
  
  <xsl:template match="/">
    <ad>
      <xsl:apply-templates></xsl:apply-templates>
    </ad>
  </xsl:template>

  <xsl:template match="//span[@class='offer-details__name']">
    <spec>
      <xsl:attribute name="type"><xsl:value-of select="text()"/></xsl:attribute>
      <xsl:value-of select="./following-sibling::node()/text()"/>
    </spec>
    <xsl:apply-templates></xsl:apply-templates>
  </xsl:template>

  <xsl:template match="//div[@class='offer-titlebox']">
    <title><xsl:value-of select="./h1/text()"/></title>
  </xsl:template>
  
  <xsl:template match="//div[@class='price ']">
    <price><xsl:value-of select="./text()"/></price>
  </xsl:template>
  
  <xsl:template match="//div[@id='textContent']">
    <description>
      <xsl:apply-templates select="*|node()" mode="description"/>
    </description>
  </xsl:template>
  <xsl:template match="node()" mode="description">
    <xsl:value-of select="."/>
    <xsl:apply-templates select="@*|node()"/>
  </xsl:template>
  <xsl:template match="//br" mode="description">
    <xsl:apply-templates select="@*|node()"/>
  </xsl:template>

  <xsl:template match="@*|node()">
    <xsl:apply-templates select="@*|node()"/>
  </xsl:template>
</xsl:stylesheet>