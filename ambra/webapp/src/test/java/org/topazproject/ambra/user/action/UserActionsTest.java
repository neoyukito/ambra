/* $HeadURL::                                                                            $
 * $Id$
 *
 * Copyright (c) 2006-2010 by Public Library of Science
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
package org.topazproject.ambra.user.action;

import static com.opensymphony.xwork2.Action.SUCCESS;

import static org.topazproject.ambra.Constants.AMBRA_USER_KEY;

import org.topazproject.ambra.BaseAmbraTestCase;
import org.topazproject.ambra.Constants;
import org.topazproject.ambra.user.AmbraUser;

import java.util.HashMap;
import java.util.Map;

public class UserActionsTest extends BaseAmbraTestCase {
  private static final String TEST_EMAIL = "testcase@topazproject.org";
  private static final String REAL_NAME = "Test User";
  private static final String AUTH_ID = "92L6RFPMZZ0NKDSJH8T9S1E2589EWQRK";
  private static final String USERNAME= "TEST_USERNAME";
  private static final String GIVENNAMES = "my GIVENNAMES";
  private static final String SURNAMES = "my Surnames";
  private static final String POSITION_TYPE = "my POSITION_TYPE";
  private static final String ORGANIZATION_TYPE = "my organizationType";
  private static final String POSTAL_ADDRESS = "my postalAddress";
  private static final String BIOGRAPHY_TEXT = "my biographyText";
  private static final String INTERESTS_TEXT = "my interestsText";
  private static final String RESEARCH_AREAS_TEXT = "my researchAreasText";
  private static final String CITY = "my city";
  private static final String COUNTRY = "my country";

  public void testCreateUser() throws Exception {
    final UserProfileAction createUserAction = getMockCreateUserAction(AUTH_ID);
    createUserAction.setEmail(TEST_EMAIL);
    createUserAction.setRealName(REAL_NAME);
    createUserAction.setAuthId(AUTH_ID);
    createUserAction.setDisplayName(USERNAME);
    createUserAction.setGivenNames(GIVENNAMES);
    createUserAction.setSurnames(SURNAMES);
    createUserAction.setPositionType(POSITION_TYPE);
    createUserAction.setOrganizationType(ORGANIZATION_TYPE);
    createUserAction.setPostalAddress(POSTAL_ADDRESS);
    createUserAction.setBiographyText(BIOGRAPHY_TEXT);
    createUserAction.setInterestsText(INTERESTS_TEXT);
    createUserAction.setResearchAreasText(RESEARCH_AREAS_TEXT);
    createUserAction.setCity(CITY);
    createUserAction.setCountry(COUNTRY);

    assertEquals(SUCCESS, createUserAction.executeSaveUser());
    final String topazId = createUserAction.getInternalId();
    assertNotNull(topazId);
    
    final DisplayUserAction displayUserAction = getDisplayUserAction();
    displayUserAction.setUserId(topazId);
    assertEquals(SUCCESS, displayUserAction.execute());

    final AmbraUser pou = displayUserAction.getPou();
    assertEquals(REAL_NAME, pou.getRealName());
    assertEquals(USERNAME, pou.getDisplayName());
    assertEquals(TEST_EMAIL, pou.getEmail());
    assertEquals(GIVENNAMES, pou.getGivenNames());
    assertEquals(POSITION_TYPE, pou.getPositionType());
		assertEquals(ORGANIZATION_TYPE, pou.getOrganizationType());
		assertEquals(POSTAL_ADDRESS, pou.getPostalAddress());
		assertEquals(BIOGRAPHY_TEXT, pou.getBiographyText());
		assertEquals(INTERESTS_TEXT, pou.getInterestsText());
		assertEquals(RESEARCH_AREAS_TEXT, pou.getResearchAreasText());
		assertEquals(CITY, pou.getCity());
		assertEquals(COUNTRY, pou.getCountry());

    getUserService().deleteUser(topazId);
  }

  public void testCreateUserWithRightVisibilityOfFields() throws Exception {
    final UserProfileAction createUserAction = getMockCreateUserAction(AUTH_ID);
    createUserAction.setEmail(TEST_EMAIL);
    createUserAction.setGivenNames(GIVENNAMES);
    createUserAction.setSurnames(SURNAMES);
    createUserAction.setCity(CITY);
    createUserAction.setCountry(COUNTRY);
    createUserAction.setAuthId(AUTH_ID);
    createUserAction.setDisplayName(USERNAME);
    createUserAction.setPositionType(POSITION_TYPE);
    createUserAction.setOrganizationType(ORGANIZATION_TYPE);
    createUserAction.setPostalAddress(POSTAL_ADDRESS);

    assertEquals(SUCCESS, createUserAction.executeSaveUser());
    final String topazId = createUserAction.getInternalId();
    assertNotNull(topazId);

    final DisplayUserAction displayUserAction = getDisplayUserAction();
    displayUserAction.setUserId(topazId);
    assertEquals(SUCCESS, displayUserAction.execute());

    final AmbraUser pou = displayUserAction.getPou();
    assertEquals(TEST_EMAIL, pou.getEmail());
    assertEquals(USERNAME, pou.getDisplayName());
    assertEquals(GIVENNAMES, pou.getGivenNames());
    assertEquals(SURNAMES, pou.getSurnames());
    assertEquals(POSITION_TYPE, pou.getPositionType());
		assertEquals(ORGANIZATION_TYPE, pou.getOrganizationType());
		assertEquals(POSTAL_ADDRESS, pou.getPostalAddress());
		assertEquals(CITY, pou.getCity());
		assertEquals(COUNTRY, pou.getCountry());

    getUserService().deleteUser(topazId);
  }

  public void testCreateAdminUser() throws Exception {
    final UserProfileAction createUserAction = getMockCreateUserAction(AUTH_ID);
    createUserAction.setEmail(TEST_EMAIL);
    createUserAction.setGivenNames(GIVENNAMES);
    createUserAction.setSurnames(SURNAMES);
    createUserAction.setCity(CITY);
    createUserAction.setCountry(COUNTRY);
    createUserAction.setAuthId(AUTH_ID);
    createUserAction.setDisplayName(USERNAME);
    assertEquals(SUCCESS, createUserAction.executeSaveUser());
    final String topazId = createUserAction.getInternalId();
    assertNotNull(topazId);

    final AssignAdminRoleAction assignAdminRoleAction = createMockAssignAdminRoleAction(AUTH_ID, topazId);
    assignAdminRoleAction.setTopazId(topazId);
    assertEquals(SUCCESS, assignAdminRoleAction.execute());

    getUserService().deleteUser(topazId);
  }

  public void testSearchUserByUID() throws Exception {
    final String authId = AUTH_ID;
    final UserProfileAction createUserAction = getMockCreateUserAction(authId);
    createUserAction.setEmail(TEST_EMAIL);
    createUserAction.setGivenNames(GIVENNAMES);
    createUserAction.setSurnames(SURNAMES);
    createUserAction.setCity(CITY);
    createUserAction.setCountry(COUNTRY);
    createUserAction.setAuthId(AUTH_ID);
    createUserAction.setDisplayName(USERNAME);
    assertEquals(SUCCESS, createUserAction.executeSaveUser());
    final String topazId = createUserAction.getTopazId();
    
    final SearchUserAction searchUserAction = getSearchUserAction();
    searchUserAction.setAuthId(authId);
    assertEquals(SUCCESS, searchUserAction.executeFindUserByAuthId());

    final String[] topazUserIdList = searchUserAction.getTopazUserIdList();
    assertTrue(topazUserIdList.length == 1);

    final AdminUserProfileAction adminUserProfileAction = getAdminUserProfileAction();
    adminUserProfileAction.setTopazId(topazUserIdList[0]);
    assertEquals(SUCCESS, adminUserProfileAction.executeRetrieveUserProfile());

    final AmbraUser pou = adminUserProfileAction.getAmbraUserToUse();
    assertEquals(USERNAME, pou.getDisplayName());
    assertEquals(TEST_EMAIL, pou.getEmail());
    assertEquals(GIVENNAMES, pou.getGivenNames());
		assertEquals(SURNAMES, pou.getSurnames());
		assertEquals(authId, pou.getAuthId());
		assertEquals(CITY, pou.getCity());
		assertEquals(COUNTRY, pou.getCountry());
    getUserService().deleteUser(topazId);
  }

  public void testSearchUserByEmail() throws Exception {
    final String authId = AUTH_ID;
    final UserProfileAction createUserAction = getMockCreateUserAction(authId);
    createUserAction.setEmail(TEST_EMAIL);
    createUserAction.setGivenNames(GIVENNAMES);
    createUserAction.setSurnames(SURNAMES);
    createUserAction.setCity(CITY);
    createUserAction.setCountry(COUNTRY);
    createUserAction.setAuthId(AUTH_ID);
    createUserAction.setDisplayName(USERNAME);
    assertEquals(SUCCESS, createUserAction.executeSaveUser());
    final String topazId = createUserAction.getTopazId();

    final SearchUserAction searchUserAction = getSearchUserAction();
    searchUserAction.setEmailAddress(TEST_EMAIL);
    assertEquals(SUCCESS, searchUserAction.executeFindUserByEmailAddress());

    final String[] topazUserIdList = searchUserAction.getTopazUserIdList();
    assertTrue(topazUserIdList.length == 1);

    final AdminUserProfileAction adminUserProfileAction = getAdminUserProfileAction();
    adminUserProfileAction.setTopazId(topazUserIdList[0]);
    assertEquals(SUCCESS, adminUserProfileAction.executeRetrieveUserProfile());

    final AmbraUser pou = adminUserProfileAction.getAmbraUserToUse();
    assertEquals(USERNAME, pou.getDisplayName());
    assertEquals(TEST_EMAIL, pou.getEmail());
    assertEquals(GIVENNAMES, pou.getGivenNames());
		assertEquals(SURNAMES, pou.getSurnames());
		assertEquals(CITY, pou.getCity());
		assertEquals(COUNTRY, pou.getCountry());
    getUserService().deleteUser(topazId);
  }

  protected AssignAdminRoleAction createMockAssignAdminRoleAction(final String authId, final String topazId) {
    final AssignAdminRoleAction adminRoleActionToClone = super.getAssignAdminRoleAction();
    final AssignAdminRoleAction newAdminRoleAction = new AssignAdminRoleAction();
    newAdminRoleAction.setSession(createMockSessionMap(authId, topazId));

    newAdminRoleAction.setUserService(adminRoleActionToClone.getUserService());

    return newAdminRoleAction;
  }

  protected UserProfileAction getMockCreateUserAction(final String authId) {
    final UserProfileAction createUserAction = super.getMemberUserProfileAction();
    final UserProfileAction newCreateUserAction = new MemberUserProfileAction();
    newCreateUserAction.setSession(createMockSessionMap(authId, null));

    newCreateUserAction.setUserService(createUserAction.getUserService());
    newCreateUserAction.setProfanityCheckingService(createUserAction.getProfanityCheckingService());

    return newCreateUserAction;
  }

  private Map<String, Object> createMockSessionMap(final String authId, final String topazId) {
    final AmbraUser ambraUser = new AmbraUser(authId);
    if (null != topazId) {
      ambraUser.setUserId(topazId);
    }

    final Map<String, Object> sessionMap = new HashMap<String, Object>();
    sessionMap.put(AMBRA_USER_KEY, ambraUser);
    sessionMap.put(Constants.SINGLE_SIGNON_USER_KEY, authId);

    return sessionMap;
  }
}