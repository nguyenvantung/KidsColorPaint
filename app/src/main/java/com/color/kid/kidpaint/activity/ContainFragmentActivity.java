package com.color.kid.kidpaint.activity;

import android.os.Bundle;

import com.color.kid.kidpaint.R;
import com.color.kid.kidpaint.fragment.HomeFragment;
import com.color.kid.kidpaint.util.FragmentUtil;

/**
 * Created by Tung on 1/9/2017.
 */

public class ContainFragmentActivity extends OptionsActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentUtil.replaceFragment(this, new HomeFragment(),null);
    }
}
