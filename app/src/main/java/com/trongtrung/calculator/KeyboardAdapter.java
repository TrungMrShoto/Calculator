package com.trongtrung.calculator;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import static com.trongtrung.calculator.R.color.c3;

/**
 * Created by NguyenTrongTrung on 19 January 2020
 */
public class KeyboardAdapter extends BaseAdapter {
    private Context context;
    private List<String> listOfKeyBoardCharacter;
    private int layout;

    public KeyboardAdapter(Context context, List<String> listOfKeyBoardCharacter, int layout) {
        this.context = context;
        this.listOfKeyBoardCharacter = listOfKeyBoardCharacter;
        this.layout = layout;
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

        String character = listOfKeyBoardCharacter.get(position);
        if (character.equals(GeneralCharacter.DEL) || character.equals(GeneralCharacter.CE)) {
            holder.txtKey.setBackgroundColor(context.getResources().getColor(R.color.c3,null));
        }
        holder.txtKey.setText(character);
        return convertView;
    }
}
