package com.github.karthyks.socialgameworks.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.github.karthyks.socialgameworks.AppSession;
import com.github.karthyks.socialgameworks.R;


public class MainActivity extends AppCompatActivity {

  private TextView tvUserId;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    tvUserId = (TextView) findViewById(R.id.tv_user_id);
    tvUserId.setText(AppSession.getInstance(this).getAuthenticatedUser().getSteamId());
  }
}
