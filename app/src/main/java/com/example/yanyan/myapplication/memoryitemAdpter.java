package com.example.yanyan.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by yanyan on 11/3/18.
 */

public class memoryitemAdpter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Memoryitem> memoList;
    private LayoutInflater mInflater;

    public memoryitemAdpter(Context mContext, ArrayList<Memoryitem> memoList) {
        //initialize instances variables
        this.mContext = mContext;
        this.memoList = memoList;
//        this.filterpostlist =mPostList;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return memoList.size();
    }

    @Override
    public Object getItem(int position) {
        return memoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            //inflate
            convertView = mInflater.inflate(R.layout.memorylistitem, parent, false);
            //add the views to the holder
            holder = new ViewHolder();
            holder.imageView = convertView.findViewById(R.id.memopic);
            holder.dateTextView = convertView.findViewById(R.id.item_date);
            holder.locationTextView = convertView.findViewById(R.id.item_location);

            //add the holder to the view
            convertView.setTag(holder);
        } else {
            //get the view holder from converview
            holder = (ViewHolder) convertView.getTag();
        }

        ImageView imageView = holder.imageView;
        TextView dateTextView = holder.dateTextView;
        TextView locationTextView = holder.locationTextView;

        Memoryitem memo = (Memoryitem) getItem(position);


        dateTextView.setText(memo.getdate());
        locationTextView.setText(memo.getLocation());
        //imageView



        return convertView;
    }

    private static class ViewHolder {
        public ImageView imageView;
        public TextView dateTextView;
        public TextView locationTextView;

    }
}
