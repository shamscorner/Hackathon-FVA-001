package com.fva_001.flashvsarrow.com.fva_001;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;

/**
 * Created by ShamimH on 21-Mar-16.
 */
public class GoHomepage extends Dialog implements View.OnClickListener {

    public Context c;
    public Dialog d;
    Button btnStay, btnGo;

    public GoHomepage(AppCompatActivity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a.getApplicationContext();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setContentView(R.layout.dialog_exit_homepage);

        btnGo = (Button)findViewById(R.id.btn_go);
        btnStay = (Button)findViewById(R.id.btn_stay);

        btnGo.setOnClickListener(this);
        btnStay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        new ButtonClick(getContext(), v);
        switch (v.getId()) {
            case R.id.btn_go:
                // go back to the homepage
                Intent intent = new Intent(getContext(), HomepageActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                c.startActivity(intent);
                break;
            case R.id.btn_stay:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
}
