package com.fva_001.flashvsarrow.com.fva_001;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

/**
 * Created by ShamimH on 23-Mar-16.
 */
public class LevelRajshahi  extends AppCompatActivity {

    Button btnMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //set the background in full screen mode
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_rajshahi);

        btnMap = (Button)findViewById(R.id.btn_map_in_level);

        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogGoBackToMap dialogGoBackToMap = new DialogGoBackToMap(LevelRajshahi.this);
                dialogGoBackToMap.show();
            }
        });
    }

    public void goCompleted(View view){
        Intent intent = new Intent(getApplicationContext(), LevelCompleted.class);
        startActivity(intent);
    }

    //handle the back button
    @Override
    public void onBackPressed() {
        DialogGoBackToMap dialog = new DialogGoBackToMap(LevelRajshahi.this);
        dialog.show();
    }
}
