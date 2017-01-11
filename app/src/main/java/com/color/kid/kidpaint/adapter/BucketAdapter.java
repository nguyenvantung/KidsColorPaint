package com.color.kid.kidpaint.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.color.kid.kidpaint.R;
import com.color.kid.kidpaint.adapter.viewHolder.BucketViewHolder;
import com.color.kid.kidpaint.listener.OnClickItemDraw;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Tung Nguyen on 12/27/2016.
 */
public class BucketAdapter extends RecyclerView.Adapter<BucketViewHolder>{

    public List<Bucket> bucketList = new ArrayList<>();

    public OnClickItemDraw onClickItemDraw;

    public void setOnClickItemDraw(OnClickItemDraw onClickItemDraw){
        this.onClickItemDraw = onClickItemDraw;
    }

    public BucketAdapter(List<Bucket> bucketList){
        this.bucketList = bucketList;
    }

    @Override
    public BucketViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BucketViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false));
    }

    @Override
    public void onBindViewHolder(BucketViewHolder holder, int position) {
        holder.setOnClickItemDraw(onClickItemDraw);
        holder.setData(bucketList.get(position));
    }

    @Override
    public int getItemCount() {
        return bucketList.size();
    }
}
