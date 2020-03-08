package com.Vikas.GetUrlFromWebview;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    WebView webView;
    String URL1 = "https://stackoverflow.com/questions/60577403/how-to-get-url-from-long-click-links-in-a-webview/60577736?noredirect=1#comment107173847_60577736";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webview);

        webView.clearHistory();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setWebViewClient(new WebViewClient() {

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

            }

            public void onPageFinished(WebView view, String url) {

            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                URL1 = url;
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        webView.loadUrl(URL1);
        // Register the context menu for web view
        registerForContextMenu(webView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        // Get the web view hit test result
        final WebView.HitTestResult result = webView.getHitTestResult();

        // If user long press on url
        if (result.getType() == WebView.HitTestResult.ANCHOR_TYPE ||
                result.getType() == WebView.HitTestResult.SRC_ANCHOR_TYPE) {

            // Set the title for context menu
            menu.setHeaderTitle("\t\t\t\t\t\t\t\t\t\t ◦ ◉ ⦿ Select ⦿ ◉ ◦ \t");

            // Add an item to the menu
            menu.add(0, 1, 0, " \t \t➤\t Show URL")
                    .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            String Pressed_url = result.getExtra();
                            Toast.makeText(MainActivity.this, "URL is:-" + Pressed_url,
                                    Toast.LENGTH_SHORT).show();
                            return false;
                        }
                    });
        }
    }
}
