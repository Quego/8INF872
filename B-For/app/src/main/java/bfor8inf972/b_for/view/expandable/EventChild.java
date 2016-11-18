package bfor8inf972.b_for.view.expandable;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Mathias on 15/11/2016.
 */

public class EventChild {

    private String name;
    private String date;
    private String distance;

    private EventParent child;


    public EventChild() {
        this.name = "";
        this.date="";
        this.distance="";
        this.child = null;
    }

    public EventChild(String name, String distance, Date date, EventParent child) {
        this.name = name;
        this.child = child;
        this.distance = distance;
        SimpleDateFormat sdf = new SimpleDateFormat("'Le' dd/MM/yyyy 'à' hh:mm");
        this.date = sdf.format(date);
    }

    public EventChild(String name, String distance, String date, EventParent child) {
        this.name = name;
        this.child = child;
        this.distance = distance;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EventParent getChild() {
        return child;
    }

    public void setChild(EventParent child) {
        this.child = child;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
