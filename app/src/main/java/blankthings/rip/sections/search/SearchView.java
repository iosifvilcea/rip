package blankthings.rip.sections.search;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import blankthings.rip.R;

import static blankthings.rip.R.id.recyclerView;

/**
 * SearchView
 *   Contains a recyclerview which houses horizontally scrolling sub-reddits.
 *
 * Created by iosifvilcea on 2/11/17.
 */

public class SearchView extends FrameLayout {

    private RecyclerView categoriesRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private SearchAdapter adapter;
    private View emptyView;

    public SearchView(Context context) {
        this(context, null);
    }

    public SearchView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupViews();
    }

    protected void setupViews() {
        final LayoutInflater inflater =  LayoutInflater.from(getContext());
        final View view = inflater.inflate(R.layout.recycler_layout, this, true);
        categoriesRecyclerView = (RecyclerView) view.findViewById(recyclerView);
        emptyView = view.findViewById(R.id.empty_view_layout);

        linearLayoutManager = new LinearLayoutManager(getContext());
        adapter = new SearchAdapter(getContext());

        categoriesRecyclerView.setAdapter(adapter);
        categoriesRecyclerView.setLayoutManager(linearLayoutManager);
    }
}
