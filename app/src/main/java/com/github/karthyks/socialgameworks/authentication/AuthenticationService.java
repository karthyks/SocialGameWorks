package com.github.karthyks.socialgameworks.authentication;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.github.karthyks.socialgameworks.SocialGameWorks;


public class AuthenticationService extends Service {
  private static final String TAG = AuthenticationService.class.getSimpleName();
  // Value below must match the account type specified in res/xml/syncadapter.xml
  public static final String ACCOUNT_TYPE = "com.github.karthyks.socialgameworks.account";
  private Authenticator mAuthenticator;

  @Override
  public void onCreate() {
    mAuthenticator = new Authenticator(this);
  }

  @Override
  public IBinder onBind(Intent intent) {
    return mAuthenticator.getIBinder();
  }

  @SuppressWarnings("MissingPermission")
  public static Account getActiveAccount() {
    AccountManager accountManager = AccountManager.get(SocialGameWorks.getAppContext());
    Account[] accounts = accountManager.getAccountsByType(ACCOUNT_TYPE);
    if (accounts.length > 0) {
      return accounts[0];
    }
    return null;
  }
}
