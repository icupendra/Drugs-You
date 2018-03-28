package lk.techgays.drugsyou.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import lk.techgays.drugsyou.R;
import lk.techgays.drugsyou.item.Item_Category;


import java.util.List;

public class Category_Adapter extends ArrayAdapter<Item_Category> {
	
	
	private final Activity activity;
	private final List<Item_Category> itemsLatest;
    private final int row;
	private int lastPosition = -1;

	 
	public Category_Adapter(Activity act, List<Item_Category> arrayList) {
			super(act, R.layout.category_item, arrayList);
			this.activity = act;
			this.row = R.layout.category_item;
			this.itemsLatest = arrayList;

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


			if ((itemsLatest == null) || ((position + 1) > itemsLatest.size()))
				return view;

         Item_Category objLatestBean = itemsLatest.get(position);

		 	Typeface tf = Typeface.createFromAsset(activity.getAssets(), "fonts/NotoSansSinhala-Regular.ttf");

			holder.txt_category= view.findViewById(R.id.text_cattitle);
		 	holder.txt_category.setTypeface(tf);
 			holder.txt_category.setText(objLatestBean.getCatName());

		 Animation animation = AnimationUtils.loadAnimation(activity, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
		 view.startAnimation(animation);
		 lastPosition = position;

 			
 			return view;
			
		}

		public class ViewHolder {
		 
 			TextView txt_category;
 			 
		}

	 
}
