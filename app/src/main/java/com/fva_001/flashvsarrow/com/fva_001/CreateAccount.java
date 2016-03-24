package com.fva_001.flashvsarrow.com.fva_001;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by ShamimH on 25-Mar-16.
 */
public class CreateAccount extends AppCompatActivity {

    private Button btnPlay, btnProgress;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);

        btnPlay = (Button)findViewById(R.id.btn_play);
        btnPlay.setVisibility(View.INVISIBLE);
        btnProgress = (Button)findViewById(R.id.btn_see_progress);
        btnProgress.setVisibility(View.INVISIBLE);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            type = extras.getInt("type");
        }

        if(type == 1){
            btnPlay.setVisibility(View.VISIBLE);
        }else if(type == 2){
            btnProgress.setVisibility(View.VISIBLE);
        }

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ButtonClick(getApplicationContext(), v);
                Intent intent = new Intent(getApplicationContext(), SeekStoreroom.class);
                startActivity(intent);
            }
        });

        btnProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ButtonClick(getApplicationContext(), v);
            }
        });
    }

    //the function for the back button
    private void goBack(){
        Intent intent = new Intent(getApplicationContext(), HomepageActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.anim_slide_in_right);
    }

    //handle the back button
    @Override
    public void onBackPressed() {
        goBack();
    }

    public void backtohomepage(View v){
        new ButtonClick(getApplicationContext(), v);
        goBack();
    }
}
