package com.example.android.gridviewtest;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by anjana on 10/1/15.
 */
public class ButtonAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;

    static final String[] numbers = new String[] {
            "A", "B", "C", "D", "E",
            "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O",
            "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"};

    public ButtonAdapter(Context context){
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public class ViewHolder
    {
        public Button button;
    }

    final ArrayList<String> list = new ArrayList<>(Arrays.asList(numbers));
    @Override
    public int getCount() {
        return 26;
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
            ViewHolder holdr;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.button, parent, false);
            holdr = new ViewHolder();
            holdr.button = (Button) convertView.findViewById(R.id.button_alpha);
            convertView.setTag(holdr);

        }
        else
        {
            holdr = (ViewHolder) convertView.getTag();
        }
        holdr.button.setText(list.get(position));

        holdr.button.clearFocus();
        Log.i("position", "" + position);

        return convertView;

    }
}
