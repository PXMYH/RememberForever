package com.example.com.pxmyh.caloriecalculator;

import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class gridViewAdapter extends BaseAdapter {
	
	// variable declaration
	private Context mContext;
	private final String[] mFoodName;
	private final int[] mFoodImageId;
	
	// Constructor
	public gridViewAdapter (Context c, String[] mFoodName, int[] mFoodImageId) {
		mContext = c;
		this.mFoodName = mFoodName;
		this.mFoodImageId = mFoodImageId;
	}
	
	// getView method
	public View getView (int position, View convertView, ViewGroup parent){
		
		View gridView;
		
		// get Layout Inflater service 
		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		if (convertView == null) {  // if it's not recycled, initialize some attributes
			gridView = new View(mContext);
			
    		// get layout from customized embedded layout view in grid view
            gridView = inflater.inflate(R.layout.activity_food, null);
            TextView textView = (TextView) gridView.findViewById(R.id.grid_item_food_name);
            ImageView imageView = (ImageView) gridView.findViewById(R.id.grid_item_image);
            textView.setText(mFoodName[position]);
            imageView.setImageResource(mFoodImageId[position]);
        } else {
            gridView = (View) convertView;
        }
		
		return gridView;
	}
	
//    class OnImageClickListener implements OnClickListener {
//    	 
//        int postion;
// 
//        // constructor
//        public OnImageClickListener(int position) {
//            this.postion = position;
//        }
// 
//        @Override
//        public void onClick(View v) {
//            // on selecting grid view image
//            // launch full screen activity
//            Intent i = new Intent(_activity, FullScreenViewActivity.class);
//            i.putExtra("position", postion);
//            _activity.startActivity(i);
//        }
// 
//    }
	
	
	
	@Override
	public int getCount() {
		return mFoodName.length;
	}
 
	@Override
	public Object getItem(int position) {
		return mFoodImageId[position];
	}
 
	@Override
	public long getItemId(int position) {
		return 0;
	}
	
}