package bfor8inf972.b_for.view.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.Profile;
import com.facebook.login.widget.ProfilePictureView;

import java.util.ArrayList;
import java.util.Date;

import bfor8inf972.b_for.R;
import bfor8inf972.b_for.representation.Localization;
import bfor8inf972.b_for.representation.Party;
import bfor8inf972.b_for.view.customViews.ExpandableEventAdapter;
import bfor8inf972.b_for.view.customViews.ExpandableMyEventAdapter;
import bfor8inf972.b_for.view.customViews.ExpandableNextEventAdapter;
import bfor8inf972.b_for.view.customViews.ExpandablePendingEventAdapter;


public class OverviewFragment extends Fragment {

    private OnFragmentInteractionListener mListener;


    private ExpandableMyEventAdapter myEvents_listAdapter;
    private ExpandableListView myEvents_expandableListView;
    private ArrayList<Party> myEvents_list = new ArrayList<Party>();

    private ExpandableNextEventAdapter nextEvents_listAdapter;
    private ExpandableListView nextEvents_expandableListView;
    private ArrayList<Party> nextEvents_list = new ArrayList<Party>();

    private ExpandablePendingEventAdapter pendingEvents_listAdapter;
    private ExpandableListView pendingEvents_expandableListView;
    private ArrayList<Party> pendingEvents_list = new ArrayList<Party>();


    public OverviewFragment() {
        // Required empty public constructor
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
        // Inflate the layout for this fragment
        View myFragmentView = inflater.inflate(R.layout.fragment_overview, container, false);

        loadMyEvents();

        myEvents_expandableListView = (ExpandableListView) myFragmentView.findViewById(R.id.expandableListView_myEvents);
        myEvents_listAdapter = new ExpandableMyEventAdapter(getContext(), myEvents_list);
        myEvents_expandableListView.setAdapter(myEvents_listAdapter);
        loadNextEvents();

        nextEvents_expandableListView = (ExpandableListView) myFragmentView.findViewById(R.id.expandableListView_nextEvents);
        nextEvents_listAdapter = new ExpandableNextEventAdapter(getContext(), nextEvents_list);
        nextEvents_expandableListView.setAdapter(nextEvents_listAdapter);

        loadPendingEvents();
        pendingEvents_expandableListView = (ExpandableListView) myFragmentView.findViewById(R.id.expandableListView_pendingEvents);
        pendingEvents_listAdapter = new ExpandablePendingEventAdapter(getContext(), pendingEvents_list);
        pendingEvents_expandableListView.setAdapter(pendingEvents_listAdapter);


        myEvents_expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                myEvents_listAdapter.setListViewHeight(parent, groupPosition);
                return false;
            }
        });

        nextEvents_expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                nextEvents_listAdapter.setListViewHeight(parent, groupPosition);
                return false;
            }
        });

        pendingEvents_expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                pendingEvents_listAdapter.setListViewHeight(parent, groupPosition);
                return false;
            }
        });

        myEvents_listAdapter.setListViewHeight(myEvents_expandableListView, -1);
        nextEvents_listAdapter.setListViewHeight(nextEvents_expandableListView, -1);
        pendingEvents_listAdapter.setListViewHeight(pendingEvents_expandableListView, -1);

        InitialiseProfilInfo(myFragmentView);
        ((LinearLayout) myFragmentView.findViewById(R.id.profile_layout)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                Toast toast = Toast.makeText(getContext(), "Please change action in " + this.getClass(), Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        return myFragmentView;
    }

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

        myEvents_list.add(new Party(0, "Grosse fête", new Date().toString(), "now", null, null, new Localization(0,"1","2","Chicoutimi"), null, null, true, "17H", "23H", 20, 5) );
        myEvents_list.add(new Party(0, "Soirée Lan", new Date().toString(), "now", null, null, new Localization(0,"1","2","Chicoutimi"), null, null, true, "17H", "23H", 100, 2) );
        myEvents_list.add(new Party(0, "Soirée GOT", new Date().toString(), "now", null, null, new Localization(0,"1","2","Chicoutimi"), null, null, true, "17H", "23H", 20, 4) );

       //addEvent(myEvents_list, "Grosse fête", new Date(), "12 km", "Un appartement, 50 m², une table de beer-pong ...");
       //addEvent(myEvents_list, "Soirée Lan ", new Date(), "12 km", "Venez geeker avec moi, j'ai pas d'amis");
       //addEvent(myEvents_list, "Soirée GOT ", new Date(), "12 km", "Pop corn, série, chill");

    }

    private void loadNextEvents() {
        nextEvents_list.clear();
        nextEvents_list.add(new Party(0, "Fin du monde", new Date().toString(), "now", null, null, new Localization(0,"1","2","Chicoutimi"), null, null, true, "17H", "23H", 20, 3) );
        nextEvents_list.add(new Party(0, "C'est l'été", new Date().toString(), "now", null, null, new Localization(0,"1","2","Chicoutimi"), null, null, true, "17H", "23H", 20, 1) );


       //addEvent(nextEvents_list, "Fin du monde", new Date(), "42 km", "La fin du monde, la fin du monde ! La fin du monde, la fin du monde ! La fin du monde, la fin du monde ! ");
       //addEvent(nextEvents_list, "C'est l'été", new Date(), "57 km", "Une piscine, des meufs, de l'alcool, soyez vous-même");
    }

    private void loadPendingEvents() {
        pendingEvents_list.clear();
        pendingEvents_list.add(new Party(0, "Fin du monde", new Date().toString(), "now", null, null, new Localization(0,"1","2","Chicoutimi"), null, null, true, "17H", "23H", 20, 3) );
        pendingEvents_list.add(new Party(0, "C'est l'été", new Date().toString(), "now", null, null, new Localization(0,"1","2","Chicoutimi"), null, null, true, "17H", "23H", 20, 1) );
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
