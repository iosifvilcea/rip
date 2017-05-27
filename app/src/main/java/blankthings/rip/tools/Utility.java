package blankthings.rip.tools;

import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.util.TypedValue;

/**
 * Created by iosifvilcea on 7/9/16.
 */
public class Utility {


    private Utility() {
    }


    public static float dpToPixels(final Context context, final int dp) {
        final Resources r = context.getResources();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
    }


    /**
     * Gets the state of the Network.
     * @param context
     * @return true if connected.
     */
    public static Boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }


    /**
     * Gets the state of Airplane Mode.
     * @param context
     * @return true if enabled.
     */
    private static boolean isAirplaneModeOn(Context context) {
        return Settings.System.getInt(context.getContentResolver(),
                Settings.Global.AIRPLANE_MODE_ON, 0) != 0;

    }

}
