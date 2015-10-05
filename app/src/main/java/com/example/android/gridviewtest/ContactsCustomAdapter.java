package com.example.android.gridviewtest;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anjana on 10/1/15.
 */
public class ContactsCustomAdapter extends BaseAdapter implements Filterable {

    Context mContext;
    LayoutInflater inflater;
    private List<ContactModel> mainDataList = null;
    private ArrayList<ContactModel> arraylist;

    ItemFilter itemFilter = new ItemFilter();


    public ContactsCustomAdapter(Context context, List<ContactModel> mainDataList) {
        mContext = context;
        this.mainDataList = mainDataList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.arraylist = new ArrayList<ContactModel>();
        this.arraylist.addAll(mainDataList);
    }

    @Override
    public Filter getFilter() {

        return  itemFilter;
    }


    static class ViewHolder {
        protected TextView name;
        protected TextView number;
        protected TextView email;
        protected ImageView image;
    }


    @Override
    public int getCount() {
        String msg = "Size : " + mainDataList.size();
        Log.d("", msg);
        return mainDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mainDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.list_item_layout, parent, false);


            holder.name = (TextView) convertView.findViewById(R.id.text_1);
            holder.number = (TextView) convertView.findViewById(R.id.text_2);
            holder.email = (TextView) convertView.findViewById(R.id.text_3);

            convertView.setTag(holder);
           // convertView.setTag(R.id.img,holder.image);
            convertView.setTag(R.id.text_1, holder.name);
            convertView.setTag(R.id.text_2, holder.number);
            convertView.setTag(R.id.text_3, holder.number);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }


        holder.name.setText(mainDataList.get(position).getName());
        holder.number.setText(mainDataList.get(position).getPhNumber());
        holder.email.setText(mainDataList.get(position).getEmail());


        return convertView;

    }

    private class ItemFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String filterString = constraint.toString().toLowerCase();
            Log.d("filterString", filterString);
            FilterResults results = new FilterResults();

           final List<ContactModel> list = new ArrayList<ContactModel>(mainDataList);

            int count = list.size();
            final ArrayList<ContactModel> nlist = new ArrayList<ContactModel>(count);

            String filterableString;

            for (int i = 0; i < count; i++) {
                filterableString = "" + list.get(i).getName();
                if (filterableString.toLowerCase().contains(filterString)) {
                    ContactModel mYourCustomData = list.get(i);
                    nlist.add(mYourCustomData);
                }
            }

            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            ArrayList<ContactModel> temp = (ArrayList<ContactModel>) results.values;
            mainDataList.clear();
            mainDataList.addAll(temp);
           notifyDataSetChanged();
            temp.clear();
            notifyDataSetInvalidated();
           // mainDataList = (List<ContactModel>) results.values;
            //notifyDataSetChanged()
            //notifyDataSetChanged(); // notifies the data with new filtered values


        }
    }
    }
