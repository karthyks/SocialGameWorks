package com.github.karthyks.socialgameworks;

import android.app.Application;
import android.content.Context;


public class SocialGameWorks extends Application {

  private static Context context;

  @Override
  public void onCreate() {
    context = this.getApplicationContext();
    super.onCreate();
  }

  public static Context getAppContext() {
    return SocialGameWorks.context;
  }
}
