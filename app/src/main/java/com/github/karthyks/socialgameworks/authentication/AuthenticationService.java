package com.github.karthyks.socialgameworks.authentication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;


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
}
