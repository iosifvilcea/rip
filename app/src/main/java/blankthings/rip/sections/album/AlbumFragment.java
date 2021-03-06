package blankthings.rip.sections.album;

import android.content.IntentFilter;
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
import android.widget.Toast;

import java.util.ArrayList;

import blankthings.rip.R;
import blankthings.rip.api.ApiController;
import blankthings.rip.api.redditmodels.Child;
import blankthings.rip.api.redditmodels.Thing;
import blankthings.rip.api.redditmodels.ThingWrapper;
import blankthings.rip.receivers.ConnectivityReceiver;
import blankthings.rip.sections.album.detail.DetailFragment;
import blankthings.rip.sections.base.BaseFragment;
import blankthings.rip.views.OnItemClickListener;
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

    protected ConnectivityReceiver receiver;


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
        receiver = new ConnectivityReceiver();
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
        albumView.setOnItemClickListener(onItemClick);
    }


    @Override
    public void onResume() {
        super.onResume();
        toolbarManager.setTitle(subValue);
        toolbarManager.enableToolbarScroll(true);
        toolbarManager.showTabs(false);
        fetchSub();

        final IntentFilter intentFilter = new IntentFilter(ConnectivityReceiver.AIRPLANE_MODE);
        getContext().registerReceiver(receiver, intentFilter);
    }


    @Override
    public void onPause() {
        getContext().unregisterReceiver(receiver);
        super.onPause();
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


    private void fetchSub() {
        navigator.startLoading();
        ApiController.getInstance().listingRequest(
                subValue,
                ApiController.SortType.HOT,
                25,
                null,
                null,
                subCallback);
    }


    private void fetchSubAfter() {
        navigator.startLoading();
        ApiController.getInstance().listingRequest(
                afterValue,
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
                final ArrayList<Child> filteredCards = filterUrls(thingWrap);

                afterValue = thingWrap.getAfter();
                if (albumView != null) {
                    albumView.addCardList(filteredCards);
                }
            }
        }

        @Override
        public void onFailure(Call<Thing> call, Throwable t) {
            navigator.stopLoading();
            Toast.makeText(getContext(), R.string.error_network_failed, Toast.LENGTH_SHORT).show();
            if (t != null) {
                Log.e(TAG, t.getMessage());
            }
        }
    };


    /**
     * Replace urls coming from reddituploads that have the html character code &amp; in the url string
     *   where there should be a &
     *
     * @param thingWrapper Wrapper class
     * @return Filtered list of Urls.
     */
    private ArrayList<Child> filterUrls(final ThingWrapper thingWrapper) {
        final ArrayList<Child> filteredCards = new ArrayList<>();

        for (final Child child : thingWrapper.getChildren()) {
            if (child.getData() == null || child.getData().getUrl() == null) {
                continue;
            }

            if (child.getData().getUrl().contains("/comments/")) {
                continue;
            }

            if (child.getData().getUrl().contains("/r/")) {
                continue;
            }

            fixAmpersandUrls(child);
            appendJpgToImgurUrls(child);
            filteredCards.add(child);
        }

        return filteredCards;
    }


    private void fixAmpersandUrls(final Child child) {
        final String url = child.getData().getUrl();
        if (url.contains("reddituploads")) {
            String fixedUrl = url.replaceAll("(&amp;)", "&");
            child.getData().setUrl(fixedUrl);
        }
    }


    private void appendJpgToImgurUrls(final Child child) {
        String url = child.getData().getUrl();
        if (url.contains("imgur.com") && !url.contains(".jpg")) {
            url = String.format("%s%s", child.getData().getUrl(),".jpg");
            child.getData().setUrl(url);
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


    /**
     * Endless Recycler Scroll
     *  When last item is scrolled to, fetch next subreddit items.
     */
    private final AlbumView.OnViewActionListener actionListener =
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


    private final OnItemClickListener onItemClick = new OnItemClickListener() {
        @Override
        public void onItemClick(View itemView, int position) {
            final Child child = albumView.getItem(position);
            final Bundle bundle = new Bundle();
            bundle.putParcelable(DetailFragment.DETAIL_KEY, child);
            navigator.toDetail(bundle);
        }
    };
}
