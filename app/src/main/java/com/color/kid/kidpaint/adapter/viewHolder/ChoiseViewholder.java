package com.color.kid.kidpaint.adapter.viewHolder;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.color.kid.kidpaint.R;
import com.color.kid.kidpaint.activity.MainActivity;
import com.color.kid.kidpaint.constan.AppConstance;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Tung Nguyen on 12/22/2016.
 */
public class ChoiseViewholder extends RecyclerView.ViewHolder implements View.OnClickListener{
    @Bind(R.id.imgItem)
    ImageView imageView;
    private int drawableData;

    public ChoiseViewholder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
    }

    public void setData(int drawable) {
        drawableData = drawable;
        imageView.setImageDrawable(imageView.getContext().getDrawable(drawable));
    }

    @Override
    public void onClick(View view) {
        //FragmentUtil.pushFragment((FragmentActivity)itemView.getContext(), MainFragment.newInstance(drawableData), null);
        Intent intent = new Intent((FragmentActivity)itemView.getContext(), MainActivity.class);
        intent.putExtra(AppConstance.KEY_DRAWABLE, drawableData);
        itemView.getContext().startActivity(intent);
    }
}
