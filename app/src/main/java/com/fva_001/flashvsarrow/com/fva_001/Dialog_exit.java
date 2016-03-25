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
public class Dialog_exit extends Dialog implements android.view.View.OnClickListener {

    public Context c;
    public Button yes, no;

    public Dialog_exit(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a.getApplicationContext();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setContentView(R.layout.dialog_exit);
        yes = (Button) findViewById(R.id.btn_exit);
        no = (Button) findViewById(R.id.btn_cancel);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        new ButtonClick(getContext(), v);
        switch (v.getId()) {
            case R.id.btn_exit:
                //exit the app totally
                Intent intent = new Intent(getContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("EXIT", true);
                c.startActivity(intent);
                break;
            case R.id.btn_cancel:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
}