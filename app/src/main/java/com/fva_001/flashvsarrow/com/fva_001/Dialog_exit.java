package com.fva_001.flashvsarrow.com.fva_001;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.*;
import android.view.View;
import android.view.Window;
import android.widget.Button;

/**
 * Created by ShamimH on 20-Mar-16.
 */
public class Dialog_exit extends Dialog implements android.view.View.OnClickListener {

    public Activity c;
    public Button yes, no;

    public Dialog_exit(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
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