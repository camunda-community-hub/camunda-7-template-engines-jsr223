/*
 * Copyright Camunda Services GmbH and/or licensed to Camunda Services GmbH
 * under one or more contributor license agreements. Licensed under a commercial license.
 * You may not use this file except in compliance with the commercial license.
 */
package org.camunda.bpm.extension.xslt;

import javax.script.CompiledScript;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;

/**
 * @author Stefan Hentschel.
 */
public class XsltCompiledScript extends CompiledScript {

  protected final ScriptEngine scriptEngine;
  protected final Templates templates;

  public XsltCompiledScript(ScriptEngine scriptEngine, Templates templates) {
    this.templates = templates;
    this.scriptEngine = scriptEngine;
  }

  public Object eval(ScriptContext context) throws ScriptException {
    Transformer transformer;

    try {
      transformer = templates.newTransformer();
      return ((XsltScriptEngine) scriptEngine).evaluate(transformer, context);
    } catch(TransformerException e) {
      throw new ScriptException(e);
    }
  }

  public ScriptEngine getEngine() {
    return scriptEngine;
  }
}
