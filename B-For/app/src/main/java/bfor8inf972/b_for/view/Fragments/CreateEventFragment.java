package bfor8inf972.b_for.view.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HeaderViewListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import bfor8inf972.b_for.R;
import bfor8inf972.b_for.Utils.OnClickUtils;
import bfor8inf972.b_for.view.customViews.RemovableRowAdapter;

public class CreateEventFragment extends Fragment {

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

    Button date;
    Button beginHour;
    Button endHour;
    RemovableRowAdapter removableRowAdapter;
    ListView itemsToBring;
    ArrayList<String> listItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myFragmentView = inflater.inflate(R.layout.fragment_create_event, container, false);

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
        final EditText addEditText = (EditText) footer.findViewById(R.id.footer_editText);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add string to listView
                String addedItem = addEditText.getText().toString();
                if(!addedItem.trim().equals("")) {
                    listItems.add(addEditText.getText().toString());
                    removableRowAdapter.setListViewHeight(itemsToBring);
                    addEditText.setText("");
                    removableRowAdapter.notifyDataSetChanged();
                }
                addEditText.requestFocus();
            }
        });
        return myFragmentView;
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
