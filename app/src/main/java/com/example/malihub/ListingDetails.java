package com.example.rayson.malihub;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


public class ListingDetails extends AppCompatActivity implements OnClickIListener{
    private Serializable_PropData propData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing_details);

        Bundle extra = getIntent().getExtras();
        if (extra != null){
            propData = (Serializable_PropData)getIntent().getSerializableExtra("Prop_Data");
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout collapsingToolbar= (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(propData.getProp_address());
        collapsingToolbar.setCollapsedTitleTextColor(Color.BLACK);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("Details"));
        tabLayout.addTab(tabLayout.newTab().setText("Images"));
        tabLayout.addTab(tabLayout.newTab().setText("Location"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final ListingDetailsPagerAdapter adapter = new ListingDetailsPagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount(), propData);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void FragmentSelected() {

    }

    @Override
    public void StartIntent(SearchParams searchParams) {

    }

    @Override
    public void GetPropDetails(Serializable_PropData SP) {

        String _city = SP.getProp_city();
        String _addr = SP.getProp_address();
        String _ownerName = SP.getProp_owner();
        Intent sharingIntent=new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody= "Property Name: " + _city + "_" + _addr+ "\n Owner Name: " + _ownerName;
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT,"Property");
        sharingIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
        startActivity(Intent.createChooser(sharingIntent,"Sharevia"));
    }
}