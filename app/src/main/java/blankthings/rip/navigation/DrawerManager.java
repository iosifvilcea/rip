package blankthings.rip.navigation;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import blankthings.rip.MainActivity;
import blankthings.rip.R;
import blankthings.rip.sections.album.OnItemClickListener;

/**
 * Manages drawer items and header.
 *
 * Created by iosifvilcea on 1/20/17.
 */

public enum DrawerManager {

    INSTANCE;

    private static final String TAG = DrawerManager.class.getSimpleName();
    private static ActionBarDrawerToggle drawerToggle;
    private static DrawerLayout drawerLayout;
    private static WeakReference<MainActivity> mainAct;
    private static Navigator navigator = Navigator.INSTANCE;
    private static ToolbarManager toolbarManager = ToolbarManager.INSTANCE;


    /**
     * @param mainActivity - calling activity.
     */
    public void initializeDrawerManager(final MainActivity mainActivity, final Toolbar tb) {
        if (mainActivity == null || tb == null) {
            Log.e(TAG, "Initialization Error. Parameters cannot be null.");
            return;
        }

        mainAct = new WeakReference<>(mainActivity);

        setupDrawerToggle(tb);
        setupLeftDrawerViews();
    }


    private void setupDrawerToggle(final Toolbar toolbar) {
        final MainActivity mainActivity = mainAct.get();
        if (mainActivity == null) {
            Log.e(TAG, "Error setting up Drawer Toggle");
            return;
        }

        drawerLayout = (DrawerLayout) mainActivity.findViewById(R.id.drawerLayout);
        drawerToggle = new ActionBarDrawerToggle(mainActivity, drawerLayout, toolbar,
                R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.setToolbarNavigationClickListener(onHomeClicked);
        drawerToggle.syncState();
    }


    private void setupLeftDrawerViews() {
        final MainActivity mainActivity = mainAct.get();
        if (mainActivity == null) {
            Log.e(TAG, "Error setting up Left Drawer Views.");
            return;
        }

        final FrameLayout frame = (FrameLayout) mainActivity.findViewById(R.id.drawerContent);
        frame.setOnClickListener(doNothingOnClick);

        final FrameLayout content = (FrameLayout) frame.findViewById(R.id.drawerFrame);
        final SectionView mainSection = generateMainSection();
        content.addView(mainSection);
    }

    private final View.OnClickListener doNothingOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /** Absorbs clicks to prevent being able to click through the view. */
        }
    };


    private SectionView generateMainSection() {
        final MainActivity mainActivity = mainAct.get();
        if (mainActivity == null) {
            Log.e(TAG, "MainActivity is null. Cannot generate Main Section.");
            return null;
        }

        final SectionView sectionView = new SectionView(mainActivity);
        sectionView.setSections(new ArrayList<SectionItem>() {{
            add(new SectionItem(0, "Home", android.R.drawable.ic_delete, false));
            add(new SectionItem(1, "AbandonedPorn", android.R.drawable.ic_dialog_alert, false));
            add(new SectionItem(2, "Pictures", android.R.drawable.ic_input_add, false));
            add(new SectionItem(3, "MaleLivingSpace", android.R.drawable.ic_menu_agenda, false));
            add(new SectionItem(4, "Food", android.R.drawable.ic_media_next, false));
            add(new SectionItem(5, "Food", android.R.drawable.ic_media_next, false));
            add(new SectionItem(6, "Food", android.R.drawable.ic_media_next, false));
            add(new SectionItem(7, "Food", android.R.drawable.ic_media_next, false));
            add(new SectionItem(8, "Food", android.R.drawable.ic_media_next, false));
            add(new SectionItem(9, "Food", android.R.drawable.ic_media_next, false));
            add(new SectionItem(10, "Settings", android.R.drawable.ic_secure, true));
        }});

        sectionView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                final SectionItem item = sectionView.getSection(position);
                goToSelectedNavItem(item);
            }
        });
        return sectionView;
    }


    private final View.OnClickListener onHomeClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            openDrawer();
        }
    };


    /**
     * @param item - Navigation Section item
     */
    private void goToSelectedNavItem(final SectionItem item) {
        closeDrawer();
        item.setChecked(true);
        toolbarManager.setTitle(item.getTitle());

        switch (item.getId()) {
            case 0:
                navigator.toHome();
                break;
            case 1:
                navigator.toSingleSub("roomporn");
                break;
            case 2:
                navigator.toSingleSub("AmateurRoomPorn");
                break;
            case 3:
                navigator.toSingleSub("Food");
                break;
            case 4:
                navigator.toSingleSub("GifRecipes");
                break;
            case 5:
                navigator.toSettings();
                break;
            default:
                navigator.toHome();
                break;
        }
    }


    public ActionBarDrawerToggle getDrawerToggle() {
        return drawerToggle;
    }


    public void openDrawer() {
        drawerLayout.openDrawer(GravityCompat.START);
    }


    public void closeDrawer() {
        drawerLayout.closeDrawer(GravityCompat.START);
    }
}
