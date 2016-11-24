package com.github.karthyks.socialgameworks.login;


public interface ILoginCallback {

  int SUCCESS = 0;
  int FAILURE = -1;

  void onSuccess(String userId);

  void onFailure();
}
