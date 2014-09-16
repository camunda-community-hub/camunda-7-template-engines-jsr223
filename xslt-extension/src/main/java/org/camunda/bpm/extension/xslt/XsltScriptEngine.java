/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.camunda.bpm.extension.xslt;

import net.sf.saxon.TransformerFactoryImpl;
import org.camunda.commons.utils.IoUtil;

import javax.script.*;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;
import java.util.Properties;

/**
 * @author Stefan Hentschel.
 */
public class XsltScriptEngine extends AbstractScriptEngine implements Compilable {

  protected ScriptEngineFactory scriptEngineFactory;

  public XsltScriptEngine() {
    this(null);
  }

  public XsltScriptEngine(ScriptEngineFactory scriptEngineFactory) {
    this.scriptEngineFactory = scriptEngineFactory;
  }

  public CompiledScript compile(String script) throws ScriptException {
    return compile(new StringReader(script));
  }

  public CompiledScript compile(Reader script) throws ScriptException {
    try {
      TransformerFactory factory = new TransformerFactoryImpl();

      Templates templates = factory.newTemplates(new StreamSource(script));
      return new XsltCompiledScript(this, templates);
    } catch(TransformerException e) {
      throw new ScriptException(e);
    }
  }

  public Object eval(String script, ScriptContext context) throws ScriptException {
    return eval(new StringReader(script), context);
  }

  public Object eval(Reader reader, ScriptContext context) throws ScriptException {
    Transformer transformer;

    try {
      TransformerFactory factory = new TransformerFactoryImpl();
      transformer = factory.newTransformer(new StreamSource(reader));
    } catch(Exception e) {
      throw new ScriptException(e);
    }

    return evaluate(transformer, context);
  }

  public Object evaluate(Transformer transformer, ScriptContext context) throws ScriptException{
    StringWriter writer = new StringWriter();
    Bindings bindings = context.getBindings(ScriptContext.ENGINE_SCOPE);
    Properties properties = (Properties) context.getAttribute("properties", ScriptContext.ENGINE_SCOPE);

    try {
      transformer.clearParameters();

      // set output properties
      if(properties != null && !properties.isEmpty()) {
        transformer.setOutputProperties(properties);
      }

      // set source xml
      String source = (String) bindings.get("camunda_source");

      // this will prevent that camunda source will be mapped into the stylesheet template
      bindings.remove("camunda_source");

      // set bindings for stylesheet template
      if(!bindings.isEmpty()) {
        for(Map.Entry<String, Object> entry : bindings.entrySet()) {
          if(entry.getValue() != null) {
            transformer.setParameter(entry.getKey(), entry.getValue());
          }
        }
      }

      transformer.transform(new StreamSource(IoUtil.stringAsInputStream(source)), new StreamResult(writer));
      writer.flush();
    } catch(TransformerException e) {
      throw new ScriptException(e);
    }

    return writer.toString();
  }

  public Bindings createBindings() {
    return new SimpleBindings();
  }

  public ScriptEngineFactory getFactory() {
    return scriptEngineFactory;
  }
}
