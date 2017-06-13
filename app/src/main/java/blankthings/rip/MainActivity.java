package blankthings.rip;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import blankthings.rip.navigation.DrawerManager;
import blankthings.rip.navigation.NavigationContract;
import blankthings.rip.navigation.NavigatorImpl;
import blankthings.rip.navigation.ToolbarManager;

public class MainActivity extends AppCompatActivity implements NavigationContract.HasNavigator {

    private final static String TAG = MainActivity.class.getSimpleName();

    private NavigationContract.Navigator navigator;
    private ActionBarDrawerToggle drawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigator = new NavigatorImpl(this);
        setupNavigationManagers();

        navigator.toHome();
//        setupInitialFragment();
    }


    /**
     *  Initializes DrawerManager which handles interactions between
     *    - Toolbar
     *    - DrawerLayout
     *    - NavigationView
     *    - DrawerToggle
     */
    private void setupNavigationManagers() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ToolbarManager.INSTANCE.initializeToolbarManager(this, toolbar);
        DrawerManager.INSTANCE.initializeDrawerManager(this, toolbar);
        drawerToggle = DrawerManager.INSTANCE.getDrawerToggle();
    }


    @Override
    public NavigationContract.Navigator getNavigator() {
        return navigator;
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public void onBackPressed() {
        final boolean wasBackPressHandled = navigator.goBack();
        if (!wasBackPressHandled) {
            super.onBackPressed();
        }
    }
}
