package com.example.rayson.malihub;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.Spinner;

import java.util.List;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

/**
 * Created by rayson 20/06/2019
 */
public class RecyclerView_Fragment extends android.support.v4.app.Fragment implements View.OnClickListener
{
    public RecyclerView_Fragment(){}
    private static final String SECTION = "SECTION";
    private static int ID;
    private static List<Serializable_PropData> ResultProps;
    public static RecyclerView_Fragment FragmentFactory(int SectionID, List<Serializable_PropData> results)
    {
        ID = SectionID;
        RecyclerView_Fragment NewFragment = new RecyclerView_Fragment();
        Bundle NewBundle = new Bundle();
        NewBundle.putInt(SECTION,SectionID);
        NewFragment.setArguments(NewBundle);
        ResultProps = results;
        return NewFragment;
    }
    private RecyclerView MyRCView;
    private RecyclerViewAdapter MyRCAdapter;
    private OnClickIListener IReference;
    private View RootView = null;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        RootView = inflater.inflate(R.layout.recyclerview_fragment, container, false);
        MyRCView = (RecyclerView) RootView.findViewById(R.id.MyRecyclerView);
        try
        {
            IReference = (OnClickIListener)getContext();
        }
        catch (ClassCastException E)
        {
            throw new ClassCastException("OOPS!!");
        }

        RecyclerView.LayoutManager myRCLManager = new LinearLayoutManager(getActivity());
        MyRCView.setLayoutManager(myRCLManager);
        MyRCAdapter = new RecyclerViewAdapter(getActivity(), ResultProps);
        MyRCView.setAdapter(MyRCAdapter);
        MyRCAdapter.SetEventHandler(new EventHandler() {
            @Override
            public void GetDetails(Serializable_PropData SP) {
                IReference.GetPropDetails(SP);
            }
        });
        ItemAnimation();
        AdapterAnimation();
        return RootView;
    }


    private int getIndex(Spinner spinner, String myString)
    {
        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                index = i;
                break;
            }
        }
        return index;
    }

    private void ItemAnimation()
    {
        SlideInLeftAnimator ItemAnimator = new SlideInLeftAnimator();
        ItemAnimator.setInterpolator( new OvershootInterpolator());
        ItemAnimator.setAddDuration(2000);
        ItemAnimator.setRemoveDuration(2000);
        MyRCView.setItemAnimator(ItemAnimator);
    }

    private void AdapterAnimation()
    {
        AlphaInAnimationAdapter ADP = new AlphaInAnimationAdapter(MyRCAdapter);
        ScaleInAnimationAdapter AnimatedAdaptor = new ScaleInAnimationAdapter(ADP);
        MyRCView.setAdapter(AnimatedAdaptor);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        inflater.inflate(R.menu.contextual_menu_toolbar, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onClick(View v) {

    }
}