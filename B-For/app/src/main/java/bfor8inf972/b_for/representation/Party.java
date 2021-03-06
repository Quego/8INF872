package bfor8inf972.b_for.representation;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.io.Serializable;
import java.util.HashSet;

public class Party implements Serializable {
    private int id;
    private String title;
    private String creationTime;
    private String updateTime;

    private User user;

    private HashSet<User> guests;

    private Localization localization;

    private ListMissing listMissing;

    private HashSet<User> sleepingGuests;

    private boolean sleepAvailable;

    private String startingHour;

    private String finishingHour;

    private int maxPeople;

    private int minimumStars;

    public Party() {
        this.id = -1;
        this.title = "UNKNOWN";
        this.creationTime = "UNKNOWN";
        ;
        this.updateTime = "UNKNOWN";
        this.user = new User();
        this.guests = new HashSet<>();
        this.localization = new Localization();
        this.listMissing = new ListMissing();
        this.sleepingGuests = new HashSet<>();
        this.sleepAvailable = false;
        this.startingHour = "UNKNOWN";
        this.finishingHour = "UNKNOWN";
        this.maxPeople = -1;
        this.minimumStars = -1;
    }

    public Party(int id, String title, String creationTime, String updateTime, User user, HashSet<User> guests, Localization localization, ListMissing listMissing, HashSet<User> sleepingGuests, boolean sleepAvailable, String startingHour, String finishingHour, int maxPeople, int minimumStars) {
        this.id = id;
        this.title = title;
        this.creationTime = creationTime;
        this.updateTime = updateTime;
        this.user = user;
        this.guests = guests;
        this.localization = localization;
        this.listMissing = listMissing;
        this.sleepingGuests = sleepingGuests;
        this.sleepAvailable = sleepAvailable;
        this.startingHour = startingHour;
        this.finishingHour = finishingHour;
        this.maxPeople = maxPeople;
        this.minimumStars = minimumStars;
    }

    public Party(JsonElement json) {
        Gson gson = new Gson();

        Party party = new Party();
        try {
            party = gson.fromJson(json, Party.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.id = party.getId();
        this.title = party.getTitle();
        this.creationTime = party.getCreationTime();
        this.updateTime = party.getUpdateTime();
        this.user = party.getUser();
        this.guests = party.getGuests();
        this.localization = party.getLocalization();
        this.listMissing = party.getListMissing();
        this.sleepingGuests = party.getSleepingGuests();
        this.sleepAvailable = party.isSleepAvailable();
        this.startingHour = party.getStartingHour();
        this.finishingHour = party.getFinishingHour();
        this.maxPeople = party.getMaxPeople();
        this.minimumStars = party.getId();
    }

    public String toJson() {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public HashSet<User> getGuests() {
        return guests;
    }

    public void setGuests(HashSet<User> guests) {
        this.guests = guests;
    }

    public Localization getLocalization() {
        return localization;
    }

    public void setLocalization(Localization localization) {
        this.localization = localization;
    }

    public ListMissing getListMissing() {
        return listMissing;
    }

    public void setListMissing(ListMissing listMissing) {
        this.listMissing = listMissing;
    }

    public HashSet<User> getSleepingGuests() {
        return sleepingGuests;
    }

    public void setSleepingGuests(HashSet<User> sleepingGuests) {
        this.sleepingGuests = sleepingGuests;
    }

    public boolean isSleepAvailable() {
        return sleepAvailable;
    }

    public void setSleepAvailable(boolean sleepAvailable) {
        this.sleepAvailable = sleepAvailable;
    }

    public String getStartingHour() {
        return startingHour;
    }

    public void setStartingHour(String startingHour) {
        this.startingHour = startingHour;
    }

    public String getFinishingHour() {
        return finishingHour;
    }

    public void setFinishingHour(String finishingHour) {
        this.finishingHour = finishingHour;
    }

    public int getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(int maxPeople) {
        this.maxPeople = maxPeople;
    }

    public int getMinimumStars() {
        return minimumStars;
    }

    public void setMinimumStars(int minimumStars) {
        this.minimumStars = minimumStars;
    }

    public boolean validateImportantFields() {

        //TODO check fields when they're added
        if (getStartingHour() == null || getStartingHour().equals("")
                || getFinishingHour() == null || getFinishingHour().equals("")
                || getTitle() == null || getTitle().equals("")
                || getMaxPeople() == 0
                || getLocalization() == null)
            return false;

        return true;
    }
}
