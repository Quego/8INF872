package bfor8inf972.b_for.representation;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.io.Serializable;

public class ItemMissing implements Serializable {

    private int id;

    private String name;

    private int quantity;
    private int size;
    private String metric;

    private User user;

    private boolean checked;

    public ItemMissing(){
        this.id = -1;

        this.name = "UNKNOWN";

        this.quantity = -1;
        this.size = -1;

        this.metric = "UNKNOWN";

        this.user = new User();

        this .checked = false;
    }

    public ItemMissing(int id, String name, int quantity, int size, String metric, User user, boolean checked){
        this.id = id;

        this.name = name;

        this.quantity = quantity;
        this.size = size;

        this.metric = metric;

        this.user = user;

        this.checked = checked;
    }

    public ItemMissing(JsonElement json) {
        Gson gson = new Gson();

        ItemMissing itemMissing= new ItemMissing();
        try {
            itemMissing = gson.fromJson(json,ItemMissing.class);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        this.id = itemMissing.getId();

        this.name = itemMissing.getName();

        this.quantity = itemMissing.getQuantity();
        this.size = itemMissing.getSize();

        this.metric = itemMissing.getMetric();

        this.user = itemMissing.getUser();

        this.checked = itemMissing.isChecked();
    }

    //(Serialization)
    public String toJson(){
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setSize(int size)
    {
        this.size = size;
    }

    public int getSize()
    {
        return size;
    }

    public void setMetric(String metric)
    {
        this.metric = metric;
    }

    public String getMetric()
    {
        return metric;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isChecked() {
        return checked;
    }
}
