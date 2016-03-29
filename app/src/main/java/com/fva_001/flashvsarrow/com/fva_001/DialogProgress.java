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
import android.widget.ImageButton;

/**
 * Created by ShamsCorner on 29-Mar-16.
 */
public class DialogProgress extends Dialog {
    public Context c;

    public DialogProgress(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a.getApplicationContext();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_progress);

        // event for the close button
        ImageButton btn_close = (ImageButton)findViewById(R.id.dialog_btn_progress_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ButtonClick(getContext(), v);
                dismiss();
            }
        });

        // the progress create button
        Button btnCreate = (Button)findViewById(R.id.btn_create);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ButtonClick(getContext(), v);
                Intent intent = new Intent(getContext(), CreateAccount.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("type", 1);
                intent.putExtra("exit", 0);
                c.startActivity(intent);
                dismiss();
            }
        });

        // the progress visit button
        Button btnVisit = (Button)findViewById(R.id.btn_visit);
        btnVisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ButtonClick(getContext(), v);
                Intent intent = new Intent(getContext(), CreateAccount.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("type", 2);
                intent.putExtra("exit", 0);
                c.startActivity(intent);
                dismiss();
            }
        });
    }
}
