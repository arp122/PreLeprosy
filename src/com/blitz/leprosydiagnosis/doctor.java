package com.blitz.leprosydiagnosis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class doctor extends Activity implements OnItemClickListener{
	private GoogleMap googleMap;
	double myLongitude = 0,  myLatitude = 0;
	ProgressDialog dialog = null;
	String response = null;
	ListView lvDoc;
	String email[],name[],desc[],phone[],lat[],longt[];
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_layout);
        lvDoc=(ListView)findViewById(R.id.lvDoc);
        dialog = ProgressDialog.show(doctor.this, "", "Please wait...", true);
		
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
		lvDoc.setOnItemClickListener(this);
        new SubmitToPHP().execute("safsaf");
 
    }
 
    /**
     * function to load map. If map is not created it will create it for you
     * */
    private void initilizeMap() {
        if (googleMap == null) {
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                    R.id.map)).getMap();
 
            // check if map is created successfully or not
            if (googleMap == null) {
                Toast.makeText(getApplicationContext(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }
            
         
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
            
            CameraPosition cameraPosition = new CameraPosition.Builder().target(
                    new LatLng(myLatitude, myLongitude)).zoom(12).build();
     
    googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
             
            // create marker
            MarkerOptions marker = new MarkerOptions().position(new LatLng(myLatitude, myLongitude)).title("Hello Maps ");
             
            // adding marker
            googleMap.addMarker(marker);
        }
    }
 
    @Override
    protected void onResume() {
        super.onResume();
      
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
			dialog.dismiss();
			initilizeMap();
			System.out.println("In on post execute");
			if(response == null)
			{
				Toast.makeText(doctor.this, "Some Error Occoured", Toast.LENGTH_SHORT).show();
			}
			else
			{	
				JSONArray obj;
				
				try {
					obj = new JSONArray(response);
					System.out.println("the percentage is"+response+obj.length());
					
					email=new String[obj.length()];
					desc=new String[obj.length()];
					name=new String[obj.length()];
					phone=new String[obj.length()];
					lat=new String[obj.length()];
					longt=new String[obj.length()];
					for(int i=0;i<obj.length();i++){
						email[i]=obj.getJSONObject(i).getString("email");
						desc[i]=obj.getJSONObject(i).getString("desc");
						name[i]=obj.getJSONObject(i).getString("name");
						phone[i]=obj.getJSONObject(i).getString("phone");
						lat[i]=obj.getJSONObject(i).getString("lat");
						longt[i]=obj.getJSONObject(i).getString("long");
					}
					List<RowItemDoctor> rowItems = new ArrayList<RowItemDoctor>();
					for (int i = 0; i<obj.length(); i++) {
						System.out.println("name and desc"+lat[i]+longt[i]);
						RowItemDoctor item = new RowItemDoctor(name[i],desc[i]);
						rowItems.add(item);
					}
					CustomBaseAdapter adapter = new CustomBaseAdapter(getApplicationContext(), rowItems);
					lvDoc.setAdapter(adapter);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				//CustomBaseAdapter adapter= new CustomBaseAdapter(this, items)

			
				
			}
	
		}


	}
	
	protected void tryLogin()
	{           
		HttpURLConnection connection;
		OutputStreamWriter request = null;
		
		URL url = null;   
		response = null;         
		String parameters = "lat="+myLatitude+"&long="+myLongitude;
		
		System.out.println(parameters);
		
	

		try
		{
			url = new URL("http://ieeevit.com/jugaadathon/docs.php");
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

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		
		MarkerOptions marker = new MarkerOptions().position(new LatLng(Double.parseDouble(lat[position]), Double.parseDouble(longt[position]))).title("Hello Maps ");
        	
        // adding marker
        googleMap.addMarker(marker);
		
	}
}
