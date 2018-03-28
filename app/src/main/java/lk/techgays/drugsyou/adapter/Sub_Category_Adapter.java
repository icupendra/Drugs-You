package lk.techgays.drugsyou.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import lk.techgays.drugsyou.R;
import lk.techgays.drugsyou.item.Item_Sub_Category;

public class Sub_Category_Adapter extends ArrayAdapter<Item_Sub_Category> {

	private final Activity activity;
	private final List<Item_Sub_Category> itemsLatest;
	private final int row;

	public Sub_Category_Adapter(Activity act, List<Item_Sub_Category> arrayList) {
		super(act, R.layout.storylist_item, arrayList);
		this.activity = act;
		this.row = R.layout.storylist_item;
		this.itemsLatest = arrayList;
	}


	@Override
	public View getView(final int position, View convertView,  ViewGroup parent) {
		View view = convertView;
		Sub_Category_Adapter.ViewHolder holder;
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			if (inflater != null) {
				view = inflater.inflate(row, null);
			}

			holder = new Sub_Category_Adapter.ViewHolder();
			if (view != null) {
				view.setTag(holder);
			}
		} else {
			holder = (Sub_Category_Adapter.ViewHolder) view.getTag();
		}

		if ((itemsLatest == null) || ((position + 1) > itemsLatest.size()))
			return view;

		Item_Sub_Category objLatestBean = itemsLatest.get(position);


			Typeface tf = Typeface.createFromAsset(activity.getAssets(), "fonts/NotoSansSinhala-Regular.ttf");

			holder.txt_category = view.findViewById(R.id.text_storylistname);
			holder.txt_category.setTypeface(tf);
			holder.txt_category.setText(objLatestBean.getSubCatName());


		return view;

	}

	public class ViewHolder {

		TextView txt_category;

	}

}
