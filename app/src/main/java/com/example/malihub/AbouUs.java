package com.example.rayson.malihub;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class AbouUs extends Fragment {


    public AbouUs() {
        // Required empty public constructor
    }


    public static AbouUs newInstance() {
        return new AbouUs();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_abou_us, container, false);
    }

}
