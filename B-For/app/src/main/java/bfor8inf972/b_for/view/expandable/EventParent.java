package bfor8inf972.b_for.view.expandable;

/**
 * Created by Mathias on 15/11/2016.
 */

public class EventParent {

    private String details ;
    private float minRating;

    public EventParent() {
        this.details = "";
        this.minRating=0;
    }

    public EventParent(String details, float minRating) {
        this.details=details;
        this.minRating=minRating;
    }


    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public float getMinRating() {
        return minRating;
    }

    public void setMinRating(float minRating) {
        this.minRating = minRating;
    }
}
