package blankthings.rip.navigation.section;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import blankthings.rip.R;

/**
 * Created by iosifvilcea on 10/18/16.
 */

public class SectionView extends FrameLayout {

    private static final String TAG = SectionView.class.getSimpleName();

    private ExpandableSectionAdapter adapter;


    public SectionView(Context context) {
        super(context);
        inflate(context, R.layout.recycler_layout, this);
        setupViews();
    }


    protected void setupViews() {
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        adapter = new ExpandableSectionAdapter(getContext(), new ArrayList());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchCb);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        RemoveItemDecoration removeItemDecoration = new RemoveItemDecoration();
        recyclerView.addItemDecoration(removeItemDecoration);
    }


    public void setSections(final List<ParentSubSection> items) {
        adapter.setSections(items);
    }


    public ParentSubSection getSection(final int parentPosition, final int childPosition) {
        return adapter.getSection(parentPosition, childPosition);
    }


    public void setOnExpandableSectionListener(
            final ExpandableSectionAdapter.OnExpandableSectionListener listener) {
        adapter.setOnExpandableSectionListener(listener);
    }


    private ItemTouchHelper.SimpleCallback itemTouchCb =
            new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                    ItemTouchHelper.RIGHT) {

                @Override
                public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                    adapter.onItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                    return true;
                }

                @Override
                public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                    adapter.removeItem(viewHolder.getAdapterPosition());
                }

                @Override public boolean isLongPressDragEnabled() { return true; }
                @Override public boolean isItemViewSwipeEnabled() { return true; }
    };
}
