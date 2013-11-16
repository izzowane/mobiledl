package org.mobiledl.dlverifier;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class DDSViewActivity extends Activity {
	private static final String TAG = DDSViewActivity.class.getSimpleName();
	WebView webView = null;
	String myDlNO = "";
	public static final String EXTRA_DLNO = "dlno";
	public static final String EXTRA_BARCODE_DATA = "barcodedata";

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webview);

		webView = (WebView) findViewById(R.id.webView);

		webView.setInitialScale(1);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setLoadWithOverviewMode(true);
		webView.getSettings().setUseWideViewPort(true);
		webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		webView.setScrollbarFadingEnabled(false);
		
		myDlNO = getIntent().getStringExtra(EXTRA_DLNO);

		Log.d(TAG, " onCreate() myDlNO = [" + myDlNO + "]");

		webView.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(webView, url);
				fillTextField();
			}
		});

		webView.loadUrl("https://online.dds.ga.gov/DLStatus/");

	}

	@Override
	protected void onStart() {
		super.onStart();

		myDlNO = getIntent().getStringExtra(EXTRA_DLNO);

		Log.d(TAG, " onStart() myDlNO = [" + myDlNO + "]");
		
		
	}

	private void fillTextField() {

		if (myDlNO != null && !myDlNO.isEmpty()) {
			webView.loadUrl("javascript:document.getElementById('MainBody_txtDLNumber').value ='"
					+ myDlNO + "';");
		}
		// webView.loadUrl("javascript:document.getElementById('btnContinue').click();");
	}

}
