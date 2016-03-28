package com.fva_001.flashvsarrow.com.fva_001;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ShamimH on 22-Mar-16.
 */
public class DialogArea extends Dialog {

    public Context c;
    private String areaName;
    private ImageView areaPart;
    private ProgressBar progressbarPollution, progressbarUnorganized, progressbarRisk;
    int pollution = 0, unorganized = 0, risk = 0;
    TextView percentPol, percentUnor, percentRisk;

    public DialogArea(Activity a, String s, int pollution,int unorganized,int risk) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a.getApplicationContext();
        this.areaName = s;
        this.pollution = pollution;
        this.unorganized = unorganized;
        this.risk = risk;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setContentView(R.layout.dialog_area_part);

        // progressbars
        progressbarPollution = (ProgressBar)findViewById(R.id.progressbar_pollution);
        progressbarUnorganized = (ProgressBar)findViewById(R.id.progressbar_unorganized);
        progressbarRisk = (ProgressBar)findViewById(R.id.progressbar_risk);

        progressbarPollution.setProgress(pollution);
        progressbarUnorganized.setProgress(unorganized);
        progressbarRisk.setProgress(risk);

        //textviews
        percentPol = (TextView)findViewById(R.id.percent_pollution);
        percentUnor = (TextView)findViewById(R.id.percent_unorganized);
        percentRisk = (TextView)findViewById(R.id.percent_risk);

        percentPol.setText(pollution+"%");
        percentUnor.setText(unorganized+"%");
        percentRisk.setText(risk+"%");

        // the area part of the map
        areaPart = (ImageView)findViewById(R.id.map_area_part_frame);

        //the drawable part of the different region
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("rangpur", R.drawable.area_rangpur);
        map.put("rajshahi", R.drawable.area_rajshahi);
        map.put("dhaka", R.drawable.area_dhaka);
        map.put("khulna", R.drawable.area_khulna);
        map.put("barishal", R.drawable.area_barishal);
        map.put("sylhet", R.drawable.area_sylhet);
        map.put("chittagong", R.drawable.area_chittagong);

        switch (areaName){
            case "rangpur":
                areaPart.setImageResource(map.get("rangpur"));
                break;
            case "rajshahi":
                areaPart.setImageResource(map.get("rajshahi"));
                break;
            case "dhaka":
                areaPart.setImageResource(map.get("dhaka"));
                break;
            case "khulna":
                areaPart.setImageResource(map.get("khulna"));
                break;
            case "barishal":
                areaPart.setImageResource(map.get("barishal"));
                break;
            case "chittagong":
                areaPart.setImageResource(map.get("chittagong"));
                break;
            case "sylhet":
                areaPart.setImageResource(map.get("sylhet"));
                break;
        }

        //the cancel button
        Button btn_cancel = (Button)findViewById(R.id.btn_cancel_area_part_dialog);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ButtonClick(getContext(), v);
                dismiss();
            }
        });

        //the start level button
        Button btnStartLevel = (Button)findViewById(R.id.btn_start_level);
        btnStartLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ButtonClick(getContext(), v);
                switch (areaName){
                    case "rangpur":
                        break;
                    case "rajshahi":
                        Intent intent = new Intent(getContext(), LevelRajshahi.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        c.startActivity(intent);
                        dismiss();
                        break;
                    case "dhaka":
                        break;
                    case "khulna":
                        break;
                    case "barishal":
                        break;
                    case "chittagong":
                        break;
                    case "sylhet":
                        break;
                }
            }
        });

    }
}
