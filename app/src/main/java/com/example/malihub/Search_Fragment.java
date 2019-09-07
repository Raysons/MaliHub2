package com.example.rayson.malihub;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;
import java.util.Objects;

/**
 * Created by rayson 20/06/2019
 */
public class Search_Fragment extends android.support.v4.app.Fragment
{
    private OnClickIListener IReference;
    private SearchParams SearchParameters;
    private static final Firebase QueryRef = new Firebase("https://malihub.firebaseio.com/Listings");
    public Search_Fragment()
    {

    }
    private static final String SECTION = "SECTION";

    public static Search_Fragment FragmentFactory(int SectionID)
    {
        Search_Fragment NewFragment = new Search_Fragment();
        Bundle NewBundle = new Bundle();
        NewBundle.putInt(SECTION,SectionID);
        NewFragment.setArguments(NewBundle);
        return NewFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
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
            case 0: {
                RootView = inflater.inflate(R.layout.main_searchpage, container, false);
                try
                {
                    IReference = (OnClickIListener)getContext();
                }
                catch (ClassCastException E)
                {
                    throw new ClassCastException("OOPS!!");
                }
                Bitmap bitmapImage = BitmapFactory.decodeResource(getResources(), R.drawable.search_background);
                int nh = (int) (bitmapImage.getHeight() * (1080.0 / bitmapImage.getWidth()));
                Bitmap scaled = Bitmap.createScaledBitmap(bitmapImage, 1080, nh, true);
                ImageView HomeImage = (ImageView) RootView.findViewById(R.id.search_image);
                HomeImage.setImageBitmap(scaled);
                HomeImage.setAlpha(0.6f);
                Button SearchButton = (Button)RootView.findViewById(R.id.search_button);
                SearchButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        String PropertyType = "Apartment";
                        String Furnished = "Any";
                        int Bedrooms = 1;
                        int MinArea = 0;
                        int MaxArea = 0;
                        int MinBudget = 0;
                        int MaxBudget = 0;
                        String ListType = "RENT";
                        String Locality = "";
                        String City = ""; String State = "";
                        Spinner PropertyTypeSpinner = (Spinner)RootView.findViewById(R.id.prop_type_spinner);
                        Spinner BedroomSpinner = (Spinner)RootView.findViewById(R.id.bedrooms_spinner);
                        EditText MinAreaText = (EditText)RootView.findViewById(R.id.text_min_area);
                        EditText MaxAreaText = (EditText)RootView.findViewById(R.id.text_max_area);
                        RadioGroup BuyRent = (RadioGroup)RootView.findViewById(R.id.radio_buy_rent);
                        EditText MinBudText = (EditText)RootView.findViewById(R.id.text_min_budget);
                        EditText MaxBudText = (EditText)RootView.findViewById(R.id.text_max_budget);
                        android.widget.SearchView LocalitySearch = (android.widget.SearchView) RootView.findViewById(R.id.searchView);
                        if(LocalitySearch!=null)
                            Locality = LocalitySearch.getQuery().toString();
                        if(PropertyTypeSpinner!=null)
                            PropertyType = PropertyTypeSpinner.getSelectedItem().toString();
                        if(BedroomSpinner!=null)
                        {
                            Bedrooms = Integer.parseInt(BedroomSpinner.getSelectedItem().toString().substring(0, 1));
                            if(Bedrooms == 4 && BedroomSpinner.getSelectedItemPosition() == 4)
                                Bedrooms = 5;
                        }
                        if(MinAreaText!=null) {
                            if(Objects.equals(MinAreaText.getText().toString(), "") || !isInteger(MinAreaText.getText().toString()))
                                MinArea = 0;
                            else
                                MinArea = Integer.parseInt(MinAreaText.getText().toString());
                        }
                        if(MaxAreaText!=null){
                            if(Objects.equals(MaxAreaText.getText().toString(), "") || !isInteger(MaxAreaText.getText().toString()))
                                MaxArea = 0;
                            else
                                MaxArea = Integer.parseInt(MaxAreaText.getText().toString());
                        }
                        if(MinBudText!=null){
                            if(Objects.equals(MinBudText.getText().toString(), "") || !isInteger(MinBudText.getText().toString()))
                                MinBudget = 0;
                            else
                            MinBudget = Integer.parseInt(MinBudText.getText().toString());
                        }
                        if(MaxBudText!=null){
                            if(Objects.equals(MaxBudText.getText().toString(), "") || !isInteger(MaxBudText.getText().toString()))
                                MaxBudget = 0;
                            else
                            MaxBudget = Integer.parseInt(MaxBudText.getText().toString());
                        }
                        if(BuyRent != null)
                        {
                            RadioButton SelectedRB = (RadioButton)RootView.findViewById(BuyRent.getCheckedRadioButtonId());
                            if(SelectedRB.getText().equals("BUY"))
                                ListType = "BUY";
                            else
                                ListType = "RENT";
                        }
                        Spinner States = (Spinner)getActivity().findViewById(R.id.state_spinner);
                        Spinner Cities = (Spinner)getActivity().findViewById(R.id.city_spinner);
                        if(States!=null)
                            State = States.getSelectedItem().toString();
                        if(Cities!=null)
                            City = Cities.getSelectedItem().toString();

                        SearchParameters = new SearchParams(PropertyType,Furnished,Bedrooms,MinArea,MaxArea,MinBudget,MaxBudget,ListType, Locality, City, State);
                        IReference.StartIntent(SearchParameters);
                    }
                });
                break;
            }
            default:break;
        }

        new GetData().execute();
        return RootView;
    }

    private class GetData extends AsyncTask<Void,Void,Void>
    {
        public GetData()
        {

        }
        @Override
        protected Void doInBackground(Void... params)
        {
            QueryRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot)
                {
                    AllListings.All_Listings.clear();
                    Log.d("Snapshot", String.valueOf(dataSnapshot.getChildrenCount()));
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        Serializable_PropData current = AllListings.ListingFactory((HashMap) ds.getValue());
                        AllListings.All_Listings.add(current);
                    }
                    Log.d("Listings", String.valueOf(AllListings.All_Listings.size()));
                }
                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
            return null;
        }
    }

    private static boolean isInteger(String s) {
        return isInteger(s, 10);
    }

    private static boolean isInteger(String s, int radix) {
        if(s.isEmpty()) return false;
        for(int i = 0; i < s.length(); i++) {
            if(i == 0 && s.charAt(i) == '-') {
                if(s.length() == 1) return false;
                else continue;
            }
            if(Character.digit(s.charAt(i), radix) < 0) return false;
        }
        return true;
    }
}
