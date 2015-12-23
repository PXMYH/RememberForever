package com.example.com.pxmyh.caloriecalculator;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class progress extends Activity {
	
	static private final int GET_DAILY_CALORIES_REQUEST_CODE = 1;
	private static final String TAG_TOTAL_CALORIES_DATA_FETCH	= "totalCaloriesFetch";
	
	public static final String PREFERENCE_NAME = "todayCaloriesData";
	private static final String TAG_TODAY_CALORIES_DATA_PREF = "todayCalories";
	
	private static int mDailyCalories = 0;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_progress);
		
		// find daily calories 
		final TextView dailyCalories = (TextView) findViewById(R.id.todayCaloriesProgress);
		dailyCalories.setText("" + 0);
		
		
		
		// find the calorie picker toggle button
		Button caloriePickerButton = (Button) findViewById (R.id.calorie_picker);
		caloriePickerButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				//startActivityNoResultIntent();
				
				startActivityForResultIntent();
			}
		});
		
		
		// find the BMI calculator toggle button
		Button BMICalculatorButton = (Button) findViewById (R.id.button_start_BMI);
		BMICalculatorButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// start calorie picker toggle activity
				Intent BMIIntent = new Intent(progress.this,bmi.class);
				startActivity (BMIIntent);
			}
		});
		
		
		// find the clear data button
		Button clearButton = (Button) findViewById(R.id.button_clear);
		clearButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// clear today's calories number and previous accrued data

				// TODO: use SharedPreference framework in Android instead of static variable to clear value
//				SharedPreferences settings = getSharedPreferences(PREFERENCE_NAME, 0);
//				settings.getInt(TAG_TODAY_CALORIES_DATA_PREF, 0);
				
				
				calorieSrcPicker.mCaloriesTotal = 0;
				
				dailyCalories.setText("" + 0);
			}
		});
		
		
		
	}

	// start activity without getting a result
	private void startActivityNoResultIntent() {
		Intent caloriePickerIntent = new Intent(progress.this,calorieSrcPicker.class);
		startActivity (caloriePickerIntent);
	}
	
	// start activity and getting a result back
	private void startActivityForResultIntent() {
		
		// start calories picker toggle activity and retrieve daily total calories
		Intent caloriePickerIntent = new Intent(progress.this,calorieSrcPicker.class);
		startActivityForResult(caloriePickerIntent, GET_DAILY_CALORIES_REQUEST_CODE);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		// Process the result only if this method received both a
		// RESULT_OK result code and a recognized request code
		// If so, update the Textview showing the user-entered text.
		
		super.onActivityResult(requestCode, resultCode, data);
		
		Log.i("progress onActivityResult ", "Inside onActivityResult");
		
		if (requestCode == GET_DAILY_CALORIES_REQUEST_CODE) {
			
			Log.i("progress onActivityResult ", "requestCode is " + requestCode);
			
			if (resultCode == RESULT_OK) {
				
				mDailyCalories = data.getIntExtra(TAG_TOTAL_CALORIES_DATA_FETCH, 0);
				
				Log.i("Progress onActivityResult ", "" + mDailyCalories);
				
				// find the textview to show daily calories value
				TextView dailyCalories = (TextView) findViewById(R.id.todayCaloriesProgress);
				dailyCalories.setText("" + mDailyCalories);
			}
		}
	
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.progress, menu);
		return true;
	}
	
	@Override
	public void onResume () {
		super.onResume();
	}	
	
	@Override
	public void onPause () {
		super.onPause();
	}

}
