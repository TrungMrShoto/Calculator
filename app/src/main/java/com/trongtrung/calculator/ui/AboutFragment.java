package com.trongtrung.calculator.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.trongtrung.calculator.R;

/**
 * Created by NguyenTrongTrung on 17 January 2020
 */
public class AboutFragment extends Fragment {

    ImageView author_photo;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_about, container, false);

        author_photo = root.findViewById(R.id.author_photo);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.author_picture);
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(),bitmap);
        roundedBitmapDrawable.setCircular(true);
        author_photo.setImageDrawable(roundedBitmapDrawable);

        return root;
    }
}

