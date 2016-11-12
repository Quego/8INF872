package bfor8inf972.b_for.view;




import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import bfor8inf972.b_for.R;

public class HomeActivity extends AppCompatActivity
        implements ProfilFragment.OnFragmentInteractionListener,
        CreateEventFragment.OnFragmentInteractionListener,
        FindEventFragment.OnFragmentInteractionListener,
        TermOfUseFragment.OnFragmentInteractionListener,
        SettingsFragment.OnFragmentInteractionListener,
        NavigationView.OnNavigationItemSelectedListener {

    CreateEventFragment createEventFragment;
    FindEventFragment findEventFragment;
    ProfilFragment profilFragment;
    SettingsFragment settingFragment;
    TermOfUseFragment termOfUseFragment;

    public HomeActivity(){
        createEventFragment = new CreateEventFragment();
        findEventFragment = new FindEventFragment();
        profilFragment = new ProfilFragment();
        settingFragment = new SettingsFragment();
        termOfUseFragment = new TermOfUseFragment();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        getSupportFragmentManager().beginTransaction().
                add(R.id.FragmentContainer, profilFragment)
                .commit();

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
        //getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profil) {
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.FragmentContainer, profilFragment)
                        .commit();
            // Handle the camera action
        } else if (id == R.id.nav_cgu) {
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.FragmentContainer, termOfUseFragment)
                    .commit();
        }
        else if (id == R.id.nav_createEvent) {
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.FragmentContainer, createEventFragment)
                    .commit();
        }
        else if (id == R.id.nav_settings) {
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.FragmentContainer, settingFragment)
                    .commit();
        }
        else if (id == R.id.nav_findEvent) {
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.FragmentContainer, findEventFragment)
                    .commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
