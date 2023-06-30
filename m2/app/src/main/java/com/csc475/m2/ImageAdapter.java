package com.csc475.m2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ImageAdapter extends ArrayAdapter<ImageModel> {

    public ImageAdapter(@NonNull Context context, ArrayList<ImageModel> imageModelArrayList) {
        super(context, 0, imageModelArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listitemView = convertView;
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.card_item, parent, false);
        }

        ImageModel imageModel = getItem(position);
        TextView imageTV = listitemView.findViewById(R.id.idTVImage);
        ImageView imageIV = listitemView.findViewById(R.id.idIVImage);

        imageTV.setText(imageModel.getImage_name());
        imageIV.setImageResource(imageModel.getImgId());
        return listitemView;
    }
}
