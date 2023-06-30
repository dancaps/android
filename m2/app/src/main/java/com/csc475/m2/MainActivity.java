package com.csc475.m2;

import android.os.Bundle;
import android.widget.GridView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView imageGV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageGV = findViewById(R.id.idGVImages);
        ArrayList<ImageModel> imageModelArrayList = new ArrayList<ImageModel>();

        imageModelArrayList.add(new ImageModel("Eiffel Tower", R.drawable.et));
        imageModelArrayList.add(new ImageModel("Colosseum", R.drawable.colosseum));
        imageModelArrayList.add(new ImageModel("Louvre", R.drawable.louvre));
        imageModelArrayList.add(new ImageModel("Statue of Liberty", R.drawable.sol));

        ImageAdapter adapter = new ImageAdapter(this, imageModelArrayList);
        imageGV.setAdapter(adapter);
    }
}