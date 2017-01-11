package com.color.kid.kidpaint.adapter.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.color.kid.kidpaint.R;
import com.color.kid.kidpaint.activity.PaintroidApplication;
import com.color.kid.kidpaint.ui.TopBar;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Tung Nguyen on 12/27/2016.
 */
public class PickerViewholder extends RecyclerView.ViewHolder implements View.OnClickListener{

    @Bind(R.id.imgItem)
    ImageView imageView;

    public PickerViewholder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
    }

    public void setData(int drawable) {
        //imageView.setImageDrawable(imageView.getContext().getDrawable(drawable));
    }

    @Override
    public void onClick(View view) {
        if (PaintroidApplication.currentTool != null) {
            PaintroidApplication.currentTool
                    .attributeButtonClick(TopBar.ToolButtonIDs.BUTTON_ID_PARAMETER_BOTTOM_2);
        }
    }

}
