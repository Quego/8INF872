package bfor8inf972.b_for.view.customViews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import bfor8inf972.b_for.R;


/**
 * Adapter for R.layout.expandable_event_parent and R.layout.expandable_event_child
 */
public class ExpandableViewCustomAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<EventParent> parents;

    public ExpandableViewCustomAdapter(Context context, ArrayList<EventParent> parents) {
        this.context = context;
        this.parents = parents;
    }

    public void remove(int index) {
        parents.remove(index);
    }

    @Override
    public int getGroupCount() {
        return parents.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return parents.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        EventChild productList = parents.get(groupPosition).getChild();
        return productList;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {
        EventParent currentChild = (EventParent) getGroup(groupPosition);
        if (view == null) {
            LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inf.inflate(R.layout.expandable_event_parent, null);
        }

        TextView title = (TextView) view.findViewById(R.id.event_title);
        title.setText(currentChild.getName().trim());

        TextView date = (TextView) view.findViewById(R.id.event_date);
        date.setText(currentChild.getDate().trim());

        TextView distance = (TextView) view.findViewById(R.id.event_distance);
        distance.setText(currentChild.getDistance().trim());

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {

        EventChild currentParent = (EventChild) getChild(groupPosition, childPosition);
        if (view == null) {
            LayoutInflater layInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layInflater.inflate(R.layout.expandable_event_child, null);
        }

        ((RatingBar) view.findViewById(R.id.recquired_rating)).setRating(currentParent.getMinRating());

        TextView details = (TextView) view.findViewById(R.id.details);
        details.setText(currentParent.getDetails().trim());

        //TODO
        ((Button) view.findViewById(R.id.participate_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(v.getContext(), "Please change action in " + this.getClass(), Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
