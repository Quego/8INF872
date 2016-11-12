package bfor8inf972.b_for.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;

import java.util.logging.Logger;

import bfor8inf972.b_for.MainActivity;
import bfor8inf972.b_for.R;
import bfor8inf972.b_for.controller.ApplicationController;
import bfor8inf972.b_for.representation.User;
import bfor8inf972.b_for.service.AuthenticationFacebook;

//TODO JAVADOC
public class AuthenticationActivity extends AppCompatActivity {


    private static final Logger LOGGER = Logger.getLogger(AuthenticationActivity.class.getName());


    private AuthenticationFacebook authenticationFacebook;

    private CallbackManager         callbackManager; /* Manage the callback for facebook authentication. */

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.authenticationFacebook = new AuthenticationFacebook(this);

        setContentView(R.layout.activity_authentication);

        callbackManager = CallbackManager.Factory.create();

        /* Management of the facebook sign in button. */
        LoginButton fbLoginButton = (LoginButton) findViewById(R.id.fb_login_button);
        fbLoginButton.setReadPermissions("user_friends");
        fbLoginButton.registerCallback(callbackManager, this.authenticationFacebook.facebookCallbackManager);
    }

    /**{@inheritDoc}*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        /* Facebook management. */
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    /**{@inheritDoc}*/
    @Override
    protected void onPause() {

        super.onPause();
    }

    /**{@inheritDoc}*/
    protected void onStop() {
        super.onStop();

        this.authenticationFacebook.stopTracker();
    }

    /**
     * The user is authenticated, we can launch the main activity
     *
     * @param user the user
     */
    public void launchMainActivity(User user) {

       // Intent intent = new Intent(this, MainActivity.class);

        if(user != null) {
            //TODO COMPLETE
            ApplicationController.getInstance().getUserController().addUser(user, this);
        }

       // startActivity(intent);
    }
}