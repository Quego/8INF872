package bfor8inf972.b_for.representation;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.io.Serializable;

public class Localization implements Serializable {

    private int id;

    private String longitude = "";
    private String latitude = "";

    private String address = "";

    public Localization() {
        this.id = -1;

        this.longitude = "UNKNOWN";
        this.latitude = "UNKNOWN";
        this.address = "UNKNOWN";

    }

    public Localization(int id, String longitude, String latitude, String address) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
    }

    public Localization(JsonElement json) {
        Gson gson = new Gson();

        Localization localization = new Localization();

        try {
            localization = gson.fromJson(json, Localization.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.id = localization.getId();
        this.longitude = localization.getLongitude();
        this.latitude = localization.getLatitude();
        this.address = localization.getAddress();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
