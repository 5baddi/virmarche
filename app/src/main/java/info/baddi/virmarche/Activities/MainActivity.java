package info.baddi.virmarche.Activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import info.baddi.virmarche.Fragments.LocateFragment;
import info.baddi.virmarche.Fragments.LocationFragment;
import info.baddi.virmarche.Fragments.SettingsFragment;
import info.baddi.virmarche.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fragmentManager;
    private FloatingActionButton locate;
    private Toolbar toolbar;
    private String title;
    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.app_name);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        title = getString(R.string.app_name);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);

        fragmentManager = getFragmentManager();

        int updateFragment = getIntent().getIntExtra("fragment", 0);
        if(updateFragment != 0)
        {
            switch (updateFragment)
            {
                case R.id.nav_locate:
                    bundle = getIntent().getExtras();
                    LocateFragment locateFragment = new LocateFragment();
                    locateFragment.setArguments(bundle);
                    displayFragment(getString(R.string.fragment_locate), locateFragment);
                break;
            }
        }

        locate = (FloatingActionButton) findViewById(R.id.locate);
        locate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                locate.hide();
                displayFragment(getString(R.string.fragment_locate), new LocateFragment());
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_open, R.string.navigation_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        switch (id)
        {
            case R.id.settings_action:
                displayFragment(getString(R.string.settings_action), new SettingsFragment());
            break;
            case R.id.about_action:
                title = getString(R.string.app_name);
                toolbar.setTitle(title);
            break;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id)
        {
            case R.id.nav_locate:

                displayFragment(getString(R.string.fragment_locate), new LocateFragment());
            break;
            case R.id.nav_locations:
                displayFragment(getString(R.string.fragment_locate), new LocationFragment());
            break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void displayFragment(String title, Fragment fragment)
    {
        toolbar.setTitle(title);
        fragmentManager.beginTransaction().replace(R.id.frameContainer, fragment).commit();
    }
}
