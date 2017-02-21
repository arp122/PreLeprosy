package com.blitz.leprosydiagnosis;

import java.util.List;



import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;



/**
 * Created by arpit on 8/31/13.
 */


public class CustomBaseAdapter extends BaseAdapter {
    Context context;
    List<RowItemDoctor> rowItems;
    
    int i=0;

    
    public CustomBaseAdapter(Context context, List<RowItemDoctor> items) {
        this.context = context;
        this.rowItems = items;
    }

    /*private view holder class*/
    private class ViewHolder {

        TextView txtTitle;
        
        TextView txtDesc;
        
        
        
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        
        

        LayoutInflater mInflater = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item_details, null);
            holder = new ViewHolder();
            holder.txtDesc = (TextView) convertView.findViewById(R.id.tvDesc);
            holder.txtTitle = (TextView) convertView.findViewById(R.id.tvName);
            
            
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        
        final RowItemDoctor rowItem = (RowItemDoctor) getItem(position);

        holder.txtDesc.setText(rowItem.getDesciption());
        holder.txtTitle.setText(rowItem.getName());
        
        

        return convertView;
    }

    @Override
    public int getCount() {
        return rowItems.size();
    }

    @Override
    public Object getItem(int position) {
        return rowItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return rowItems.indexOf(getItem(position));
    }
}