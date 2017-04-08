package blankthings.rip.navigation;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import java.lang.ref.WeakReference;

import blankthings.rip.MainActivity;
import blankthings.rip.R;

/**
 * ToolbarManager
 *   Manages Toolbar and TabLayout.
 *
 * Created by iosifvilcea on 2/1/17.
 */

public enum ToolbarManager {

    INSTANCE;

    private static final String TAG = ToolbarManager.class.getSimpleName();

    private static AppBarLayout appBarLayout;
    private static Toolbar toolbar;
    private static TabLayout tabLayout;
    private static WeakReference<MainActivity> mainAct;
    private static Navigator navigator = Navigator.INSTANCE;

    public void initializeToolbarManager(final MainActivity mainActivity, final Toolbar bar) {
        if (mainActivity == null || bar == null) {
            Log.e(TAG, "Initialization Error. Parameters cannot be null.");
            return;
        }

        tabLayout = (TabLayout) mainActivity.findViewById(R.id.sliding_tabs);
        appBarLayout = (AppBarLayout) mainActivity.findViewById(R.id.appBar);
        toolbar = bar;
        mainAct = new WeakReference<>(mainActivity);
    }


    /**
     * @param queryListener - query callback
     */
    public void setSearchView(final SearchView.OnQueryTextListener queryListener) {
        // TODO - FIX THIS.
//        final Menu toolbarMenu = toolbar.getMenu();
//        final MenuItem searchItem = toolbarMenu.findItem(R.id.searchItem);
//        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
//        searchView.setOnQueryTextListener(queryListener);
    }


    /**
     * Allows / Disallows toolbar to scroll.
     * @param enable - true or false.
     */
    public void enableToolbarScroll(final boolean enable) {
        appBarLayout.setExpanded(true);
        final AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
        if (enable) {
            params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
                    | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
                    | AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP);
        } else {
            params.setScrollFlags(0);
        }

        toolbar.setLayoutParams(params);
    }

    public TabLayout getTabLayout() {
        return tabLayout;
    }


    /**
     * Shows / Hides TabLayout.
     * @param show - true or false
     */
    public void showTabs(final boolean show) {
        final int visibility = (show) ? View.VISIBLE : View.GONE;
        tabLayout.setVisibility(visibility);
    }


    /**
     * @param title
     */
    public void setTitle(final CharSequence title) {
        final MainActivity mainActivity = mainAct.get();
        if (mainActivity != null && !TextUtils.isEmpty(title)) {
            mainActivity.setTitle(title);
        }
    }

}
