package bfor8inf972.b_for.view.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.Profile;
import com.facebook.login.widget.ProfilePictureView;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import bfor8inf972.b_for.R;
import bfor8inf972.b_for.representation.Localization;
import bfor8inf972.b_for.representation.Party;
import bfor8inf972.b_for.representation.User;
import bfor8inf972.b_for.view.customViews.ExpandableMyEventAdapter;
import bfor8inf972.b_for.view.customViews.ExpandableNextEventAdapter;
import bfor8inf972.b_for.view.customViews.ExpandablePendingEventAdapter;


public class OverviewFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private ExpandableMyEventAdapter myEvents_listAdapter;
    private ExpandableListView myEvents_expandableListView;
    private ArrayList<Party> myEvents_list;

    private ExpandableNextEventAdapter nextEvents_listAdapter;
    private ExpandableListView nextEvents_expandableListView;
    private ArrayList<Party> nextEvents_list;

    private ExpandablePendingEventAdapter pendingEvents_listAdapter;
    private ExpandableListView pendingEvents_expandableListView;
    private ArrayList<Party> pendingEvents_list;


    public OverviewFragment() {
        // Required empty public constructor
        myEvents_list = new ArrayList<Party>();
        nextEvents_list = new ArrayList<Party>();
        pendingEvents_list = new ArrayList<Party>();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    public static OverviewFragment newInstance(String param1, String param2) {
        OverviewFragment fragment = new OverviewFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.overview_title);

        // Inflate the layout for this fragment
        View myFragmentView = inflater.inflate(R.layout.fragment_overview, container, false);

        //Handle user owned events
        loadMyEvents();

        myEvents_expandableListView = (ExpandableListView) myFragmentView.findViewById(R.id.expandableListView_myEvents);
        if(myEvents_listAdapter==null)
        myEvents_listAdapter = new ExpandableMyEventAdapter(myEvents_expandableListView, getContext(), myEvents_list);
        else
            myEvents_listAdapter.setParentView(myEvents_expandableListView);
        myEvents_expandableListView.setAdapter(myEvents_listAdapter);


        //Handle user next events
        loadNextEvents();
        nextEvents_expandableListView = (ExpandableListView) myFragmentView.findViewById(R.id.expandableListView_nextEvents);
        nextEvents_listAdapter = new ExpandableNextEventAdapter(getContext(), nextEvents_list);
        nextEvents_expandableListView.setAdapter(nextEvents_listAdapter);

        //Handle user event waiting for validation
        loadPendingEvents();

            pendingEvents_expandableListView = (ExpandableListView) myFragmentView.findViewById(R.id.expandableListView_pendingEvents);
            pendingEvents_listAdapter = new ExpandablePendingEventAdapter(getContext(), pendingEvents_list);
            pendingEvents_expandableListView.setAdapter(pendingEvents_listAdapter);



        //Use setListViewHeight onClick because screen scrollview conflicts with ExpandableList scrollview


        nextEvents_expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                nextEvents_listAdapter.setListViewHeight(parent, groupPosition);
                return false;
            }
        });

        pendingEvents_expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                pendingEvents_listAdapter.setListViewHeight(parent, groupPosition);
                return false;
            }
        });


        //Update height once
        myEvents_listAdapter.setListViewHeight(myEvents_expandableListView, -1);
        nextEvents_listAdapter.setListViewHeight(nextEvents_expandableListView, -1);
        pendingEvents_listAdapter.setListViewHeight(pendingEvents_expandableListView, -1);


        InitialiseProfilInfo(myFragmentView);

        //Handle profile layout click
        ((LinearLayout) myFragmentView.findViewById(R.id.profile_layout)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                Toast toast = Toast.makeText(getContext(), "Communiquer avec l'API dans : " + this.getClass(), Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        return myFragmentView;
    }

    /**
     * @param v the view where are defined recquired view
     */
    private void InitialiseProfilInfo(View v) {
        TextView firstName = (TextView) v.findViewById(R.id.first_name);
        TextView lastName = (TextView) v.findViewById(R.id.last_name);
        ProfilePictureView profilePictureView = (ProfilePictureView) v.findViewById(R.id.picture_profile);

        Profile profile = Profile.getCurrentProfile();
        if (profile != null) {
            firstName.setText(profile.getFirstName());
            lastName.setText(profile.getLastName());
            profilePictureView.setProfileId(profile.getId());
        }
        //TODO use API
        ((RatingBar) v.findViewById(R.id.current_rating)).setRating(2.8f);
    }


    private void loadMyEvents() {
        myEvents_list.clear();
        myEvents_list.add(new Party(0, "Grosse fête", new Date().toString(), "now", null, new HashSet<User>(), new Localization(0, "1", "2", "Chicoutimi"), null, new HashSet<User>(), true, "17H", "23H", 20, 5));
        myEvents_list.add(new Party(0, "Soirée Lan", new Date().toString(), "now", null, new HashSet<User>(), new Localization(0, "1", "2", "La baie"), null, new HashSet<User>(), true, "17H", "23H", 100, 2));
        myEvents_list.add(new Party(0, "Soirée GOT", new Date().toString(), "now", null, new HashSet<User>(), new Localization(0, "1", "2", "Montréal"), null, new HashSet<User>(), true, "17H", "23H", 30, 4));
    }

    private void loadNextEvents() {
        nextEvents_list.clear();
        nextEvents_list.add(new Party(0, "Fin du monde", new Date().toString(), "now", null, new HashSet<User>(), new Localization(0, "1", "2", "Chicoutimi"), null, new HashSet<User>(), true, "17H", "23H", 40, 3));
        nextEvents_list.add(new Party(0, "C'est l'été", new Date().toString(), "now", null, new HashSet<User>(), new Localization(0, "1", "2", "La baie"), null, new HashSet<User>(), true, "17H", "23H", 20, 1));
    }

    private void loadPendingEvents() {
        pendingEvents_list.clear();
        pendingEvents_list.add(new Party(0, "Fin du monde", new Date().toString(), "now", null, new HashSet<User>(), new Localization(0, "1", "2", "Chicoutimi"), null, new HashSet<User>(), true, "17H", "23H", 10, 3));
        pendingEvents_list.add(new Party(0, "C'est l'été", new Date().toString(), "now", null, new HashSet<User>(), new Localization(0, "1", "2", "Chicoutimi"), null, new HashSet<User>(), true, "17H", "23H", 60, 1));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
