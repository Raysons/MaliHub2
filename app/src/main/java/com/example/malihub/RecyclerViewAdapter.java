package com.example.rayson.malihub;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by rayson 20/06/2019
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> implements EventHandler{
    private OnClickIListener IListener;
    private EventHandler EHandler;
    private final Context MyContext;
    private static List<Serializable_PropData> ResultProps;

    public RecyclerViewAdapter(Context context, List<Serializable_PropData> resultprops) {
        this.MyContext = context;
        ResultProps = resultprops;
    }
    public void SetEventHandler(EventHandler MyEventHandler) {
        this.EHandler = MyEventHandler;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View MyView;
        MyView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new MyViewHolder(MyView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Serializable_PropData serializablepropdata = ResultProps.get(position);
        holder.PropertyBeds.setText(serializablepropdata.getProp_bed().concat(" BHK"));
        holder.PropertyName.setText(serializablepropdata.getProp_name());
        String Price = "Price $";
        holder.PropertyPrice.setText(Price.concat(serializablepropdata.getProp_price()));
        holder.PropertyType.setText(serializablepropdata.getProp_type());
        Bitmap image = Base64toImage(serializablepropdata.getImageURLs());
        //if(image != null)
          //  holder.PropertyImage.setImageBitmap(image);
    }

    @Override
    public int getItemCount() {
        return ResultProps.size();
    }

    @Override
    public void GetDetails(Serializable_PropData SP) {

        IListener = (OnClickIListener)this.MyContext;
        IListener.GetPropDetails(SP);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public final TextView PropertyPrice;
        public final ImageView PropertyImage;
        public final TextView PropertyName;
        public final TextView PropertyType;
        public final TextView PropertyBeds;

        public MyViewHolder(final View V) {
            super(V);
            PropertyType = (TextView) V.findViewById(R.id.card_type);
            PropertyBeds = (TextView) V.findViewById(R.id.card_bed);
            PropertyImage = (ImageView) V.findViewById(R.id.card_image);
            PropertyName = (TextView) V.findViewById(R.id.card_name);
            PropertyPrice = (TextView) V.findViewById(R.id.card_price);
            V.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Serializable_PropData Current = ResultProps.get(getAdapterPosition());
                    EHandler.GetDetails(Current);
                }
            });
        }
    }

    private Bitmap Base64toImage(String img){
        Bitmap bitmap = null;
        try{
            byte [] encodeByte= Base64.decode(img.getBytes(), Base64.DEFAULT);
            bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            Log.d("img", "su");
        }
        catch(Exception e){
            e.getMessage();
            Log.d("e: ", e.getMessage());
        }
        return bitmap;
    }
}