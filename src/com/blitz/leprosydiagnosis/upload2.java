package com.blitz.leprosydiagnosis;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class upload2 extends Activity 
{
	ProgressDialog dialog = null;
	String upLoadServerUri = "http://ieeevit.com/jugaadathon/gray.php";
	int serverResponseCode = 0;
	String response;
	String link;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
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


		Upload("/mnt/sdcard/Leprosy/Gray/image.jpg",0);
	}


	public Bitmap toGrayscale(Bitmap bmpOriginal)
	{        
		int width, height;
		height = bmpOriginal.getHeight();
		width = bmpOriginal.getWidth();    

		Bitmap bmpGrayscale = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
		Canvas c = new Canvas(bmpGrayscale);
		Paint paint = new Paint();
		ColorMatrix cm = new ColorMatrix();
		cm.setSaturation(0);
		ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
		paint.setColorFilter(f);
		c.drawBitmap(bmpOriginal, 0, 0, paint);
		return bmpGrayscale;
	}
	public void Upload(final String filePath, final int count) 
	{


		dialog = ProgressDialog.show(this, "", "Uploading file...", true);

		new Thread(new Runnable() {
			public void run() {
				runOnUiThread(new Runnable() {
					public void run() {
						//  Toast.makeText(getApplicationContext(), "uploading started.....", Toast.LENGTH_SHORT).show();
					}
				});                     

				uploadFile(filePath,count);

			}
		}).start();       
	}



	HttpURLConnection conn = null;
	public int uploadFile(String sourceFileUri, int index)
	{


		String fileName = sourceFileUri;

		DataOutputStream dos = null; 
		String lineEnd = "\r\n";
		String twoHyphens = "--";
		String boundary = "*****";
		int bytesRead, bytesAvailable, bufferSize;
		byte[] buffer;
		int maxBufferSize = 1 * 1024 * 1024;
		File sourceFile = new File(sourceFileUri);


		if (!sourceFile.isFile()) {

			dialog.dismiss();

			/* Log.e("uploadFile", "Source File not exist :"
                                   +uploadFilePath + "" + uploadFileName);*/

			runOnUiThread(new Runnable() {
				public void run() {
					Toast.makeText(getApplicationContext(), "Soucre file not found.....", Toast.LENGTH_SHORT).show();


				}
			});

			return 0;

		}
		else
		{

			try {

				// open a URL connection to the Servlet
				FileInputStream fileInputStream = new FileInputStream(sourceFile);
				URL url = new URL(upLoadServerUri);

				// Open a HTTP  connection to  the URL
				conn = (HttpURLConnection) url.openConnection();
				conn.setDoInput(true); // Allow Inputs
				conn.setDoOutput(true); // Allow Outputs
				conn.setUseCaches(false); // Don't use a Cached Copy
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Connection", "Keep-Alive");
				conn.setRequestProperty("ENCTYPE", "multipart/form-data");
				conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
				conn.setRequestProperty("file", fileName);

				dos = new DataOutputStream(conn.getOutputStream());

				dos.writeBytes(twoHyphens + boundary + lineEnd);
				dos.writeBytes("Content-Disposition: form-data; name=file;filename=\""
						+ fileName + "\"" + lineEnd);
				dos.writeBytes(lineEnd);
				
				// create a buffer of  maximum size
				bytesAvailable = fileInputStream.available();

				bufferSize = Math.min(bytesAvailable, maxBufferSize);
				buffer = new byte[bufferSize];

				// read file and write it into form...
				bytesRead = fileInputStream.read(buffer, 0, bufferSize); 

				while (bytesRead > 0)
				{

					dos.write(buffer, 0, bufferSize);
					bytesAvailable = fileInputStream.available();
					bufferSize = Math.min(bytesAvailable, maxBufferSize);
					bytesRead = fileInputStream.read(buffer, 0, bufferSize);  

				}

				// send multipart form data necesssary after file data...
				dos.writeBytes(lineEnd);
				dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

				// Responses from the server (code and message)
				serverResponseCode = conn.getResponseCode();
				String serverResponseMessage = conn.getResponseMessage();


				Log.i("uploadFile", "HTTP Response is : "
						+ serverResponseMessage + ": " + serverResponseCode);

				try
				{
					String line = "";   
					InputStreamReader isr = new InputStreamReader(conn.getInputStream());
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
					System.out.println("Response = "+response);

					try
					{

						JSONObject obj = new JSONObject(response);
						response = obj.getString("image");

						SharedPreferences sharedPreferences = getSharedPreferences("data", 0);
						SharedPreferences.Editor editor = sharedPreferences.edit();
						editor.putString("json2", ""+response);
						editor.commit();

					}
					catch(JSONException e)
					{
						e.printStackTrace();
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}

				if(serverResponseCode == 200){

					runOnUiThread(new Runnable() {
						public void run() {




							// messageText.setText(msg);
							Toast.makeText(getApplicationContext(), "File Upload Complete.", Toast.LENGTH_SHORT).show();

						}
					});               
				}   

				//close the streams //
				fileInputStream.close();
				dos.flush();
				dos.close();

				Intent i = new Intent(upload2.this, FinalPosting.class);
				startActivity(i);

			} catch (MalformedURLException ex) {

				dialog.dismiss(); 
				ex.printStackTrace();

				runOnUiThread(new Runnable() {
					public void run() {
						//  messageText.setText("MalformedURLException Exception : check script url.");
						Toast.makeText(getApplicationContext(), "MalformedURLException",
								Toast.LENGTH_SHORT).show();
					}
				});

				Log.e("Upload file to server", "error: " + ex.getMessage(), ex); 
			} catch (Exception e) {

				dialog.dismiss(); 
				e.printStackTrace();

				runOnUiThread(new Runnable() {
					public void run() {
						//   messageText.setText("Got Exception : see logcat ");
						Toast.makeText(getApplicationContext(), "Got Exception : see logcat ",
								Toast.LENGTH_SHORT).show();
					}
				});
				Log.e("Upload file to server Exception", "Exception : "
						+ e.getMessage(), e); 
			}
			dialog.dismiss();      
			return serverResponseCode;


		}
	} 
}
