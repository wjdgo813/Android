package com.example.lg.firstgdg2;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by LG on 2016-01-27.
 */
public class ListViewAdapter extends BaseAdapter {

    private Context mContext = null;
    private ArrayList<ListData> mListData = new ArrayList<ListData>();

    private class ViewHolder{
        public ImageView mIcon;
        public TextView mTitle;
      //  public TextView mThumbnailText;
    }


    public ListViewAdapter(Context mContext){
        super();
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mListData.size();
    }

    @Override
    public Object getItem(int position) {
        return mListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView ==null){

            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item,null);

            holder.mIcon = (ImageView)convertView.findViewById(R.id.image);
            holder.mTitle = (TextView)convertView.findViewById(R.id.titleText);
           // holder.mThumbnailText = (TextView)convertView.findViewById(R.id.thumbnailText);


            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder)convertView.getTag();
        }

        ListData mData = mListData.get(position);



        if(mData.mIcon != null){
            holder.mIcon.setVisibility(View.VISIBLE);
            //holder.mIcon.setImageDrawable(mData.mIcon);
            Log.v("mData.mIcon",mData.mIcon);
            Glide.with(mContext)
                    .load(mData.mIcon)
                    .into(holder.mIcon);
        }
        else{
            holder.mIcon.setVisibility(View.GONE);
        }
       // holder.mThumbnailText.setText(mData.mLink);
        holder.mTitle.setText(mData.mTitle);


        return convertView;
    }

    public void addItem(String img,String mTitle,String mLink){
        Log.v("ListViewAdapter",img);
        ListData addInfo=null;
        addInfo = new ListData();

        addInfo.mIcon = img;
        addInfo.mTitle = mTitle;
        addInfo.mLink = mLink;
        mListData.add(addInfo);

    }
    public void dataChange(ListViewAdapter mAdapter){
        mAdapter.notifyDataSetChanged();
    }


}

