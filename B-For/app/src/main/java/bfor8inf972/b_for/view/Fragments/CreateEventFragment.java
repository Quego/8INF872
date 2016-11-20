package bfor8inf972.b_for.view.Fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import bfor8inf972.b_for.R;
import bfor8inf972.b_for.Utils.OnClickUtils;

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
    DatePickerDialog datePickerDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View myFragmentView = inflater.inflate(R.layout.fragment_create_event, container, false);
        date = (Button) myFragmentView.findViewById(R.id.select_date);
        date.setOnClickListener(new OnClickUtils().createDatePickerListener(getContext(),date));

        beginHour = (Button) myFragmentView.findViewById(R.id.select_time_start);
        beginHour.setOnClickListener(new OnClickUtils().createTimePickerListener(getContext(),beginHour));

        endHour = (Button) myFragmentView.findViewById(R.id.select_time_end);
        endHour.setOnClickListener(new OnClickUtils().createTimePickerListener(getContext(),endHour));

        return myFragmentView;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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
