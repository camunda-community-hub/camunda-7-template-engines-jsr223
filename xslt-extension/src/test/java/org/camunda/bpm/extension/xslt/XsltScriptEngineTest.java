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
      assertThat(evaluate(xsltStylesheet)).isEqualTo(expected);
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
}
