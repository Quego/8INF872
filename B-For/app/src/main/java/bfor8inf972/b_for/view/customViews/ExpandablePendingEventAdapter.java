package bfor8inf972.b_for.view.customViews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import bfor8inf972.b_for.R;
import bfor8inf972.b_for.representation.Party;


/**
 * Adapter for R.layout.expandable_event_parent and R.layout.expandable_pending_event_child
 */
public class ExpandablePendingEventAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<Party> parties;

    public ExpandablePendingEventAdapter(Context context, ArrayList<Party> parties) {
        this.context = context;
        this.parties = parties;
    }

    public void remove(int index) {
        parties.remove(index);
    }

    @Override
    public int getGroupCount() {
        return parties.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return parties.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        Party p = parties.get(groupPosition);
        return p;
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
        Party party = (Party) getGroup(groupPosition);
        if (view == null) {
            LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inf.inflate(R.layout.expandable_event_parent, null);
        }

        TextView title = (TextView) view.findViewById(R.id.event_title);
        title.setText(party.getTitle().trim());

        TextView date = (TextView) view.findViewById(R.id.event_date);
        date.setText(party.getCreationTime().trim());

        TextView distance = (TextView) view.findViewById(R.id.event_distance);
        distance.setText(party.getLocalization().getAddress());

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {

        Party party = (Party) getChild(groupPosition, childPosition);
        if (view == null) {
            LayoutInflater layInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layInflater.inflate(R.layout.expandable_pending_event_child, null);
        }

        ((RatingBar) view.findViewById(R.id.recquired_rating)).setRating(party.getMinimumStars());

        TextView details = (TextView) view.findViewById(R.id.details);
        details.setText("TODO : ajouter des details pour un évènement");

        TextView guestCount = (TextView) view.findViewById(R.id.guest_count);
        guestCount.setText("Invités : " + party.getGuests().size()  + "/"+party.getMaxPeople() );

        TextView sleepingGuests = (TextView) view.findViewById(R.id.sleeping_count);
        sleepingGuests.setText("Invités dormant : " + party.getSleepingGuests().size()  + "/TODO" );


        //TODO
        ((Button) view.findViewById(R.id.unparticipate_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(v.getContext(), "Please change action in " + this.getClass(), Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        //TODO
        ((Button) view.findViewById(R.id.details_button)).setOnClickListener(new View.OnClickListener() {
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

    /**
     *
     * @param listView the listView to resize
     * Resize given ListView in Height since Listview Conflicts with its parent scrollview
     */
    public void setListViewHeight(ExpandableListView listView, int group) {
        ExpandablePendingEventAdapter listAdapter = (ExpandablePendingEventAdapter) listView.getExpandableListAdapter();
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
}
