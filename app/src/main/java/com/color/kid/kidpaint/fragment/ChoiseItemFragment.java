package com.color.kid.kidpaint.fragment;

import android.graphics.Typeface;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.color.kid.kidpaint.R;
import com.color.kid.kidpaint.adapter.ChoiseFragmentAdapter;
import com.color.kid.kidpaint.ui.ItemOffsetDecoration;

import butterknife.Bind;

/**
 * Created by Tung Nguyen on 12/22/2016.
 */
public class ChoiseItemFragment extends BaseFragment{

    @Bind(R.id.gradient_textview)
    TextView tvTitle;

    public int[] listAminal = {R.drawable.small_picture_1, R.drawable.small_picture_2, R.drawable.small_picture_3, R.drawable.small_picture_4, R.drawable.small_picture_5,
            R.drawable.small_picture_6, R.drawable.small_picture_7, R.drawable.small_picture_8, R.drawable.small_picture_9, R.drawable.small_picture_10, R.drawable.small_picture_11,
            R.drawable.small_picture_12, R.drawable.small_picture_13, R.drawable.small_picture_14, R.drawable.small_picture_15, R.drawable.small_picture_16, R.drawable.small_picture_17,
            R.drawable.small_picture_18, R.drawable.small_picture_19, R.drawable.small_picture_20, R.drawable.small_picture_21, R.drawable.small_picture_22, R.drawable.small_picture_23,
            R.drawable.small_picture_24, R.drawable.small_picture_25, R.drawable.small_picture_26, R.drawable.small_picture_27, R.drawable.small_picture_28, R.drawable.small_picture_29,
            R.drawable.small_picture_30, R.drawable.small_picture_31, R.drawable.small_picture_32, R.drawable.small_picture_33, R.drawable.small_picture_34, R.drawable.small_picture_35,
            R.drawable.small_picture_36, R.drawable.small_picture_37, R.drawable.small_picture_38, R.drawable.small_picture_39, R.drawable.small_picture_40, R.drawable.small_picture_41,
            R.drawable.small_picture_42, R.drawable.small_picture_43, R.drawable.small_picture_44, R.drawable.small_picture_45, R.drawable.small_picture_46, R.drawable.small_picture_47,
            R.drawable.small_picture_48, R.drawable.small_picture_49, R.drawable.small_picture_50, R.drawable.small_picture_51, R.drawable.small_picture_52, R.drawable.small_picture_53,
            R.drawable.small_picture_54, R.drawable.small_picture_56, R.drawable.small_picture_57, R.drawable.small_picture_58, R.drawable.small_picture_59, R.drawable.small_picture_60,
            R.drawable.small_picture_61, R.drawable.small_picture_62, R.drawable.small_picture_63, R.drawable.small_picture_64, R.drawable.small_picture_65, R.drawable.small_picture_66,
            };
    @Bind(R.id.listItem)
    RecyclerView recyclerView;

    public static ChoiseItemFragment newInstance(){
        return new ChoiseItemFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_choise;
    }

    @Override
    protected void initView(View root) {
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getActivity(), R.dimen.item_image);
        recyclerView.addItemDecoration(itemDecoration);
        Typeface font1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/cooper_black.ttf");
        tvTitle.setTypeface(font1);

    }

    @Override
    protected void initData() {
        ChoiseFragmentAdapter adapter = new ChoiseFragmentAdapter(listAminal);
        recyclerView.setAdapter(adapter);
    }
}
