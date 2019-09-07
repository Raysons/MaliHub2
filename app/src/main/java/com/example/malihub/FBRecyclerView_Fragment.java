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

import com.firebase.client.Firebase;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

/**
 * Created by rayson 20/06/2019
 */
public class FBRecyclerView_Fragment extends android.support.v4.app.Fragment implements View.OnClickListener
{
    //Toolbar Top;
    public FBRecyclerView_Fragment(){}
    private static final String SECTION = "SECTION";
    private static SearchParams FSearchObject = null;
    private static int ID;
    public static FBRecyclerView_Fragment FragmentFactory(int SectionID, SearchParams searchParams)
    {
        ID = SectionID;
        FBRecyclerView_Fragment NewFragment = new FBRecyclerView_Fragment();
        Bundle NewBundle = new Bundle();
        NewBundle.putInt(SECTION,SectionID);
        NewFragment.setArguments(NewBundle);
        FSearchObject = searchParams;
        return NewFragment;
    }
    private RecyclerView MyRCView;
    private FBRecyclerViewAdapter MyFirebaseRVAdapter;
    private OnClickIListener IReference;
    private View RootView = null;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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

        final Firebase QueryRef = new Firebase("https://malihub.firebaseio.com/Listings");
        MyFirebaseRVAdapter = new FBRecyclerViewAdapter(Serializable_PropData.class,
                FBRecyclerViewAdapter.MyViewHolder.class, QueryRef, getActivity(), FSearchObject);
        MyRCView.setAdapter(MyFirebaseRVAdapter);
        ItemAnimation();
        AdapterAnimation();
        return RootView;
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
        AlphaInAnimationAdapter ADP = new AlphaInAnimationAdapter(MyFirebaseRVAdapter);
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
