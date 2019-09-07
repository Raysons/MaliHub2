package com.example.rayson.malihub;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

/**
 * Created by rayson 20/06/2019
 */
public class FBRecyclerViewActivity extends AppCompatActivity implements OnClickIListener
{
    private ActionBar MyActionBar;
    private Toolbar MyToolBar;
    private SearchParams SearchObject;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setTheme(R.style.AppThemeNoAB);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        Toast.makeText(FBRecyclerViewActivity.this, "Recycler View", Toast.LENGTH_SHORT).show();
        FBRecyclerView_Fragment fragment;
        MyToolBar = (Toolbar)findViewById(R.id.action_toolbar);
        setSupportActionBar(MyToolBar);
        MyActionBar = getSupportActionBar();
        if(MyActionBar!=null)
            MyActionBar.setDisplayHomeAsUpEnabled(true);
        Intent Search = getIntent();

        if( getIntent().getStringExtra("from").equalsIgnoreCase("SearchActivity"))
            SearchObject = (SearchParams) getIntent().getSerializableExtra("searchobject");
        if (savedInstanceState != null)
            fragment = (FBRecyclerView_Fragment) getSupportFragmentManager().getFragment(savedInstanceState, "fragment");
        else
            FBRecyclerViewActivity.this.FragmentSelected();

    }

    @Override
    public void FragmentSelected() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.Coverpage, FBRecyclerView_Fragment.FragmentFactory(0, SearchObject))
                .commit();
    }

    @Override
    public void StartIntent(SearchParams searchParams) {

    }

    @Override
    public void GetPropDetails(Serializable_PropData SP) {

    }

}
