package com.sk.roundedsectionedlist;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.demosideindex.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnTouchListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener{

	ArrayList<Object> items = new ArrayList<Object>();
	ListView listview=null;
	ArrayList<HashMap<Integer,String>> alphabets=new ArrayList<HashMap<Integer,String>>();
	ArrayList<String> countries=new ArrayList<String>();
	private static float sideIndexX;
	private static float sideIndexY;
	private int sideIndexHeight;
	int index[]=new int[27];
	int alpha_t_item[]=new int[26];

	GestureDetector mGestureDetector;

	public static ArrayList<GetterSetter> frnd_info=new ArrayList<GetterSetter>();

	private ArrayList<HashMap<Integer,String>> sections = new ArrayList<HashMap<Integer,String>>();

	class SideIndexGestureListener extends GestureDetector.SimpleOnGestureListener {
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
			sideIndexX = sideIndexX - distanceX;
			sideIndexY = sideIndexY - distanceY;
			if (sideIndexX >= 0 && sideIndexY >= 0) {
				displayListItem();
			}
			return super.onScroll(e1, e2, distanceX, distanceY);
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_alphabet);

		mGestureDetector = new GestureDetector(this, new SideIndexGestureListener());

		frnd_info.clear();

		listview=(ListView)findViewById(R.id.listView_main);
		int j=0;
		for(int i=65;i<91;i++){
			HashMap<Integer,String> map=new HashMap<Integer,String>();
			map.put(j,String.valueOf((char)i));
			alphabets.add(map); 
			j++;
		}

		fillIndex();

		countries.add("Afghanistan");
		countries.add("Albania");
		countries.add("Albania");
		countries.add("Bahrain");
		countries.add("Bangladesh");
		countries.add("Bangladesh");
		countries.add("Bangladesh");
		countries.add("Cambodia");
		countries.add("Cameroon");
		countries.add("Denmark");
		countries.add("Djibouti");
		countries.add("East Timor");
		countries.add("Ecuador");
		countries.add("Fiji");
		countries.add("Finland");
		countries.add("Gabon");
		countries.add("Georgia");
		countries.add("Gabon");
		countries.add("Georgia");
		countries.add("Gabon");
		countries.add("Georgia");
		countries.add("Gabon");
		countries.add("Georgia");
		countries.add("Gabon");
		countries.add("Georgia");
		countries.add("Gabon");
		countries.add("Georgia");
		countries.add("Gabon");
		countries.add("Georgia");
		countries.add("Gabon");
		countries.add("Georgia");
		countries.add("Gabon");
		countries.add("Georgia");
		countries.add("Gabon");
		countries.add("Georgia");
		countries.add("Gabon");
		countries.add("Georgia");
		countries.add("Gabon");
		countries.add("Georgia");
		countries.add("Haiti");
		countries.add("Holy See");
		countries.add("Holy See");
		countries.add("Holy See");
		countries.add("Holy See");
		countries.add("Holy See");
		countries.add("Iceland");
		countries.add("India");
		countries.add("Jamaica");
		countries.add("Japan");
		countries.add("Kazakhstan");
		countries.add("Kenya");
		countries.add("Laos");
		countries.add("Latvia");
		countries.add("Macau");
		countries.add("Macedonia");
		countries.add("Namibia");
		countries.add("Nauru");
		countries.add("Namibia");
		countries.add("Nauru");
		countries.add("Namibia");
		countries.add("Nauru");
		countries.add("Vanuatu");
		countries.add("Venezuela");
		countries.add("Yemen");
		countries.add("Zambia");
		countries.add("Zimbabwe");

		String firstLetter=null;
		String previousLetter=null;
		int start = 0,k=1,pos=0,end=0,counter=0;

		System.out.println("country size===="+countries.size());

		for(int i=0;i<countries.size();i++){
			firstLetter=countries.get(i).substring(0, 1).toString();

			if(previousLetter != null && !firstLetter.equals(previousLetter)){
				end=items.size()-1;
				start = end + 1;
			}

			if (!firstLetter.equals(previousLetter)) {
				items.add(new SectionItem(firstLetter));

				HashMap<Integer,String> map=new HashMap<Integer,String>();
				Log.e("Val", String.valueOf(start));
				map.put(start, firstLetter);
				sections.add(map);
				index[pos]=start;
				pos++;
				if(counter>0){
					Log.e("================", ""+k);
					alpha_t_item[counter-1]=k;

					//Log.e("POSITION", ""+(p-1)+" "+"Letter"+firstLetter+alpha_t_item[p-1]+"K===="+k);
				}
				k=1;
				counter++;
			}

			items.add(new EntryItem(countries.get(i)));
			previousLetter = firstLetter;
			k++;
		}

		if(countries.size()>0){  // this is for last position of list
			char ch=firstLetter.charAt(0);

			if((int)ch>=65 && (int)ch<=90 ||(int)ch>=97 && (int)ch<=122){
				alpha_t_item[counter-1]=k;
			}
		}
		EntryAdapter adapter = new EntryAdapter(this, items,alpha_t_item);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(this);
	}

	public void displayListItem() {
		// TODO Auto-generated method stub
		LinearLayout sideIndex = (LinearLayout) findViewById(R.id.sideIndex);
		sideIndexHeight = sideIndex.getHeight();
		double pixelPerIndexItem = (double) sideIndexHeight / 26;
		int itemPosition = (int) (sideIndexY / pixelPerIndexItem);

		if (itemPosition < alphabets.size()) {
			String subitemPosition=	alphabets.get(itemPosition).get(itemPosition);
			Toast.makeText(MainActivity.this, subitemPosition, 0).show();
			for(int i=0;i<sections.size();i++){
				if(subitemPosition.equals(sections.get(i).get(index[i]))){
					listview.setSelection(index[i]);
					Log.e("Index=====", String.valueOf(index[i]));
				}
			}
		}
	} 

	void fillIndex(){
		LinearLayout sideIndex = (LinearLayout) findViewById(R.id.sideIndex);
		sideIndex.removeAllViews();
		if(alphabets.size()<1){
			return;
		}
		int indexMaxSize = (int) Math.floor(sideIndex.getHeight() / 26);
		int tmpIndexListSize = alphabets.size();
		TextView tmpTV;
		for(int i=0;i<tmpIndexListSize;i++){
			tmpTV = new TextView(this);
			tmpTV.setText(alphabets.get(i).get(i));
			tmpTV.setGravity(Gravity.CENTER_HORIZONTAL);
			tmpTV.setTextSize(15);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
			tmpTV.setLayoutParams(params);
			sideIndex.addView(tmpTV);
		}

		sideIndexHeight = sideIndex.getHeight();

		sideIndex.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// now you know coordinates of touch
				sideIndexX = event.getX();
				sideIndexY = event.getY();

				// and can display a proper item it country list
				displayListItem();

				return false;
			}
		});
	}


	@Override
	public void onItemClick(AdapterView arg0, View arg1, int position, long arg3) {

		EntryItem item = (EntryItem)items.get(position);
		Toast.makeText(this, "You clicked " + item.title , Toast.LENGTH_SHORT).show();
	}
}