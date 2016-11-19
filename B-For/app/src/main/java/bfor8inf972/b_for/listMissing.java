package bfor8inf972.b_for;

/**
 * Created by asus f751 on 18/11/2016.
 */

public class listMissing {
    private int Id;
    private int itemMissing_id;
    private int user_id;
    private Boolean checked;

    listMissing(){

    }
    // Serialization
    //listMissing obj = new listMissing();
    // Gson gson = new Gson();
    // String json = gson.toJson(obj);


    // Deserialization
    //  listMissing obj2 = gson.fromJson(json, listMissing.class);

    public void setId(int id)
    {
        this.Id = id;
    }

    public int getId()
    {
        return Id;
    }

    public void setItemMissing_id(int itemMissing_id)
    {
        this.itemMissing_id = itemMissing_id;
    }

    public int getItemMissing_id()
    {
        return itemMissing_id;
    }

    public void setUser_id(int user_id)
    {
        this.user_id = user_id;
    }

    public int getUser_id()
    {
        return user_id;
    }

    public void setChecked(boolean checked)
    {
        this.checked = checked;
    }

    public boolean getChecked()
    {
        return checked;
    }
}
