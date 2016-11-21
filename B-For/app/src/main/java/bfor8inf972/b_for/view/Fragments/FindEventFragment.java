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
import bfor8inf972.b_for.representation.Localization;
import bfor8inf972.b_for.representation.Party;
import bfor8inf972.b_for.view.customViews.ExpandableEventAdapter;


public class FindEventFragment extends Fragment {


    private SearchView searchView;
    private String searchRequest;

    private OnFragmentInteractionListener mListener;
    private ExpandableEventAdapter events_listAdapter;
    private ExpandableListView events_expandableListView;
    private ArrayList<Party> eventList = new ArrayList<Party>();

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
        events_listAdapter = new ExpandableEventAdapter(getContext(), eventList);
        events_expandableListView.setAdapter(events_listAdapter);

        return myFragmentView;
    }


    private void loadData() {
        eventList.clear();
        eventList.add(new Party(0, "Grosse fête", new Date().toString(), "now", null, null, new Localization(0,"1","2","Chicoutimi"), null, null, true, "17H", "23H", 20, 5) );
        eventList.add(new Party(0, "Soirée Lan", new Date().toString(), "now", null, null, new Localization(0,"1","2","Chicoutimi"), null, null, true, "17H", "23H", 100, 2) );
        eventList.add(new Party(0, "Soirée GOT", new Date().toString(), "now", null, null, new Localization(0,"1","2","Chicoutimi"), null, null, true, "17H", "23H", 20, 4) );
        eventList.add(new Party(0, "Fin du monde", new Date().toString(), "now", null, null, new Localization(0,"1","2","Chicoutimi"), null, null, true, "17H", "23H", 20, 3) );
        eventList.add(new Party(0, "C'est l'été", new Date().toString(), "now", null, null, new Localization(0,"1","2","Chicoutimi"), null, null, true, "17H", "23H", 20, 1) );
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
