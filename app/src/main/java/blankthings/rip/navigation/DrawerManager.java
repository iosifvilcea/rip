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
import java.util.Arrays;
import java.util.List;

import blankthings.rip.MainActivity;
import blankthings.rip.R;
import blankthings.rip.navigation.section.ParentSubSection;
import blankthings.rip.navigation.section.SectionView;
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
        sectionView.setSections(generateMockSection());
        sectionView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                final ParentSubSection item = sectionView.getSection(position);
                goToSelectedNavItem(item);
            }
        });
        return sectionView;
    }


    // TODO - Remove "mock".
    private List<ParentSubSection> generateMockSection() {
        return new ArrayList<ParentSubSection>() {{
            add(new ParentSubSection("All"));
            add(new ParentSubSection("AbandonedPorn"));
            add(new ParentSubSection("Pictures"));
            add(new ParentSubSection("MaleLivingSpace"));

            ParentSubSection.SubSection kid1 = new ParentSubSection.SubSection("Steak", "steak");
            ParentSubSection.SubSection kid2 = new ParentSubSection.SubSection("BBQ", "bbq");
            ParentSubSection.SubSection kid3 = new ParentSubSection.SubSection("Burgers", "burgers");
            List<ParentSubSection.SubSection> children = Arrays.asList(kid1, kid2, kid3);
            ParentSubSection parentSubSection = new ParentSubSection("Meat", children);
            add(parentSubSection);

            add(new ParentSubSection("Pictures"));
            add(new ParentSubSection("Pictures"));
            add(new ParentSubSection("Pictures"));
            add(new ParentSubSection("Pictures"));
        }};
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
    private void goToSelectedNavItem(final ParentSubSection item) {
        closeDrawer();
        item.setChecked(true);
        toolbarManager.setTitle(item.getDisplayName());
        navigator.toSingleSub(item.getSubreddit());
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
