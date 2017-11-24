package com.sharma.deepak.listview;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by deepak on 19-07-2017.
 */

public class ListAdapter extends BaseAdapter {

    ArrayList<ListModel> myList = new ArrayList<>();
    LayoutInflater inflater;
    Context context;
    ArrayList<Boolean> stateList = new ArrayList<>();


    public ListAdapter(Context context, ArrayList<ListModel> myList) {
        this.myList = myList;
        this.context = context;
        inflater = LayoutInflater.from(this.context);

        for (int i = 0; i < myList.size(); i++) {
            stateList.add(myList.get(i).isFlag());
        }
    }

    @Override
    public int getCount() {
        return myList.size();
    }

    @Override
    public ListModel getItem(int position) {
        return myList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder mViewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            mViewHolder = new MyViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        ListModel currentListData = getItem(position);

        mViewHolder.tvTitle.setText(currentListData.getText());


        if (currentListData.isFlag()) {
            mViewHolder.lvItem.setBackgroundColor(ContextCompat.getColor(context, R.color.back_color));
        } else {
            mViewHolder.lvItem.setBackground(null);
        }

      /*  if (stateList.get(position)) {
            mViewHolder.lvItem.setBackgroundColor(ContextCompat.getColor(context, R.color.back_color));
        }else{
            mViewHolder.
        }*/


        return convertView;
    }

    private class MyViewHolder {
        TextView tvTitle;
        LinearLayout lvItem;

        public MyViewHolder(View item) {
            tvTitle = (TextView) item.findViewById(R.id.tv_list_item);
            lvItem = (LinearLayout) item.findViewById(R.id.ll_list_back);
        }
    }
}