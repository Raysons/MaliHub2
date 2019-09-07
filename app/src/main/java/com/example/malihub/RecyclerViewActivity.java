package com.example.rayson.malihub;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by rayson 20/06/2019
 */
public class RecyclerViewActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OnClickIListener
{
    ActionBar MyActionBar;
    Toolbar MyToolBar;
    List<Serializable_PropData> Results;
    Spinner States;
    Spinner Cities;
    NavigationView MyNavView;
    DrawerLayout MyDrawLayout;
    Firebase QueryRef;
    static CircleImageView Logo;
    static TextView Usertitle;
    static MenuItem LoginItem;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setTheme(R.style.AppThemeNoAB);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        Toast.makeText(RecyclerViewActivity.this, "Recycler View", Toast.LENGTH_SHORT).show();
        RecyclerView_Fragment fragment;
        MyToolBar = (Toolbar)findViewById(R.id.action_toolbar);
        setSupportActionBar(MyToolBar);
        MyActionBar = getSupportActionBar();
        if(MyActionBar!=null)
            MyActionBar.setDisplayHomeAsUpEnabled(true);
        QueryRef = new Firebase("https://livelarge.firebaseio.com/");
        MyNavView = (NavigationView)findViewById(R.id.NavigationView);
        MyNavView.setNavigationItemSelectedListener(this);
        MyDrawLayout = (DrawerLayout)findViewById(R.id.NavigationDrawer);

        if(QueryRef.getAuth() != null) {
            View HeaderView = (View) MyNavView.getHeaderView(0);
            CircleImageView Logo = (CircleImageView) HeaderView.findViewById(R.id.navheader_image);
            Picasso.get().load(CurrentUser.getUserImageURL()).into(Logo);
            Usertitle = (TextView) HeaderView.findViewById(R.id.navheader_label);
            Usertitle.setText(CurrentUser.getUserName());
            LoginItem = MyNavView.getMenu().getItem(1);
            LoginItem.setTitle("Logout");
        }
        ActionBarDrawerToggle ABDT = new ActionBarDrawerToggle(this, MyDrawLayout,MyToolBar,R.string.open_drawer,R.string.close_drawer){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        MyDrawLayout.setDrawerListener(ABDT);
        ABDT.syncState();
        Intent PrevActivity = getIntent();
        States = (Spinner)findViewById(R.id.state_spinner);
        Cities = (Spinner)findViewById(R.id.city_spinner);
        if(States!=null)
            States.setSelection(getIntent().getIntExtra("state",0));
        if(Cities!=null)
            Cities.setSelection(getIntent().getIntExtra("city",0));
        if( getIntent().getStringExtra("from").equalsIgnoreCase("SearchActivity"))
            Results = (List<Serializable_PropData>) getIntent().getSerializableExtra("resultobject");

        if (savedInstanceState != null)
        {
            fragment = (RecyclerView_Fragment) getSupportFragmentManager().getFragment(savedInstanceState, "fragment");
        }
        else
        {
            RecyclerViewActivity.this.FragmentSelected();
        }
    }

    @Override
    public void FragmentSelected() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.Coverpage, RecyclerView_Fragment.FragmentFactory(0, Results))
                .commit();
    }

    @Override
    public void StartIntent(SearchParams searchParams) {

    }

    @Override
    public void GetPropDetails(Serializable_PropData SP) {
        Intent GetDetails = new Intent(RecyclerViewActivity.this, ListingDetails.class);
        GetDetails.putExtra("Prop_Data", (Serializable) SP);
        GetDetails.putExtra("from", "RecyclerViewActivity");
        RecyclerViewActivity.this.startActivity(GetDetails);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        int ID = item.getItemId();
        switch (ID)
        {
            case R.id.Home:
            {
                Intent intent = new Intent(RecyclerViewActivity.this, MainActivity.class);
                RecyclerViewActivity.this.startActivity(intent);
                break;
            }
            case R.id.Login:
            {
                if(QueryRef.getAuth() == null && CurrentUser.getUserName() == null) {
                    Intent myIntent = new Intent(RecyclerViewActivity.this, LoginActivity.class);
                    RecyclerViewActivity.this.startActivity(myIntent);
                }
                else
                {
                    View HeaderView = (View) MyNavView.getHeaderView(0);
                    CircleImageView Logo = (CircleImageView) HeaderView.findViewById(R.id.navheader_image);
                    Usertitle = (TextView) HeaderView.findViewById(R.id.navheader_label);
                    LoginItem = MyNavView.getMenu().getItem(1);
                    CurrentUser.setUserImageURL("");
                    CurrentUser.setUserImageURL("");
                    CurrentUser.setUserEmail("");
                    Logo.setImageResource(R.mipmap.applogo);
                    Usertitle.setText("MaliHub");
                    LoginItem.setTitle("Login");
                    Intent myIntent = new Intent(RecyclerViewActivity.this, LoginActivity.class);
                    RecyclerViewActivity.this.startActivity(myIntent);
                }
                break;
            }
            case R.id.Search:
            {
                Intent myIntent = new Intent(RecyclerViewActivity.this, SearchActivity.class);
                RecyclerViewActivity.this.startActivity(myIntent);
                break;
            }
            case R.id.SubmitAd:
            {
                if(QueryRef.getAuth() != null && CurrentUser.getUserName() != null) {
                    Intent myIntent = new Intent(RecyclerViewActivity.this, PostListing.class);
                    RecyclerViewActivity.this.startActivity(myIntent);
                }
                else
                {
                    Toast.makeText(RecyclerViewActivity.this, "Submit ad needs user to be logged in", Toast.LENGTH_LONG).show();
                }
                break;
            }
            case R.id.AboutUs:
            {
                Intent myIntent = new Intent(RecyclerViewActivity.this, AboutUs.class);
                RecyclerViewActivity.this.startActivity(myIntent);
                break;
            }
            default:break;
        }
        MyDrawLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
