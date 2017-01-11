package com.color.kid.kidpaint.util;

/**
 * Created by Tung Nguyen on 12/27/2016.
 */

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Build.VERSION;
import android.util.Log;

import com.color.kid.kidpaint.R;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.ArrayList;
import java.util.List;


public class ColoringUtility {
   /* public static final Map<String, Integer> COLORS_BRUSH_ID_MAPS;
    public static final Map<String, Integer> COLORS_BUCKET_ID_MAPS;*/
    public static final List<Integer> COLORS_MAPS;

    static {
        COLORS_MAPS = createMap();
      /*  COLORS_BRUSH_ID_MAPS = createBrushMapID();
        COLORS_BUCKET_ID_MAPS = createBucketMapID();*/
    }

    private static List<Integer> createMap() {
        List<Integer> result = new ArrayList<>();
        result.add(Integer.valueOf(R.color.aquamarine));
        result.add( Integer.valueOf(R.color.beige));
        result.add( Integer.valueOf(R.color.black));
        result.add(Integer.valueOf(R.color.blue));
        result.add( Integer.valueOf(R.color.bright_green));
        result.add(Integer.valueOf(R.color.brown));
        result.add(Integer.valueOf(R.color.copper));
        result.add( Integer.valueOf(R.color.crimson));
        result.add( Integer.valueOf(R.color.gold));
        result.add( Integer.valueOf(R.color.green));
        result.add( Integer.valueOf(R.color.grey));
        result.add( Integer.valueOf(R.color.light_blue));
        result.add(Integer.valueOf(R.color.magenta));
        result.add( Integer.valueOf(R.color.orange));
        result.add(Integer.valueOf(R.color.pink));
        result.add( Integer.valueOf(R.color.purple));
        result.add( Integer.valueOf(R.color.red));
        result.add( Integer.valueOf(R.color.vailet));
        result.add( Integer.valueOf(R.color.white));
        result.add(Integer.valueOf(R.color.yellow));
        return result;
    }



    @TargetApi(11)
    public static Bitmap decodeMutableBitmapFromResourceId(Context context, int bitmapResId) {
        Options bitmapOptions = new Options();
        if (VERSION.SDK_INT >= 11) {
            bitmapOptions.inMutable = true;
            bitmapOptions.inScaled = false;
        }
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), bitmapResId, bitmapOptions);
        bitmap.setDensity(0);
        if (bitmap.isMutable()) {
            return bitmap;
        }
        return convertToMutable(context, bitmap);
    }

    @TargetApi(14)
    public static Bitmap convertToMutable(Context context, Bitmap imgIn) {
        int width = imgIn.getWidth();
        int height = imgIn.getHeight();
        Config type = imgIn.getConfig();
        File outputFile = null;
        try {
            outputFile = File.createTempFile(Long.toString(System.currentTimeMillis()), null, context.getCacheDir());
            outputFile.deleteOnExit();
            RandomAccessFile randomAccessFile = new RandomAccessFile(outputFile, "rw");
            FileChannel channel = randomAccessFile.getChannel();
            MappedByteBuffer map = channel.map(MapMode.READ_WRITE, 0, (long) (imgIn.getRowBytes() * height));
            imgIn.copyPixelsToBuffer(map);
            imgIn.recycle();
            Bitmap result = Bitmap.createBitmap(width, height, type);
            map.position(0);
            result.copyPixelsFromBuffer(map);
            channel.close();
            randomAccessFile.close();
            outputFile.delete();
            if (outputFile == null) {
                return result;
            }
            outputFile.delete();
            return result;
        } catch (Exception e) {
            Log.e("APICALL", "error :" + e.toString());
            return null;
        } finally {
            if (outputFile != null) {
                outputFile.delete();
            }
        }
    }
}
