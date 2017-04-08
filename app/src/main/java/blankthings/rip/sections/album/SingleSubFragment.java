package blankthings.rip.sections.album;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import blankthings.rip.R;
import blankthings.rip.api.ApiController;
import blankthings.rip.api.redditmodels.Child;
import blankthings.rip.api.redditmodels.Thing;
import blankthings.rip.api.redditmodels.ThingWrapper;
import blankthings.rip.navigation.Navigator;
import blankthings.rip.sections.base.BaseFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * SingleSubFragment
 *
 * Created by iosifvilcea on 6/18/16.
 */
public class SingleSubFragment extends BaseFragment {

    public static final String SUB_KEY = "sub_key";
    private String subValue = "";
    private String afterValue = "";

    public static SingleSubFragment newInstance(final String subreddit) {
        SingleSubFragment fragment = new SingleSubFragment();
        Bundle bundle = new Bundle();
        bundle.putString(SUB_KEY, subreddit);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static final String TAG = SingleSubFragment.class.getSimpleName();
    protected DynamicCardView dynamicCardView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        subValue = getArguments().getString(SUB_KEY);
        fetchSub();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dynamicCardView = new DynamicCardView(getContext());
        return dynamicCardView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dynamicCardView.setSwipeContainerListener(onRefreshListener);
        dynamicCardView.setOnViewListener(onViewListener);
    }


    @Override
    public void onResume() {
        super.onResume();
        toolbarManager.enableToolbarScroll(false);  // TODO - Why are we setting this as false then true?
        toolbarManager.showTabs(false);

        toolbarManager.enableToolbarScroll(true);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);

        final MenuItem moreItem = menu.findItem(R.id.moreItem);
        final SubMenu subMenu = moreItem.getSubMenu();
        if (subMenu != null) {
            for (int i=0; i < subMenu.size(); i++) {
                final MenuItem item = subMenu.getItem(i);
                item.setOnMenuItemClickListener(menuItemClickListener);
            }
        }

    }


    private MenuItem.OnMenuItemClickListener menuItemClickListener = new MenuItem.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            if (item.getItemId() == R.id.cardMenuItem) {

                Log.e(TAG, "Card Menu clicked.");
                // TODO - SWITCH TO CARD MENU

            } else if (item.getItemId() == R.id.smartViewMenuItem) {

                Log.e(TAG, "Smart Menu clicked.");
                // TODO - SWITCH TO SMART MENU

            } else if (item.getItemId() == R.id.fullViewMenuItem) {

                Log.e(TAG, "Full Menu clicked.");
                // TODO - SWITCH TO FULL MENU

            }

            return true; // True if no other callbacks necessary.
        }
    };


    // TODO - REMOVE OR FIX PLS
    // TODO - REMOVE OR FIX PLS
    // TODO - REMOVE OR FIX PLS
//    private void enable() {
//        MainActivity mainActivity = (MainActivity) getActivity();
//        Toolbar toolbar = mainActivity.getToolbar();
//
//        final AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
//        if (true) {
//            params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
//                    | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
//        } else {
//            params.setScrollFlags(0);
//        }
//
//        toolbar.setLayoutParams(params);
//    }


    private void fetchSub() {
        Navigator.INSTANCE.startLoading();
        ApiController.getInstance().listingRequest(
                "foodporn",
                ApiController.SortType.HOT,
                25,
                null,
                null,
                subCallback);
    }


    private void fetchSubAfter() {
        Navigator.INSTANCE.startLoading();
        ApiController.getInstance().listingRequest(
                "foodporn",
                ApiController.SortType.HOT,
                25,
                null,
                afterValue,
                subCallback);
    }


    /**
     * Subreddit Callback
     *
     *   onSuccess:
     *    - Wrap response into ThingWrapper
     *    - filter urls
     *    - add them to the Dynamic Card View.
     *
     *   onFailure:
     *    - Show empty screen w/ button navigating to Categories.
     */
    private Callback<Thing> subCallback = new Callback<Thing>() {
        @Override
        public void onResponse(Call<Thing> call, Response<Thing> response) {
            navigator.stopLoading();
            if (response.isSuccessful() && dynamicCardView != null) {
                dynamicCardView.showEmptyList(false);

                final ThingWrapper thingWrap = new ThingWrapper(response.body());
                final ArrayList<Child> filteredCards = filterCardUrls(thingWrap);

                afterValue = thingWrap.getAfter();
                if (dynamicCardView != null) {
                    dynamicCardView.addCardList(filteredCards);
                }
            }
        }

        @Override
        public void onFailure(Call<Thing> call, Throwable t) {
            navigator.stopLoading();
            dynamicCardView.showEmptyList(true);
            Log.e(TAG, "aww no. Networked failed.");
            Log.e(TAG, t.getMessage());
        }
    };


    /**
     * Replace urls coming from reddituploads that have the html character code &amp; in the url string
     *   where there should be a &
     *
     * @param thingWrapper Wrapper class
     * @return Filtered list of Urls.
     */
    private ArrayList<Child> filterCardUrls(final ThingWrapper thingWrapper) {
        final String key = "reddituploads";
        final ArrayList<Child> filteredCards = new ArrayList<>();

        for (final Child child : thingWrapper.getChildren()) {
            final String url = child.getData().getUrl();
            if (url.contains(key)) {
                String fixedUrl = url.replaceAll("(&amp;)", "&");
                child.getData().setUrl(fixedUrl);
            }
            filteredCards.add(child);
        }

        return filteredCards;
    }


    /**
     * Endless Recycler Scroll
     *  When last item is scrolled to, fetch next subreddit items.
     */
    private final OnViewListener onViewListener = new OnViewListener() {
        @Override
        public void onEndlessScrolled() {
            fetchSubAfter();
        }
    };


    /**
     * Swipe to Refresh layout.
     * TODO - Refresh start view
     */
    private final SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            dynamicCardView.setSwipeRefreshEnabled(false);
            fetchSub();
        }
    };


    /**
     *  OnViewListener
     *    interface for miscellaneous listeners.
     */
    protected interface OnViewListener {
        void onEndlessScrolled();
    }
}
