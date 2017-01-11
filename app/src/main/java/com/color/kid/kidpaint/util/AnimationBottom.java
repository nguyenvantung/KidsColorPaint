package com.color.kid.kidpaint.util;

import android.animation.Animator;
import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by Tung Nguyen on 12/28/2016.
 */
public class AnimationBottom implements Animator.AnimatorListener {
    private View view;
    private Context context;

    public AnimationBottom(View view, Context context) {
        this.view = view;
        this.context = context;
    }

    public void onAnimationStart(Animator animation) {
    }

    public void onAnimationEnd(Animator animation) {
       /* RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
        params.topMargin = 0;
        view.setTranslationX(0.0f);
        view.setLayoutParams(params);*/
    }

    public void onAnimationCancel(Animator animation) {
    }

    public void onAnimationRepeat(Animator animation) {
    }
}