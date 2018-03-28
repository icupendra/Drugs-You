package lk.techgays.drugsyou.story;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

class CheckUpdate {

	private String FAppID;
	private Context FC;

	void check(String AppID, Context C) {
		try {

		FAppID =AppID;
		  FC = C;

			AsyncTaskRunner runner = new AsyncTaskRunner();
			runner.execute("https://play.google.com/store/apps/details?id="+AppID);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public class AsyncTaskRunner extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected String doInBackground(String... params) {
			String str = "";
			try {
				URL url = new URL(params[0]);
				URLConnection spoof = url.openConnection();
				spoof.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0; H010818)");
				BufferedReader in = new BufferedReader(new InputStreamReader(spoof.getInputStream()));
				String strLine = "";
				// Loop through every line in the source
				while ((strLine = in.readLine()) != null) {
					str = str + strLine;
				}
			} catch (Exception e) {

				e.printStackTrace();
			}
			return str;

		}

		@Override
		protected void onPostExecute(String s) {

			try {

				Pattern pattern = Pattern.compile("Current Version\">[^<]*</dd");
				Matcher matcher = pattern.matcher(s);
				final boolean b = matcher.find();
				if (b) {
					String MarketVersionName = matcher.group(0).substring(matcher.group(0).indexOf(">") + 1, matcher.group(0).indexOf("<"));
					Log.d("VersionAvailble", MarketVersionName);
					String ExistingVersionName = FC.getPackageManager().getPackageInfo(FC.getPackageName(), 0).versionName;
					Log.d("VersionActual", ExistingVersionName);
					if (!MarketVersionName.equals(ExistingVersionName)) {
						AlertDialog.Builder alertDialog = new AlertDialog.Builder(FC);
						alertDialog.setTitle("Update Availble");
						alertDialog.setMessage("You are using " + ExistingVersionName + " version\n" + MarketVersionName + " version is availble\nDo you which to download it?");
						alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
								try {
									FC.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + FAppID)));
								} catch (android.content.ActivityNotFoundException anfe) {
									FC.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id" + FAppID)));
								}
							}
						});
						alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
							}
						});
						alertDialog.show();
					}

				}
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
	}
	private String GetHtml(String url1) {
		String str = "";
		try {
			URL url = new URL(url1);
			URLConnection spoof = url.openConnection();
			spoof.setRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0; H010818)");
			BufferedReader in = new BufferedReader(new InputStreamReader(spoof.getInputStream()));
			String strLine = "";
			// Loop through every line in the source
			while ((strLine = in.readLine()) != null) {
				str = str + strLine;
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return str;
	}
}