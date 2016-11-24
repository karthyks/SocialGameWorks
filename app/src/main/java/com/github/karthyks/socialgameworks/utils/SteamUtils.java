package com.github.karthyks.socialgameworks.utils;


public class SteamUtils {
  public static final String API_KEY = "834C5F73DC17C42C41E2BADEFBBBCB99";
  public static final String REALM_PARAM = "SocialGameWorks";
  public static final String LOGIN_URL = "https://steamcommunity.com/openid/login?" +
      "openid.claimed_id=http://specs.openid.net/auth/2.0/identifier_select&" +
      "openid.identity=http://specs.openid.net/auth/2.0/identifier_select&" +
      "openid.mode=checkid_setup&" +
      "openid.ns=http://specs.openid.net/auth/2.0&" +
      "openid.realm=https://" + REALM_PARAM + "&" +
      "openid.return_to=https://" + REALM_PARAM + "/signin/";
}
