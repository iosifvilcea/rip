package blankthings.rip.sections.album;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;

import blankthings.rip.R;
import blankthings.rip.api.redditmodels.Child;

/**
 * Created by iosifvilcea on 10/20/16.
 */

public class DynamicCardView extends FrameLayout {

    public final static String TAG = DynamicCardView.class.getSimpleName();

    private SwipeRefreshLayout swipeContainer;
    private DynamicCardAdapter adapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private SingleSubFragment.OnViewListener onViewListener;
    private View emptyView;


    public DynamicCardView(Context context) {
        this(context, null);
    }


    public DynamicCardView(final Context context, final DynamicCardAdapter.ViewType viewType) {
        super(context);
        setupViews(viewType);
    }


    //
    protected void setupViews(final DynamicCardAdapter.ViewType viewType) {
        final LayoutInflater inflater =  LayoutInflater.from(getContext());
        final View view = inflater.inflate(R.layout.recycler_refresh_swipe_layout, this, true);

        emptyView = view.findViewById(R.id.empty_view_layout);

        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView = (RecyclerView) view.findViewById(R.id.refreshRecyclerView);
        if (viewType == null) {
            adapter = new DynamicCardAdapter(getContext());
        } else {
            adapter = new DynamicCardAdapter(getContext(), viewType.value);
        }

        final EndlessRecyclerScrollListener endlessRecyclerScrollListener =
                new EndlessRecyclerScrollListener(linearLayoutManager) {
                    @Override
                    public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                        if (onViewListener != null) {
                            onViewListener.onEndlessScrolled();
                        }
                    }
                };

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addOnScrollListener(endlessRecyclerScrollListener);

        final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchCb);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);
        setSwipeRefreshEnabled(false);
    }


    //
    public void addCardList(final ArrayList<Child> children) {
        if (adapter != null) {
            adapter.addItems(children);
        }
    }


    //
    public void setOnItemClickListener(final OnItemClickListener onItemClickListener) {
        adapter.setOnItemClickListener(onItemClickListener);
    }


    //
    public void setSwipeContainerListener(final SwipeRefreshLayout.OnRefreshListener onRefresh) {
        swipeContainer.setVisibility(VISIBLE);
        swipeContainer.setOnRefreshListener(onRefresh);
    }


    //
    public void setOnViewListener(final SingleSubFragment.OnViewListener onViewListener) {
        setSwipeRefreshEnabled(true);
        this.onViewListener = onViewListener;
    }


    //
    public void setSwipeRefreshEnabled(final boolean enabled) {
        swipeContainer.setRefreshing(enabled);
    }


    //
    public void showEmptyList(final boolean show) {
        if (show) {
            emptyView.setVisibility(VISIBLE);
            swipeContainer.setVisibility(GONE);
        } else {
            emptyView.setVisibility(GONE);
            swipeContainer.setVisibility(VISIBLE);
        }
    }


    //
    private ItemTouchHelper.SimpleCallback itemTouchCb =
            new ItemTouchHelper.SimpleCallback(ItemTouchHelper.LEFT, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                              RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

            // TODO - Handle Swipe Left.

        }
    };
}
