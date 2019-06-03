/*
 * Copyright Camunda Services GmbH and/or licensed to Camunda Services GmbH
 * under one or more contributor license agreements. Licensed under a commercial license.
 * You may not use this file except in compliance with the commercial license.
 */
package org.camunda.bpm.extension.xslt;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.script.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Stefan Hentschel.
 */
public class XsltScriptEngineTest {

  protected static ScriptEngine scriptEngine;
  protected Bindings bindings;

  protected String xsltStylesheet;
  protected String expected;

  @BeforeClass
  public static void getScriptEngine() {
    ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
    scriptEngine = scriptEngineManager.getEngineByName("xslt");
  }

  @Before
  public void createBindings() {
    bindings = new SimpleBindings();
  }

  @After
  public void checkResult() throws ScriptException {
    if(xsltStylesheet != null) {
      try {
        assertThat(evaluate(xsltStylesheet)).isEqualTo(expected);
      } catch(IllegalArgumentException e) {
        assertThat(e.getMessage()).contains("The XSLT transformation requires variable 'camunda_source' to be set.");
      }
    }
  }

  protected String evaluate(String xsltStylesheet) throws ScriptException{
    return (String) scriptEngine.eval(xsltStylesheet, bindings);
  }

  @Test
  public void testScriptEngineExists() {
    assertThat(scriptEngine).isNotNull();
    assertThat(scriptEngine.getFactory().getEngineName()).isEqualTo("xslt");
  }

  @Test
  public void testConverting() {
    bindings.put("world", "world");
    bindings.put("camunda_source", XsltTestConstants.EXAMPLE_XML);
    expected = XsltTestConstants.EXAMPLE_XHTML;
    xsltStylesheet = XsltTestConstants.EXAMPLE_XSL;
  }

  @Test
  public void testFailingConverting() {
    bindings.put("world", "world");
    expected = XsltTestConstants.EXAMPLE_XHTML;
    xsltStylesheet = XsltTestConstants.EXAMPLE_XSL;
  }
}
