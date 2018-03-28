package lk.techgays.drugsyou.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import lk.techgays.drugsyou.R;
import lk.techgays.drugsyou.favorite.Pojo;


import java.util.List;

public class Favorite_Adapter extends ArrayAdapter<Pojo> {


	private final Activity activity;
	private final List<Pojo> itemsfavorite;
    private final int row;
	 

	public Favorite_Adapter(Activity act, List<Pojo> arrayList) {
		super(act, R.layout.storylist_item, arrayList);
		this.activity = act;
		this.row = R.layout.storylist_item;
		this.itemsfavorite = arrayList;
		

	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View view = convertView;
		ViewHolder holder;
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			view = inflater.inflate(row, null);

			holder = new ViewHolder();
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}

		if ((itemsfavorite == null) || ((position + 1) > itemsfavorite.size()))
			return view;

        Pojo objFavoriteBean = itemsfavorite.get(position);

		Typeface tf = Typeface.createFromAsset(activity.getAssets(), "fonts/NotoSansSinhala-Regular.ttf");

		holder.txt_favcategory= view.findViewById(R.id.text_storylistname);
		holder.txt_favcategory.setTypeface(tf);
		holder.txt_favcategory.setText(objFavoriteBean.getSTitle());
 		 
		return view;

	}

	public class ViewHolder {


		TextView txt_favcategory;

	}
} 
