package com.fva_001.flashvsarrow.com.fva_001;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.*;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

/**
 * Created by ShamimH on 20-Mar-16.
 */
public class CustomDialogFragment extends Dialog {

    public Context c;

    public CustomDialogFragment(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a.getApplicationContext();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_language);

        ImageButton btn_close = (ImageButton)findViewById(R.id.dialog_language_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ButtonClick(getContext(), v);
                dismiss();
            }
        });
    }
}