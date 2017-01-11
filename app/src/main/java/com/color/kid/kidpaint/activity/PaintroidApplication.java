package com.color.kid.kidpaint.activity;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.view.Menu;

import com.color.kid.kidpaint.command.CommandManager;
import com.color.kid.kidpaint.command.implementation.CommandManagerImplementation;
import com.color.kid.kidpaint.tools.Tool;
import com.color.kid.kidpaint.ui.DrawingSurface;
import com.color.kid.kidpaint.ui.Perspective;

/**
 * Created by Tung on 1/9/2017.
 */

public class PaintroidApplication extends android.app.Application{

    public static final String TAG = "PAINTROID";

    public static Context applicationContext;
    public static DrawingSurface drawingSurface;
    public static CommandManager commandManager;
    public static Tool currentTool;
    public static Perspective perspective;
    public static boolean openedFromCatroid = false;
    public static String catroidPicturePath;
    public static boolean isPlainImage = true;
    public static Menu menu;
    public static boolean isSaved = true;
    public static Uri savedPictureUri = null;
    public static boolean saveCopy = false;
    public static boolean scaleImage = true;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = getApplicationContext();
        commandManager = new CommandManagerImplementation();
    }

    public static String getVersionName(Context context) {
        String versionName = "unknown";
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(),
                            PackageManager.GET_META_DATA);
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException nameNotFoundException) {
            Log.e(PaintroidApplication.TAG, "Name not found",
                    nameNotFoundException);
        }
        return versionName;
    }
}
