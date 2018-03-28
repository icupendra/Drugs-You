package lk.techgays.drugsyou.story;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import lk.techgays.drugsyou.R;
import lk.techgays.drugsyou.favorite.DatabaseHandler;
import lk.techgays.drugsyou.favorite.Pojo;

import java.util.List;

public class Full_Story_Activity extends Activity {


	private ViewPager viewpagermain;
	private int currentPosition = 0;
	private String[] allArrayStorylisid;
	private String[] allArrayStorylisttitle;
	private String[] allArrayStorylistdes;
	private Button btn_addfavo;
	private DatabaseHandler db;
	private String Story_Id,LanguageId="SI";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.full_story_activity);


		Button btn_backfull = findViewById(R.id.btn_backicondetails);
		btn_addfavo= findViewById(R.id.btn_favadd);
		Button btn_share = findViewById(R.id.btn_share);

		Intent i=getIntent();
		currentPosition=i.getIntExtra("POSITION",0);
		allArrayStorylisid=i.getStringArrayExtra("StoryId");
		allArrayStorylisttitle=i.getStringArrayExtra("StoryTitle");
		allArrayStorylistdes=i.getStringArrayExtra("StoryDes");
		LanguageId=i.getStringExtra("LanguageId");

		viewpagermain= findViewById(R.id.story_viewepager);
		ImagePagerAdapter adapter = new ImagePagerAdapter();
		viewpagermain.setAdapter(adapter);


		boolean found = false;
		int j1=0;
		for(int i1=0;i1<allArrayStorylisid.length;i1++)
		{
			if(allArrayStorylisid[i1].contains(String.valueOf(currentPosition)))
			{
				found=true;
				j1=i1;
				break; 
			}
		}
		if(found)
		{
			viewpagermain.setCurrentItem(j1);
		}



		db=new DatabaseHandler(this);

		FirstFav();

		btn_backfull.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();

			}
		});

		viewpagermain.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub

				position=viewpagermain.getCurrentItem();

				Story_Id=allArrayStorylisid[position];



				List<Pojo> pojolist=db.getFavRow(Story_Id);

				if(pojolist.size()==0)
				{
					btn_addfavo.setBackgroundResource(R.drawable.addfavorite);

				}
				else
				{	
					if(pojolist.get(0).getSId().equals(Story_Id))
					{
						btn_addfavo.setBackgroundResource(R.drawable.favorite);

					}

				}


			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});

		btn_addfavo.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				currentPosition=viewpagermain.getCurrentItem();
				Story_Id=allArrayStorylisid[currentPosition];
				List<Pojo> pojolist=db.getFavRow(Story_Id);

				if(pojolist.size()==0)
				{
					AddtoFav(currentPosition);//if size is zero i.e means that record not in database show add to favorite 
				}
				else
				{	
					if(pojolist.get(0).getSId().equals(Story_Id))
					{
						RemoveFav(currentPosition);
					}

				}
			}
		});


		btn_share.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				// Toast.makeText(appContext, "BAck", Toast.LENGTH_LONG).show();
				AlertDialog.Builder alert = new AlertDialog.Builder(
						Full_Story_Activity.this);
				alert.setTitle("any question ?");
				alert.setIcon(R.drawable.question);
				alert.setMessage("any question please contact us.");

				alert.setPositiveButton("ask question",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
												int whichButton) {

								String mailto = "mailto:thushiu16@gmail.com" +
										"?cc=" + "" +
										"&subject=" + Uri.encode("Ask Drugs & You") +
										"&body=" + Uri.encode("type your question here ");

								Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
								emailIntent.setData(Uri.parse(mailto));

								try {
									startActivity(emailIntent);
								} catch (ActivityNotFoundException e) {
									e.printStackTrace();
								}

								/*// TODO Auto-generated method stub
								int first=viewpagermain.getCurrentItem();
								Intent sendIntent = new Intent();
								sendIntent.setAction(Intent.ACTION_SEND);
								sendIntent.putExtra(Intent.EXTRA_TEXT,allArrayStorylisttitle[first]+"-"+allArrayStorylistdes[first]);
								sendIntent.setType("text/plain");
								startActivity(sendIntent);*/
							}



						});

				alert.setNegativeButton("cancel",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog, int which) {

								dialog.dismiss();
							}
						});
				alert.show();



			}
		});

	}

	private class ImagePagerAdapter extends PagerAdapter {

		private final LayoutInflater inflater;

		public ImagePagerAdapter() {
			// TODO Auto-generated constructor stub

			inflater = getLayoutInflater();
		}


		@Override
		public int getCount() {
			return allArrayStorylistdes.length;

		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view.equals(object);
		}


		@Override
		public Object instantiateItem(ViewGroup container, final int position) {

			View imageLayout = inflater.inflate(R.layout.show_activity, container, false);
			assert imageLayout != null;

			Typeface tf = Typeface.createFromAsset(getApplication().getAssets(), "fonts/NotoSansSinhala-Regular.ttf");

			TextView txttitle= imageLayout.findViewById(R.id.text_storytitle);
			txttitle.setTypeface(tf);
			txttitle.setText(allArrayStorylisttitle[position]);
			WebView webview = imageLayout.findViewById(R.id.webView1);
			webview.setBackgroundColor(getResources().getColor(R.color.background));
			//webview.getSettings().setBuiltInZoomControls(true);

			webview.getSettings().setDefaultTextEncodingName("UTF-8");
			webview.setBackgroundColor(Color.WHITE);
			webview.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
			String htmlText = allArrayStorylistdes[position];

/*			String mimeType = "text/html;charset=UTF-8";
			String encoding = "utf-8";
			String htmlText = allArrayStorylistdes[position];

			String text = "<html><head>"
					+ "<style "
					+ "type=\"text/css\">body{color: #ffffff;}"
					+"@font-face {font-family: myFirstFont;src: url" +
					"(\"file:///android_asset/fonts/NotoSansSinhala-Regular.ttf\");}div "
					+ "{font-family: myFirstFont;}"
					+ "</style>"
					+ "</head>"
					+ "<body>"
					+  "<div>"+ htmlText + "</div>"
					+ "</body></html>";*/
			String folderPath;

			if (LanguageId.equalsIgnoreCase("SI")) {

				// Get the Android assets folder path
				 folderPath = "file:///android_asset/Sinhala/";


			}else
			{
				// Get the Android assets folder path
				 folderPath = "file:///android_asset/Tamil/";

			}


			// Get the HTML file name
			// Get the exact file location
			String file = folderPath + htmlText;

			webview.loadUrl(file);

			//webview.loadData(text, mimeType, encoding);

			container.addView(imageLayout, 0);
			return imageLayout;

		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

		//Activity context=Full_Story_Activity.this;


	}


	private void AddtoFav(int position)
	{
		Story_Id=allArrayStorylisid[position];
		String story_Title = allArrayStorylisttitle[position];
		String story_Des = allArrayStorylistdes[position];




		db.AddtoFavorite(new Pojo(Story_Id, story_Title, story_Des));
		Toast.makeText(getApplicationContext(), "Added to Favorite", Toast.LENGTH_SHORT).show();
		btn_addfavo.setBackgroundResource(R.drawable.favorite);

	}

	//remove from favorite
	private void RemoveFav(int position)
	{
		Story_Id=allArrayStorylisid[position];
		db.RemoveFav(new Pojo(Story_Id));
		Toast.makeText(getApplicationContext(), "Removed from Favorite", Toast.LENGTH_SHORT).show();
		btn_addfavo.setBackgroundResource(R.drawable.addfavorite);

	}


	private void FirstFav()
	{

		int first=viewpagermain.getCurrentItem();
		String Image_id=allArrayStorylisid[first];

		List<Pojo> pojolist=db.getFavRow(Image_id);
		if(pojolist.size()==0)
		{

			btn_addfavo.setBackgroundResource(R.drawable.addfavorite);

		}
		else
		{	
			if(pojolist.get(0).getSId().equals(Image_id))
			{

				btn_addfavo.setBackgroundResource(R.drawable.favorite);

			}

		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;


	}
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId()) 
		{

		case R.id.About:
			Intent intentabout=new Intent(getApplicationContext(),AboutActivity.class);
			startActivity(intentabout);

		}

		return(super.onOptionsItemSelected(item));
	}



}

