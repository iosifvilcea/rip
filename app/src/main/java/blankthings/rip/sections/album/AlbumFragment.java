package blankthings.rip.sections.album;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
import blankthings.rip.sections.base.BaseFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * AlbumFragment
 *
 * Created by iosifvilcea on 6/18/16.
 */
public class AlbumFragment extends BaseFragment {

    public static final String TAG = AlbumFragment.class.getSimpleName();
    public static final String SUB_KEY = "sub_key";

    private String subValue = "";
    private String afterValue = "";

    protected AlbumView albumView;


    public static AlbumFragment newInstance(final String subreddit) {
        AlbumFragment fragment = new AlbumFragment();
        Bundle bundle = new Bundle();
        bundle.putString(SUB_KEY, subreddit);
        fragment.setArguments(bundle);
        return fragment;
    }


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
        albumView = new AlbumView(getContext());
        return albumView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        albumView.setOnViewActionListener(actionListener);
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
                albumView.setAlbumViewType(AlbumAdapter.ViewType.CARD_VIEW);

            } else if (item.getItemId() == R.id.fullViewMenuItem) {
                albumView.setAlbumViewType(AlbumAdapter.ViewType.FULL_VIEW);

            } else if (item.getItemId() == R.id.gridViewMenuItem) {
                albumView.setAlbumViewType(AlbumAdapter.ViewType.GRID_VIEW);

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
        navigator.startLoading();
        ApiController.getInstance().listingRequest(
                "foodporn",
                ApiController.SortType.HOT,
                25,
                null,
                null,
                subCallback);
    }


    private void fetchSubAfter() {
        navigator.startLoading();
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
            if (response.isSuccessful() && albumView != null) {

                final ThingWrapper thingWrap = new ThingWrapper(response.body());
                final ArrayList<Child> filteredCards = filterCardUrls(thingWrap);

                afterValue = thingWrap.getAfter();
                if (albumView != null) {
                    albumView.addCardList(filteredCards);
                }
            }
        }

        @Override
        public void onFailure(Call<Thing> call, Throwable t) {
            navigator.stopLoading();
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
    private final DynamicCardView.OnViewActionListener actionListener =
     */
        AlbumView.OnViewActionListener actionListener =
            new AlbumView.OnViewActionListener() {

        @Override
        public void onEndlessScrolled() {
            fetchSubAfter();
        }

        @Override
        public void onItemSwiped() {
            // TODO: 4/8/17 Implement.
        }

        @Override
        public void onRefresh() {
            albumView.setSwipeRefreshEnabled(false);
            fetchSub();
        }
    };
}
