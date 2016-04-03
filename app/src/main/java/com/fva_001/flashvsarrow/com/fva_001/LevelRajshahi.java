package com.fva_001.flashvsarrow.com.fva_001;

import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by ShamimH on 23-Mar-16.
 */
public class LevelRajshahi  extends AppCompatActivity {

    private Button btnMap;
    private ImageView imgDustbin, imgBanLayer1, imgBanLayer2, imgShoes, imgBottle, imgBone, imgCupandstraw, imgCan, imgPacket, imgPaper, imgTap;
    private ImageView smoke1, smoke2, imgInstruction, imgInstruction2, imgDirection, imgBillboardBook, imgLight1, imgLight2, imgStreetLight1, imgStreetLight2;
    private ImageView imgGarbage1, imgGarbage2, imgBillboard1, imgBillboard2, imgPoster1, imgPoster2, imgPoster3, imgPoster4, imgPoster5, imgBench, imgToolsContainer;
    private AnimationDrawable animDustbin;
    private MediaPlayer background_music, success_dustbin, wrong_step, poster_sound;
    private ProgressBar progressPollution, progressUnorganized, progressRisk;
    private TextView txtPollution, txtUnorganized, txtRisk, txtTime, txtScore, txtTask, txtTaskRemain;
    private int taskTotal, taskPollution, taskUnorganized;
    private String type = "";
    private Animation animFadeOut;

    private ScoreCard scoreCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //set the background in full screen mode
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_rajshahi); //set the content view

        //initialization of all music
        background_music = MediaPlayer.create(getApplicationContext(), R.raw.background_homepage_music);
        background_music.start();
        background_music.setLooping(true);

        success_dustbin = MediaPlayer.create(getApplicationContext(), R.raw.success_drop);
        wrong_step = MediaPlayer.create(getApplicationContext(), R.raw.wrong);
        poster_sound = MediaPlayer.create(getApplicationContext(), R.raw.poster_sound);
        animFadeOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);

        //initialise all the component of the level background
        imgToolsContainer = (ImageView)findViewById(R.id.tools_container);
        imgDustbin = (ImageView)findViewById(R.id.img_dustbin);
        imgBanLayer1 = (ImageView)findViewById(R.id.img_banana_layer_1);
        imgBanLayer1.setTag("bananaLayer1");
        imgBanLayer2 = (ImageView)findViewById(R.id.img_banana_layer_2);
        imgBanLayer2.setTag("bananaLayer2");
        progressPollution = (ProgressBar)findViewById(R.id.pro_lev_pol);
        progressRisk = (ProgressBar)findViewById(R.id.pro_lev_risk);
        progressUnorganized = (ProgressBar)findViewById(R.id.pro_lev_un);
        txtPollution = (TextView)findViewById(R.id.percent_pollution_levview);
        txtRisk = (TextView)findViewById(R.id.percent_risk_levview);
        txtUnorganized = (TextView)findViewById(R.id.percent_unorganized_levview);
        txtTime = (TextView)findViewById(R.id.count_time);
        txtScore = (TextView)findViewById(R.id.count_score);
        txtTask = (TextView)findViewById(R.id.count_task);
        txtTaskRemain = (TextView)findViewById(R.id.count_task_remain);
        imgShoes = (ImageView)findViewById(R.id.shoes);
        imgShoes.setTag("shoes");
        imgBottle = (ImageView)findViewById(R.id.imageView5);
        imgBottle.setTag("bottle");
        imgBone = (ImageView)findViewById(R.id.bone);
        imgBone.setTag("bone");
        imgCupandstraw = (ImageView)findViewById(R.id.cupandstraw);
        imgCupandstraw.setTag("cupandstraw");
        imgCan = (ImageView)findViewById(R.id.bottle);
        imgCan.setTag("can");
        imgPacket = (ImageView)findViewById(R.id.packet);
        imgPacket.setTag("packet");
        imgPaper = (ImageView)findViewById(R.id.paper);
        imgPaper.setTag("paper");
        imgPoster1 = (ImageView)findViewById(R.id.poster1);
        imgPoster1.setTag("poster1");
        imgPoster2 = (ImageView)findViewById(R.id.poster2);
        imgPoster2.setTag("poster2");
        imgPoster3 = (ImageView)findViewById(R.id.poster3);
        imgPoster3.setTag("poster3");
        imgPoster4 = (ImageView)findViewById(R.id.poster4);
        imgPoster4.setTag("poster4");
        imgPoster5 = (ImageView)findViewById(R.id.poster5);
        imgPoster5.setTag("poster5");
        imgBench = (ImageView)findViewById(R.id.img_bench);
        imgBench.setTag("bench");
        imgBillboard1 = (ImageView)findViewById(R.id.img_billboard_1);
        imgBillboard1.setTag("billboard1");
        imgBillboard2 = (ImageView)findViewById(R.id.img_billboard_2);
        imgBillboard2.setTag("billboard2");
        imgBillboardBook = (ImageView)findViewById(R.id.book_shop);
        imgBillboardBook.setTag("billboard_book");
        imgGarbage1 = (ImageView)findViewById(R.id.garbage1);
        imgGarbage1.setTag("garbage1");
        imgGarbage2 = (ImageView)findViewById(R.id.garbage2);
        imgGarbage2.setTag("garbage2");
        imgStreetLight1 = (ImageView)findViewById(R.id.street_light_1);
        imgStreetLight1.setTag("street_light_1");
        imgStreetLight2 = (ImageView)findViewById(R.id.street_light_2);
        imgStreetLight2.setTag("street_light_2");
        imgLight1 = (ImageView)findViewById(R.id.light1);
        imgLight1.setTag("light1");
        imgLight2 = (ImageView)findViewById(R.id.light2);
        imgLight2.setTag("light2");
        imgDirection = (ImageView)findViewById(R.id.direction);
        imgDirection.setTag("direction");
        imgInstruction = (ImageView)findViewById(R.id.instruction);
        imgInstruction.setTag("instruction");
        imgInstruction2 = (ImageView)findViewById(R.id.instruction2);
        imgInstruction2.setTag("instruction2");

        imgInstruction.setVisibility(View.INVISIBLE);
        imgInstruction2.setVisibility(View.INVISIBLE);
        imgDirection.setVisibility(View.INVISIBLE);

        imgStreetLight1.setBackgroundColor(Color.argb(0, 0, 0, 0));
        imgStreetLight2.setBackgroundColor(Color.argb(0, 0, 0, 0));

        imgTap = (ImageView)findViewById(R.id.tap);
        ((AnimationDrawable)imgTap.getBackground()).start();
        smoke1 = (ImageView)findViewById(R.id.smoke1);
        ((AnimationDrawable)smoke1.getBackground()).start();
        smoke2 = (ImageView)findViewById(R.id.smoke2);
        ((AnimationDrawable)smoke2.getBackground()).start();

        //initialize all animation effect
        animDustbin = (AnimationDrawable) imgDustbin.getBackground();

        //initialize all the number of task
        taskPollution = 14;
        taskUnorganized = 16;
        taskTotal = taskPollution + taskUnorganized;

        //initialize the score class
        scoreCard = new ScoreCard(taskPollution, taskUnorganized);

        //counting the countdown
        scoreCard.setTime("1:20");

        //set all the value for this level
        updateScore();

        // this is the map section
        btnMap = (Button)findViewById(R.id.btn_map_in_level);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogGoBackToMap dialogGoBackToMap = new DialogGoBackToMap(LevelRajshahi.this);
                dialogGoBackToMap.show();
            }
        });

        // this is the reset handling section
        Button btnReset = (Button)findViewById(R.id.btn_reset_level);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreCard = new ScoreCard(taskPollution, taskUnorganized);
                updateScore();
                // reload this current activity
                finish();
                startActivity(getIntent());
                overridePendingTransition(0, 0);
            }
        });

        //the click listener for those items
        imgLight1.setOnClickListener(new ItemClick());
        imgLight2.setOnClickListener(new ItemClick());
        imgPoster1.setOnClickListener(new ItemClick());
        imgPoster2.setOnClickListener(new ItemClick());
        imgPoster3.setOnClickListener(new ItemClick());
        imgPoster4.setOnClickListener(new ItemClick());
        imgPoster5.setOnClickListener(new ItemClick());
        imgBillboard1.setOnClickListener(new ItemClick());
        imgBillboard2.setOnClickListener(new ItemClick());
        imgTap.setOnClickListener(new ItemClick());
        smoke1.setOnClickListener(new ItemClick());
        smoke2.setOnClickListener(new ItemClick());
        imgGarbage1.setOnClickListener(new ItemClick());
        imgGarbage2.setOnClickListener(new ItemClick());
        imgStreetLight1.setOnClickListener(new ItemClick());
        imgStreetLight2.setOnClickListener(new ItemClick());

        //the long click listener for those items
        imgBillboard2.setOnLongClickListener(new ItemLongClick());
        imgBillboard1.setOnLongClickListener(new ItemLongClick());
        imgTap.setOnLongClickListener(new ItemLongClick());
        smoke1.setOnLongClickListener(new ItemLongClick());
        smoke2.setOnLongClickListener(new ItemLongClick());
        imgGarbage1.setOnLongClickListener(new ItemLongClick());
        imgGarbage2.setOnLongClickListener(new ItemLongClick());
        imgStreetLight1.setOnLongClickListener(new ItemLongClick());
        imgStreetLight2.setOnLongClickListener(new ItemLongClick());

        //the touch listener for the dragevent
        imgBanLayer1.setOnTouchListener(new DragTouchListener());
        imgBanLayer2.setOnTouchListener(new DragTouchListener());
        imgShoes.setOnTouchListener(new DragTouchListener());
        imgBone.setOnTouchListener(new DragTouchListener());
        imgBottle.setOnTouchListener(new DragTouchListener());
        imgCupandstraw.setOnTouchListener(new DragTouchListener());
        imgCan.setOnTouchListener(new DragTouchListener());
        imgPacket.setOnTouchListener(new DragTouchListener());
        imgPaper.setOnTouchListener(new DragTouchListener());

        //the drop zone of all wast which is the dustbin
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            imgDustbin.setOnDragListener(new DustbinDragListener());
        }
    }
    private void updateScore(){
        //set all values to the layout
        progressPollution.setProgress(scoreCard.getPollutionRate());
        progressUnorganized.setProgress(scoreCard.getUnorganizedRate());
        progressRisk.setProgress(scoreCard.getRiskRate());
        txtPollution.setText(scoreCard.getPollutionRate() + "%");
        txtUnorganized.setText(scoreCard.getUnorganizedRate()+"%");
        txtRisk.setText(scoreCard.getRiskRate()+"%");
        txtTime.setText(scoreCard.getTime());
        txtScore.setText(""+scoreCard.getScore());
        txtTask.setText(""+scoreCard.getTaskCompleted());
        txtTaskRemain.setText(""+(taskTotal - scoreCard.getTaskCompleted()));
    }

    //handle the back button
    @Override
    public void onBackPressed() {
        DialogGoBackToMap dialog = new DialogGoBackToMap(LevelRajshahi.this);
        dialog.show();
    }

    //popupwindow class
    public void showTools(View v){
        new ButtonClick(getApplicationContext(), v);
        ToolsAccessories dialog = new ToolsAccessories(LevelRajshahi.this);
        dialog.show();
    }

    //click event for those items
    private final class ItemClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.light1:
                    if(new CustomAdapter().getPosition() == 6){
                        success_dustbin.start();
                        imgLight1.setBackgroundResource(R.drawable.light1);
                        scoreCard.increaseScore(20, "un");
                        updateScore();
                    }else{
                        wrong_step.start();
                        Toast.makeText(getApplicationContext(), "This item needs a tool to fix", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.light2:
                    if(new CustomAdapter().getPosition() == 6){
                        success_dustbin.start();
                        imgLight2.setBackgroundResource(R.drawable.light2);
                        scoreCard.increaseScore(20, "un");
                        updateScore();
                    }else{
                        wrong_step.start();
                        Toast.makeText(getApplicationContext(), "This item needs a tool to fix", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.poster1:
                    poster_sound.start();
                    v.startAnimation(animFadeOut);
                    v.setVisibility(View.INVISIBLE);
                    scoreCard.increaseScore(10, "un");
                    updateScore();
                    animFadeOut.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {}
                        @Override
                        public void onAnimationEnd(Animation animation) {
                            imgPoster1.clearAnimation();
                        }
                        @Override
                        public void onAnimationRepeat(Animation animation) {}
                    });
                    break;
                case R.id.poster2:
                    poster_sound.start();
                    v.startAnimation(animFadeOut);
                    v.setVisibility(View.INVISIBLE);
                    scoreCard.increaseScore(10, "un");
                    updateScore();
                    animFadeOut.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            imgPoster2.clearAnimation();
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    });
                    break;
                case R.id.poster3:
                    poster_sound.start();
                    v.startAnimation(animFadeOut);
                    v.setVisibility(View.INVISIBLE);
                    scoreCard.increaseScore(10, "un");
                    updateScore();
                    animFadeOut.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            imgPoster3.clearAnimation();
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    });
                    break;
                case R.id.poster4:
                    poster_sound.start();
                    v.startAnimation(animFadeOut);
                    v.setVisibility(View.INVISIBLE);
                    scoreCard.increaseScore(10, "un");
                    updateScore();
                    animFadeOut.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            imgPoster4.clearAnimation();
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    });
                    break;
                case R.id.poster5:
                    poster_sound.start();
                    v.startAnimation(animFadeOut);
                    v.setVisibility(View.INVISIBLE);
                    scoreCard.increaseScore(10, "un");
                    updateScore();
                    animFadeOut.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            imgPoster5.clearAnimation();
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    });
                    break;
                case R.id.img_billboard_1:
                case R.id.img_billboard_2:
                case R.id.tap:
                case R.id.smoke1:
                case R.id.smoke2:
                case R.id.garbage1:
                case R.id.garbage2:
                case R.id.street_light_1:
                case R.id.street_light_2:
                    wrong_step.start();
                    Toast.makeText(getApplicationContext(), "This item needs a tool to fix", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    //onlongclick listener for the items
    private final class ItemLongClick implements View.OnLongClickListener{

        @Override
        public boolean onLongClick(View v) {
            switch (v.getId()){
                case R.id.img_billboard_2:
                    if(new CustomAdapter().getPosition() == 3){
                        success_dustbin.start();
                        imgBillboard2.setBackgroundResource(R.drawable.billboard1);
                        scoreCard.increaseScore(20, "un");
                        updateScore();
                    }else{
                        wrong_step.start();
                        Toast.makeText(getApplicationContext(), "This item needs a tool to fix", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.img_billboard_1:
                    if(new CustomAdapter().getPosition() == 3){
                        success_dustbin.start();
                        imgBillboard1.setBackgroundResource(R.drawable.billboard2);
                        scoreCard.increaseScore(20, "un");
                        updateScore();
                    }else{
                        wrong_step.start();
                        Toast.makeText(getApplicationContext(), "This item needs a tool to fix", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.tap:
                    if(new CustomAdapter().getPosition() == 1){
                        success_dustbin.start();
                        imgTap.setBackgroundResource(R.drawable.tap_image_stop);
                        scoreCard.increaseScore(10, "pol");
                        updateScore();
                    }else{
                        wrong_step.start();
                        Toast.makeText(getApplicationContext(), "This item needs a tool to fix", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.smoke1:
                    if(new CustomAdapter().getPosition() == 7){
                        success_dustbin.start();
                        smoke1.setBackgroundResource(R.drawable.animation_smoke_safe);
                        ((AnimationDrawable)smoke1.getBackground()).start();
                        scoreCard.increaseScore(10, "pol");
                        updateScore();
                    }else{
                        wrong_step.start();
                        Toast.makeText(getApplicationContext(), "This item needs a tool to fix", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.smoke2:
                    if(new CustomAdapter().getPosition() == 7){
                        success_dustbin.start();
                        smoke2.setBackgroundResource(R.drawable.animation_smoke_safe);
                        ((AnimationDrawable)smoke2.getBackground()).start();
                        scoreCard.increaseScore(10, "pol");
                        updateScore();
                    }else{
                        wrong_step.start();
                        Toast.makeText(getApplicationContext(), "This item needs a tool to fix", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.garbage1:
                case R.id.garbage2:
                    if(new CustomAdapter().getPosition() == 8){
                        success_dustbin.start();
                        v.setVisibility(View.INVISIBLE); // have to play some animation
                        scoreCard.increaseScore(10, "pol");
                        updateScore();
                    }else{
                        wrong_step.start();
                        Toast.makeText(getApplicationContext(), "This item needs a tool to fix", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.street_light_1:
                    if(new CustomAdapter().getPosition() == 5){
                        success_dustbin.start();
                        v.setBackgroundResource(R.drawable.street_signal1);
                        scoreCard.increaseScore(20, "un");
                        updateScore();
                    }else{
                        wrong_step.start();
                        Toast.makeText(getApplicationContext(), "This item needs a tool to fix", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.street_light_2:
                    if(new CustomAdapter().getPosition() == 5){
                        success_dustbin.start();
                        v.setBackgroundResource(R.drawable.street_signal2);
                        scoreCard.increaseScore(20, "un");
                        updateScore();
                    }else{
                        wrong_step.start();
                        Toast.makeText(getApplicationContext(), "This item needs a tool to fix", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
            return true;
        }
    }

    //Draglistener class
    private final class DragTouchListener implements View.OnTouchListener{

        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN){
                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
                String[] mimeTypes = { ClipDescription.MIMETYPE_TEXT_PLAIN };
                ClipData data = new ClipData(v.getTag().toString(), mimeTypes, item);
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                v.setVisibility(View.INVISIBLE);
                v.startDrag(data, shadowBuilder, v, 0);
                return true;
            }else{
                return false;
            }
        }
    }

    //Droplistener class
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private final class DustbinDragListener implements View.OnDragListener{
        ClipData.Item item;

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            switch (action){
                case DragEvent.ACTION_DRAG_STARTED:
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    success_dustbin.start();
                    animDustbin.setOneShot(true);
                    animDustbin.setVisible(true, true);
                    animDustbin.start();

                    item = event.getClipData().getItemAt(0);
                    if(item.getText().equals("bananaLayer1")){
                        imgBanLayer1.setVisibility(View.INVISIBLE);
                        type = "pol";
                    }else if(item.getText().equals("bananaLayer2")){
                        imgBanLayer2.setVisibility(View.INVISIBLE);
                        type = "pol";
                    }else if(item.getText().equals("shoes")){
                        imgShoes.setVisibility(View.INVISIBLE);
                        type = "pol";
                    }else if(item.getText().equals("bottle")){
                        imgBottle.setVisibility(View.INVISIBLE);
                        type = "pol";
                    }else if(item.getText().equals("bone")){
                        imgBone.setVisibility(View.INVISIBLE);
                        type = "pol";
                    }else if(item.getText().equals("cupandstraw")){
                        imgCupandstraw.setVisibility(View.INVISIBLE);
                        type = "pol";
                    }else if(item.getText().equals("can")){
                        imgCan.setVisibility(View.INVISIBLE);
                        type = "pol";
                    }else if(item.getText().equals("packet")){
                        imgPacket.setVisibility(View.INVISIBLE);
                        type = "pol";
                    }else if(item.getText().equals("paper")){
                        imgPaper.setVisibility(View.INVISIBLE);
                        type = "pol";
                    }
                    scoreCard.increaseScore(10, type);
                    updateScore();
                    return true;
                case DragEvent.ACTION_DRAG_ENDED:
                    break;
                default:
                    return false;
            }
            return true;
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        // set the background music for the homepage
        if(background_music != null){
            background_music.setLooping(true);
            background_music.start();
        }else{
            background_music = MediaPlayer.create(LevelRajshahi.this, R.raw.background_homepage_music);
            background_music.start();
            background_music.setLooping(true);
        }

    }
    @Override
    protected void onResume() {
        super.onResume();
        //play the background music
        if(background_music != null){
            background_music.start();
        }else{
            background_music = MediaPlayer.create(LevelRajshahi.this, R.raw.background_homepage_music);
            background_music.start();
            background_music.setLooping(true);
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        if(background_music != null){
            background_music.stop();
        }
    }
    @Override
    public void onDestroy() {
        if(background_music != null) {
            background_music.release();
            background_music = null;
        }
        super.onDestroy();
    }

}
