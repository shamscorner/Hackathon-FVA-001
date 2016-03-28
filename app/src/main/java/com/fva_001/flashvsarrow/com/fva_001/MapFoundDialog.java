package com.fva_001.flashvsarrow.com.fva_001;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.*;
import android.view.View;
import android.view.Window;
import android.widget.Button;

/**
 * Created by ShamimH on 20-Mar-16.
 */
public class MapFoundDialog extends Dialog {

    public Context c;
    public Button yes, no;

    public MapFoundDialog(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a.getApplicationContext();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setContentView(R.layout.dialog_map_found);
        yes = (Button) findViewById(R.id.btn_hide_dialog_map_found);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ButtonClick(getContext(), v);
                dismiss();
            }
        });
        no = (Button) findViewById(R.id.btn_map_open);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ButtonClick(getContext(), v);
                Intent entireMapIntent = new Intent(getContext(), EntireMap.class);
                entireMapIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                entireMapIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                c.startActivity(entireMapIntent);
                dismiss();
            }
        });
    }
}