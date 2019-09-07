package com.example.rayson.malihub;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {

    private static final String ARG_PROPDATA = "propdata";
    private OnClickIListener IReference;
    private Serializable_PropData propData;
    private TextView addr;
    private TextView city;
    private TextView state;
    private TextView proptype;
    private TextView area;
    private TextView bedrooms;
    private TextView desc;
    private TextView amenities;
    private TextView furn;
    private TextView avail;
    private TextView price;
    private TextView listingtype;
    ImageView imageView;

    public DetailsFragment() {
        // Required empty public constructor
    }
    public static DetailsFragment newInstance(Serializable_PropData propData) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PROPDATA, propData);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null){
            propData = (Serializable_PropData) getArguments().getSerializable(ARG_PROPDATA);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView;
        try
        {
            IReference = (OnClickIListener)getContext();
        }
        catch (ClassCastException E)
        {
            throw new ClassCastException("OOPS!!");
        }
        rootView = inflater.inflate(R.layout.fragment_details, container, false);
        final String _addr = propData.getProp_address();
        final String _city = propData.getProp_city();
        String _state = propData.getProp_state();
        String _proptype = propData.getProp_type();
        String _area = propData.getProp_area();
        String _bedrooms = propData.getProp_bed();
        String _desc = propData.getProp_description();
        String _amenities = propData.getProp_amenities();
        String _furn = propData.getProp_furnished();
        String _avail = propData.getProp_AvailDate();
        String _price = propData.getProp_price();
        String _listingtype = propData.getList_type();
        final String _ownerName = propData.getProp_owner();
        listingtype = (TextView) rootView.findViewById(R.id.det_buy_rent);
        listingtype.setText(_listingtype);
        addr = (TextView) rootView.findViewById(R.id.det_addr);
        addr.setText(_addr);
        city = (TextView) rootView.findViewById(R.id.det_city);
        city.setText(_city);
        state = (TextView) rootView.findViewById(R.id.det_state);
        state.setText(_state);
        proptype = (TextView) rootView.findViewById(R.id.det_propertytype);
        proptype.setText(_proptype);
        area = (TextView) rootView.findViewById(R.id.det_area);
        area.setText(_area);
        bedrooms = (TextView) rootView.findViewById(R.id.det_bedrooms);
        bedrooms.setText(_bedrooms);
        desc = (TextView) rootView.findViewById(R.id.det_desc);
        desc.setText(_desc);
        amenities = (TextView) rootView.findViewById(R.id.det__amenities);
        amenities.setText(_amenities);
        furn = (TextView) rootView.findViewById(R.id.det_furn);
        furn.setText(_furn);
        avail = (TextView) rootView.findViewById(R.id.det_avail);
        avail.setText(_avail);
        price = (TextView) rootView.findViewById(R.id.det_price);
        price.setText(_price);
        FloatingActionButton fab=(FloatingActionButton)getActivity().findViewById(R.id.share);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                IReference.GetPropDetails(propData);
            }
        });
        return rootView;
    }

    protected Bitmap Base64toImage(String img){
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
