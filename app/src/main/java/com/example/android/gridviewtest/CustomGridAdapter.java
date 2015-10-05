package com.example.android.gridviewtest;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

/**
 * Created by anjana on 9/29/15.
 */
public class CustomGridAdapter extends BaseAdapter {

    private Context context;
    private String[] items;
    LayoutInflater inflater;

    public CustomGridAdapter(Context context, String[] items) {
        this.context = context;
        this.items = items;
        inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }



    @Override
    public int getCount() {

        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.button_layout, null);
        }
        final Button button = (Button) convertView.findViewById(R.id.bu_text);
        button.setText(items[position]);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            }
        });

        return convertView;
    }
}
