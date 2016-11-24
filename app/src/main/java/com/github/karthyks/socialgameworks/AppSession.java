package com.github.karthyks.socialgameworks;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.github.karthyks.socialgameworks.authentication.AuthenticationService;
import com.github.karthyks.socialgameworks.model.UserModel;

public class AppSession {
  private static final String TAG = AppSession.class.getSimpleName();
  private static UserModel signedInUser;
  private static final long SYNC_FREQUENCY = 5 * 60 * 60;  // 5 hour (in seconds)
  public static final String SESSION_EXPIRED_ACTION = "session_expired_action";

  private static AppSession instance = new AppSession();
  private static Context context;

  public static AppSession getInstance(Context context) {
    AppSession.context = context;
    return instance;
  }

  private AppSession() {
  }

  public UserModel getAuthenticatedUser() {
    if (signedInUser == null) {
      AccountManager accountManager = AccountManager.get(context);
      Account account = AuthenticationService.getActiveAccount();
      if (account == null) return null;
      signedInUser = UserModel.fromAccount(account, accountManager);
      if (TextUtils.isEmpty(signedInUser.getName())
          || TextUtils.isEmpty(signedInUser.getSteamId())) {
        return null;
      }
    }
    return signedInUser;
  }

  public void onRemoveAuthenticatedUser(Account accountToRemove) {
    signedInUser = null;
  }

  public void broadcastSessionExpire() {
    Intent intent = new Intent();
    intent.setAction(SESSION_EXPIRED_ACTION);
    SocialGameWorks.getAppContext().sendBroadcast(intent);
  }

  public boolean isUserAuthenticated() {
    return (getAuthenticatedUser() != null && !getAuthenticatedUser().isSessionExpired());
  }

  public boolean isUserSessionExpired() {
    return (getAuthenticatedUser() != null && getAuthenticatedUser().isSessionExpired());
  }


  public void setSignedInUser(UserModel userModel) {
    Account account = AuthenticationService.getActiveAccount();
    if (account != null) {
      if (account.name.equals(userModel.getName())) {
        Log.i(TAG, "User already signed in");
      } else {
        throw new UnsupportedOperationException("Not more than one user can signin");
      }
      return;
    }

    AccountManager accountManager = AccountManager.get(context);
    account = new Account(userModel.getName(), AuthenticationService.ACCOUNT_TYPE);
    if (accountManager.addAccountExplicitly(account, "", userModel.toUserBundle())) {
//      // Inform the system that this account supports sync
//      ContentResolver.setIsSyncable(account, LocalStoreContract.CONTENT_AUTHORITY, 1);
//      // Inform the system that this account is eligible for auto sync when the network is up
//      ContentResolver.setSyncAutomatically(account, LocalStoreContract.CONTENT_AUTHORITY, true);
//      // Recommend a schedule for automatic synchronization. The system may modify this based
//      // on other scheduled syncs and network utilization.
//      ContentResolver.addPeriodicSync(
//          account, LocalStoreContract.CONTENT_AUTHORITY, new Bundle(), SYNC_FREQUENCY);
//      // reset the cloud value of gcmid to re upload the id to the cloud.
    }
    signedInUser = userModel;
  }

  public void setUserSessionExpired(boolean isExpired) {
    Account account = AuthenticationService.getActiveAccount();
    if (account != null) {
      AccountManager accountManager = AccountManager.get(context);
      accountManager.setUserData(account, UserModel.PREF_SESSION_STATUS, String.valueOf(isExpired));
    }
    if (signedInUser != null) {
      signedInUser.setSessionExpired(isExpired);
    }
    if (isExpired) {
      broadcastSessionExpire();
    }
  }

  public void updateUserCredentials(UserModel updatedModel) {
    Account account = AuthenticationService.getActiveAccount();
    if (account != null && account.name.equals(updatedModel.getName())) {
      // valid to update the user credentials.
      AccountManager accountManager = AccountManager.get(context);
      accountManager.setPassword(account, "");
      accountManager.setUserData(account, UserModel.PREF_SESSION_STATUS,
          String.valueOf(updatedModel.isSessionExpired()));
      signedInUser = updatedModel;
    }
  }

  public void updateUserBundle(UserModel userModel) {
    Account account = AuthenticationService.getActiveAccount();
    if (account != null && account.name.equals(userModel.getName())) {
      Bundle bundle = userModel.toUserBundle();
      AccountManager accountManager = AccountManager.get(context);
      for (String key : bundle.keySet()) {
        accountManager.setUserData(account, key, bundle.getString(key));
      }
      signedInUser = userModel;
    }
  }
}
