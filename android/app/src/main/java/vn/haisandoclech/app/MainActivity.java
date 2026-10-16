package vn.haisandoclech.app;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Window;
import android.view.View;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;

public class MainActivity extends Activity {
    private WebView webView;
    private static final String STORE_URL = "https://haisandoclech.vn/";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = getWindow();
        window.setStatusBarColor(Color.rgb(8, 126, 174));
        window.setNavigationBarColor(Color.rgb(8, 126, 174));

        // Use a native root container to reserve the system-bar safe area.
        // This is more reliable than applying insets directly to WebView on
        // Android 15+ (target SDK 35), where edge-to-edge is enforced.
        FrameLayout root = new FrameLayout(this);
        root.setBackgroundColor(Color.rgb(8, 126, 174));

        webView = new WebView(this);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setSupportZoom(true);

        if (android.os.Build.VERSION.SDK_INT >= 30) {
            // Android 15 enforces edge-to-edge for apps targeting SDK 35.
            // Apply insets to the ROOT container and consume them so WebView
            // doesn't apply the same inset a second time (double padding).
            window.setDecorFitsSystemWindows(false);
            root.setOnApplyWindowInsetsListener((view, insets) -> {
                android.graphics.Insets bars = insets.getInsets(
                    WindowInsets.Type.systemBars() | WindowInsets.Type.displayCutout()
                );
                view.setPadding(bars.left, bars.top, bars.right, bars.bottom);
                return WindowInsets.CONSUMED;
            });
        }

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                String scheme = request.getUrl().getScheme();
                return !("https".equalsIgnoreCase(scheme) || "http".equalsIgnoreCase(scheme));
            }
        });
        webView.setWebChromeClient(new WebChromeClient());
        root.addView(webView, new FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        ));
        setContentView(root);

        if (savedInstanceState == null) {
            webView.loadUrl(STORE_URL);
        } else {
            webView.restoreState(savedInstanceState);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (webView != null) {
            webView.saveState(outState);
        }
    }

    @Override
    public void onBackPressed() {
        if (webView != null && webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
