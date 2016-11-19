package bfor8inf972.b_for.view.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

import bfor8inf972.b_for.R;
import bfor8inf972.b_for.view.expandable.ExpandableViewCustomAdapter;
import bfor8inf972.b_for.view.expandable.EventChild;
import bfor8inf972.b_for.view.expandable.EventParent;


public class FindEventFragment extends Fragment {


    private SearchView searchView;
    private String searchRequest;

    private OnFragmentInteractionListener mListener;
    private ExpandableViewCustomAdapter events_listAdapter;
    private ExpandableListView events_expandableListView;
    private ArrayList<EventParent> eventList = new ArrayList<EventParent>();

    public FindEventFragment() {
        searchView = null;
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("searchRequest", searchRequest);
        super.onSaveInstanceState(savedInstanceState);
    }


    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            searchRequest = savedInstanceState.getString("searchRequest");
        }
        super.onViewStateRestored(savedInstanceState);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_search_bar, menu);
        super.onCreateOptionsMenu(menu, inflater);

        final MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setQueryHint(getString(R.string.search_bar));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //TODO
                Toast toast = Toast.makeText(getContext(), "Please change action in " + this.getClass(), Toast.LENGTH_SHORT);
                toast.show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                searchRequest = s;
                return false;
            }
        });

        if (searchView != null) {
            searchView.setQuery(searchRequest, false);
        }
    }

    public static FindEventFragment newInstance(String param1, String param2) {
        FindEventFragment fragment = new FindEventFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myFragmentView = inflater.inflate(R.layout.fragment_find_event, container, false);

        // add data for displaying in expandable list view
        loadData();

        events_expandableListView = (ExpandableListView) myFragmentView.findViewById(R.id.expandableListView_events);
        events_listAdapter = new ExpandableViewCustomAdapter(getContext(), eventList);
        events_expandableListView.setAdapter(events_listAdapter);

        return myFragmentView;
    }


    private void loadData() {
        eventList.clear();
        addEvent("Grosse fête ", new Date(), "12 km", "Un appartement, 50 m², une table de beer-pong ...");
        addEvent("Soirée LAN", new Date(), "13 km", "Prenez vos manettes de Xbox et venez découvrir des jeux en local !");
        addEvent("Fin du monde", new Date(), "42 km", "La fin du monde, la fin du monde ! La fin du monde, la fin du monde ! La fin du monde, la fin du monde ! ");
        addEvent("C'est l'été", new Date(), "57 km", "Une piscine, des meufs, de l'alcool, soyez vous-même");
    }


    private void addEvent(String name, Date date, String distance, String details) {

        EventChild child = new EventChild(details, 2.5f);
        EventParent parent = new EventParent(name, distance, date, child);
        eventList.add(parent);
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


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
