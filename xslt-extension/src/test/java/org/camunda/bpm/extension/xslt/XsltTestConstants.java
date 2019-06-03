/*
 * Copyright Camunda Services GmbH and/or licensed to Camunda Services GmbH
 * under one or more contributor license agreements. Licensed under a commercial license.
 * You may not use this file except in compliance with the commercial license.
 */
package org.camunda.bpm.extension.xslt;

import org.camunda.commons.utils.IoUtil;

/**
 * @author Stefan Hentschel.
 */
public class XsltTestConstants {

  public static final String EXAMPLE_XSL_FILE_NAME = "org/camunda/bpm/extension/xslt/example_xsl.xsl";

  public static final String EXAMPLE_XML_FILE_NAME = "org/camunda/bpm/extension/xslt/example_xml.xml";

  public static final String EXAMPLE_XHTML_FILE_NAME = "org/camunda/bpm/extension/xslt/example_xhtml.xhtml";

  public static final String EXAMPLE_XML = IoUtil.fileAsString(EXAMPLE_XML_FILE_NAME);

  public static final String EXAMPLE_XSL = IoUtil.fileAsString(EXAMPLE_XSL_FILE_NAME);

  public static final String EXAMPLE_XHTML = IoUtil.fileAsString(EXAMPLE_XHTML_FILE_NAME);
}
