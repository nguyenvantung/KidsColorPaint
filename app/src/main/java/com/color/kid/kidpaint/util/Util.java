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
        Display display = activity.getWindowManager().getDefaultDisplay();
        float width = display.getWidth();
        Bitmap bitmap = Bitmap.createBitmap((int)width, (int)width, Bitmap.Config.ARGB_8888);
        if (drawable instanceof BitmapDrawable) {
            bitmap = ((BitmapDrawable)drawable).getBitmap();
            return Util.getResizedBitmap(bitmap, (int) width, (int)width);
        }
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public static Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
        return resizedBitmap;
    }

    public static Bitmap overlay(Bitmap bmp1, Bitmap bmp2) {
        Bitmap bmOverlay = Bitmap.createBitmap(bmp1.getWidth(), bmp1.getHeight(), bmp1.getConfig());
        Canvas canvas = new Canvas(bmOverlay);
        canvas.drawBitmap(bmp1, new Matrix(), null);
        canvas.drawBitmap(bmp2, 0, 0, null);
        return bmOverlay;
    }


}
