package com.fva_001.flashvsarrow.com.fva_001;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by ShamimH on 23-Mar-16.
 */
public class LevelRajshahi  extends AppCompatActivity {

    Button btnMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

    }
}
