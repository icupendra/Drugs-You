package lk.techgays.drugsyou.story;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import lk.techgays.drugsyou.adapter.Category_Adapter;
import lk.techgays.drugsyou.item.Item_Category;
import lk.techgays.drugsyou.xmlhandler.Category_XMLHandler;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.util.ArrayList;
import lk.techgays.drugsyou.R;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class Category_Activity extends Activity {

    private ListView gridview;
	private ArrayList<Item_Category> itemsList;
	private Item_Category objLatestBean;
    private String LanguageId ="NON";
	CheckUpdate CheckUpdate;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.category_activty);

		Button btn_Ask = findViewById(R.id.btn_Ask);

		Intent iin= getIntent();
		Bundle b = iin.getExtras();

		if(b!=null)
		{
			LanguageId =(String) b.get("LanguageId");

		}else {
			LanguageId="SI";
		}



		gridview= findViewById(R.id.main_gridview);
        Button btn_favo = findViewById(R.id.btn_favo);

		parseXML();

		btn_favo.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intentfav=new Intent(getApplicationContext(),Favorite_Activity.class);
				intentfav.putExtra("LanguageId", LanguageId);
				startActivity(intentfav);
			}
		});

		gridview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
				// TODO Auto-generated method stub
				objLatestBean=itemsList.get(arg2);
				String list= objLatestBean.getCatId();

				if (objLatestBean.getSubCatStatus().equalsIgnoreCase("true")) {

					Intent intentlist = new Intent(getApplicationContext(), SubCategory_Activity.class);
					intentlist.putExtra("id", list);
					intentlist.putExtra("LanguageId", LanguageId);
					startActivity(intentlist);

				}else {

					Intent intentlist = new Intent(getApplicationContext(), StoryList_Activity.class);
					intentlist.putExtra("id", list);
					intentlist.putExtra("LanguageId", LanguageId);
					intentlist.putExtra("SubCatId", "0");
					startActivity(intentlist);
				}
			}
		});

		btn_Ask.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				// Toast.makeText(appContext, "BAck", Toast.LENGTH_LONG).show();
				AlertDialog.Builder alert = new AlertDialog.Builder(
						Category_Activity.this);
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

	private void parseXML() {


		try {

			Log.w("AndroidParseXMLActivity", "Start");
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			XMLReader xr = sp.getXMLReader();
			Category_XMLHandler myXMLHandler = new Category_XMLHandler();
			xr.setContentHandler(myXMLHandler);

			if (LanguageId.equalsIgnoreCase("SI")) {
				xr.parse(new InputSource(getAssets().open("Sinhala/category.xml")));

			}else
			{
				xr.parse(new InputSource(getAssets().open("Tamil/category.xml")));
			}
			itemsList = myXMLHandler.getItemsList();

		} catch (Exception e) {
			Log.w("AndroidParseXMLActivity", e);
		}


        Category_Adapter adaptercategory = new Category_Adapter(Category_Activity.this, itemsList);
		gridview.setAdapter(adaptercategory);

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

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// Toast.makeText(appContext, "BAck", Toast.LENGTH_LONG).show();
			AlertDialog.Builder alert = new AlertDialog.Builder(
					Category_Activity.this);
			alert.setTitle(getString(R.string.app_name));
			alert.setIcon(R.drawable.logo);
			alert.setMessage("Are You Sure You Want To Quit?");

			alert.setPositiveButton("Yes",
					new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,
						int whichButton) {
					//final String appName = getPackageName();
					//CheckUpdate = new CheckUpdate();
					//CheckUpdate.check(appName,getApplication());
				  finish();
				}



			});

			alert.setNegativeButton("Rate App",
				     new DialogInterface.OnClickListener() {

				      public void onClick(DialogInterface dialog, int which) {
				       
				       final String appName = getPackageName();
				       try {
				        startActivity(new Intent(Intent.ACTION_VIEW,
				          Uri.parse("market://details?id="
				            + appName)));
				       } catch (android.content.ActivityNotFoundException anfe) {
				        startActivity(new Intent(
				          Intent.ACTION_VIEW,
				          Uri.parse("http://play.google.com/store/apps/details?id="
				            + appName)));
				       }
				      }
				     });
				   alert.show();
				   return true;
				  }

				  return super.onKeyDown(keyCode, event);

				 }
				 

			}
