package com.github.karthyks.socialgameworks.login;

import android.app.Dialog;
import android.content.Context;

import com.github.karthyks.socialgameworks.R;


public class SteamDialog extends Dialog {

  private Context context;
  private ILoginCallback loginCallback;

  public SteamDialog(Context context, ILoginCallback loginCallback) {
    super(context);
    this.context = context;
    this.loginCallback = loginCallback;
    this.setContentView(R.layout.dialog_login);
  }
}
