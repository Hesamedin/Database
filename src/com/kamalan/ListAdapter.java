package com.kamalan;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {

	private final String TAG = "*** ListAdapter ***";
	
	private LayoutInflater myInflater;
	private List<Contact> list;
	
	public ListAdapter(Context context) {
		myInflater = LayoutInflater.from(context);
		
		Log.i(TAG, "Adapter setuped successfully.");
	}
	
	public void setData(List<Contact> list) {
		this.list = list;
		
		Log.i(TAG, "Data passed to the adapter.");
	}
	
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;        
		
		if (convertView == null) {
        	convertView 	= myInflater.inflate(R.layout.list_adapter, null);
        	holder 			= new ViewHolder();
			holder.tvId  	= (TextView) convertView.findViewById(R.id.tvId);
			holder.tvName  	= (TextView) convertView.findViewById(R.id.tvName);
			holder.tvPhone 	= (TextView) convertView.findViewById(R.id.tvPhone);
			
			convertView.setTag(holder);
        } else {
        	holder = (ViewHolder) convertView.getTag();
        }
		
		holder.tvId.setTag(list.get(position).getIdInString());
		holder.tvId.setText(list.get(position).getIdInString());
		holder.tvName.setText(list.get(position).getName());
		holder.tvPhone.setText(list.get(position).getPhoneNumber());
		
		return convertView;
	}

	static class ViewHolder {
    	TextView tvId;
    	TextView tvName;
    	TextView tvPhone;
    }

}
