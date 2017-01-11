package com.color.kid.kidpaint.util;

import android.content.Context;
import android.media.MediaPlayer;

import java.util.Map;

/**
 * Created by Tung Nguyen on 12/28/2016.
 */
public class MySoundManager {
    private static MySoundManager _instance;
    private Context mContext;
    private Map<String, Integer> mMap;
    private MediaPlayer mMediaPlayer;

    /* renamed from: com.coloring.book.animals.utility.MySoundManager.1 */
    class C03141 implements MediaPlayer.OnCompletionListener {
        C03141() {
        }

        public void onCompletion(MediaPlayer mp) {
            if (mp != null) {
                try {
                    if (mp.isPlaying()) {
                        mp.stop();
                    }
                    mp.reset();
                    mp.release();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    /* renamed from: com.coloring.book.animals.utility.MySoundManager.2 */
    class C03152 implements MediaPlayer.OnCompletionListener {
        final /* synthetic */ MediaPlayer val$mediaPlayer;

        C03152(MediaPlayer mediaPlayer) {
            this.val$mediaPlayer = mediaPlayer;
        }

        public void onCompletion(MediaPlayer mp) {
            try {
                if (this.val$mediaPlayer != null) {
                    if (this.val$mediaPlayer.isPlaying()) {
                        this.val$mediaPlayer.stop();
                    }
                    this.val$mediaPlayer.reset();
                    this.val$mediaPlayer.release();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    static {
        _instance = null;
    }

    public static synchronized MySoundManager getInstance(Context context) {
        MySoundManager mySoundManager;
        synchronized (MySoundManager.class) {
            if (_instance == null) {
                _instance = new MySoundManager(context);
            }
            mySoundManager = _instance;
        }
        return mySoundManager;
    }

    private MySoundManager(Context context) {
        //this.mMap = createMap();
        this.mContext = context;
    }

  /*  public synchronized void playSound(String sound) {
        try {
            if (new Preferences(this.mContext).isSoundSettings()) {
                if (((Integer) this.mMap.get(sound)) != null) {
                    int rawResource = this.mContext.getResources().getIdentifier(sound, "raw", this.mContext.getPackageName());
                    if (this.mMediaPlayer != null) {
                        try {
                            this.mMediaPlayer.release();
                        } catch (Exception ex) {
                           ex.printStackTrace();
                        }
                    }
                    this.mMediaPlayer = MediaPlayer.create(this.mContext, rawResource);
                    this.mMediaPlayer.setVolume(1.0f, 1.0f);
                    this.mMediaPlayer.setOnCompletionListener(new C03141());
                    this.mMediaPlayer.start();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

   /* public synchronized void playSF() {
        try {
            if (new Preferences(this.mContext).isSoundSettings()) {
                MediaPlayer mediaPlayer = MediaPlayer.create(this.mContext, this.mContext.getResources().getIdentifier("sf_" + String.valueOf(new Random().nextInt(16)), "raw", this.mContext.getPackageName()));
                mediaPlayer.setVolume(1.0f, 1.0f);
                mediaPlayer.setOnCompletionListener(new C03152(mediaPlayer));
                mediaPlayer.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

}
