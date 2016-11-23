package com.github.karthyks.socialgameworks.splash;

import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.OperationCanceledException;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.karthyks.socialgameworks.AppSession;
import com.github.karthyks.socialgameworks.R;
import com.github.karthyks.socialgameworks.authentication.AuthenticationService;

public class SplashActivity extends AppCompatActivity implements AccountManagerCallback<Bundle> {

  private static final String TAG = SplashActivity.class.getSimpleName();
  private ProgressDialog progressDialog;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);

    if (!AppSession.isUserAuthenticated()) {
      final AccountManager accountManager = AccountManager.get(this);
      if (AppSession.isUserSessionExpired()) {
        accountManager.updateCredentials(AuthenticationService.getActiveAccount(),
            "", null, this, this, null);
      } else {
        accountManager.addAccount(
            AuthenticationService.ACCOUNT_TYPE, null, null, null, SplashActivity.this,
            SplashActivity.this, null);
      }
    } else {
      // User logged in already.
      onLoginSuccess();
    }
  }

  private void onLoginSuccess() {

  }

  @Override
  public void run(AccountManagerFuture<Bundle> accountManagerFuture) {
    try {
      Bundle bundle = accountManagerFuture.getResult();
      Log.i(TAG, "Added account bundle is " + bundle);
      onLoginSuccess();
    } catch (OperationCanceledException e) {
      Log.i(TAG, "authentication operation was cancelled");
      finish();
    } catch (Exception ex) {
      showErrorDialog(ex.getMessage());
    }
  }

  private void showErrorDialog(String message) {
    if (progressDialog != null && progressDialog.isShowing()) {
      progressDialog.dismiss();
    }
    AlertDialog.Builder dialog = new AlertDialog.Builder(this);
    dialog.setMessage(
        "Error(s) occurred. Look into DDMS log for details, " + "please. Errors: " + message)
        .create().show();
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    if (progressDialog != null && progressDialog.isShowing()) {
      progressDialog.dismiss();
    }
  }
}
