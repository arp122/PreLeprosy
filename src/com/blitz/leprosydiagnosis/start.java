package com.blitz.leprosydiagnosis;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;



public class start extends ActionBarActivity {
	

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	
	
	private ActionBarDrawerToggle mDrawerToggle;

	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private static String[] mTitles = {"Leprosy Test","About","Feedback", "How it works"};
	//ActionBar actionBar = getActionBar();
	ActionBar bar= getSupportActionBar();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	
		setContentView(R.layout.start_draw);
			
		try
		{
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
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

		
		
		mTitle = mDrawerTitle = getTitle();
        
       mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mTitles));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  
                mDrawerLayout,         
                R.drawable.ic_drawer,  
                R.string.drawer_open  
                ) {
            public void onDrawerClosed(View view) {
            	getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); 
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
                boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
                invalidateOptionsMenu(); 
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        if (savedInstanceState == null) {
            selectItem(0);
        }
        
        
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    case android.R.id.home:
	        if(mDrawerLayout.isDrawerOpen(mDrawerList)) {
	            mDrawerLayout.closeDrawer(mDrawerList);
	        }
	        else {
	            mDrawerLayout.openDrawer(mDrawerList);
	        }
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}
	  private class DrawerItemClickListener implements ListView.OnItemClickListener {
	        @Override
	        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	            selectItem(position);
	        }
	    }
	private void selectItem(int position) {
		
		if(position==0){
        Fragment fragment = new PlanetFragment();
        Bundle args = new Bundle();
        args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        mDrawerList.setItemChecked(position, true);
        setTitle("Leprosy Diagnosis");
        mDrawerLayout.closeDrawer(mDrawerList);
		}
		else if(position==1){
			Fragment fragment = new PlanetFragment1();
	        Bundle args = new Bundle();
	        args.putInt(PlanetFragment1.ARG_PLANET_NUMBER, position);
	        fragment.setArguments(args);

	        FragmentManager fragmentManager = getSupportFragmentManager();
	        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

	        mDrawerList.setItemChecked(position, true);
	        setTitle(mTitles[position]);
	        mDrawerLayout.closeDrawer(mDrawerList);
		}
		else if(position==2){
			Fragment fragment = new PlanetFragment2();
	        Bundle args = new Bundle();
	        args.putInt(PlanetFragment2.ARG_PLANET_NUMBER, position);
	        fragment.setArguments(args);

	        FragmentManager fragmentManager = getSupportFragmentManager();
	        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

	        mDrawerList.setItemChecked(position, true);
	        setTitle(mTitles[position]);
	        mDrawerLayout.closeDrawer(mDrawerList);
		}
		else if(position==3){
			String str="http://www.preleprosy.com/index.html";

			 Intent how = new Intent(Intent.ACTION_VIEW,Uri.parse(str));
		     startActivity(how);
		}
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    /**
     * Fragment that appears in the "content_frame", shows a planet
     */
   /* public static class PlanetFragment extends Fragment {
        public static final String ARG_PLANET_NUMBER = "planet_number";

        public PlanetFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_planet, container, false);
            int i = getArguments().getInt(ARG_PLANET_NUMBER);
            String planet = mTitles[i];

            int imageId = getResources().getIdentifier(planet.toLowerCase(Locale.getDefault()),
                            "drawable", getActivity().getPackageName());
            ((ImageView) rootView.findViewById(R.id.image)).setImageResource(imageId);
            getActivity().setTitle(planet);
            return rootView;
        }
    }*/
    public static class PlanetFragment extends Fragment implements OnItemClickListener {
        public static final String ARG_PLANET_NUMBER = "planet_number";
        ListView lvMain;
        public PlanetFragment() {
            // Empty constructor required for fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.start, container, false);
            int j = getArguments().getInt(ARG_PLANET_NUMBER);
            lvMain=(ListView)rootView.findViewById(R.id.lvMain);
    		initListView();
    		lvMain.setOnItemClickListener(this);
            
            return rootView;
    }
        public void initListView()
    	{	

    		Typeface font;
    		RowItem item;
    		List<RowItem> rowItems;
    		//font = Typeface.createFromAsset(getAssets(), "BrandonText-Medium.otf");
    		rowItems = new ArrayList<RowItem>();
    		item = new RowItem ("z","sdf","sdf","sdf","#CCCCCC",1,2,"");
    		rowItems.add(item);
    		item = new RowItem ("z","sdf","sdf","sdf","#CCCCCC",2,2,"");
    		rowItems.add(item);
    		item = new RowItem ("z","sdf","sdf","sdf","#CCCCCC",5,2,"");
    		rowItems.add(item);
    		item = new RowItem ("z","sdf","sdf","sdf","#CCCCCC",6,2,"");
    		rowItems.add(item);
    		item = new RowItem ("z","sdf","sdf","sdf","#CCCCCC",3,2,"");
    		rowItems.add(item);
    		item = new RowItem ("z","sdf","sdf","sdf","#CCCCCC",4,2,"");
    		rowItems.add(item);
    		item = new RowItem ("z","sdf","sdf","sdf","#CCCCCC",4,2,"");
    		rowItems.add(item);
    		
    		System.out.println("added events");
    		CustomAdapterMulti adapter = new CustomAdapterMulti(this.getActivity(), rowItems);
    		//LayoutInflater inflater = getLayoutInflater();
    		//lvMain.setBackgroundColor(Color.parseColor("#00FFFFFF"));
    		lvMain.setAdapter(adapter);
    		

    	}
        @Override
    	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
    		// TODO Auto-generated method stub
    		if(position==1){
    			Intent intent =new Intent(this.getActivity(),MainActivity.class);
    			startActivity(intent);
    		}
    		else if(position==2){
    			Intent intent =new Intent(this.getActivity(),InfoActivity.class);
    			startActivity(intent);
    		}
    		else if(position==3){
    			Intent intent =new Intent(this.getActivity(),doctor.class);
    			startActivity(intent);
    		}
    	}
    }
    public static class PlanetFragment1 extends Fragment  {
        public static final String ARG_PLANET_NUMBER = "planet_number";
        ListView lvMain;
        public PlanetFragment1() {
            // Empty constructor required for fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.about, container, false);
            
            
            return rootView;
    }
        
    }
    public static class PlanetFragment2 extends Fragment  {
        public static final String ARG_PLANET_NUMBER = "planet_number";
        ListView lvMain;
        public PlanetFragment2() {
            // Empty constructor required for fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.feedback, container, false);
            
            
            return rootView;
    }
        
    }
    public static class PlanetFragment3 extends Fragment  {
        public static final String ARG_PLANET_NUMBER = "planet_number";
        ListView lvMain;
        public PlanetFragment3() {
            // Empty constructor required for fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.start, container, false);
            
            
            return rootView;
    }
        
    }
	
	
}
