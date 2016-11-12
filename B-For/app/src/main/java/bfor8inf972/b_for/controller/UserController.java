package bfor8inf972.b_for.controller;

import android.content.Context;

import java.util.logging.Logger;

import bfor8inf972.b_for.representation.User;

/**
 * The user controller
 * Make the link between the model and the view
 * Manages all the events that can affect a user
 * Calls the corresponding DAO when the data must be persist or retrieve
 */
public class UserController {

    private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());

    private User user;

    UserController()
    {
        this.user = new User();
    }

    public void addUser(User user, Context context/*Other stuff*/)
    {
        //TODO SQL STUFF
    }

    private void retrieveUser(Context context, String authenticationId/*Other stuff*/) {
        //TODO SQL STUFF
    }

    public void updateUser(Context context, User user/*Other stuff*/) {
        //TODO SQL STUFF
    }


    public User getUser()
    {
        return this.user;
    }


}
