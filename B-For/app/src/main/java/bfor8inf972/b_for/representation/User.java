package bfor8inf972.b_for.representation;

import java.io.Serializable;

//TODO JAVADOC
public class User implements Serializable {

    private String FirstName;
    private String LastName;
    private String id;

    public User()
    {
        this.FirstName = "UNKNOWN";
        this.LastName = "UNKNOWN";
        this.id = "-1";

    }

    public User(String FirstName,String LastName,String id)
    {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.id = id;

    }

    public String getFirstName()
    {
        return this.FirstName;
    }



    public String getId() {
        return this.id;
    }



    public String getLastName()
    {
        return this.LastName;
    }



}

