package bfor8inf972.b_for.representation;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.HashSet;

public class GroupUser {
    private int id;
    private HashSet<User> users;
    private User driver;

    GroupUser() {
        this.id = -1;
        this.users = new HashSet<User>();
        this.driver = new User();
    }

    public GroupUser(int id, HashSet<User> users, User driver) {
        this.id = id;
        this.users = users;
        this.driver = driver;
    }

    public GroupUser(JsonElement json) {
        Gson gson = new Gson();
        GroupUser groupUser = new GroupUser();
        try {
            groupUser = gson.fromJson(json, GroupUser.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.id = groupUser.getId();
        this.users = groupUser.getUsers();
        this.driver = groupUser.getDriver();
    }

    public String toJson() {
        //TODO Test on object collections
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HashSet<User> getUsers() {
        return this.users;
    }

    public void setUsers(HashSet<User> users) {
        this.users = users;
    }

    public User getDriver() {
        return this.driver;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }
}
