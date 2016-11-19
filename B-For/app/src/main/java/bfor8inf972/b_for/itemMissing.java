package bfor8inf972.b_for;

/**
 * Created by asus f751 on 18/11/2016.
 */

public class itemMissing {
    private int Id;
    private String name;
    private int quantity;
    private int size;
    private String metric;

    itemMissing(){

    }
    // Serialization
    //itemMissing obj = new itemMissing();
    // Gson gson = new Gson();
    // String json = gson.toJson(obj);


    // Deserialization
    //  itemMissing obj2 = gson.fromJson(json, itemMissing.class);

    public void setId(int id)
    {
        this.Id = id;
    }

    public int getId()
    {
        return Id;
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
}
