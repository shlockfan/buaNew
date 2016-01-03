package chenanze.com.buanews.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import chenanze.com.buanews.Constant;
import chenanze.com.buanews.R;
import chenanze.com.buanews.entity.WebContent;

public class ContentDetailWebActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_detail);
        WebView webView = (WebView) findViewById(R.id.content_detail_wv);

        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        webView.setWebViewClient(new MyBrowser());


        WebContent webContent = (WebContent) getIntent().getSerializableExtra(Constant.INTENT_CONTENT_DETAIL_WEB);

        webView.loadUrl(webContent.getUrl());
    }


    // Manages the behavior when URLs are loaded
    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}
