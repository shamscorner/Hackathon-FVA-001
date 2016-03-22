package com.fva_001.flashvsarrow.com.fva_001;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * Created by ShamimH on 21-Mar-16.
 */
public class ButtonClick {

    private MediaPlayer button_music;
    private Animation animationAlpha;

    ButtonClick(Context context, View view){
        //animation for the button click
        animationAlpha = AnimationUtils.loadAnimation(context, R.anim.anim_alpha);
        view.startAnimation(animationAlpha);
        button_music = MediaPlayer.create(context, R.raw.button_click);
        button_music.start();
    }
}
