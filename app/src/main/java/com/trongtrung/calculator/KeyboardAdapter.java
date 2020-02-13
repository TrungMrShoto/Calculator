package com.trongtrung.calculator;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;


/**
 * Created by NguyenTrongTrung on 19 January 2020
 */
public class KeyboardAdapter extends BaseAdapter {
    private final int paddingVertical;
    private Context context;
    private List<Element> listOfKeyBoardCharacter;
    private int layout;
    private float textSize;

    public KeyboardAdapter(Context context, List<Element> listOfKeyBoardCharacter, int layout, float textSize,int paddingVertical) {
        this.context = context;
        this.listOfKeyBoardCharacter = listOfKeyBoardCharacter;
        this.layout = layout;
        this.textSize = textSize;
        this.paddingVertical=paddingVertical;
    }

    @Override
    public int getCount() {
        return listOfKeyBoardCharacter.size();
    }

    @Override
    public Object getItem(int position) {
        return listOfKeyBoardCharacter.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        TextView txtKey;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null)
        {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            holder.txtKey = convertView.findViewById(R.id.character);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        Element character = listOfKeyBoardCharacter.get(position);

        holder.txtKey.setBackgroundColor(context.getResources().getColor(character.getColor(),null));
        holder.txtKey.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        holder.txtKey.setText(character.getName());
        holder.txtKey.setPadding(10,paddingVertical,10,paddingVertical);
        return convertView;
    }
}
