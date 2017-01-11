package com.color.kid.kidpaint.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.color.kid.kidpaint.R;
import com.color.kid.kidpaint.adapter.viewHolder.PencilViewholder;
import com.color.kid.kidpaint.adapter.viewHolder.PickerViewholder;
import com.color.kid.kidpaint.listener.OnClickItemBush;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Tung Nguyen on 12/27/2016.
 */
public class PencilAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final int HEADER = 0;
    private static final int BODY = 1;

    private List<Pencil> pencilList = new ArrayList<>();

    private OnClickItemBush onClickItemDraw;

    public void setOnClickItemDraw(OnClickItemBush onClickItemDraw){
        this.onClickItemDraw = onClickItemDraw;
    }

    public PencilAdapter(List<Pencil> pencilList){
        this.pencilList = pencilList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEADER){
            return new PickerViewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.image_picket, parent, false));
        }else {
            return new PencilViewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pencil, parent, false));
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position > 0){
            PencilViewholder viewholder = (PencilViewholder) holder;
            viewholder.setOnClickItemDraw(onClickItemDraw);
            viewholder.setData(pencilList.get(position - 1));
            viewholder.setAnimation(pencilList.get(position - 1).select);
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
        return pencilList.size() + 1;
    }
}
