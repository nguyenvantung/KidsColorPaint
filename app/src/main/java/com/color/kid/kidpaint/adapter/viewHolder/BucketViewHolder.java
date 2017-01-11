package com.color.kid.kidpaint.adapter.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.color.kid.kidpaint.R;
import com.color.kid.kidpaint.adapter.Bucket;
import com.color.kid.kidpaint.listener.OnClickItemDraw;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Tung Nguyen on 12/27/2016.
 */
public class BucketViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    @Bind(R.id.imgItem)
    ImageView imgItem;
    public OnClickItemDraw onClickItemDraw;
    private Bucket bucket;

    public BucketViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (onClickItemDraw != null){
            onClickItemDraw.onclickItemDraw(bucket.color, true);
        }
    }

    public void setData(Bucket data) {
        bucket = data;
        imgItem.setImageResource(data.drawable);
    }

    public void setOnClickItemDraw(OnClickItemDraw onClickItemDraw) {
        this.onClickItemDraw = onClickItemDraw;
    }
}