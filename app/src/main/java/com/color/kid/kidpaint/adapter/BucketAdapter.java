package com.color.kid.kidpaint.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.color.kid.kidpaint.R;
import com.color.kid.kidpaint.adapter.viewHolder.BucketViewHolder;
import com.color.kid.kidpaint.adapter.viewHolder.PencilViewholder;
import com.color.kid.kidpaint.adapter.viewHolder.PickerViewholder;
import com.color.kid.kidpaint.listener.OnClickItemDraw;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Tung Nguyen on 12/27/2016.
 */
public class BucketAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final int HEADER = 0;
    private static final int BODY = 1;

    public List<Bucket> bucketList = new ArrayList<>();

    public OnClickItemDraw onClickItemDraw;

    public void setOnClickItemDraw(OnClickItemDraw onClickItemDraw){
        this.onClickItemDraw = onClickItemDraw;
    }

    public BucketAdapter(List<Bucket> bucketList){
        this.bucketList = bucketList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEADER){
            return new PickerViewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.image_picket, parent, false));
        }else {
            return new BucketViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false));
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position > 0){
            BucketViewHolder viewholder = (BucketViewHolder) holder;
            viewholder.setOnClickItemDraw(onClickItemDraw);
            viewholder.setData(bucketList.get(position));
        }else {
            PickerViewholder pickerViewholder = (PickerViewholder) holder;
            pickerViewholder.setData(R.drawable.color_picker);
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return HEADER;
        }else {
            return BODY;
        }
    }

    @Override
    public int getItemCount() {
        return bucketList.size() + 1;
    }
}
