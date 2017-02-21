package com.blitz.leprosydiagnosis;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class InfoActivity extends Activity implements OnClickListener{
	
	Typeface font1, font2;
	TextView tvMain, tvInfo,tvCauses,tvSigns,tvCatching,tvDiagnosis,tvTreatment,tvPrevention,tvUnderstanding;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.faq);
		
		//font1 = Typeface.createFromAsset(getAssets(), "palantinoltstdmedium.otf");
		//font2 = Typeface.createFromAsset(getAssets(), "palantinoltstdroman.otf");
		
		tvMain = (TextView)findViewById(R.id.tvMain);
		//tvMain.setTypeface(font1);
		tvInfo = (TextView)findViewById(R.id.tvInfo);
		//tvInfo.setTypeface(font2);
		tvCauses = (TextView)findViewById(R.id.tvCauses);
		//tvCauses.setTypeface(font2);
		tvSigns = (TextView)findViewById(R.id.tvSigns);
		//tvSigns.setTypeface(font2);
		tvCatching = (TextView)findViewById(R.id.tvCatching);
		//tvCatching.setTypeface(font2);
		tvDiagnosis = (TextView)findViewById(R.id.tvDiagnosis);
		//tvDiagnosis.setTypeface(font2);
		tvTreatment = (TextView)findViewById(R.id.tvTreatment);
		//tvTreatment.setTypeface(font2);
		tvPrevention = (TextView)findViewById(R.id.tvPrevention);
		//tvPrevention.setTypeface(font2);
		tvUnderstanding = (TextView)findViewById(R.id.tvUnderstanding);
		//tvUnderstanding.setTypeface(font2);
		
		tvCauses.setOnClickListener(this);
		tvSigns.setOnClickListener(this);
		tvCatching.setOnClickListener(this);
		tvDiagnosis.setOnClickListener(this);
		tvTreatment.setOnClickListener(this);
		tvPrevention.setOnClickListener(this);
		tvUnderstanding.setOnClickListener(this);
		
		
	}

	@Override
	public void onClick(View v) {
		
		Intent intent = new Intent(InfoActivity.this,MoreInfo.class);
		int category = 0;
		// TODO Auto-generated method stub
		
		switch(v.getId())
		{
		case R.id.tvCauses: 
			intent.putExtra("category", 1);
			startActivity(intent);
			break;
		case R.id.tvSigns: 
			intent.putExtra("category", 2);
			startActivity(intent);
			break;
		case R.id.tvCatching: 
			intent.putExtra("category", 3);
			startActivity(intent);
			break;
		case R.id.tvDiagnosis: 
			intent.putExtra("category", 4);
			startActivity(intent);
			break;
		case R.id.tvTreatment: 
			intent.putExtra("category", 5);
			startActivity(intent);
			break;
		case R.id.tvPrevention: 
			intent.putExtra("category", 6);
			startActivity(intent);
			break;
		case R.id.tvUnderstanding: 
			intent.putExtra("category", 7);
			startActivity(intent);
			break;
		
		}
	}

}
