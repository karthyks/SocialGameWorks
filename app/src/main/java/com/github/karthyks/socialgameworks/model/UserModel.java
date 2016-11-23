package com.github.karthyks.socialgameworks.model;


import android.accounts.Account;
import android.accounts.AccountManager;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.util.Locale;

public class UserModel implements Parcelable {

  public static final String PREF_STEAM_ID = "steam_id";
  public static final String PREF_NAME = "name";
  public static final String PREF_EMAIL = "email";
  public static final String PREF_SESSION_STATUS = "session_status";

  @SerializedName("steamid")
  private String steamId;
  @SerializedName("name")
  private String name;
  @SerializedName("email")
  private String email;

  private boolean isSessionExpired;

  public UserModel() {

  }

  public UserModel(Parcel parcel) {
    fromBundle(this, parcel.readBundle());
  }

  public UserModel(String steamId, String name, String email) {
    this.steamId = steamId;
    this.name = name;
    this.email = email;
  }

  private void fromBundle(UserModel userModel, Bundle bundle) {
    userModel.steamId = bundle.getString(PREF_STEAM_ID);
    userModel.name = bundle.getString(PREF_NAME);
    userModel.email = bundle.getString(PREF_EMAIL);
    userModel.isSessionExpired = bundle.getBoolean(PREF_SESSION_STATUS);
  }

  public Bundle toUserBundle() {
    Bundle bundle = new Bundle();
    bundle.putString(PREF_STEAM_ID, steamId);
    bundle.putString(PREF_NAME, name);
    bundle.putString(PREF_EMAIL, email);
    bundle.putString(PREF_SESSION_STATUS, String.valueOf(isSessionExpired));
    return bundle;
  }

  public static UserModel fromAccount(Account account, AccountManager accountManager) {
    UserModel userModel = new UserModel();
    userModel.setName(account.name);
    userModel.setSteamId(accountManager.getUserData(account, PREF_STEAM_ID));
    userModel.setEmail(accountManager.getUserData(account, PREF_EMAIL));
    if (!TextUtils.isEmpty(accountManager.getUserData(account, PREF_SESSION_STATUS))) {
      userModel.setSessionExpired(Boolean.valueOf(accountManager.getUserData(account,
          PREF_SESSION_STATUS)));
    } else {
      userModel.setSessionExpired(false);
    }
    return userModel;
  }

  public static final Creator<UserModel> CREATOR = new Creator<UserModel>() {
    @Override
    public UserModel createFromParcel(Parcel in) {
      return new UserModel(in);
    }

    @Override
    public UserModel[] newArray(int size) {
      return new UserModel[size];
    }
  };

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel parcel, int i) {
    parcel.writeBundle(toUserBundle());
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSteamId() {
    return steamId;
  }

  public void setSteamId(String steamId) {
    this.steamId = steamId;
  }

  public boolean isSessionExpired() {
    return isSessionExpired;
  }

  public void setSessionExpired(boolean sessionExpired) {
    isSessionExpired = sessionExpired;
  }

  @Override
  public String toString() {
    return String.format(Locale.getDefault(), "name: %s, steamId: %s, email: %s",
        name, steamId, email);
  }
}
