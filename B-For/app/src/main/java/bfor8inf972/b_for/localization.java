package bfor8inf972.b_for;
/**
 * Created by asus f751 on 18/11/2016.
 */
public class localization {
    private int Id;
    private String longitude="";
    private String latitude="";
    private String address="";

    localization(){

    }
    // Serialization
    //localization obj = new localization();
   // Gson gson = new Gson();
   // String json = gson.toJson(obj);


    // Deserialization
  //  localization obj2 = gson.fromJson(json, localization.class);

    public void setId(int id)
    {
        this.Id = id;
    }

    public int getId()
    {
        return Id;
    }

    public void setLongitude(String longitude)
    {
        this.longitude = longitude;
    }

    public String getLongitude()
    {
        return longitude;
    }

    public void setLatitude(String latitude)
    {
        this.latitude = latitude;
    }

    public String getLatitude()
    {
        return latitude;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getAddress()
    {
        return address;
    }

    /*public static void main(String[] args) {
        localization obj = new localization();
        Gson gson = new Gson();
        String json = gson.toJson(obj);
    }*/

}
