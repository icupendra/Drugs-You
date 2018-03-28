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
import lk.techgays.drugsyou.item.Item_StoryList;

import java.util.List;

public class StoryList_Adapter extends ArrayAdapter<Item_StoryList> {

	private final Activity activity;
	private final List<Item_StoryList> itemsLatest;
    private final int row;
	 
	public StoryList_Adapter(Activity act, List<Item_StoryList> arrayList) {
			super(act, R.layout.storylist_item, arrayList);
			this.activity = act;
			this.row = R.layout.storylist_item;
			this.itemsLatest = arrayList;
		}
	 

	 @Override
		public View getView(final int position, View convertView,  ViewGroup parent) {
			View view = convertView;
			ViewHolder holder;
			if (view == null) {
				LayoutInflater inflater = (LayoutInflater) activity
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				if (inflater != null) {
					view = inflater.inflate(row, null);
				}

				holder = new ViewHolder();
				if (view != null) {
					view.setTag(holder);
				}
			} else {
				holder = (ViewHolder) view.getTag();
			}

			if ((itemsLatest == null) || ((position + 1) > itemsLatest.size()))
				return view;

         Item_StoryList objLatestBean = itemsLatest.get(position);

		 	Typeface tf = Typeface.createFromAsset(activity.getAssets(), "fonts/NotoSansSinhala-Regular.ttf");
			holder.txt_category= view.findViewById(R.id.text_storylistname);
		 	holder.txt_category.setTypeface(tf);
			holder.txt_category.setText(objLatestBean.getStoryTitle());

          	return view;
			
		}

		public class ViewHolder {

			TextView txt_category;

		}
 
}
