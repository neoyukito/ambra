/* $HeadURL::                                                                            $
 * $Id$
 *
 * Copyright (c) 2007-2010 by Public Library of Science
 * http://plos.org
 * http://ambraproject.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
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

package org.topazproject.ambra.struts2;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import org.apache.struts2.StrutsStatics;
import org.apache.struts2.json.JSONResult;

import javax.servlet.http.HttpServletResponse;

/**
 * @author stevec
 *
 */
public class JsonResult extends JSONResult {
  private boolean noCache;

  /**
   * 
   */
  public JsonResult() {
    super();
  }

  public void execute(ActionInvocation invocation) throws Exception {
    ActionContext actionContext = invocation.getInvocationContext();
    HttpServletResponse response = (HttpServletResponse) actionContext
      .get(StrutsStatics.HTTP_RESPONSE);

    if (noCache) {
      // HTTP 1.1 browsers should defeat caching on this header
      response.setHeader("Cache-Control", "no-cache");
      // HTTP 1.0 browsers should defeat caching on this header
      response.setHeader("Pragma", "no-cache");
      // Last resort for those that ignore all of the above
      response.setHeader("Expires", "0");
    }
    super.execute(invocation);
  }

  /**
   * @param noCache The noCache to set.
   */
  public void setNoCache(boolean noCache) {
    this.noCache = noCache;
  }
}
