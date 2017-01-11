package com.color.kid.kidpaint.util;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.view.Display;

import com.color.kid.kidpaint.R;


/**
 * Created by Tung Nguyen on 12/23/2016.
 */
public class Util {

    public static void playSound(Activity activity, int sound){
        MediaPlayer mp = MediaPlayer.create(activity, sound);
        mp.start();
    }

    public static MediaPlayer playMusic(Activity activity){
        MediaPlayer player = null;
        if (player == null){
            player = MediaPlayer.create(activity, R.raw.bgr_be_happy);
            player.setLooping(true);
        }
        return player;
    }

    public static Bitmap drawableToBitmap (Drawable drawable, Activity activity) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable)drawable).getBitmap();
        }
        Display display = activity.getWindowManager().getDefaultDisplay();
        float width = display.getWidth();
        float height = display.getHeight();
        Bitmap bitmap = Bitmap.createBitmap((int)width, (int)height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public static Bitmap overlay(Bitmap bmp1, Bitmap bmp2) {
        Bitmap bmOverlay = Bitmap.createBitmap(bmp1.getWidth(), bmp1.getHeight(), bmp1.getConfig());
        Canvas canvas = new Canvas(bmOverlay);
        canvas.drawBitmap(bmp1, new Matrix(), null);
        canvas.drawBitmap(bmp2, 0, 0, null);
        return bmOverlay;
    }


}
