package bfor8inf972.b_for.representation;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.io.Serializable;

public class User implements Serializable {

    private String firstName;
    private String lastName;

    private String email;

    private String id;

    private String phone;

    private int age;

    private String address;

    public User() {
        this.firstName = "UNKNOWN";
        this.lastName = "UNKNOWN";

        this.email = "UNKNOWN";

        this.id = "-1";

        this.phone = "UNKNOWN";

        this.age = -1;

        this.address = "UNKNOWN";
    }

    public User(String firstName,String lastName,String email, String id, String phone, int age, String address)
    {
        this.firstName = firstName;
        this.lastName = lastName;

        this.email = email;

        this.id = id;

        this.phone = phone;

        this.age = age;

        this.address = address;

    }

    public User(JsonElement json){
        Gson gson = new Gson();

        User user = new User();
        try {
        user =  gson.fromJson(json,User.class);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();

        this.email = user.getEmail();

        this.id = user.getId();

        this.phone = user.getPhone();

        this.age = user.getAge();

        this.address = user.getAddress();
    }

    //(Serialization)
    public String toJson(){
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id)
    {
        this.id = id;
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

}

