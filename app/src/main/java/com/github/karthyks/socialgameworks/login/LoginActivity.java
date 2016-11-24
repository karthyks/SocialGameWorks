package com.github.karthyks.socialgameworks.login;

import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.github.karthyks.socialgameworks.AppSession;
import com.github.karthyks.socialgameworks.R;
import com.github.karthyks.socialgameworks.authentication.AuthenticationService;
import com.github.karthyks.socialgameworks.model.UserModel;


public class LoginActivity extends AccountAuthenticatorActivity implements View.OnClickListener {

  private static final String TAG = LoginActivity.class.getSimpleName();
  private Button btnLogin;
  private AppSession appSession;

  @Override
  protected void onCreate(Bundle icicle) {
    super.onCreate(icicle);
    setContentView(R.layout.activity_login);
    appSession = AppSession.getInstance(this);
    injectViews();
  }

  private void injectViews() {
    btnLogin = (Button) findViewById(R.id.btn_login);
    btnLogin.setOnClickListener(this);
  }

  private void doLoginStuff(UserModel userModel) {
    Log.i(TAG, "Successful Login " + userModel.toString());
    if (appSession.isUserSessionExpired()) {
      appSession.updateUserCredentials(userModel);
    } else {
      appSession.setSignedInUser(userModel);
    }
    final Intent authResultIntent = new Intent();
    authResultIntent.putExtra(AccountManager.KEY_ACCOUNT_NAME,
        String.valueOf(userModel.getName()));
    authResultIntent.putExtra(AccountManager.KEY_ACCOUNT_TYPE, AuthenticationService.ACCOUNT_TYPE);
    setAccountAuthenticatorResult(authResultIntent.getExtras());
    setResult(RESULT_OK, authResultIntent);
//    dismissDialog();
    finish();
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.btn_login:
        startActivityForResult(new Intent(this, SteamBrowserActivity.class), 300);
        break;
      default:
    }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == 300) {
      switch (resultCode) {
        case ILoginCallback.SUCCESS:
          if (data != null) {
            String userId = data.getStringExtra(UserModel.PREF_STEAM_ID);
            UserModel userModel = new UserModel(userId, "Name", "Email");
            doLoginStuff(userModel);
          }
          break;
        default:
          super.onActivityResult(requestCode, resultCode, data);
      }
    }
  }
}
