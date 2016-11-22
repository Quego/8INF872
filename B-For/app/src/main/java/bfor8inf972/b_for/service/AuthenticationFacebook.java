package bfor8inf972.b_for.service;

import com.facebook.AccessToken;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.logging.Logger;

import bfor8inf972.b_for.representation.User;
import bfor8inf972.b_for.view.AuthenticationActivity;

public class AuthenticationFacebook {

    private static final Logger LOGGER = Logger.getLogger(AuthenticationFacebook.class.getName());

    private AuthenticationActivity activity;
    private ProfileTracker profileTracker;
    private Profile profile;

    /**
     * Facebook callback which manage the authentication
     */
    public FacebookCallback<LoginResult> facebookCallbackManager = new FacebookCallback<LoginResult>() {

        @Override
        public void onSuccess(LoginResult loginResult) {

            LOGGER.info("User successfully connected");
            profile = Profile.getCurrentProfile();
            if (profile != null)
            activity.launchMainActivity(authenticate());
        }

        @Override
        public void onCancel() {
            LOGGER.info("AuthenticationService canceled");
        }

        @Override
        public void onError(FacebookException e) {
            LOGGER.warning("Error while connected " + e.getMessage());
        }
    };

    public AuthenticationFacebook(AuthenticationActivity activity) {

        FacebookSdk.sdkInitialize(activity.getApplicationContext());
        this.activity = activity;
        initTracker();
    }

    public User authenticate() {

        User user = null;

        if (this.profile == null) {
            profile = Profile.getCurrentProfile();
        }

        if (profile != null) {
            user = new User();
            user.setFirstName(profile.getFirstName());
            user.setLastName(profile.getLastName());
            user.setId(profile.getId());
        }

        return user;
    }

    /**
     * Init the trackers which purpose is to notify the change
     * of the authentication or the facebook profile
     */
    private void initTracker() {

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile) {

                profile = newProfile;
                if (AccessToken.getCurrentAccessToken()!=null)
                activity.launchMainActivity(authenticate());
            }
        };

        profileTracker.startTracking();
    }

    /**
     * Stop tracking
     */
    public void stopTracker() {

        profileTracker.stopTracking();

    }

}
