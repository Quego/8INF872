package bfor8inf972.b_for.view.Fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.util.ArrayList;
import java.util.Date;

import bfor8inf972.b_for.R;
import bfor8inf972.b_for.Utils.OnClickUtils;
import bfor8inf972.b_for.representation.Party;
import bfor8inf972.b_for.view.customViews.RemovableRowAdapter;

public class CreateEventFragment extends Fragment {

    Button date;
    Button beginHour;
    Button endHour;
    Button pickLocation;
    EditText addEditText;
    EditText maxPeople;
    EditText maxSleepinPeople;
    RatingBar recquiredStars;
    AutoCompleteTextView detailsField;

    RemovableRowAdapter removableRowAdapter;
    ListView itemsToBring;
    ArrayList<String> listItems;

    int PLACE_PICKER_REQUEST = 1;
    PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

    private OnFragmentInteractionListener mListener;

    public CreateEventFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     **/
    public static CreateEventFragment newInstance(String param1, String param2) {
        CreateEventFragment fragment = new CreateEventFragment();
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


    public boolean askPermission(String permissionToAsk) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (getActivity().checkSelfPermission(permissionToAsk)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{permissionToAsk}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            return true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.create_event_title);

        View myFragmentView = inflater.inflate(R.layout.fragment_create_event, container, false);

        //Get views to get values later
        maxPeople = (EditText) myFragmentView.findViewById(R.id.max_guest);
        maxSleepinPeople = (EditText) myFragmentView.findViewById(R.id.max_sleeping_guest);
        recquiredStars = (RatingBar) myFragmentView.findViewById(R.id.ratingBar);
        detailsField = (AutoCompleteTextView) myFragmentView.findViewById(R.id.details);


        //Handle date button
        date = (Button) myFragmentView.findViewById(R.id.select_date);
        date.setOnClickListener(new OnClickUtils().createDatePickerListener(getContext(), date));


        //Handle start Time button
        beginHour = (Button) myFragmentView.findViewById(R.id.select_time_start);
        beginHour.setOnClickListener(new OnClickUtils().createTimePickerListener(getContext(), beginHour));

        //Handle end Time button
        endHour = (Button) myFragmentView.findViewById(R.id.select_time_end);
        endHour.setOnClickListener(new OnClickUtils().createTimePickerListener(getContext(), endHour));

        //Handle list items to bring
        listItems = new ArrayList<String>();
        removableRowAdapter = new RemovableRowAdapter(listItems, myFragmentView.getContext());

        itemsToBring = (ListView) myFragmentView.findViewById(R.id.itemsToBring);
        itemsToBring.setAdapter(removableRowAdapter);

        //Handle footer button
        View footer = (View) myFragmentView.inflate(getContext(), R.layout.removable_list_footer, null);
        itemsToBring.addFooterView(footer);
        removableRowAdapter.setListViewHeight(itemsToBring);

        Button buttonAdd = (Button) footer.findViewById(R.id.footer_buttonAdd);
        addEditText = (EditText) footer.findViewById(R.id.footer_editText);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add string to listView
                String addedItem = addEditText.getText().toString();
                if (!addedItem.trim().equals("")) {
                    listItems.add(addEditText.getText().toString());
                    removableRowAdapter.setListViewHeight(itemsToBring);
                    addEditText.setText("");
                    removableRowAdapter.notifyDataSetChanged();
                }
                addEditText.requestFocus();
            }
        });


        pickLocation = (Button) myFragmentView.findViewById(R.id.location_button);
        pickLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (askPermission(Manifest.permission.ACCESS_FINE_LOCATION) && askPermission(Manifest.permission.INTERNET)) {
                    try {
                        startActivityForResult(builder.build(getActivity()), PLACE_PICKER_REQUEST);
                    } catch (GooglePlayServicesRepairableException e) {
                        e.printStackTrace();
                    } catch (GooglePlayServicesNotAvailableException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        //Handle button create
        Button createEvent = (Button) myFragmentView.findViewById(R.id.createEvent);
        createEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO Set party and send it to API
                Party p = new Party();
                //TODO add date
                //p.setDate(date.getText());
                p.setMaxPeople(Integer.parseInt(maxPeople.getText().toString()));
                //TODO add maxSleeping
                //p.setMaxPeopleSleeping(Integer.parseInt(maxSleepinPeople.getText().toString()));
                p.setStartingHour(beginHour.getText().toString());
                p.setFinishingHour(endHour.getText().toString());
                p.setCreationTime(new Date().toString());
                //TODO create listMissing
                //p.setListMissing();
                //Todo setMinimumStars is Float
                //p.setMinimumStars(recquiredStars.getRating());
                //TODO add details
                //p.setDetails(detailsField.getText().toString();
                //TODO add connected user
                //p.setUser();

                if (p.validateImportantFields()) {
                    //TODO speak with API
                } else {
                    Toast toast = Toast.makeText(v.getContext(), "Veuillez compléter tous les champs ", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
        return myFragmentView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     **/
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
