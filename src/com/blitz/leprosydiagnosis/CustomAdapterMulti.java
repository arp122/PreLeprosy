package com.blitz.leprosydiagnosis;


import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomAdapterMulti extends BaseAdapter implements OnClickListener, OnItemClickListener {


	public class ViewHolder {
		TextView tvCheck;
		/*TextView txtTitle;
		TextView txtLoc;
		TextView txtTime;
		TextView txtCat;
		View	colourBar;
		TextView txtVenue;
		TextView txtCancel;
		Button btnMore;
		ArrayList<Item> gridArray = new ArrayList<Item>();
		LinearLayout llGrid;*/
		//NetworkImageView ivIcon;
	}
	public static final int TYPE_TWO = 2;
	public static final int TYPE_ONE = 1;
	public static final int TYPE_THREE = 3;
	public static final int TYPE_FOUR = 4;
	public static final int TYPE_FIVE = 5;
	public static final int TYPE_SIX = 6;
	Context context;
	ViewHolder holder = null;
	private List<RowItem> objects;
	View v;
	/*DatabaseHandlerCalender db;
	List<Events> event_1;

	public String[] titles;
	public String[] chapter_name;
	public String[] date;
	public String[] time;
	public String[] venue;
	public String[] fee;
	public String[] description;
	public String[] cord;
	public String[] cord_no;
	public int[] images={R.drawable.event_1,R.drawable.event_2,R.drawable.event_3,R.drawable.event_4,R.drawable.event_5,R.drawable.event_6};
	public int[] going;
	public int[] event_id;
	public String[] thumb;
	public String[] cover;*/
	@Override
	public int getViewTypeCount() {
		return objects.size();
	}

	@Override
	public int getItemViewType(int position) {
		RowItem object;
		object=objects.get(position);
		return object.getType();
	}

	public CustomAdapterMulti(Context context, List<RowItem> objects) {
		this.context=context;
		this.objects = objects;
		//db= new DatabaseHandlerCalender(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {


		RowItem object;
		v=convertView;
		object=objects.get(position);
		int listViewItemType = object.getType();
		
		if (convertView == null) {
			if (listViewItemType == TYPE_ONE) {
				convertView = LayoutInflater.from(context).inflate(R.layout.list_one, null);
				holder = new ViewHolder();
				holder.tvCheck=(TextView)convertView.findViewById(R.id.tvCheck);
				holder.tvCheck.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent= new Intent(context,MainActivity.class);
					
						context.startActivity(intent);
					}
				});
				convertView.setTag(holder);
				/*
				
				convertView = LayoutInflater.from(context).inflate(R.layout.list_item, null);
				
				holder.txtTitle = (TextView) convertView.findViewById(R.id.textViewtitle);
				holder.txtLoc = (TextView) convertView.findViewById(R.id.textViewLocation);
				holder.txtTime=(TextView) convertView.findViewById(R.id.textViewTime);
				holder.txtVenue = (TextView)convertView.findViewById(R.id.textViewViewVenue);
				holder.txtCancel = (TextView)convertView.findViewById(R.id.textViewCancel);
				holder.ivIcon=(NetworkImageView)convertView.findViewById(R.id.ivIcon);
				holder.txtTitle.setText(object.getTitle());
				holder.txtLoc.setText(object.getLocation());
				holder.txtTime.setText(object.getTime());
				//holder.ivIcon.setImageResource(object.getImage());
				
				RequestQueue mRequestQueue;
				ImageLoader mImageLoader;
				mRequestQueue = Volley.newRequestQueue(context);
				mImageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {
				    private final LruCache<String, Bitmap> mCache = new LruCache<String, Bitmap>(10);
				    public void putBitmap(String url, Bitmap bitmap) {
				        mCache.put(url, bitmap);
				    }
				    public Bitmap getBitmap(String url) {
				        return mCache.get(url);
				    }
				});
				
				holder.ivIcon.setImageUrl(object.getThumb()
						, mImageLoader);
				holder.txtVenue.setOnClickListener(this);
				holder.txtCancel.setOnClickListener(this);
				convertView.setTag(holder);*/
				System.out.println("list_one");
				
				}
			 else if (listViewItemType == TYPE_TWO) {
				convertView = LayoutInflater.from(context).inflate(R.layout.list_second, null);
				holder = new ViewHolder();
				convertView.setTag(holder);
				/*holder.mGridView = (ExpandableHeightGridView)convertView.findViewById(R.id.gvTodayEvents);
				holder.mGridView.setExpanded(true);
				holder.mGridView.setOnItemClickListener(this);
				int n=db.count();
				RowItemGrid item;
				List<RowItemGrid> rowItems;
				rowItems = new ArrayList<RowItemGrid>();
				db=new DatabaseHandlerCalender(context);
				event_1=db.getAllEvents();
				System.out.println("event_1"+event_1.size());
				titles = new String[event_1.size()];
				chapter_name = new String[event_1.size()];
				date = new String[event_1.size()];
				time = new String[event_1.size()];
				description = new String[event_1.size()];
				going = new int[event_1.size()];
				event_id = new int[event_1.size()];
				fee = new String[event_1.size()];
				venue = new String[event_1.size()];
				cord = new String[event_1.size()];
				cord_no = new String[event_1.size()];
				thumb = new String[event_1.size()];
				cover = new String[event_1.size()];
				System.out.println("sizeeee"+event_1.size());
				int i=0;
				for (Events cn : event_1)
				{	
					titles[i]=""+cn.getEventName();
					chapter_name[i]=""+cn.getChapterName();
					going[i]=cn.getGoing();
					event_id[i]=Integer.parseInt(cn.getEventId());
					date[i]=cn.getDate();
					time[i]=cn.getTime();
					description[i]=cn.getDescription();
					fee[i]=cn.getFee();
					venue[i]=cn.getVenue();
					cord[i]=cn.getVenue();
					cord_no[i]=cn.getCord_no();
					thumb[i]=cn.getThumb();
					cover[i]=cn.getCover();
					i++;
				}
				System.out.println("even1sizw"+event_1.size());
				for ( i = 0; i<event_1.size(); i++) {
					System.out.println("titles"+event_id[i]+"value of i"+i);

					item = new RowItemGrid(titles[i],chapter_name[i],R.drawable.event_1,going[i],event_id[i],thumb[i]);
					rowItems.add(item);
					
				}


				holder.customGridAdapter = new CustomAdapterGrid(this.context, rowItems);
				holder.mGridView.setAdapter(holder.customGridAdapter);*/

			
				System.out.println("list_two");
				

			}else if (listViewItemType == TYPE_THREE) {
				convertView = LayoutInflater.from(context).inflate(R.layout.list_third, null);
				holder = new ViewHolder();

				convertView.setTag(holder);
				System.out.println("list_three");
				
			} 
			else if (listViewItemType == TYPE_FOUR) {
				convertView = LayoutInflater.from(context).inflate(R.layout.list_forth, null);
				holder = new ViewHolder();

				convertView.setTag(holder);
			} 
			else if (listViewItemType == TYPE_FIVE) {
				convertView = LayoutInflater.from(context).inflate(R.layout.list_fifth, null);
				holder = new ViewHolder();

				convertView.setTag(holder);
			} 
			else if (listViewItemType == TYPE_SIX) {
				convertView = LayoutInflater.from(context).inflate(R.layout.list_sixth, null);
				holder = new ViewHolder();

				convertView.setTag(holder);
			} 
			/*else if (listViewItemType == TYPE_FIVE) {
				convertView = LayoutInflater.from(context).inflate(R.layout.no_events, null);
				holder = new ViewHolder();

				convertView.setTag(holder);
			} */

		
		else{
			holder = (ViewHolder) convertView.getTag();
			System.out.println("list_normal");
			
		}
		}
		return convertView;
		
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getCount() {
		return objects.size();
	}

	@Override
	public Object getItem(int position) {
		return objects.get(position);
	}

	@Override
	public long getItemId(int position) {
		return objects.indexOf(getItem(position));
	}

	

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		// TODO Auto-generated method stub
		
	}

}
