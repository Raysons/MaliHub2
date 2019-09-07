package com.example.rayson.malihub;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.Query;
import com.firebase.ui.FirebaseRecyclerAdapter;

/**
 * Created by rayson 20/06/2019
 */
public class FBRecyclerViewAdapter extends FirebaseRecyclerAdapter<Serializable_PropData, FBRecyclerViewAdapter.MyViewHolder> {

    private static EventHandler EHandler;
    private final SearchParams ASearchObject;
    private static View CurrentCardView;
    private static ViewGroup Parent;

    public FBRecyclerViewAdapter(Class<Serializable_PropData> modelClass,
                                 Class<MyViewHolder> viewHolderClass,
                                 Query ref, Context mcontext, SearchParams searchParams)
    {
        super(modelClass, R.layout.cardview, viewHolderClass, ref);
        this.ASearchObject = searchParams;
        Log.d("Malihub", "adapter const");
    }

    public void SetEventHandler(EventHandler MyEventHandler)
    {
        EHandler = MyEventHandler;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Parent = parent;
        CurrentCardView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new MyViewHolder(CurrentCardView);
    }
    @Override
    protected void populateViewHolder(MyViewHolder myViewHolder, Serializable_PropData serializablepropdata, int i) {
        if (Integer.parseInt(serializablepropdata.getProp_price()) <= ASearchObject.getMaxBudget())
        {
            myViewHolder.PropertyBeds.setText(serializablepropdata.getProp_bed().concat(" BHK"));
            myViewHolder.PropertyName.setText(serializablepropdata.getProp_name());
            String Price = "Price $";
            myViewHolder.PropertyPrice.setText(Price.concat(serializablepropdata.getProp_price()));
            myViewHolder.PropertyType.setText(serializablepropdata.getProp_type());
            //Picasso.with(context).load(serializablepropdata.getImageURLs()).into(myViewHolder.PropertyImage);
        }
        else
        {
            ViewGroup A = (ViewGroup) CurrentCardView.getParent();
            ViewManager Vm = (ViewManager) CurrentCardView.getParent();
            //ViewGroup P = Parent;
            Parent.removeView(CurrentCardView);
            CurrentCardView.setVisibility(View.GONE);
        }
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public final TextView PropertyPrice;
        public ImageView PropertyImage;
        public final TextView PropertyName;
        public final TextView PropertyType;
        public final TextView PropertyBeds;

        public MyViewHolder(final View V) {
            super(V);

            PropertyType = (TextView) V.findViewById(R.id.card_type);
            PropertyBeds = (TextView) V.findViewById(R.id.card_bed);
            //PropertyImage = (ImageView) V.findViewById(R.id.card_image);
            PropertyName = (TextView) V.findViewById(R.id.card_name);
            PropertyPrice = (TextView) V.findViewById(R.id.card_price);
        }
    }
}
