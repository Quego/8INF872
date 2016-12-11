package bfor8inf972.b_for.Utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.location.places.ui.PlacePicker;

/**
 * Created by Mathias on 11/12/2016.
 */

public class PermissionUtils {

    public static int PLACE_PICKER_REQUEST = 1;
    public static PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

    public static boolean askPermission(Activity activity,String permissionToAsk) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (activity.checkSelfPermission(permissionToAsk)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(activity, new String[]{permissionToAsk}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            return true;
        }
    }
}
