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
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by ShamimH on 04-Apr-16.
 */
public class DialogLevelEnd extends Dialog {

    public Context c;
    int highScore;

    public DialogLevelEnd(Activity a, int highScore) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a.getApplicationContext();
        this.highScore = highScore;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_map_found);

        setCancelable(false);
        setCanceledOnTouchOutside(false);

        TextView txtHeader = (TextView)findViewById(R.id.textView);
        txtHeader.setText("Times Up");

        ImageView timeview = (ImageView)findViewById(R.id.imageView2);
        timeview.setBackgroundResource(R.drawable.time_up);


        Button btn_close = (Button)findViewById(R.id.btn_hide_dialog_map_found);
        btn_close.setVisibility(View.INVISIBLE);

        Button btnGo = (Button)findViewById(R.id.btn_map_open);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ButtonClick(getContext(), v);
                Intent intent = new Intent(getContext(), LevelCompleted.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("HIGH_SCORE", highScore);
                c.startActivity(intent);
                dismiss();
            }
        });
    }
}
