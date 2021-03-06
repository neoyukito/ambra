/*
 * Copyright (c) 2006-2012 by Public Library of Science http://plos.org http://ambraproject.org
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ambraproject.search;

import org.ambraproject.ApplicationException;

/**
 * Send saved searches
 */
public interface SavedSearchSender {
  /**
   * Send the saved search to all the users that are associated with it.
   *
   * @param searchJob
   *
   * @throws ApplicationException
   */
  public void sendSavedSearch(SavedSearchJob searchJob) throws ApplicationException;
}
