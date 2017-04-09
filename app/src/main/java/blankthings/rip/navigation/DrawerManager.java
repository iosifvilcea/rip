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
import blankthings.rip.navigation.section.ExpandableSectionAdapter;
import blankthings.rip.navigation.section.ParentSubSection;
import blankthings.rip.navigation.section.Section;
import blankthings.rip.navigation.section.SectionView;

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


    private final View.OnClickListener doNothingOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /** Absorbs clicks to prevent being able to click through the view. */
        }
    };



    private final View.OnClickListener onHomeClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            openDrawer();
        }
    };


    private final ExpandableSectionAdapter.OnExpandableSectionListener expandableSectionListener =
            new ExpandableSectionAdapter.OnExpandableSectionListener() {
                @Override
                public void onItemClicked(final ParentSubSection section) {
                    if (!section.hasChildren()) {
                        goToSelectedNavItem(section);
                    }
                }

                @Override
                public void onItemLongClicked(int parentPosition) {
                    // TODO - Impl.
                    Log.e(TAG, "OnItemLongClicked");
                }

                @Override
                public void onItemSwiped(int parentPosition, int childPosition) {
                    // TODO - Impl.
                    Log.e(TAG, "OnItemSwiped");
                }
    };


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


    private SectionView generateMainSection() {
        final MainActivity mainActivity = mainAct.get();
        if (mainActivity == null) {
            Log.e(TAG, "MainActivity is null. Cannot generate Main Section.");
            return null;
        }

        final SectionView sectionView = new SectionView(mainActivity);
        sectionView.setSections(generateMockSection());
        sectionView.setOnExpandableSectionListener(expandableSectionListener);
        return sectionView;
    }


    // TODO - Remove "mock".
    private List<ParentSubSection> generateMockSection() {
        return new ArrayList<ParentSubSection>() {{

            add(new ParentSubSection(R.id.home, "Home"));
            add(new ParentSubSection(R.id.explore, "Explore"));
            add(new ParentSubSection(R.id.search, "Search"));
            add(new ParentSubSection(R.id.settings, "Settings"));

            add(new ParentSubSection("All"));
            add(new ParentSubSection("AbandonedPorn"));
            add(new ParentSubSection("Pictures"));
            add(new ParentSubSection("MaleLivingSpace"));

            Section kid1 = new Section("Steak");
            Section kid2 = new Section("Bbq");
            Section kid3 = new Section("Burgers");

            List<Section> children = Arrays.asList(kid1, kid2, kid3);
            ParentSubSection parentSubSection = new ParentSubSection("Meat", children);
            add(parentSubSection);

            add(new ParentSubSection("Pictures"));
            add(new ParentSubSection("Pictures"));
        }};
    }


    /**
     * @param item - Navigation Section item
     */
    private void goToSelectedNavItem(final Section item) {
        closeDrawer();
        item.setSelected(true);
        toolbarManager.setTitle(item.getDisplayName());

        if (item.getId() instanceof String) {
            navigator.toSingleSub((String) item.getId());

        } else {
            navigator.toFragmentWithId((Integer) item.getId());
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
