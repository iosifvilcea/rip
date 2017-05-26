package blankthings.rip.sections.album;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;

import blankthings.rip.R;
import blankthings.rip.api.redditmodels.Child;
import blankthings.rip.views.EndlessRecyclerScrollListener;
import blankthings.rip.views.OnItemClickListener;

/**
 * Contains three view types that resizes a recycler item's image.
 * SwipeRefresh
 *
 * Created by iosifvilcea on 10/20/16.
 */

public class AlbumView extends FrameLayout {

    public final static String TAG = AlbumView.class.getSimpleName();

    private SwipeRefreshLayout swipeContainer;
    private RecyclerView recyclerView;
    private AlbumAdapter adapter;
    private OnViewActionListener onViewActionListener;


    /**
     *  Notifies observer of view actions.
     */
    public interface OnViewActionListener {
        void onEndlessScrolled();
        void onItemSwiped();
        void onRefresh();
    }


    public AlbumView(Context context) {
        this(context, null);
    }


    // TODO: 4/8/17 GET VIEWTYPE FROM XML.
    public AlbumView(final Context context, final AlbumAdapter.ViewType viewType) {
        super(context);
        setupViews(viewType);
    }


    protected void setupViews(final AlbumAdapter.ViewType viewType) {
        adapter = new AlbumAdapter(getContext(), viewType);

        final View view = inflate(getContext(), R.layout.recycler_refresh_swipe_layout, this);
        recyclerView = (RecyclerView) view.findViewById(R.id.refreshRecyclerView);

        final LinearLayoutManager layoutManager = configureLayoutManager(viewType);
        final EndlessRecyclerScrollListener endlessRecyclerScrollListener =
                new EndlessRecyclerScrollListener(layoutManager) {
                    @Override
                    public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                        if (onViewActionListener != null) {
                            onViewActionListener.onEndlessScrolled();
                        }
                    }
        };

        recyclerView.addOnScrollListener(endlessRecyclerScrollListener);
        recyclerView.setAdapter(adapter);

        final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchCb);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(onRefreshListener);
        setSwipeRefreshEnabled(false);
    }


    private LinearLayoutManager configureLayoutManager(AlbumAdapter.ViewType viewType) {
        final LinearLayoutManager layoutManager;
        if (viewType != null && viewType == AlbumAdapter.ViewType.GRID_VIEW) {
            final int itemPerRow = 3;
            layoutManager = new GridLayoutManager(getContext(), itemPerRow);
        } else {
            layoutManager = new LinearLayoutManager(getContext());
        }

        recyclerView.setLayoutManager(layoutManager);
        return layoutManager;
    }


    public void addCardList(final ArrayList<Child> children) {
        if (adapter != null) {
            adapter.addItems(children);
        }
    }


    public void setOnViewActionListener(final OnViewActionListener onViewActionListener) {
        this.onViewActionListener = onViewActionListener;
    }


    public void setSwipeRefreshEnabled(final boolean enabled) {
        swipeContainer.setRefreshing(enabled);
    }


    public void setAlbumViewType(final AlbumAdapter.ViewType viewType) {
        configureLayoutManager(viewType);
        adapter.setAlbumViewType(viewType);
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        adapter.setOnItemClickListener(listener);
    }


    public Child getItem(int pos) {
        return adapter.getItem(pos);
    }


    private final ItemTouchHelper.SimpleCallback itemTouchCb =
            new ItemTouchHelper.SimpleCallback(ItemTouchHelper.RIGHT, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                              RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            if (onViewActionListener != null) {
                onViewActionListener.onItemSwiped();
            }
        }
    };


    /**
     * Callback when the user pulls down to refresh.
     */
    private SwipeRefreshLayout.OnRefreshListener onRefreshListener =
            new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            if (onViewActionListener != null) {
                onViewActionListener.onRefresh();
            }
        }
    };
}
