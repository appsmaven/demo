package com.sk.roundedsectionedlist;

import java.util.ArrayList;

import com.example.demosideindex.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

@SuppressWarnings("rawtypes")
public class EntryAdapter extends BaseAdapter{
	public LayoutInflater  vi;
	public Context context;
	public ArrayList items;
	public int arr[];
	public EntryAdapter(Context context,ArrayList items,int arr[]) {
		this.context = context;
		this.items = items;
		this.arr=arr;
		vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v=convertView;
		int pos=position;
		ViewHolder1 holder1=new ViewHolder1();
		final Item i = (Item) items.get(position);
		if (i != null) {

			if(i.isSection()){
				SectionItem si = (SectionItem)i;
				v = vi.inflate(R.layout.list_item_section, null);
				v.setOnClickListener(null);
				v.setOnLongClickListener(null);
				v.setLongClickable(false);

				holder1.sectionView = (TextView) v.findViewById(R.id.list_item_section_text);
				holder1.sectionView.setText(si.getTitle());
			}
			else
			{
				if (v!=null) {
					EntryItem ei = (EntryItem)i;
					v = vi.inflate(R.layout.list_item_entry, null);
					holder1.title = (TextView)v.findViewById(R.id.list_item_entry_title);
					holder1.ll=(LinearLayout)v.findViewById(R.id.ll);

					holder1.ll.setPadding(0, 10, 0, 0);

					holder1.title.setText(ei.title);
					
					int round_postion = 0;
					for (int j = 0; j < arr.length;j++) {
						round_postion+= arr[j];
						if(position==round_postion-1)
							holder1.ll.setBackgroundResource(R.drawable.list_corner_shape1);
					}
				}
			}
		}
		return v;
	}
	public class ViewHolder1{
		TextView sectionView,title;
		//ImageView imgTick,imgCross;
		LinearLayout ll;
	}
}