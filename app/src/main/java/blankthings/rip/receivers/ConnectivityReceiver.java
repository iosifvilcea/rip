package blankthings.rip.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.widget.Toast;

/**
 * Created by iosif on 5/27/17.
 */

public class ConnectivityReceiver extends BroadcastReceiver {

    public static final String AIRPLANE_MODE = "android.intent.action.AIRPLANE_MODE";

    @Override
    public void onReceive(final Context context, final Intent intent) {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                // TODO: 5/27/17 - Handle UI properly.
                final boolean isAirplaneModeOn = intent.getBooleanExtra("state", false);
                final String resultText = (isAirplaneModeOn) ? "on" : "off";
                Toast.makeText(context, String.format("Airplane mode has been turned %s.", resultText), Toast.LENGTH_SHORT).show();

            }
        });
    }

}
