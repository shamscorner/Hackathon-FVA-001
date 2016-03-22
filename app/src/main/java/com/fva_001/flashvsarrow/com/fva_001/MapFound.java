package com.fva_001.flashvsarrow.com.fva_001;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by ShamimH on 21-Mar-16.
 */
public class MapFound extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_map);
    }

    // this is called when the gamer will find the map
    public void openMap(View view){
        new ButtonClick(getApplicationContext(), view);
        MapFoundDialog dialog = new MapFoundDialog(MapFound.this);
        dialog.show();
    }

    // this function is called when gamer want to go back to the homepage
    public void goHomapageScreen(View view){
        goBack();
        new ButtonClick(getApplicationContext(), view);
    }

    // handle the go back function
    private void goBack(){
        GoHomepage dialog = new GoHomepage(MapFound.this);
        dialog.show();
    }

    //handle the back button
    @Override
    public void onBackPressed() {
        goBack();
    }

}
