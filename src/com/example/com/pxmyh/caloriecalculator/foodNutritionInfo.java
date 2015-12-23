package com.example.com.pxmyh.caloriecalculator;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

public class foodNutritionInfo extends Activity {
	
	
	// <---- Variable declaration ---->
	
	// tags
	private static final String TAG_INDIV_CALORIES_DATA_FETCH	= "individualCaloriesFetch";
	
	private static int foodQuantity = 0;
	
	
	
	@Override 
	public void onCreate (Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nutritioninfo);
		
		// TODO: think about using crat later on
//		// <---- add up button in action bar ---->
//		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		// Put specific food image in position
		
		// find the imageview where food picture will be located
		ImageView mFoodNutritionImage = (ImageView) findViewById(R.id.nutrition_image);
		
		// TODO: explicit intent -- remove later
		final Intent mNutritionInfoReturn = getIntent();

		final int 		imagePosition 	= mNutritionInfoReturn.getIntExtra("Image_Position", 0);
		int[] 			imageList 		= mNutritionInfoReturn.getIntArrayExtra("Image_List");
		String[] 		imageName 		= mNutritionInfoReturn.getStringArrayExtra("Image_Name");
		int[] 			imageMeasure	= mNutritionInfoReturn.getIntArrayExtra("Image_Measure");
		int[] 			imageProtein	= mNutritionInfoReturn.getIntArrayExtra("Image_Protein");
		final int[] 	imageCalories	= mNutritionInfoReturn.getIntArrayExtra("Image_Calories");
		int[] 			imageFat		= mNutritionInfoReturn.getIntArrayExtra("Image_Fat");
		int[] 			imageCarb		= mNutritionInfoReturn.getIntArrayExtra("Image_Carb");
		int[] 			imageSugars		= mNutritionInfoReturn.getIntArrayExtra("Image_Sugars");

		
		// set image resource 
		mFoodNutritionImage.setImageResource(imageList[imagePosition]);
		
		
		// Find buttons on the page and specify their functionalities
		
		// Number Picker
		final NumberPicker mFoodQuantity = (NumberPicker) findViewById(R.id.food_quantity);
		mFoodQuantity.setMaxValue(100);
		mFoodQuantity.setMinValue(0);

		
		// Text Field
		TextView mNameTag     = (TextView) findViewById(R.id.food_name);
		TextView mDescription = (TextView) findViewById(R.id.description);
		TextView mMeasureTag  = (TextView) findViewById(R.id.measure_per_g);
		TextView mProteinTag  = (TextView) findViewById(R.id.protein_g);
		TextView mCaloriesTag = (TextView) findViewById(R.id.calories_g);
		TextView mFatTag      = (TextView) findViewById(R.id.fat_g);
		TextView mCarbTag     = (TextView) findViewById(R.id.carb_g);
		//TextView mSugarsTag		= (TextView) findViewById(R.id.sugars_g);
		
		// Check Box
		//final CheckBox mCheck = (CheckBox) findViewById(R.id.food_select);
		
		// Button
		Button mButtonYa  = (Button) findViewById(R.id.button_select);
		Button mButtonWoo = (Button) findViewById(R.id.button_cancel);
		
		
		// functional area: set each view components functions
		
		// <----- update tags ----->
		// name tag
		String name_tag = imageName[imagePosition];
		mNameTag.setText(name_tag);
		
		// measure tag
		mMeasureTag.setText("" + imageMeasure[imagePosition]);
		
		// protein tag
		mProteinTag.setText("" + imageProtein[imagePosition]);
		
		// calories tag
		mCaloriesTag.setText("" + imageCalories[imagePosition]);
		
		// fat tag
		mFatTag.setText("" + imageFat[imagePosition]);
		
		// carb tag
		mCarbTag.setText("" + imageCarb[imagePosition]);
		
		// sugars tag
		
		
		
		// get food quantity
		mFoodQuantity.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
			
			@Override
			public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
				//foodQuantity = mFoodQuantity.getValue();
				foodQuantity = newVal;
				Log.i("foodNutritionInfo", "" + foodQuantity);
			}
		});
		
		
		
		// Ya button -- select/yes/confirm button
		mButtonYa.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
					int individual_calories_subtotal = imageCalories[imagePosition] * foodQuantity;
					mNutritionInfoReturn.putExtra(TAG_INDIV_CALORIES_DATA_FETCH, individual_calories_subtotal);
					setResult(RESULT_OK, mNutritionInfoReturn);
					finish();
			}
		});
		
		// Woo button -- cancel button
		mButtonWoo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
	}
	
	
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//	    switch (item.getItemId()) {
//	    // Respond to the action bar's Up/Home button
//	    case android.R.id.home:
//	        NavUtils.navigateUpFromSameTask(this);
//	        return true;
//	    }
//	    return super.onOptionsItemSelected(item);
//	}

}