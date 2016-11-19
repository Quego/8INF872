package bfor8inf972.b_for;

/**
 * Created by asus f751 on 18/11/2016.
 */

public class groupUser {
    private int Id;
    private int user_id;
    private int driver_id;

    groupUser(){

    }
    // Serialization
    //groupUser obj = new groupUser();
    // Gson gson = new Gson();
    // String json = gson.toJson(obj);


    // Deserialization
    //  groupUser obj2 = gson.fromJson(json, groupUser.class);

    public void setId(int id)
    {
        this.Id = id;
    }

    public int getId()
    {
        return Id;
    }

    public void setUser_id(int user_id)
    {
        this.user_id = user_id;
    }

    public int getUser_id()
    {
        return user_id;
    }

    public void setDriver_id(int driver_id)
    {
        this.driver_id = driver_id;
    }

    public int getDriver_id()
    {
        return driver_id;
    }
}
