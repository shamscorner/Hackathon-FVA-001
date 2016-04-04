package com.fva_001.flashvsarrow.com.fva_001;

import android.media.MediaPlayer;

/**
 * Created by ShamimH on 04-Apr-16.
 */
public class MediaPlayerRelease implements MediaPlayer.OnCompletionListener {
    @Override
    public void onCompletion(MediaPlayer mp) {
        mp.release();
    }
}
