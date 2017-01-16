package com.color.kid.kidpaint.adapter.viewHolder;

import android.animation.Animator;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.color.kid.kidpaint.R;
import com.color.kid.kidpaint.adapter.Pencil;
import com.color.kid.kidpaint.listener.OnClickItemBush;
import com.color.kid.kidpaint.util.DebugLog;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Tung Nguyen on 12/27/2016.
 */
public class PencilViewholder extends RecyclerView.ViewHolder implements View.OnClickListener, Animator.AnimatorListener {
    @Bind(R.id.imgItem)
    ImageView imgItem;

    @Bind(R.id.layoutPencil)
    public RelativeLayout layoutPencil;

    public OnClickItemBush clickItemBush;
    private Pencil pencil;

    public PencilViewholder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (clickItemBush != null) {
            pencil.select = true;
            pencil.position = getAdapterPosition();
            clickItemBush.selectItemBush(pencil);
        }
    }

    public void setAnimation(boolean select){
        if (select && getAdapterPosition() == pencil.position) {
            layoutPencil.animate().translationY((float) imgItem.getContext().getResources()
                    .getDimensionPixelSize(R.dimen.item_select)).setDuration(300)
                    .setInterpolator(new BounceInterpolator()).setListener(this).start();

        }else {
            layoutPencil.animate().translationY((float) imgItem.getContext().getResources()
                    .getDimensionPixelSize(R.dimen.item_default)).setDuration(300)
                    .setInterpolator(new BounceInterpolator()).setListener(this).start();
        }
    }

    public void setData(Pencil data) {
        pencil = data;
        imgItem.setImageResource(data.drawable);

    }

    public void setOnClickItemDraw(OnClickItemBush onClickItemDraw) {
        this.clickItemBush = onClickItemDraw;
    }


    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {

    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }
}
