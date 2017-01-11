package com.color.kid.kidpaint.fragment;

import android.animation.Animator;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.color.kid.kidpaint.R;
import com.color.kid.kidpaint.constan.AppConstance;
import com.color.kid.kidpaint.util.FragmentUtil;
import com.color.kid.kidpaint.util.Util;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Tung Nguyen on 12/22/2016.
 */
public class HomeFragment extends BaseFragment {

    @Bind(R.id.homePlay)
    ImageView imgPlay;

    @Bind(R.id.homeLayoutSound)
    RelativeLayout layoutSound;

    @Bind(R.id.homeSetting)
    ImageView imgSetting;

    @Bind(R.id.homeMusic)
    ImageView imgMusic;

    @Bind(R.id.homeSound)
    ImageView imgSound;

    private boolean isOpenSetting;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View root) {

    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.homePlay)
    public void gotoSelectItem() {
        FragmentUtil.pushFragment(getActivity(), ChoiseItemFragment.newInstance(), null);
    }

    @OnClick(R.id.homeMusic)
    public void handleMusic() {
        if (!AppConstance.isPlaymusic) {
            imgMusic.setSelected(true);
            AppConstance.isPlaymusic = true;
            //Util.playMusic(getActivity()).start();
        } else {
            AppConstance.isPlaymusic = false;
            imgMusic.setSelected(false);
            //Util.playMusic(getActivity()).stop();
        }
    }

    @OnClick(R.id.homeSound)
    public void handleSound() {
        if (!AppConstance.isSound) {
            imgSound.setSelected(true);
            AppConstance.isSound = true;
        } else {
            imgSound.setSelected(false);
            AppConstance.isSound = false;
        }
    }

    @OnClick(R.id.homeSetting)
    public void handleSlideSetting() {
        if (((RelativeLayout.LayoutParams) layoutSound.getLayoutParams()).leftMargin == 0) {
            layoutSound.animate().translationX((float)getResources().getDimensionPixelSize(R.dimen.main_sound_margin_left)).setDuration(300).setInterpolator(new BounceInterpolator()).setListener(new AnimationLeft()).start();
        } else {
            layoutSound.animate().translationX((float) getResources().getDimensionPixelSize(R.dimen.main_sound_margin_left) * -1).setDuration(300).setInterpolator(new BounceInterpolator()).setListener(new AnimationRight()).start();
        }

    }

    class AnimationLeft implements Animator.AnimatorListener {
        AnimationLeft() {
        }

        public void onAnimationStart(Animator animation) {
        }

        public void onAnimationEnd(Animator animation) {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) layoutSound.getLayoutParams();
            params.leftMargin = getResources().getDimensionPixelSize(R.dimen.main_sound_margin_left);
            layoutSound.setTranslationX(0.0f);
            layoutSound.setLayoutParams(params);
        }

        public void onAnimationCancel(Animator animation) {
        }

        public void onAnimationRepeat(Animator animation) {
        }
    }

    /* renamed from: com.coloring.book.animals.fragment.MainFragment.8.2 */
    class AnimationRight implements Animator.AnimatorListener {
        AnimationRight() {
        }

        public void onAnimationStart(Animator animation) {
        }

        public void onAnimationEnd(Animator animation) {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) layoutSound.getLayoutParams();
            params.leftMargin = 0;
            layoutSound.setTranslationX(0.0f);
            layoutSound.setLayoutParams(params);
        }

        public void onAnimationCancel(Animator animation) {
        }

        public void onAnimationRepeat(Animator animation) {
        }
    }


}
