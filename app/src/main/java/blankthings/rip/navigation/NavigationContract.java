package blankthings.rip.navigation;

/**
 * Created by iosif on 6/11/17.
 */

public interface NavigationContract {


    interface HasNavigator {
        Navigator getNavigator();
    }


    /**
     * Handles Fragment / Activity Navigation.
     */
    interface Navigator {

        /**
         * @param tag - String identifier.
         */
        void goToFragment(final String tag);


        /**
         * @return true if BackPress was handled.
         */
        boolean goBack();


        /**
         * @param tag - String identifier.
         */
        void goBackToFragment(final String tag);


        void startLoading();


        void stopLoading();


        void toHome();

        /**
         * @param sub - Subreddit identifier.
         */
        void toSingleSub(final String sub);


        void toSearch();


        void toImageVP();


        void toWebView(final String url);

    }


    /**
     * Handles Drawer.
     */
    interface DrawerManager {


        void init(Navigator navigator);


        void openDrawer();


        void closeDrawer();


        void lockDrawer(int gravity);

    }


    /**
     * Handles Toolbar.
     */
    interface ToolbarManager {


        void init(Navigator navigator);


        void setTitle();


        void enableBackButton();


        void enableHomeButton();


        void enableSearch(boolean enable);


        void enableTabs(boolean enable);

    }

}
