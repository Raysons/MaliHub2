package com.example.rayson.malihub;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by rayson 20/06/2019
 */
public class Main_Fragment  extends android.support.v4.app.Fragment{
    public Main_Fragment()
    {

    }
    private static final String SECTION = "SECTION";

    public static Main_Fragment FragmentFactory(int SectionID)
    {
        Main_Fragment NewFragment = new Main_Fragment();
        Bundle NewBundle = new Bundle();
        NewBundle.putInt(SECTION,SectionID);
        NewFragment.setArguments(NewBundle);
        return NewFragment;
    }

    private View RootView = null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        RootView = null;
        int Choose = getArguments().getInt(SECTION);
        switch (Choose)
        {
            case 0:
                RootView = inflater.inflate(R.layout.main_coverpage, container, false); break;
            default:break;
        }
        return RootView;
    }
}
