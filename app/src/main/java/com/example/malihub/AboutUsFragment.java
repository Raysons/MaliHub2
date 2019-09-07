package com.example.rayson.malihub;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class AboutUsFragment extends Fragment {

   // private AboutUsView aboutusview;

    public AboutUsFragment() {
        // Required empty public constructor
    }

    public static AboutUsFragment newInstance() {
        return new AboutUsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        // aboutusview = (AboutUsView) rootView.findViewById(R.id.thermometer);

        return inflater.inflate(R.layout.fragment_about_us, container, false);
    }

}
