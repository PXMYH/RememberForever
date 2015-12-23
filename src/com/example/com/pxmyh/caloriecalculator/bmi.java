package com.example.com.pxmyh.caloriecalculator;

import java.text.DecimalFormat;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class bmi extends Activity {
	@Override 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bmi);
		
		// add up button in action bar
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		
		// BMI calculator
		Button 	 mButtonBMI = (Button) findViewById(R.id.button_BMI_calc);
		mButtonBMI.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				// find each components in view
				TextView mBMI = (TextView) findViewById(R.id.BMI);
				TextView mBMIComment = (TextView) findViewById(R.id.BMI_comment);
				EditText mHeight = (EditText) findViewById(R.id.BMI_height_input);
				EditText mWeight = (EditText) findViewById(R.id.BMI_weight_input);
				
				String mHeight_s = mHeight.getText().toString();
				String mWeight_s = mWeight.getText().toString();
				double mHeightVal = 0.0, mWeightVal = 0.0;
				
				if (!mHeight_s.isEmpty() && mHeight_s != null) {
					mHeightVal = Double.parseDouble(mHeight_s);
				}
				
				if (!mWeight_s.isEmpty() && mWeight_s != null) {
					mWeightVal = Double.parseDouble(mWeight_s);
				}
				
				if (mHeightVal != 0.0 && mWeightVal != 0.0) {
					// calculate BMI only if both height and weight information is available
					double mBMIVal = mWeightVal / ( (mHeightVal / 100.0) * (mHeightVal / 100.0) );
					mBMI.setText("" + new DecimalFormat("##.##").format(mBMIVal));
					
					
					// classification
					if (mBMIVal < 18.5) {
						mBMIComment.setText("Underweight!!! You are so skinny ~.~");
					} else if (mBMIVal >= 18.5 && mBMIVal <= 22.9) {
						mBMIComment.setText("Normal!!! Keep it up ^.^");
					} else if (mBMIVal >= 23.0 && mBMIVal <= 24.9) {
						mBMIComment.setText("Overweight - At Risk!!! Be careful ~");
					} else if (mBMIVal >= 25.0 && mBMIVal <= 29.9) {
						mBMIComment.setText("Overweight - Moderately Obese!!! Stop!!!");
					} else {
						mBMIComment.setText("Overweight - Severely Obese!!! You are DONE .........");
					}
					
					
				} else {
					mBMI.setText("INFORMATION IS MISSING, OR ARE YOU A DWARF OR YOU DON'T EXIST??");
				}
				
				//TODO: add age tag; add a color showing which region Liz is in now (Red -> Overweight etc.)
				// change the return text to be more informative
			}
		});
		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    // Respond to the action bar's Up/Home button
	    case android.R.id.home:
	        NavUtils.navigateUpFromSameTask(this);
	        return true;
	    }
	    return super.onOptionsItemSelected(item);
	}

}