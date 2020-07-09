<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:xs="http://www.w3.org/2001/XMLSchema"
  exclude-result-prefixes="xs"
  version="2.0">
  
  <xsl:output method="xml" indent="yes"/>
  
  <xsl:template match="//description">
    <xsl:analyze-string regex="[\s^]([abcdefghjklmnprstuvwxyzABCDEFGHJKLMNPRSTUVWXYZ0123456789]{{17}})[\s$]" select="./text()">
      <xsl:matching-substring>
        <vin>
          <xsl:value-of select="regex-group(1)"/>
        </vin>
      </xsl:matching-substring>
    </xsl:analyze-string>

    <description>
      <xsl:value-of select="."/>
    </description>
  </xsl:template>

  <xsl:template match="@*|node()">
    <xsl:copy>
      <xsl:apply-templates select="@*|node()"/>
    </xsl:copy>
  </xsl:template>
</xsl:stylesheet>