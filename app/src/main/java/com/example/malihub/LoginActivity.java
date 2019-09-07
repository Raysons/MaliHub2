package com.example.rayson.malihub;

/**
 * Created by rayson 20/06/2019
 * */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.ui.auth.core.AuthProviderType;
import com.firebase.ui.auth.core.FirebaseLoginBaseActivity;
import com.firebase.ui.auth.core.FirebaseLoginError;
import com.squareup.picasso.Picasso;

import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class LoginActivity extends FirebaseLoginBaseActivity implements NavigationView.OnNavigationItemSelectedListener{

    private Firebase firebaseRef;
    private EditText userNameET;
    private EditText passwordET;
    private ActionBar MyActionBar;
    private Toolbar MyToolBar;
    Spinner States;
    Spinner Cities;
    private NavigationView MyNavView;
    private DrawerLayout MyDrawLayout;
    static CircleImageView Logo;
    private static TextView Usertitle;
    private static MenuItem LoginItem;
    private String mName = "";
    private String PicUrl = "";

    /* String Constants */
    private static final String FIREBASEREF = "https://malihub.firebaseio.com/";
    private static final String FIREBASE_ERROR = "Firebase Error";
    private static final String USER_ERROR = "User Error";
    private static final String LOGIN_SUCCESS = "Login Success";
    private static final String LOGOUT_SUCCESS = "Welcome to login page";
    private static final String USER_CREATION_SUCCESS =  "Successfully created user";
    private static final String USER_CREATION_ERROR =  "User creation error";
    private static final String EMAIL_INVALID =  "Email is invalid :";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        firebaseRef = new Firebase(FIREBASEREF);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userNameET = (EditText)findViewById(R.id.edit_text_email);
        passwordET = (EditText)findViewById(R.id.edit_text_password);
        Button login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.showFirebaseLoginPrompt();
            }
        });
        MyToolBar = (Toolbar)findViewById(R.id.login_toolbar);
        setSupportActionBar(MyToolBar);
        MyActionBar = getSupportActionBar();
        if(MyActionBar!=null)
            MyActionBar.setDisplayHomeAsUpEnabled(true);
        MyNavView = (NavigationView)findViewById(R.id.NavigationView);
        MyNavView.setNavigationItemSelectedListener(this);
        MyDrawLayout = (DrawerLayout)findViewById(R.id.NavigationDrawer);

        if(firebaseRef.getAuth() != null && CurrentUser.getUserName() != null) {
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
        Button createButton = (Button) findViewById(R.id.button);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });
        if(firebaseRef != null)
            firebaseRef.unauth();
    }

    @Override
    protected void onFirebaseLoginProviderError(FirebaseLoginError firebaseLoginError) {
        Snackbar snackbar = Snackbar.
                make(userNameET, FIREBASE_ERROR + firebaseLoginError.message, Snackbar.LENGTH_LONG);
        snackbar.show();
        resetFirebaseLoginPrompt();
    }

    @Override
    protected void onFirebaseLoginUserError(FirebaseLoginError firebaseLoginError) {
        Snackbar snackbar = Snackbar
                .make(userNameET, USER_ERROR + firebaseLoginError.message, Snackbar.LENGTH_LONG);
        snackbar.show();
        resetFirebaseLoginPrompt();
    }

    @Override
    public Firebase getFirebaseRef() {
        return firebaseRef;
    }

    @Override
    public void onFirebaseLoggedIn(AuthData authData)
    {
        switch (authData.getProvider()) {
            case "password":
                mName = (String) authData.getProviderData().get("email");
                break;
            default: {
                mName = (String) authData.getProviderData().get("displayName");
                break;
            }
        }
        Toast.makeText(getApplicationContext(), LOGIN_SUCCESS, Toast.LENGTH_SHORT).show();
        Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
        CurrentUser.setUserName(mName);
        PicUrl = (String) authData.getProviderData().get("profileImageURL");
        CurrentUser.setUserImageURL(PicUrl);
        myIntent.putExtra("from", "LoginActivity");
        LoginActivity.this.startActivity(myIntent);
    }

    @Override
    public void onFirebaseLoggedOut() {
        Toast.makeText(getApplicationContext(), LOGOUT_SUCCESS, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        setEnabledAuthProvider(AuthProviderType.PASSWORD);
        setEnabledAuthProvider(AuthProviderType.GOOGLE);
        setEnabledAuthProvider(AuthProviderType.FACEBOOK);
    }

    // Validate email address for new accounts.
    private boolean isEmailValid(String email) {
        boolean isGoodEmail = (email != null && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches());
        if (!isGoodEmail) {
            userNameET.setError(EMAIL_INVALID + email);
            return false;
        }
        return true;
    }

    // create a new user in Firebase
    private void createUser() {
        if(userNameET.getText() == null ||  !isEmailValid(userNameET.getText().toString())) {
            return;
        }
        firebaseRef.createUser(userNameET.getText().toString(), passwordET.getText().toString(),
                new Firebase.ValueResultHandler<Map<String, Object>>() {
                    @Override
                    public void onSuccess(Map<String, Object> result) {
                        Snackbar snackbar = Snackbar.make(userNameET, USER_CREATION_SUCCESS, Snackbar.LENGTH_SHORT);
                        snackbar.show();
                    }
                    @Override
                    public void onError(FirebaseError firebaseError)
                    {
                        Snackbar snackbar = Snackbar.make(userNameET, USER_CREATION_ERROR, Snackbar.LENGTH_SHORT);
                        snackbar.show();
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
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                LoginActivity.this.startActivity(intent);
                break;
            }
            case R.id.Login:
            {
                if(firebaseRef.getAuth() == null) {
                    Intent myIntent = new Intent(LoginActivity.this, LoginActivity.class);
                    LoginActivity.this.startActivity(myIntent);
                }
                else
                {
                    View HeaderView = MyNavView.getHeaderView(0);
                    CircleImageView Logo = (CircleImageView) HeaderView.findViewById(R.id.navheader_image);
                    Picasso.get().load(CurrentUser.getUserImageURL()).into(Logo);
                    Usertitle = (TextView) HeaderView.findViewById(R.id.navheader_label);
                    LoginItem = MyNavView.getMenu().getItem(1);
                    CurrentUser.setUserImageURL("");
                    CurrentUser.setUserImageURL("");
                    CurrentUser.setUserEmail("");
                    Logo.setImageResource(R.mipmap.applogo);
                    Usertitle.setText("LiveLarge");
                    LoginItem.setTitle("Login");
                    firebaseRef.unauth();
                    Intent myIntent = new Intent(LoginActivity.this, LoginActivity.class);
                    LoginActivity.this.startActivity(myIntent);
                }
                break;
            }
            case R.id.Search:
            {
                Intent myIntent = new Intent(LoginActivity.this, SearchActivity.class);
                LoginActivity.this.startActivity(myIntent);
                break;
            }
            case R.id.SubmitAd:
            {
                if(firebaseRef.getAuth() != null && CurrentUser.getUserName() != null) {
                    Intent myIntent = new Intent(LoginActivity.this, PostListing.class);
                    LoginActivity.this.startActivity(myIntent);
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Submit ad needs user to be logged in", Toast.LENGTH_LONG).show();
                }
                break;
            }
            case R.id.AboutUs:
            {
                Intent myIntent = new Intent(LoginActivity.this, AboutUs.class);
                LoginActivity.this.startActivity(myIntent);
                break;
            }
            default:break;
        }
        MyDrawLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
