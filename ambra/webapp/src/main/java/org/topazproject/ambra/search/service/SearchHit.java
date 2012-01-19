/* $HeadURL::                                                                            $
 * $Id$
 *
 * Copyright (c) 2006-2008 by Topaz, Inc.
 * http://topazproject.org
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
package org.topazproject.ambra.search.service;

import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.net.URI;

import org.apache.commons.lang.StringUtils;

/**
 * Value object that holds the result of a single search item
 *
 * @author Viru
 * @author Eric Brown
 */
public class SearchHit implements Comparable<SearchHit> {
  private final double hitScore;
  private final String uri;
  private final String title;
  private final String highlight;
  private final Date   date;
  private final String creator;
  private final String journalKey;
  private final String journalTitle;
  private final String articleTypeForDisplay;

  /**
   * Create a search hit with the values set
   */
  public SearchHit(double hitScore, String uri, String title, String highlight,
                   Collection<String> creators, Date date, String journalKey,
                   String journalTitle, String articleTypeForDisplay) {
    this.hitScore   = hitScore;
    this.uri        = uri;
    this.title      = title;
    this.highlight  = highlight;
    this.creator    = StringUtils.join(creators, ", ");
    this.date       = date;
    this.journalKey = journalKey;
    this.journalTitle = journalTitle;
    this.articleTypeForDisplay = articleTypeForDisplay;
  }

  public int compareTo(SearchHit o) {
    // descending
    return Double.compare(o.hitScore, hitScore);
  }

  /**
   * @return the hit object's uri
   */
  public String getUri() {
    return uri;
  }

  /**
   * Getter for property 'hitScore'.
   * @return Value for property 'hitScore'.
   */
  public double getHitScore() {
    return hitScore;
  }

  /**
   * Getter for property 'creator'.
   * @return Value for property 'creator'.
   */
  public String getCreator() {
    return creator;
  }

  /**
   * Getter for property 'date'.
   * @return Value for property 'date'.
   */
  public Date getDate() {
    return date;
  }

  /**
   * Getter for property 'highlight'.
   * @return Value for property 'highlight'.
   */
  public String getHighlight() {
    return highlight;
  }

  /**
   * Getter for property 'title'.
   * @return Value for property 'title'.
   */
  public String getTitle() {
    return title;
  }

  /**
   * Get the key for the Journal to which this SearchHit belongs.
   * @return Key for the Journal to which this SearchHit belongs.
   */
  public String getJournalKey() {
    return journalKey;
  }

  /**
   * Get the Dublin Core Title for the Journal to which this SearchHit belongs.
   * @return Dublin Core Title for the Journal to which this SearchHit belongs.
   */
  public String getJournalTitle() {
    return journalTitle;
  }

  /**
   * Get the type of the Article as a String ready for display.
   * @return Type of the Article as a String ready for display
   */
  public String getArticleTypeForDisplay() {
    return articleTypeForDisplay;
  }

  /**
   * Returns the URI (identical to the <code>getUri()</code> method).
   * @return the URI for this SearchHit.
   */
  public String toString() {
    return getUri();
  }

}