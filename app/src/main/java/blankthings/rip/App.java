package blankthings.rip;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Base App.
 *
 * Created by iosifvilcea on 8/23/16.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
    }

}
