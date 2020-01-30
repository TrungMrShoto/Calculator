package com.trongtrung.calculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by NguyenTrongTrung on 30 January 2020
 */
public class ConverterSpinnerAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<ConverterItemSpinner> list;

    public ConverterSpinnerAdapter(Context context, int layout, List<ConverterItemSpinner> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        TextView itemName;
        ImageView image;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null)
        {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            holder.itemName = convertView.findViewById(R.id.name);
            holder.image = convertView.findViewById(R.id.image);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        ConverterItemSpinner item = list.get(position);
        holder.image.setImageResource(item.getImage());
        holder.itemName.setText(item.getItemName());
        return convertView;
    }
}
