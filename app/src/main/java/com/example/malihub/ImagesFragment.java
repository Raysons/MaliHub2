package com.example.rayson.malihub;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ImagesFragment extends Fragment {

    private static final String ARG_PROPDATA = "propdata";
    private Serializable_PropData propData;
    private ImageView houseimage;

    public ImagesFragment() {
        // Required empty public constructor
    }

    public static ImagesFragment newInstance(Serializable_PropData propData) {
        ImagesFragment fragment = new ImagesFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PROPDATA, propData);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if (getArguments() != null){
            propData = (Serializable_PropData) getArguments().getSerializable(ARG_PROPDATA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView;
        rootView = inflater.inflate(R.layout.fragment_images, container, false);

        houseimage = (ImageView) rootView.findViewById(R.id.houseimage);
        if(propData.getImageURLs()!=null)
        {
            if(setBackdropImage(propData.getImageURLs())!=null)
                houseimage.setImageBitmap(setBackdropImage(propData.getImageURLs()));
            else
                houseimage.setImageResource(R.drawable.xcardview_image);
        }
        else
        houseimage.setImageResource(R.drawable.xcardview_image);

        return rootView;
    }

    private Bitmap setBackdropImage(String img){
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
