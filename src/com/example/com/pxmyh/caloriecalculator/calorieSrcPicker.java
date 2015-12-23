package com.example.com.pxmyh.caloriecalculator;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

public class calorieSrcPicker extends Activity {
	
	GridView gridView;
	
	
	// <---- Variable declaration --->
	
	// food names 
	String[] mFoodName = {
			"apple",
			"apricot",
			"avocado",
			"azukibean",
			"banana",
			"blueberry",
			"egg",
			"eggplant",
			"grape",
			"kitkat",
			"milk",
			"orange",
			"peach",
			"pineapple",
			"strawberry",
			"watermelon"
	};
	
	// food images
	int[] mFoodImage = {
			R.raw.apple, 
			R.raw.apricot, 
			R.raw.avocado, 
			R.raw.azukibean, 
			R.raw.banana,
			R.raw.blueberry, 
			R.raw.egg, 
			R.raw.eggplant, 
			R.raw.grape, 
			R.raw.kitkat, 
			R.raw.milk,
			R.raw.orange,
			R.raw.peach, 
			R.raw.pineapple, 
			R.raw.strawberry, 
			R.raw.watermelon
	};
	
	// nutrition table list
	int[] mMeasureValueList;
	int[] mProteinValueList;
	int[] mCaloriesValueList;
	int[] mFatValueList;
	int[] mCarbValueList;
	int[] mSugarsValueList;
	
	// Intent Tags
	protected static final String EXTRA_RES_ID = "POS";
	
	private static final String TAG_NAME 		= "name";
	private static final String TAG_LABEL 		= "nutritionLabel";
	private static final String TAG_MEASURE 	= "measure";
	private static final String TAG_PROTEIN 	= "protein";
	private static final String TAG_CALORIES	= "calories";
	private static final String TAG_FAT			= "fat";
	private static final String TAG_CARB		= "carb";
	private static final String TAG_SUGARS		= "sugars";
	
	private static final String TAG_INDIV_CALORIES_DATA_FETCH	= "individualCaloriesFetch";
	private static final String TAG_TOTAL_CALORIES_DATA_FETCH	= "totalCaloriesFetch";
	
	static private final int GET_INDIVIDUAL_CALORIES_REQUEST_CODE = 0xF00D; 
	
	String name= null, measure = null, protein = null, calories = null, fat = null, carb = null, sugars = null;
		
	public static int mCaloriesTotal = 0;


	@Override 
	public void onCreate (Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_caloriessrcpicker);
	
		// TODO: figure out how to use caret in next revision			
//		// add up button in action bar
//		getActionBar().setDisplayHomeAsUpEnabled(true);
	
		// set up the grid view image adapter
		GridView foodGallery = (GridView) findViewById (R.id.foodChoices);
		final gridViewAdapter mGridViewAdapter = new gridViewAdapter(this, mFoodName, mFoodImage);
		foodGallery.setAdapter(mGridViewAdapter);
		
		
		// extract food nutrition table
		
		// load JSON file
		BufferedReader jsonReader = new BufferedReader(new InputStreamReader(this.getResources().openRawResource(R.raw.food_info)));
		StringBuilder jsonBuilder = new StringBuilder();
		try {
			for (String line = null; (line = jsonReader.readLine()) != null;) {
				//Log.i("BUFFER", "the line now is " + line);
				jsonBuilder.append(line).append("\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// parse JSON
		JSONTokener tokener = new JSONTokener(jsonBuilder.toString());
		JSONArray jsonArray = null;
		try {
			jsonArray = new JSONArray(tokener);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// allocate array space
		mMeasureValueList 		= new int[jsonArray.length()];
		mProteinValueList		= new int[jsonArray.length()];
		mCaloriesValueList		= new int[jsonArray.length()];
		mFatValueList			= new int[jsonArray.length()];
		mCarbValueList			= new int[jsonArray.length()];
		mSugarsValueList		= new int[jsonArray.length()];
		
		for (int index = 0; index < jsonArray.length(); index++) {
			// declare variables
			JSONObject jsonObject = null;
			
			try {
				jsonObject = jsonArray.getJSONObject(index);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				name = jsonObject.getString(TAG_NAME);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				measure 		= jsonObject.getJSONObject(TAG_LABEL).getString(TAG_MEASURE);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				protein 		= jsonObject.getJSONObject(TAG_LABEL).getString(TAG_PROTEIN);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				calories 	= jsonObject.getJSONObject(TAG_LABEL).getString(TAG_CALORIES);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				fat 			= jsonObject.getJSONObject(TAG_LABEL).getString(TAG_FAT);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				carb 		= jsonObject.getJSONObject(TAG_LABEL).getString(TAG_CARB);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				sugars		= jsonObject.getJSONObject(TAG_LABEL).getString(TAG_SUGARS);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
			// convert nutrition data type from 'String' to 'Integer'
			mMeasureValueList[index] = Integer.parseInt(measure);
			mProteinValueList[index] = Integer.parseInt(protein);
			mCaloriesValueList[index] = Integer.parseInt(calories);
			mFatValueList[index] = Integer.parseInt(fat);
			mCarbValueList[index] = Integer.parseInt(carb);
			mSugarsValueList[index] = Integer.parseInt(sugars);
		}
		
		
		
		foodGallery.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	            // log name of the food chosen
				// calculate total calories
				Toast.makeText(calorieSrcPicker.this, "You Clicked " + mFoodName[+position], Toast.LENGTH_SHORT).show();
				
				// start full screen image view with nutrition information activity 
				Intent mNutritionInfo = new Intent(calorieSrcPicker.this, foodNutritionInfo.class);
				mNutritionInfo.putExtra("Image_Position", 	position);
				mNutritionInfo.putExtra("Image_List"    , 	mFoodImage);
				mNutritionInfo.putExtra("Image_Name"    , 	mFoodName);
				mNutritionInfo.putExtra("Image_Measure" , 	mMeasureValueList);
				mNutritionInfo.putExtra("Image_Protein" , 	mProteinValueList);
				mNutritionInfo.putExtra("Image_Calories", 	mCaloriesValueList);
				mNutritionInfo.putExtra("Image_Fat"		, 	mFatValueList);
				mNutritionInfo.putExtra("Image_Carb"	, 	mCarbValueList);
				mNutritionInfo.putExtra("Image_Sugars"	, 	mSugarsValueList);
				
				startActivityForResult(mNutritionInfo, GET_INDIVIDUAL_CALORIES_REQUEST_CODE);
				
			}
		});
		
		
		
		// find the done button and return the total calories result
		Button mButtonDone = (Button) findViewById(R.id.button_done);
		mButtonDone.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// return the total calories count back to progress activity
				Intent mCaloriesTotalIntent = getIntent();
				mCaloriesTotalIntent.putExtra(TAG_TOTAL_CALORIES_DATA_FETCH, mCaloriesTotal);
				Log.i("caloriesSrcPicker create", "return total_calories = " + mCaloriesTotal);
				setResult(RESULT_OK, mCaloriesTotalIntent);
				finish();
			}
		});
		
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
	    // Check which request we're responding to
	    if (requestCode == GET_INDIVIDUAL_CALORIES_REQUEST_CODE) {
	        // Make sure the request was successful
	        if (resultCode == RESULT_OK) {
	        	int mIndividualCalories = data.getIntExtra(TAG_INDIV_CALORIES_DATA_FETCH, 0);
	        	mCaloriesTotal = mCaloriesTotal + mIndividualCalories;
	        	Log.i("caloriesSrcPicker onActivityResult", "total " + mCaloriesTotal);
	        }
	    }
	}
	
	
// TODO: figure out how to use caret in next revision	
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//	    switch (item.getItemId()) {
//	    // Respond to the action bar's Up/Home button
//	    case android.R.id.home:
//	        NavUtils.navigateUpFromSameTask(this);
//	        return true;
//	    }
//	    
//	    return super.onOptionsItemSelected(item);
//	}
	

}