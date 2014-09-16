<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns="http://www.w3.org/1999/xhtml">

  <xsl:output method="xml"
              doctype-system="http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd"
              doctype-public="-//W3C//DTD XHTML 1.1//EN"
              indent="yes" />

  <xsl:param name="world" />

  <xsl:template match="/customer">
    <html>
      <head>
        <title>Hello <xsl:value-of select="$world" />!</title>
      </head>
      <body>
        <table>
          <tr>

          <xsl:apply-templates />

          </tr>
        </table>
      </body>
    </html>
  </xsl:template>

  <xsl:template match="id">
    <td>
      <xsl:apply-templates />
    </td>
  </xsl:template>

  <xsl:template match="name">
    <td>
      <xsl:apply-templates />
    </td>
  </xsl:template>

  <xsl:template match="sex">
    <td>
      <xsl:apply-templates />
    </td>
  </xsl:template>

</xsl:stylesheet>