package bfor8inf972.b_for.view.customViews;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import bfor8inf972.b_for.R;

/**
 * Adapter for R.layout.removable_text_row
 */
public class RemovableRowAdapter extends BaseAdapter implements ListAdapter {

    private ArrayList<String> list = new ArrayList<String>();
    private Context context;

    public RemovableRowAdapter(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.removable_text_row, null);
        }

        //Handle TextView and display string from list
        TextView listItemText = (TextView) view.findViewById(R.id.row_text);
        listItemText.setText(list.get(position));

        //Handle buttons and add onClickListeners
        Button removeButton = (Button) view.findViewById(R.id.row_remove);

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                notifyDataSetChanged();
            }
        });

        return view;
    }


    /**
     *
     * @param listView the listView to resize
     * Resize given ListView in Height since Listview Conflicts with its parent scrollview
     */
    public void setListViewHeight(ListView listView) {
        ListAdapter listAdapter = (ListAdapter) listView.getAdapter();
        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.EXACTLY);

        for (int i = 0; i < listAdapter.getCount(); i++) {
            View groupItem = listAdapter.getView(i, null, listView);
            groupItem.measure(0, 0);
            totalHeight += groupItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
}