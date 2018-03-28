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
import android.widget.TextView;
import lk.techgays.drugsyou.R;
import lk.techgays.drugsyou.adapter.Favorite_Adapter;
import lk.techgays.drugsyou.favorite.DatabaseHandler;
import lk.techgays.drugsyou.favorite.Pojo;


import java.util.ArrayList;
import java.util.List;

public class Favorite_Activity extends Activity {

	private ListView listfavo;
	private DatabaseHandler db;
	private Favorite_Adapter favo_adapter;
	private List<Pojo> allData;
 	private Pojo pojoitem;
	private TextView txt_no;
	private EditText edtsearch;
	private int textlength = 0;
	private String[] allArrayStorylisid;
	private String[] allArrayStorylisttitle;
	private String[] allArrayStorylistdes;
	String LanguageId="SI";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.favorite_activity);

		Intent i=getIntent();
		LanguageId=i.getStringExtra("LanguageId");
		db=new DatabaseHandler(getApplicationContext());
		txt_no= findViewById(R.id.text_no_fav);
		listfavo= findViewById(R.id.listView_fav);
		edtsearch= findViewById(R.id.edit_search);
		Button btn_baackfav = findViewById(R.id.btn_backiconfav);
		allData=db.getAllData();
		if(allData.size()==0)
		{
			listfavo.setAdapter(null);
			txt_no.setVisibility(View.VISIBLE);

		}
		else
		{
			favo_adapter=new Favorite_Adapter(Favorite_Activity.this, allData);
			listfavo.setAdapter(favo_adapter);
			txt_no.setVisibility(View.GONE);
		}




		btn_baackfav.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
			}
		});

		listfavo.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {
				// TODO Auto-generated method stub
				pojoitem=allData.get(position);
				int pos= Integer.parseInt(pojoitem.getSId());
				Log.e("pos", ""+pos);
				
 				Intent intshow=new Intent(Favorite_Activity.this,Full_Story_Activity.class);
				intshow.putExtra("POSITION", pos);
				intshow.putExtra("StoryId", allArrayStorylisid);
				intshow.putExtra("StoryTitle", allArrayStorylisttitle);
				intshow.putExtra("StoryDes", allArrayStorylistdes);
				intshow.putExtra("LanguageId", LanguageId);
				startActivity(intshow);
			}
		});

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		allData=db.getAllData();
		if(allData.size()==0)
		{
			listfavo.setAdapter(null);
			txt_no.setVisibility(View.VISIBLE);

		}
		else
		{
			favo_adapter=new Favorite_Adapter(Favorite_Activity.this, allData);
			listfavo.setAdapter(favo_adapter);
			txt_no.setVisibility(View.GONE);
		}


		ArrayList<String> allListStorylisid = new ArrayList<>();
		ArrayList<String> allListStorylisttitle = new ArrayList<>();
		ArrayList<String> allListStorylistdes = new ArrayList<>();
		



		allArrayStorylisid=new String[allListStorylisid.size()];
		allArrayStorylisttitle=new String[allListStorylisttitle.size()];
		allArrayStorylistdes=new String[allListStorylistdes.size()];

		for(int i=0;i<allData.size();i++)
		{
			pojoitem=allData.get(i);

			allListStorylisid.add(pojoitem.getSId());
			allArrayStorylisid= allListStorylisid.toArray(allArrayStorylisid);

			allListStorylisttitle.add(pojoitem.getSTitle());
			allArrayStorylisttitle= allListStorylisttitle.toArray(allArrayStorylisttitle);

			allListStorylistdes.add(pojoitem.getSDes());
			allArrayStorylistdes= allListStorylistdes.toArray(allArrayStorylistdes);

		}


		edtsearch.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence cs, int start, int before, int count) {
				// TODO Auto-generated method stub
				//StoryList_Activity.this.adapterstorylistry.getFilter().filter(cs.toString());


				textlength = edtsearch.getText().length();

				allData.clear();

				for(int i=0;i< allArrayStorylisttitle.length;i++)
				{
					if(textlength <= allArrayStorylisttitle[i].length())
					{
						if(edtsearch.getText().toString().equalsIgnoreCase((String) allArrayStorylisttitle[i].subSequence(0, textlength)))
						{


							Pojo objItem = new Pojo();


							objItem.setSId(allArrayStorylisid[i]);
							objItem.setSTitle(allArrayStorylisttitle[i]);
							objItem.setSDes(allArrayStorylistdes[i]);
							
							allData.add(objItem);

						}
					}
				}

				favo_adapter=new Favorite_Adapter(Favorite_Activity.this, allData );
				listfavo.setAdapter(favo_adapter);


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
