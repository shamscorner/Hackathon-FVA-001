package com.fva_001.flashvsarrow.com.fva_001;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by ShamimH on 03-Apr-16.
 */
public class CustomAdapter extends BaseAdapter {

    String[] result;
    Context c;
    int[] imgId;
    private static LayoutInflater inflater = null;
    private static int posiitonVal;

    public CustomAdapter(){}
    public CustomAdapter(Context c, String[] result, int[] imgId){
        this.result = result;
        this.imgId = imgId;
        this.c = c;
        inflater = (LayoutInflater)c.getSystemService(c.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public int getPosition(){
        return posiitonVal;
    }

    public class Holder{
        TextView txt;
        ImageView img;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        Holder holder = new Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.tools_view, null);
        holder.txt = (TextView)rowView.findViewById(R.id.text_tools);
        holder.img = (ImageView)rowView.findViewById(R.id.img_tools);

        holder.txt.setText(result[position]);
        holder.img.setImageResource(imgId[position]);

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ButtonClick(c, v);
                posiitonVal = position;
            }
        });
        return rowView;
    }
}
