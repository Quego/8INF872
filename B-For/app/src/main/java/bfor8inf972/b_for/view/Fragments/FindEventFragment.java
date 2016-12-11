package bfor8inf972.b_for.view.Fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import bfor8inf972.b_for.R;
import bfor8inf972.b_for.Utils.PermissionUtils;
import bfor8inf972.b_for.representation.Localization;
import bfor8inf972.b_for.representation.Party;
import bfor8inf972.b_for.representation.User;
import bfor8inf972.b_for.view.customViews.ExpandableEventAdapter;


public class FindEventFragment extends Fragment {

    private Button pickLocation;
    private Button searchButton;
    private SeekBar rangePicker;
    private TextView rangeText;
    private int selectedRange;
    private int minRange;
    private int maxRange;

    private OnFragmentInteractionListener mListener;
    private ExpandableEventAdapter events_listAdapter;
    private ExpandableListView events_expandableListView;
    private ArrayList<Party> eventList = new ArrayList<Party>();

    public FindEventFragment() {
        minRange = 10;
        maxRange = 100;
        selectedRange = maxRange/2+minRange;
    }


    public static FindEventFragment newInstance(String param1, String param2) {
        FindEventFragment fragment = new FindEventFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
        }
        super.onViewStateRestored(savedInstanceState);
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
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.findEvent_title);

        View myFragmentView = inflater.inflate(R.layout.fragment_find_event, container, false);

        pickLocation = (Button) myFragmentView.findViewById(R.id.location_button);
        pickLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PermissionUtils.askPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) && PermissionUtils.askPermission(getActivity(), Manifest.permission.INTERNET)) {
                    try {
                        startActivityForResult(PermissionUtils.builder.build(getActivity()), PermissionUtils.PLACE_PICKER_REQUEST);
                    } catch (GooglePlayServicesRepairableException e) {
                        e.printStackTrace();
                    } catch (GooglePlayServicesNotAvailableException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        searchButton = (Button) myFragmentView.findViewById(R.id.launch_search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO communicate with API
                Toast toast = Toast.makeText(v.getContext(), "Communiquer avec l'API dans : " + this.getClass(), Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        rangePicker = (SeekBar) myFragmentView.findViewById(R.id.km_range);
        rangePicker.setMax(maxRange);
        rangePicker.setProgress(selectedRange);

        rangeText = (TextView) myFragmentView.findViewById(R.id.km_range_text);
        if (savedInstanceState == null) {
            rangeText.setText("rayon : " + selectedRange + " km");
        }

        rangePicker.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress < minRange) {
                    rangeText.setText("rayon : " + selectedRange + " km");
                } else {
                    selectedRange = progress;
                }
                rangeText.setText("rayon : " + selectedRange + " km");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });


        // add data for displaying in expandable list view
        loadData();

        //Handle event list
        events_expandableListView = (ExpandableListView) myFragmentView.findViewById(R.id.expandableListView_events);
        events_listAdapter = new ExpandableEventAdapter(getContext(), eventList);
        events_expandableListView.setAdapter(events_listAdapter);

        return myFragmentView;
    }


    private void loadData() {
        eventList.clear();
        eventList.add(new Party(0, "Grosse fête", new Date().toString(), "now", null, new HashSet<User>(), new Localization(0, "1", "2", "Chicoutimi"), null, new HashSet<User>(), true, "17H", "23H", 30, 5));
        eventList.add(new Party(0, "Soirée Lan", new Date().toString(), "now", null, new HashSet<User>(), new Localization(0, "1", "2", "Chicoutimi"), null, new HashSet<User>(), true, "17H", "23H", 100, 2));
        eventList.add(new Party(0, "Soirée GOT", new Date().toString(), "now", null, new HashSet<User>(), new Localization(0, "1", "2", "Chicoutimi"), null, new HashSet<User>(), true, "17H", "23H", 10, 4));
        eventList.add(new Party(0, "Fin du monde", new Date().toString(), "now", null, new HashSet<User>(), new Localization(0, "1", "2", "Chicoutimi"), null, new HashSet<User>(), true, "17H", "23H", 40, 3));
        eventList.add(new Party(0, "C'est l'été", new Date().toString(), "now", null, new HashSet<User>(), new Localization(0, "1", "2", "Chicoutimi"), null, new HashSet<User>(), true, "17H", "23H", 20, 1));
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PermissionUtils.PLACE_PICKER_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                Place place = PlacePicker.getPlace(data, getContext());
                pickLocation.setText(place.getAddress());
            }
        }
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
