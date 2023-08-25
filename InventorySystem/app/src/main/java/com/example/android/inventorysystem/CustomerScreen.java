package com.example.android.inventorysystem;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class CustomerScreen extends Fragment implements BackpressedListener {

    public static BackpressedListener backpressedlistener;
    public CustomerScreen() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.customer_screen, container, false);
    }

    @Override
    public void onPause() {
        backpressedlistener=null;
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        backpressedlistener=this;
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getContext(),"Back Button Pressed",Toast.LENGTH_LONG).show();
    }
}