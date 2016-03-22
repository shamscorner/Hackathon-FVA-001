package com.fva_001.flashvsarrow.com.fva_001;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.*;
import android.view.Window;

/**
 * Created by ShamimH on 20-Mar-16.
 */
public class CustomDialogFragment extends Dialog {

    public Activity c;
    public Dialog d;

    public CustomDialogFragment(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setContentView(R.layout.dialog_language);
    }
}