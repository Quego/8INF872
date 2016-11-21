package bfor8inf972.b_for.controller;


import java.util.logging.Logger;

public class ApplicationController {

    private static final Logger LOGGER = Logger.getLogger(ApplicationController.class.getName());
    private static ApplicationController instance = null;
    private UserController userController;

    private ApplicationController() {
    }

    public static ApplicationController getInstance() {
        if (instance == null) {
            instance = new ApplicationController();
            instance.initialize();
        }

        return instance;
    }

    private void initialize() {
        //permissionController needs to be instanciated before other controllers

        userController = new UserController();

    }

    public UserController getUserController() {
        return this.userController;
    }

}

