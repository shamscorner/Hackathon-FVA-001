package com.fva_001.flashvsarrow.com.fva_001;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;

/**
 * Created by ShamimH on 03-Apr-16.
 */
public class ToolsAccessories extends Dialog {

    private Context c;
    private GridView gridView;
    private static String[] listNames = {"", "Repairing Kit", "Mop", "Crane", "Manhole Lid", "Street Signal", "Street Light", "Smoke Filter", "Call Van"};
    private static int[] listImages = {R.drawable.background_process_front, R.drawable.repairing_kit, R.drawable.mop, R.drawable.crane, R.drawable.manhole_lid,
            R.drawable.street_signal1, R.drawable.light1, R.drawable.smoke_filter, R.drawable.garbage_van};

    public ToolsAccessories(Activity a) {
        super(a);
        this.c = a.getApplicationContext();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_tools);
        gridView = (GridView)findViewById(R.id.tools_view);
        gridView.setAdapter(new CustomAdapter(c, listNames, listImages));

        //the dismiss dialog part here...
        Button btn_close = (Button)findViewById(R.id.btn_close_dialog_tools);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ButtonClick(getContext(), v);
                dismiss();
            }
        });
    }
}
