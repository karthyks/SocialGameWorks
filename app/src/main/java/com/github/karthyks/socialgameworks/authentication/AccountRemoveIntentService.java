package com.github.karthyks.socialgameworks.authentication;

import android.accounts.Account;
import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.github.karthyks.socialgameworks.AppSession;

public class AccountRemoveIntentService extends IntentService {
  private static final String TAG = AccountRemoveIntentService.class.getSimpleName();
  public static final String ACCOUNT_TO_REMOVE = "account_to_remove";

  public AccountRemoveIntentService() {
    super("AccountRemoveIntentService");
  }

  @Override
  protected void onHandleIntent(Intent intent) {
    Account accountToRemove = intent.getParcelableExtra(ACCOUNT_TO_REMOVE);
    AppSession appSession = AppSession.getInstance(this);
    try {
      Log.i(TAG, "Process started to remove account " + accountToRemove.name);
      appSession.onRemoveAuthenticatedUser(accountToRemove);
      appSession.broadcastSessionExpire();
    } catch (Exception e) {
      e.printStackTrace();
      Log.i(TAG, "Error @ " + e.toString());
    }
  }
}
