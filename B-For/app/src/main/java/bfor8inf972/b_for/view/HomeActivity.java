package bfor8inf972.b_for.view;




import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.Profile;
import com.facebook.login.widget.ProfilePictureView;

import java.io.InputStream;
import java.net.URL;

import bfor8inf972.b_for.R;
import bfor8inf972.b_for.representation.User;

public class HomeActivity extends AppCompatActivity
        implements ProfilFragment.OnFragmentInteractionListener,
        CreateEventFragment.OnFragmentInteractionListener,
        FindEventFragment.OnFragmentInteractionListener,
        TermOfUseFragment.OnFragmentInteractionListener,
        SettingsFragment.OnFragmentInteractionListener,
        ManageEvents.OnFragmentInteractionListener,
        OverviewFragment.OnFragmentInteractionListener,
        NavigationView.OnNavigationItemSelectedListener {

    private ManageEvents manageEventsFragment;
    private FindEventFragment findEventFragment;
    private ProfilFragment profilFragment;
    private SettingsFragment settingFragment;
    private TermOfUseFragment termOfUseFragment;
    private OverviewFragment overviewFragment;
    private User user;

    private String currentFragmentName;

    public HomeActivity(){
        manageEventsFragment = new ManageEvents();
        findEventFragment = new FindEventFragment();
        profilFragment = new ProfilFragment();
        settingFragment = new SettingsFragment();
        termOfUseFragment = new TermOfUseFragment();
        overviewFragment = new OverviewFragment();

        currentFragmentName=null;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        user = (User) getIntent().getSerializableExtra("User");

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

        InitialiseUserInfo(navigationView.getHeaderView(0));

        //replace fragment only if it's first time on activity
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.FragmentContainer, overviewFragment)
                    .commit();
            currentFragmentName = getResources().getString(R.string.overview_title);
        }

        //get restored data
        if (savedInstanceState != null) {
            currentFragmentName = savedInstanceState.getString("currentFragmentName");
        }
        getSupportActionBar().setTitle(currentFragmentName);
    }

    private void InitialiseUserInfo(View v){
        TextView firstName = (TextView) v.findViewById(R.id.first_name);
        TextView lastName = (TextView) v.findViewById(R.id.last_name);
        ProfilePictureView profilePictureView =(ProfilePictureView) v.findViewById(R.id.picture_profile);
        if(user!=null) {
            firstName.setText(user.getFirstName());
            lastName.setText(user.getLastName());
            profilePictureView.setProfileId(user.getId());
        }
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("currentFragmentName", currentFragmentName);
        super.onSaveInstanceState(savedInstanceState);
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
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profil) {
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.FragmentContainer, profilFragment)
                    .commit();
            currentFragmentName = getResources().getString(R.string.profil_title);
        } else if (id == R.id.nav_termOfUse) {
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.FragmentContainer, termOfUseFragment)
                    .commit();
            currentFragmentName = getResources().getString(R.string.termOfUse_title);
        } else if (id == R.id.nav_manageEvent) {
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.FragmentContainer, manageEventsFragment)
                    .commit();
            currentFragmentName = getResources().getString(R.string.manageEvent_title);
        } else if (id == R.id.nav_settings) {
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.FragmentContainer, settingFragment)
                    .commit();
            currentFragmentName = getResources().getString(R.string.settings_title);
        } else if (id == R.id.nav_findEvent) {
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.FragmentContainer, findEventFragment)
                    .commit();
            currentFragmentName = getResources().getString(R.string.findEvent_title);
        } else if (id == R.id.nav_overview) {
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.FragmentContainer, overviewFragment)
                    .commit();
            currentFragmentName = getResources().getString(R.string.overview_title);
        }

        getSupportActionBar().setTitle(currentFragmentName);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
