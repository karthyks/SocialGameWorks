package com.github.karthyks.socialgameworks.login;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.github.karthyks.socialgameworks.R;
import com.github.karthyks.socialgameworks.model.UserModel;

import static com.github.karthyks.socialgameworks.utils.SteamUtils.LOGIN_URL;
import static com.github.karthyks.socialgameworks.utils.SteamUtils.REALM_PARAM;


public class SteamBrowserActivity extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_steam_browser);
    WebView steamWebView = (WebView) findViewById(R.id.web_view_browser);
    steamWebView.getSettings().setJavaScriptEnabled(true);
    steamWebView.setWebViewClient(new SteamBrowser());
    steamWebView.loadUrl(LOGIN_URL);
  }

  private class SteamBrowser extends WebViewClient {
    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
      setTitle(url);
      Uri Url = Uri.parse(url);

      if (Url.getAuthority().equals(REALM_PARAM.toLowerCase())) {
        // That means that authentication is finished and the url contains user's id.
        view.stopLoading();

        // Extracts user id.
        Uri userAccountUrl = Uri.parse(Url.getQueryParameter("openid.identity"));
        String userId = userAccountUrl.getLastPathSegment();
        Toast.makeText(SteamBrowserActivity.this, userId, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.putExtra(UserModel.PREF_STEAM_ID, userId);
        SteamBrowserActivity.this.setResult(ILoginCallback.SUCCESS, intent);
        SteamBrowserActivity.this.finish();
      }
    }
  }
}
