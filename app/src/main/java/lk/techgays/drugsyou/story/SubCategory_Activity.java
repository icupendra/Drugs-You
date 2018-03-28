package lk.techgays.drugsyou.story;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import lk.techgays.drugsyou.R;
import lk.techgays.drugsyou.adapter.Sub_Category_Adapter;
import lk.techgays.drugsyou.item.Item_Sub_Category;
import lk.techgays.drugsyou.xmlhandler.SubCategory_XMLHandler;

public class SubCategory_Activity extends Activity {

	private Sub_Category_Adapter adapterstorylistry;
	private ListView lsv;
	private ArrayList<Item_Sub_Category> itemsListstorylist;
	private ArrayList<Item_Sub_Category> subCatItemsList;
	private Item_Sub_Category objLatestBean;
	private EditText edtsearch;
	private String id,LanguageId="SI",SubCatId;
	private String[] allArrayStorylisid;
	private String[] allArrayStorylisttitle;
	private String[] allArrayStorylistdes;
	private String[] allArraySubCatStatus;
	private String[] allArraySub_cat_name;
	private ArrayList<String> allListStorylisid;
	private ArrayList<String> allListStorylisttitle;
	private ArrayList<String> allListStorylistdes;
	private ArrayList<String> allListSub_cat_name;
	private ArrayList<String> allListSubCatStatus;
	private int textlength = 0;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.storylist_activity);

		lsv= findViewById(R.id.listView1);
		edtsearch= findViewById(R.id.edit_search);
		edtsearch.requestFocusFromTouch();
		Button btnbacklist = findViewById(R.id.btn_backiconlist);

		allListStorylisid= new ArrayList<>();
		allListStorylisttitle= new ArrayList<>();
		allListStorylistdes= new ArrayList<>();
		allListSub_cat_name = new ArrayList<>();
		allListSubCatStatus = new ArrayList<>();

		allArrayStorylisid=new String[allListStorylisid.size()];
		allArrayStorylisttitle=new String[allListStorylisttitle.size()];
		allArrayStorylistdes=new String[allListStorylistdes.size()];
		allArraySub_cat_name = new String[allListSub_cat_name.size()];
		allArraySubCatStatus = new String[allListSubCatStatus.size()];

		subCatItemsList =  new ArrayList<>();
 
		Intent i=getIntent();
		id=i.getStringExtra("id");
		LanguageId=i.getStringExtra("LanguageId");

		parseXML();

		btnbacklist.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
			}
		});


		lsv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				// TODO Auto-generated method stub
				objLatestBean=itemsListstorylist.get(position);
				SubCatId = objLatestBean.getSubCatId();
				int pos= Integer.parseInt(objLatestBean.getSubCatId());

				Intent intshow=new Intent(SubCategory_Activity.this,StoryList_Activity.class);
				intshow.putExtra("POSITION", pos);
				intshow.putExtra("StoryId", allArrayStorylisid);
				intshow.putExtra("StoryTitle", allArrayStorylisttitle);
				intshow.putExtra("StoryDes", allArrayStorylistdes);
				intshow.putExtra("LanguageId", LanguageId);
				intshow.putExtra("id", id);
				intshow.putExtra("SubCatId", SubCatId);
 				startActivity(intshow);
			}
		});
	}

	private void parseXML() {

		try {

			Log.w("AndroidParseXMLActivity", "Start");
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			XMLReader xr = sp.getXMLReader();
			SubCategory_XMLHandler myXMLHandler = new SubCategory_XMLHandler();
			xr.setContentHandler(myXMLHandler);

			if (LanguageId.equalsIgnoreCase("SI")) {

				xr.parse(new InputSource(getAssets().open("Sinhala/categorySubitem_"+id+".xml")));

			}else
			{
				xr.parse(new InputSource(getAssets().open("Tamil/categorySubitem_"+id+".xml")));
			}


			itemsListstorylist = myXMLHandler.getItemsList();


		} catch (Exception e) {
			Log.w("AndroidParseXMLActivity", e);

		}

		for(int i=0;i<itemsListstorylist.size();i++)
		{
			objLatestBean=itemsListstorylist.get(i);

			if (objLatestBean.getSubCatStatus().equalsIgnoreCase("true")) {

			allListStorylisid.add(objLatestBean.getCatId());
			allArrayStorylisid=allListStorylisid.toArray(allArrayStorylisid);

			allListStorylisttitle.add(objLatestBean.getSubCatId());
			allArrayStorylisttitle=allListStorylisttitle.toArray(allArrayStorylisttitle);

			allListSub_cat_name.add(objLatestBean.getSubCatName());
			allArraySub_cat_name = allListSub_cat_name.toArray(allArraySub_cat_name);

			allListSubCatStatus.add(objLatestBean.getSubCatStatus());
			allArraySubCatStatus = allListSubCatStatus.toArray(allArraySubCatStatus);

			subCatItemsList.add(objLatestBean);

			}
		}



		adapterstorylistry=new Sub_Category_Adapter(SubCategory_Activity.this, subCatItemsList );
		lsv.setAdapter(adapterstorylistry);



		edtsearch.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence cs, int start, int before, int count) {
				// TODO Auto-generated method stub
				//StoryList_Activity.this.adapterstorylistry.getFilter().filter(cs.toString());


				textlength = edtsearch.getText().length();

				itemsListstorylist.clear();

				for(int i=0;i< allArrayStorylisttitle.length;i++)
				{
					if(textlength <= allArrayStorylisttitle[i].length())
					{
						if(edtsearch.getText().toString().equalsIgnoreCase((String) allArrayStorylisttitle[i].subSequence(0, textlength)))
						{


							Item_Sub_Category objItem = new Item_Sub_Category();


							objItem.setCatId(allArrayStorylisid[i]);
							objItem.setSubCatId(allArrayStorylisttitle[i]);

						 
							itemsListstorylist.add(objItem);

						}
					}
				}

				adapterstorylistry=new Sub_Category_Adapter(SubCategory_Activity.this, itemsListstorylist );
				lsv.setAdapter(adapterstorylistry);


			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
				// TODO Auto-generated method stub

			}
		});   

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
 