package com.blitz.leprosydiagnosis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FinalPosting extends Activity 
{
	
	String response = null;
	//TextView tv;
	TextView tvTry,tvHow,tvDoc,tvLevelDetail,tvTimeDetail;
	ImageView ivHome;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.final_low);
		
		tvTry=(TextView)findViewById(R.id.tvTry1);
		tvHow=(TextView)findViewById(R.id.tvHow1);
		tvDoc=(TextView)findViewById(R.id.tvDoc1);
		ivHome=(ImageView)findViewById(R.id.ivHome1);
		
		try
		{
			android.app.ActionBar bar = getActionBar();
			ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#0099CC"));
			bar.setBackgroundDrawable(colorDrawable);
			int titleId = getResources().getIdentifier("action_bar_title", "id",
					"android");
			TextView textView = (TextView) findViewById(titleId);
			textView.setTextColor(Color.WHITE);
			//textView.setTypeface(font_bold);

		}
		catch(NullPointerException e)
		{
			e.printStackTrace();
		}
		
		new SubmitToPHP().execute("");
	}
	public class SubmitToPHP extends AsyncTask<String, Integer, String>
	{


		@Override
		protected void onPreExecute()
		{
			 
		}

		@Override
		protected String doInBackground(String... strings)
		{
				
			
				tryLogin();
		
			return null;
		}


		@Override
		protected void onProgressUpdate(Integer... values)
		{

		}

		@Override
		protected void onPostExecute(String s)
		{
			
			System.out.println("In on post execute");
			if(response == null)
			{
				Toast.makeText(FinalPosting.this, "Some Error Occoured", Toast.LENGTH_SHORT).show();
			}
			else
			{	Random r = new Random();
				int i=r.nextInt(5);
				float f=r.nextFloat();
				
				response.trim();
				System.out.println("the percentage is"+response);
				
			}
	
		}


	}
	
	protected void tryLogin()
	{           
		HttpURLConnection connection;
		OutputStreamWriter request = null;
		
		SharedPreferences sharedPreferences = getSharedPreferences("data", 0);
		String name = sharedPreferences.getString("name", "error");
		String age = sharedPreferences.getString("age", "error");
		String link1 = sharedPreferences.getString("json1", "error");
		String link2 = sharedPreferences.getString("json2", "error");
		String pressure = sharedPreferences.getString("pressure", "error");
		
		double myLongitude = 0,  myLatitude = 0;
		
		LocationManager mlocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE); 


		Location location = mlocManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

		if(location!=null) {
			myLongitude = location.getLongitude();
			myLatitude= location.getLatitude();

		}
		else {
			myLongitude =0;
			myLatitude= 0;
		}
		
		TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		String deviceID = telephonyManager.getDeviceId();

		URL url = null;   
		response = null;         
		String parameters = "url=http://ieeevit.com/jugaadathon/uploaded/"+link2;
		
		System.out.println(parameters);
		
	

		try
		{
			url = new URL("http://ieeevit.com/jugaadathon/getvalue.php");
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestMethod("POST");    

			request = new OutputStreamWriter(connection.getOutputStream());
			request.write(parameters);
			request.flush();
			request.close();            
			String line = "";               
			InputStreamReader isr = new InputStreamReader(connection.getInputStream());
			BufferedReader reader = new BufferedReader(isr);
			StringBuilder sb = new StringBuilder();
			while ((line = reader.readLine()) != null)
			{
				sb.append(line);
			}
			// Response from server after login process will be stored in response variable.                
			response = sb.toString();
			// You can perform UI operations here          
			isr.close();
			reader.close();

		}
		catch(IOException e)
		{
			// Error
		}
	}
	public void How(View v) {
		String str="http://www.preleprosy.com/index.html";

		 Intent how = new Intent(Intent.ACTION_VIEW,Uri.parse(str));
	    startActivity(how);
	}
	public void Try(View v) {
		Intent intent_try=new Intent(this,MainActivity.class);
		startActivity(intent_try);
	}
	public void  Doc(View v) {
		Intent intent_doc=new Intent(this,doctor.class);
		startActivity(intent_doc);
	}
	public void Home(View v) {
		Intent intent_home=new Intent(this,start.class);
		startActivity(intent_home);
	}
}
