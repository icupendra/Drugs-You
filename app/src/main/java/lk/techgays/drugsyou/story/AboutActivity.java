package lk.techgays.drugsyou.story;

import android.app.Activity;
import android.graphics.Color;
import android.view.Window;
import android.webkit.WebView;
import android.os.Bundle;
import lk.techgays.drugsyou.R;

public class AboutActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);

		WebView webview = findViewById(R.id.webView1);
		webview.setBackgroundColor(getResources().getColor(R.color.background));
		webview.getSettings().setDefaultTextEncodingName("UTF-8");
		webview.setBackgroundColor(Color.WHITE);
		webview.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
		// Get the Android assets folder path
		String Path = "file:///android_asset/index.html";
		webview.loadUrl(Path);

	}

}
