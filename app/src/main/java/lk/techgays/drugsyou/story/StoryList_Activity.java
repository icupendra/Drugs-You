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
import lk.techgays.drugsyou.R;
import lk.techgays.drugsyou.adapter.StoryList_Adapter;
import lk.techgays.drugsyou.item.Item_StoryList;
import lk.techgays.drugsyou.xmlhandler.StoryList_XMLHandler;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class StoryList_Activity extends Activity {

	private StoryList_Adapter adapterstorylistry;
	private ListView lsv;
	private ArrayList<Item_StoryList> itemsListstorylist;
	private ArrayList<Item_StoryList> newStoryList;
	private Item_StoryList objLatestBean;
	private EditText edtsearch;
	private String id,LanguageId="SI",SubCatId="";
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
	private int currentPosition = 0;

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
		newStoryList =  new ArrayList<>();

		Intent i=getIntent();

		/*currentPosition=i.getIntExtra("POSITION",0);
		allArrayStorylisid=i.getStringArrayExtra("StoryId");
		allArrayStorylisttitle=i.getStringArrayExtra("StoryTitle");
		allArrayStorylistdes=i.getStringArrayExtra("StoryDes");*/

		id=i.getStringExtra("id");
		SubCatId = i.getStringExtra("SubCatId");
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
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {
				// TODO Auto-generated method stub
				objLatestBean=itemsListstorylist.get(position);
				int pos= Integer.parseInt(objLatestBean.getStoryId());

				Intent intshow=new Intent(StoryList_Activity.this,Full_Story_Activity.class);
				intshow.putExtra("POSITION", pos);
				intshow.putExtra("StoryId", allArrayStorylisid);
				intshow.putExtra("StoryTitle", allArrayStorylisttitle);
				intshow.putExtra("StoryDes", allArrayStorylistdes);
				intshow.putExtra("LanguageId", LanguageId);
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
			StoryList_XMLHandler myXMLHandler = new StoryList_XMLHandler();
			xr.setContentHandler(myXMLHandler);

			if (LanguageId.equalsIgnoreCase("SI")) {

				xr.parse(new InputSource(getAssets().open("Sinhala/categoryitem_"+id+".xml")));

			}else
			{
				xr.parse(new InputSource(getAssets().open("Tamil/categoryitem_"+id+".xml")));
			}


			itemsListstorylist = myXMLHandler.getItemsList();


		} catch (Exception e) {
			Log.w("AndroidParseXMLActivity", e);

		}
		for(int i=0;i<itemsListstorylist.size();i++)
		{
			objLatestBean=itemsListstorylist.get(i);

			if (objLatestBean.getSubCatId().equalsIgnoreCase(SubCatId.trim())) {


				allListStorylisid.add(objLatestBean.getStoryId());
				allArrayStorylisid = allListStorylisid.toArray(allArrayStorylisid);

				allListStorylisttitle.add(objLatestBean.getStoryTitle());
				allArrayStorylisttitle = allListStorylisttitle.toArray(allArrayStorylisttitle);

				allListStorylistdes.add(objLatestBean.getStoryDes());
				allArrayStorylistdes = allListStorylistdes.toArray(allArrayStorylistdes);

				newStoryList.add(objLatestBean);

			}else if (objLatestBean.getSubCatId().equalsIgnoreCase("0")) {

				allListStorylisid.add(objLatestBean.getStoryId());
				allArrayStorylisid = allListStorylisid.toArray(allArrayStorylisid);

				allListStorylisttitle.add(objLatestBean.getStoryTitle());
				allArrayStorylisttitle = allListStorylisttitle.toArray(allArrayStorylisttitle);

				allListStorylistdes.add(objLatestBean.getStoryDes());
				allArrayStorylistdes = allListStorylistdes.toArray(allArrayStorylistdes);
				newStoryList.add(objLatestBean);
			}
		}

		adapterstorylistry=new StoryList_Adapter(StoryList_Activity.this, newStoryList );
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


							Item_StoryList objItem = new Item_StoryList();


							objItem.setStoryId(allArrayStorylisid[i]);
							objItem.setStoryTitle(allArrayStorylisttitle[i]);
							objItem.setStoryDes(allArrayStorylistdes[i]);
						 
							itemsListstorylist.add(objItem);

						}
					}
				}

				adapterstorylistry=new StoryList_Adapter(StoryList_Activity.this, itemsListstorylist );
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
 