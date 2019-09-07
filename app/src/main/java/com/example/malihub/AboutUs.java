package com.example.rayson.malihub;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewAnimator;

import com.firebase.client.Firebase;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class AboutUs extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private ActionBar MyActionBar;
    private Toolbar MyToolBar;
    private NavigationView MyNavView;
    private DrawerLayout MyDrawLayout;
    static CircleImageView Logo;
    private static TextView Usertitle;
    private static MenuItem LoginItem;
    private Firebase QueryRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        QueryRef = new Firebase("https://malihub.firebaseio.com/");
        final ViewAnimator viewAnimator1 = (ViewAnimator)this.findViewById(R.id.viewFlipper);
        MyToolBar = (Toolbar)findViewById(R.id.action_toolbar);
        setSupportActionBar(MyToolBar);
        MyActionBar = getSupportActionBar();
        if(MyActionBar!=null)
            MyActionBar.setDisplayHomeAsUpEnabled(true);
        MyNavView = (NavigationView)findViewById(R.id.NavigationView);
        MyNavView.setNavigationItemSelectedListener(this);
        MyDrawLayout = (DrawerLayout)findViewById(R.id.NavigationDrawer);
        if(CurrentUser.getUserName() != null) {
            View HeaderView = MyNavView.getHeaderView(0);
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
        Toast.makeText(AboutUs.this, "Search Page", Toast.LENGTH_SHORT).show();
        viewAnimator1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimationFactory.flipTransition(viewAnimator1);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        int ID = item.getItemId();
        switch (ID)
        {
            case R.id.Home:
            {
                Intent intent = new Intent(AboutUs.this, MainActivity.class);
                AboutUs.this.startActivity(intent);
                break;
            }
            case R.id.Login:
            {
                if(QueryRef.getAuth() == null) {
                    Intent myIntent = new Intent(AboutUs.this, LoginActivity.class);
                    AboutUs.this.startActivity(myIntent);
                }
                else
                {
                    View HeaderView = MyNavView.getHeaderView(0);
                    CircleImageView Logo = (CircleImageView) HeaderView.findViewById(R.id.navheader_image);
                    Usertitle = (TextView) HeaderView.findViewById(R.id.navheader_label);
                    LoginItem = MyNavView.getMenu().getItem(1);
                    CurrentUser.setUserImageURL("");
                    CurrentUser.setUserImageURL("");
                    CurrentUser.setUserEmail("");
                    Logo.setImageResource(R.mipmap.applogo);
                    Usertitle.setText("LiveLarge");
                    LoginItem.setTitle("Login");
                    Intent myIntent = new Intent(AboutUs.this, LoginActivity.class);
                    AboutUs.this.startActivity(myIntent);
                    QueryRef.unauth();
                }
                break;
            }
            case R.id.Search:
            {
                Intent myIntent = new Intent(AboutUs.this, SearchActivity.class);
                AboutUs.this.startActivity(myIntent);
                break;
            }
            case R.id.SubmitAd:
            {
                if(QueryRef.getAuth() != null && CurrentUser.getUserName() != null) {
                    Intent myIntent = new Intent(AboutUs.this, PostListing.class);
                    AboutUs.this.startActivity(myIntent);
                }
                else
                {
                    Toast.makeText(AboutUs.this, "Submit ad needs user to be logged in", Toast.LENGTH_LONG).show();
                }
                break;
            }
            case R.id.AboutUs:
            {

                Intent myIntent = new Intent(AboutUs.this, AboutUs.class);
                AboutUs.this.startActivity(myIntent);
                break;
            }
            default:break;
        }
        MyDrawLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
