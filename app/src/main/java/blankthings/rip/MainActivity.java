package blankthings.rip;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import blankthings.rip.navigation.DrawerManager;
import blankthings.rip.navigation.Navigator;
import blankthings.rip.navigation.ToolbarManager;
import blankthings.rip.sections.album.AlbumFragment;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = MainActivity.class.getSimpleName();

    private final Navigator navigator = Navigator.INSTANCE;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigator.initializeNavigator(this);
        setupNavigationManagers();
        setupInitialFragment();
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
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }


    public void setupInitialFragment() {
        final AlbumFragment albumFragment = AlbumFragment.newInstance("all");
        navigator.addFragment(albumFragment, "home");
    }


    @Override
    public void onBackPressed() {
        final boolean wasBackPressHandled = navigator.back();
        if (!wasBackPressHandled) {
            super.onBackPressed();
        }
    }
}
