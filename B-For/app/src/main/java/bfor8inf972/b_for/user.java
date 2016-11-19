package bfor8inf972.b_for;

/**
 * Created by asus f751 on 18/11/2016.
 */

public class user {
    private int Id;
    private String name;
    private String email;
    private String phone;
    private int age;
    private String address;
    private int facebook_Id;

    user(){

    }

    // Serialization
    //user obj = new user();
    // Gson gson = new Gson();
    // String json = gson.toJson(obj);


    // Deserialization
    //  user obj2 = gson.fromJson(json, user.class);

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

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getEmail()
    {
        return email;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getPhone()
    {
        return phone;
    }
    public void setAge(int age)
    {
        this.age = age;
    }

    public int getAge()
    {
        return age;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getAddress()
    {
        return address;
    }

    public void setFacebook_Id(int facebook_id)
    {
        this.facebook_Id = facebook_id;
    }

    public int getFacebook_Id()
    {
        return facebook_Id;
    }
}

