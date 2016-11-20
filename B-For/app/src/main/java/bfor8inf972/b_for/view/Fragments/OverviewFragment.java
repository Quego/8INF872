package bfor8inf972.b_for.view.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import bfor8inf972.b_for.R;
import bfor8inf972.b_for.view.customViews.EventChild;
import bfor8inf972.b_for.view.customViews.EventParent;
import bfor8inf972.b_for.view.customViews.ExpandableViewCustomAdapter;


public class OverviewFragment extends Fragment {

    private OnFragmentInteractionListener mListener;


    private ExpandableViewCustomAdapter myEvents_listAdapter;
    private ExpandableListView myEvents_expandableListView;
    private ArrayList<EventParent> myEvents_list = new ArrayList<EventParent>();

    private ExpandableViewCustomAdapter nextEvents_listAdapter;
    private ExpandableListView nextEvents_expandableListView;
    private ArrayList<EventParent> nextEvents_list = new ArrayList<EventParent>();

    private ExpandableViewCustomAdapter pendingEvents_listAdapter;
    private ExpandableListView pendingEvents_expandableListView;
    private ArrayList<EventParent> pendingEvents_list = new ArrayList<EventParent>();


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
        myEvents_listAdapter = new ExpandableViewCustomAdapter(getContext(), myEvents_list);
        myEvents_expandableListView.setAdapter(myEvents_listAdapter);
        loadNextEvents();

        nextEvents_expandableListView = (ExpandableListView) myFragmentView.findViewById(R.id.expandableListView_nextEvents);
        nextEvents_listAdapter = new ExpandableViewCustomAdapter(getContext(), nextEvents_list);
        nextEvents_expandableListView.setAdapter(nextEvents_listAdapter);

        loadPendingEvents();
        pendingEvents_expandableListView = (ExpandableListView) myFragmentView.findViewById(R.id.expandableListView_pendingEvents);
        pendingEvents_listAdapter = new ExpandableViewCustomAdapter(getContext(), pendingEvents_list);
        pendingEvents_expandableListView.setAdapter(pendingEvents_listAdapter);


        myEvents_expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                setListViewHeight(parent, groupPosition);
                return false;
            }
        });

        nextEvents_expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                setListViewHeight(parent, groupPosition);
                return false;
            }
        });

        pendingEvents_expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                setListViewHeight(parent, groupPosition);
                return false;
            }
        });

        setListViewHeight(myEvents_expandableListView, -1);
        setListViewHeight(nextEvents_expandableListView, -1);
        setListViewHeight(pendingEvents_expandableListView, -1);

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


    /**
     *
     * @param listView the listView to resize
     * Resize given ListView in Height since Listview Conflicts with its parent scrollview
     */
    private void setListViewHeight(ExpandableListView listView, int group) {
        ExpandableViewCustomAdapter listAdapter = (ExpandableViewCustomAdapter) listView.getExpandableListAdapter();
        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.EXACTLY);

        for (int i = 0; i < listAdapter.getGroupCount(); i++) {
            View groupItem = listAdapter.getGroupView(i, false, null, listView);
            groupItem.measure(0, 0);

            totalHeight += groupItem.getMeasuredHeight();

            if (group != -1 && ((listView.isGroupExpanded(i)) && (i != group)) || ((!listView.isGroupExpanded(i)) && (i == group))) {
                View listItem = listAdapter.getChildView(i, 0, false, null, listView);
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                totalHeight += listItem.getMeasuredHeight();
            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getGroupCount() - 1));
        listView.setLayoutParams(params);

    }


    private void loadMyEvents() {
        myEvents_list.clear();
        addEvent(myEvents_list, "Grosse fête", new Date(), "12 km", "Un appartement, 50 m², une table de beer-pong ...");
        addEvent(myEvents_list, "Soirée Lan ", new Date(), "12 km", "Venez geeker avec moi, j'ai pas d'amis");
        addEvent(myEvents_list, "Soirée GOT ", new Date(), "12 km", "Pop corn, série, chill");

    }

    private void loadNextEvents() {
        nextEvents_list.clear();
        addEvent(nextEvents_list, "Fin du monde", new Date(), "42 km", "La fin du monde, la fin du monde ! La fin du monde, la fin du monde ! La fin du monde, la fin du monde ! ");
        addEvent(nextEvents_list, "C'est l'été", new Date(), "57 km", "Une piscine, des meufs, de l'alcool, soyez vous-même");
    }

    private void loadPendingEvents() {
        addEvent(pendingEvents_list, "C'est l'été", new Date(), "57 km", "Une piscine, des meufs, de l'alcool, soyez vous-même");
        addEvent(myEvents_list, "Projet X ", new Date(), "47 km", "Un appartement, 50 m², une table de beer-pong ...");
    }


    private void addEvent(ArrayList<EventParent> useList, String name, Date date, String distance, String details) {
        EventChild child = new EventChild(details, 2.5f);
        EventParent parent = new EventParent(name, distance, date, child);
        useList.add(parent);
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
