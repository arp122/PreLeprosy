package com.blitz.leprosydiagnosis;




import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


@SuppressLint("ValidFragment")
public class MainActivity extends FragmentActivity implements OnPageChangeListener  {
	SectionsPagerAdapter mSectionsPagerAdapter;
	
	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;
	int first_page=0,second_page=0,third_page=0,fourth_page=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_main);
		
	mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		mViewPager.setOnPageChangeListener(this);
		
	}
	public class SectionsPagerAdapter extends FragmentPagerAdapter
	{

		public SectionsPagerAdapter(FragmentManager fm) 
		{
			super(fm);
		}

		Bundle args;
		@Override
		public Fragment getItem(int position) 
		{
			switch (position)
			{

			case 0:
				Fragment fragment = new DummySectionFragment();
				args = new Bundle();
				args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
				fragment.setArguments(args);
				
				return fragment;

			case 1:
				Fragment fragment2 = new DummySectionFragment2();
				args = new Bundle();
				args.putInt(DummySectionFragment2.ARG_SECTION_NUMBER, position + 2);
				fragment2.setArguments(args);
				return fragment2;
			case 2:
				Fragment fragment3 = new DummySectionFragment3();
				args = new Bundle();
				args.putInt(DummySectionFragment3.ARG_SECTION_NUMBER, position + 3);
				fragment3.setArguments(args);
				return fragment3;
			case 3:
				Fragment fragment4 = new DummySectionFragment4();
				args = new Bundle();
				args.putInt(DummySectionFragment4.ARG_SECTION_NUMBER, position + 4);
				fragment4.setArguments(args);
				return fragment4;


			default:
				return null;
			}


		}


		@Override
		public int getCount() 
		{
			// Show 3 total pages.
			return 4;
		}
		
		

	}
	public class DummySectionFragment extends Fragment implements OnClickListener 
	{
		public static final String ARG_SECTION_NUMBER = "section_number";
		SurfaceView view;
		float pressure = 0;
		Canvas canvas;
		float maxPressure = 0;
		Button bThemFeels;
		ImageView ivGrayCircle,ivGrayCircleRight;
		
		public DummySectionFragment()
		{
			
		}


		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) 
		{
			View rootView = inflater.inflate(R.layout.first, container, false);
			view = (SurfaceView)rootView.findViewById(R.id.svCircle);
			ivGrayCircle=(ImageView)rootView.findViewById(R.id.ivGrayCircle);
			ivGrayCircleRight=(ImageView)rootView.findViewById(R.id.ivGrayCircleRight);
			
			bThemFeels = (Button)rootView.findViewById(R.id.bThemFeels);
			bThemFeels.setOnClickListener(this);
			view.setBackgroundColor(Color.parseColor("#FFFFFF"));
			view.setOnTouchListener(new View.OnTouchListener() 
			{	
			    @Override
			    public boolean onTouch(View v, MotionEvent event) 
			    {	view.setBackgroundResource(0);
			        pressure = event.getPressure();
			        if(pressure > maxPressure)
			        {
			        	maxPressure = pressure;
			        }
			        drawCircle();
			        return true;
			    }
			});
			if(first_page==1)
				ivGrayCircle.setImageResource(R.drawable.red_circle);
			if(second_page==1)
				ivGrayCircleRight.setImageResource(R.drawable.red_circle_right);
			
			return rootView;	
		}

		public void drawCircle()
		{
			canvas = view.getHolder().lockCanvas();
			canvas.drawColor(Color.WHITE);
			 Paint paint = new Paint();
			 paint.setColor(Color.CYAN);
		        paint.setStyle(Paint.Style.FILL);
			canvas.drawCircle(canvas.getWidth()/2, 
					canvas.getHeight()/2, pressure * canvas.getWidth()/2
					,paint);
			view.getHolder().unlockCanvasAndPost(canvas);
		}
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			SharedPreferences sharedPreferences = getSharedPreferences("data", 0);
			SharedPreferences.Editor editor = sharedPreferences.edit();
			editor.putString("pressure", ""+pressure);
			editor.commit();
			SharedPreferences sharedPreferences_strip=getSharedPreferences("strip",0);
			SharedPreferences.Editor editor_strip = sharedPreferences_strip.edit();
			editor_strip.putString("value_first", ""+1);
			editor_strip.commit();
			first_page=1;
			ivGrayCircle.setImageResource(R.drawable.red_circle);
			mViewPager.setAdapter(mSectionsPagerAdapter);
			mViewPager.setCurrentItem(1);
		}

	}
	public class DummySectionFragment2 extends Fragment implements OnClickListener 
	{
		public static final String ARG_SECTION_NUMBER = "section_number";
		TextView btOn;
		Button btOff;
		Chronometer cr;
		public  Camera cam = null;// has to be static, otherwise onDestroy() destroys it
		ImageView ivGrayCircleLeft,ivGrayCircle,ivGrayCircleRight;
		public DummySectionFragment2()
		{

		}


		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) 
		{
			View rootView = inflater.inflate(R.layout.second, container, false);
			btOff=(Button)rootView.findViewById(R.id.btOff);
			btOn=(TextView)rootView.findViewById(R.id.textView2);
			cr=(Chronometer)rootView.findViewById(R.id.chronometer1);
			btOff.setOnClickListener(this);
			btOn.setOnClickListener(this);
			ivGrayCircleLeft=(ImageView)rootView.findViewById(R.id.ivGrayCircleLeft);
			ivGrayCircle=(ImageView)rootView.findViewById(R.id.ivGrayCircle);
			ivGrayCircleRight=(ImageView)rootView.findViewById(R.id.ivGrayCircleRight);
			
			if(first_page==1)
				ivGrayCircleLeft.setImageResource(R.drawable.red_circle_left);
			if(second_page==1)
				ivGrayCircle.setImageResource(R.drawable.red_circle);
			if(third_page==1)
				ivGrayCircleRight.setImageResource(R.drawable.red_circle_right);
			
			return rootView;	
		}


		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.textView2:
				try {
			        if (getPackageManager().hasSystemFeature(
			                PackageManager.FEATURE_CAMERA_FLASH)) {
			            cam = Camera.open();
			            Parameters p = cam.getParameters();
			            p.setFlashMode(Parameters.FLASH_MODE_TORCH);
			            cam.setParameters(p);
			            cam.startPreview();
			            cr.setBase(SystemClock.elapsedRealtime());
			            cr.start();
			        }
			    } catch (Exception e) {
			        e.printStackTrace();
			        Toast.makeText(getBaseContext(), "Exception flashLightOn()",
			                Toast.LENGTH_SHORT).show();
			    }
				break;
			case R.id.btOff:
				try {
			        if (getPackageManager().hasSystemFeature(
			                PackageManager.FEATURE_CAMERA_FLASH)) {
			        	long time = SystemClock.elapsedRealtime() - cr.getBase();            
			            
			        	SharedPreferences sharedPreferences = getSharedPreferences("data", 0);
						SharedPreferences.Editor editor = sharedPreferences.edit();
						editor.putString("time", ""+time);
						editor.commit();
						System.out.println("time is "+time);
			            cam.stopPreview();
			            cam.release();
			            cam = null;
			            second_page=1;
			            cr.stop();
			            ivGrayCircle.setImageResource(R.drawable.red_circle);
			            mViewPager.setAdapter(mSectionsPagerAdapter);
			            mViewPager.setCurrentItem(2);
			        }
			    } catch (Exception e) {
			        e.printStackTrace();
			        Toast.makeText(getBaseContext(), "Exception flashLightOff",
			                Toast.LENGTH_SHORT).show();
			    }
			default:
				break;
			}
		}

	}
	public class DummySectionFragment3 extends Fragment implements OnClickListener 
	{
		public static final String ARG_SECTION_NUMBER = "section_number";
		Button bt1,button1,button2,button3;
		TextView tvlevel;
		ImageView ivGrayCircleLeft,ivGrayCircle,ivGrayCircleRight;
		
		public DummySectionFragment3()
		{

		}


		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) 
		{
			View rootView = inflater.inflate(R.layout.third, container, false);
			bt1=(Button)rootView.findViewById(R.id.btOne);
			button1=(Button)rootView.findViewById(R.id.button1);
			button2=(Button)rootView.findViewById(R.id.button2);
			button3=(Button)rootView.findViewById(R.id.button3);
			tvlevel=(TextView)rootView.findViewById(R.id.tvlevel);
			bt1.setOnClickListener(this);
			button1.setOnClickListener(this);
			button2.setOnClickListener(this);
			button3.setOnClickListener(this);
			ivGrayCircleLeft=(ImageView)rootView.findViewById(R.id.ivGrayCircleLeft);
			ivGrayCircleRight=(ImageView)rootView.findViewById(R.id.ivGrayCircleRight);
			
			ivGrayCircle=(ImageView)rootView.findViewById(R.id.ivGrayCircle);
			if(second_page==1)
				ivGrayCircleLeft.setImageResource(R.drawable.red_circle_left);
			if(third_page==1)
				ivGrayCircle.setImageResource(R.drawable.red_circle);
			if(fourth_page==1)
				ivGrayCircleRight.setImageResource(R.drawable.red_circle_right);
			
			
			return rootView;	
		}

int level=0;
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			
			case R.id.btOne:
				
				break;
			
			case R.id.button1:
				Toast.makeText(getApplicationContext(), "Level One", Toast.LENGTH_SHORT).show();
				tvlevel.setText("1");
				third_page=1;
				level=1;
				ivGrayCircle.setImageResource(R.drawable.red_circle);
				break;
			case R.id.button2:
				Toast.makeText(getApplicationContext(), "Level two", Toast.LENGTH_SHORT).show();
				tvlevel.setText("2");
				third_page=1;
				level=2;
				ivGrayCircle.setImageResource(R.drawable.red_circle);
				break;
			case R.id.button3:
				Toast.makeText(getApplicationContext(), "Level three", Toast.LENGTH_SHORT).show();
				tvlevel.setText("3");
				third_page=1;
				level=3;				
				ivGrayCircle.setImageResource(R.drawable.red_circle);
				break;
			
			default:
				break;
			}
			SharedPreferences sharedPreferences = getSharedPreferences("data", 0);
			SharedPreferences.Editor editor = sharedPreferences.edit();
			editor.putString("level", ""+level);
			editor.commit();
			
		}

	}
		
	public class DummySectionFragment4 extends Fragment 
	{
		public static final String ARG_SECTION_NUMBER = "section_number";
		ImageView imVCature_pic;
		ImageView btnCapture;
		ImageView ivGrayCircleLeft,ivGrayCircle;
		
		public DummySectionFragment4()
		{

		}


		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) 
		{
			View rootView = inflater.inflate(R.layout.forth, container, false);
			ivGrayCircleLeft=(ImageView)rootView.findViewById(R.id.ivGrayCircleLeft);
			ivGrayCircle=(ImageView)rootView.findViewById(R.id.ivGrayCircle);
			if(third_page==1)
				ivGrayCircleLeft.setImageResource(R.drawable.red_circle_left);
			if(fourth_page==1)
				ivGrayCircle.setImageResource(R.drawable.red_circle);
			btnCapture=(ImageView)rootView.findViewById(R.id.ivCap);
			
			btnCapture.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					/* create an instance of intent
					 * pass action android.media.action.IMAGE_CAPTURE 
					 * as argument to launch camera
					 */
					if(first_page==1 && second_page==1 && third_page==1){
				
					Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
					/*create instance of File with name img.jpg*/
					File file = new File(Environment.getExternalStorageDirectory()+File.separator + "img.jpg");
					/*put uri as extra in intent object*/
					intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
					/*start activity for result pass intent as argument and request code */
					startActivityForResult(intent, 1);
					ivGrayCircle.setImageResource(R.drawable.red_circle);
					fourth_page=1;
					mViewPager.setAdapter(mSectionsPagerAdapter);
					mViewPager.setCurrentItem(4);
					
					}
					else{
						Toast.makeText(getApplicationContext(), "Please complete the previous test first", Toast.LENGTH_SHORT).show();
					}
				}
			});
			return rootView;	
		}
		
		@Override
		public void onActivityResult(int requestCode, int resultCode, Intent data) {
			super.onActivityResult(requestCode, resultCode, data);
			System.out.println(""+requestCode);
			//if request code is same we pass as argument in startActivityForResult
			if(requestCode==1)
			{
				//create instance of File with same name we created before to get image from storage
				File file = new File(Environment.getExternalStorageDirectory()+File.separator + "img.jpg");
				//Crop the captured image using an other intent
				try 
				{
					/*the user's device may not support cropping*/
					cropCapturedImage(Uri.fromFile(file));
				}
				catch(ActivityNotFoundException aNFE){
					//display an error message if user device doesn't support
					String errorMessage = "Sorry - your device doesn't support the crop action!";
					Toast toast = Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT);
					toast.show();
				}
			}
			if(requestCode==2)
			{
				//Create an instance of bundle and get the returned data
				Bundle extras = data.getExtras();
				//get the cropped bitmap from extras
				Bitmap thePic = extras.getParcelable("data");
				//set image bitmap to image view
				Bitmap gray=toGrayscale(thePic);
				
				int h = 20; // height in pixels
				int w = 20; // width in pixels    
				Bitmap scaled = Bitmap.createScaledBitmap(gray, h, w, true);

		        saveBitmapBlack(scaled);
				//imVCature_pic.setImageBitmap(scaled);
		        
		        Intent i = new Intent(this.getActivity(), upload1.class);
		        startActivity(i);
			}
		}
		//create helping method cropCapturedImage(Uri picUri)
		public void cropCapturedImage(Uri picUri){
			//call the standard crop action intent 
			Intent cropIntent = new Intent("com.android.camera.action.CROP");
			//indicate image type and Uri of image
			cropIntent.setDataAndType(picUri, "image/*");
			//set crop properties
			cropIntent.putExtra("crop", "true");
			//indicate aspect of desired crop
			cropIntent.putExtra("aspectX", 1);
			cropIntent.putExtra("aspectY", 1);
			//indicate output X and Y
			cropIntent.putExtra("outputX", 256);
			cropIntent.putExtra("outputY", 256);
			//retrieve data on return
			cropIntent.putExtra("return-data", true);
			//start the activity - we handle returning in onActivityResult
			startActivityForResult(cropIntent, 2);
		}
		public Bitmap toGrayscale(Bitmap bmpOriginal)
	    {       
			saveBitmapNormal(bmpOriginal);
	        int width, height;
	        height = bmpOriginal.getHeight();
	        width = bmpOriginal.getWidth();    

	        Bitmap bmpGrayscale = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
	        Canvas c = new Canvas(bmpGrayscale);
	        Paint paint = new Paint();
	        ColorMatrix cm = new ColorMatrix();
	        cm.setSaturation(0);
	        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
	        paint.setColorFilter(f);
	        c.drawBitmap(bmpOriginal, 0, 0, paint);
	        return bmpGrayscale;
	    }
		
		public void saveBitmapNormal(Bitmap bitmap) 
		{
			File graphshotsDirectory = new File("/sdcard/Leprosy/Normal");
			// have the object build the directory structure, if needed.
			graphshotsDirectory.mkdirs();
			File imagePath = new File(Environment.getExternalStorageDirectory() + "/Leprosy/Normal/image.jpg");
			FileOutputStream fos;
			try {
				fos = new FileOutputStream(imagePath);
				bitmap.compress(CompressFormat.JPEG, 100, fos);
				
				fos.flush();
				fos.close();
			} catch (FileNotFoundException e) {
				Log.e("GREC", e.getMessage(), e);
			} catch (IOException e) {
				Log.e("GREC", e.getMessage(), e);
			}
		}
		
		public void saveBitmapBlack(Bitmap bitmap) 
		{
			File graphshotsDirectory = new File("/sdcard/Leprosy/Gray");
			// have the object build the directory structure, if needed.
			graphshotsDirectory.mkdirs();
			File imagePath = new File(Environment.getExternalStorageDirectory() + "/Leprosy/Gray/image.jpg");
			FileOutputStream fos;
			try {
				fos = new FileOutputStream(imagePath);
				bitmap.compress(CompressFormat.JPEG, 100, fos);
				fos.flush();
				fos.close();
			} catch (FileNotFoundException e) {
				Log.e("GREC", e.getMessage(), e);
			} catch (IOException e) {
				Log.e("GREC", e.getMessage(), e);
			}
		}

	}
		
@Override
protected void onDestroy() {
	// TODO Auto-generated method stub
	super.onDestroy();
	
}

@Override
public void onPageScrollStateChanged(int arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void onPageScrolled(int arg0, float arg1, int arg2) {
	// TODO Auto-generated method stub
	
}

@Override
public void onPageSelected(int arg0) {
	// TODO Auto-generated method stub
	
}



}
