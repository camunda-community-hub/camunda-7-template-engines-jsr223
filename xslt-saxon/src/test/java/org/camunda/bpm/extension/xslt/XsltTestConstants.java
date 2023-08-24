/*
 * Copyright Camunda Services GmbH and/or licensed to Camunda Services GmbH
 * under one or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information regarding copyright
 * ownership. Camunda licenses this file to you under the Apache License,
 * Version 2.0; you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
