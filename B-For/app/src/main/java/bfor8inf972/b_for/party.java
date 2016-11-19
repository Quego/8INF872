package bfor8inf972.b_for;

/**
 * Created by asus f751 on 18/11/2016.
 */

public class party {
    private int Id;
    private String title;
    private String creation_time;
    private String update_time;
    private int user_id;
    private int guest_id;
    private int localization_id;
    private int listMissing_id;
    private int sleepingGuest_id;
    private int sleepAvailable_id;
    private String heure_debut;
    private String heure_fin;
    private String nbMax;
    private String nbMin_etoile;

    party(){

    }
    // Serialization
    //party obj = new party();
    // Gson gson = new Gson();
    // String json = gson.toJson(obj);


    // Deserialization
    //  party obj2 = gson.fromJson(json, party.class);

    public void setId(int id)
    {
        this.Id = id;
    }

    public int getId()
    {
        return Id;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }
    public void setCreation_time(String creation_time)
    {
        this.creation_time = creation_time;
    }

    public String getCreation_time()
    {
        return creation_time;
    }

    public void setUpdate_time(String update_time)
    {
        this.update_time = update_time;
    }

    public String getUpdate_time()
    {
        return update_time;
    }

    public void setUser_id(int user_id)
    {
        this.user_id = user_id;
    }

    public int getUser_id()
    {
        return user_id;
    }

    public void setGuest_id(int guest_id)
    {
        this.guest_id = guest_id;
    }

    public int getGuest_id()
    {
        return guest_id;
    }

    public void setLocalization_id(int localization_id)
    {
        this.localization_id = localization_id;
    }

    public int getLocalization_id()
    {
        return localization_id;
    }
    public void setListMissing_id(int listMissing_id)
    {
        this.listMissing_id = listMissing_id;
    }

    public int getListMissing_id()
    {
        return listMissing_id;
    }

    public void setSleepingGuest_id(int sleepingGuest_id)
    {
        this.sleepingGuest_id = sleepingGuest_id;
    }

    public int getSleepingGuest_id()
    {
        return sleepingGuest_id;
    }

    public void setSleepAvailable_id(int sleepAvailable_id)
    {
        this.sleepAvailable_id = sleepAvailable_id;
    }

    public int getSleepAvailable_id()
    {
        return sleepAvailable_id;
    }

    public void setHeure_debut(String heure_debut)
    {
        this.heure_debut = heure_debut;
    }

    public String getHeure_debut()
    {
        return heure_debut;
    }

    public void setHeure_fin(String heure_fin)
    {
        this.heure_fin = heure_fin;
    }

    public String getHeure_fin()
    {
        return heure_fin;
    }

    public void setNbMax(String nbMax)
    {
        this.nbMax = nbMax;
    }

    public String getNbMax()
    {
        return nbMax;
    }

    public void setNbMin_etoile(String nbMin_etoile)
    {
        this.nbMin_etoile = nbMin_etoile;
    }

    public String getNbMin_etoile()
    {
        return nbMin_etoile;
    }
}
