package blankthings.rip.navigation.section;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.FrameLayout;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.model.Parent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import blankthings.rip.R;
import blankthings.rip.sections.album.OnItemClickListener;

/**
 * Created by iosifvilcea on 10/18/16.
 */

public class SectionView extends FrameLayout {

    private static final String TAG = SectionView.class.getSimpleName();

    private ExpandableSectionAdapter adapter;
    private ExpandableSectionAdapter.OnExpandableSectionListener listener;


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
    }


    public void setSections(final List<ParentSubSection> items) {
        adapter.setSections(items);
    }


    public ParentSubSection getSection(final int parentPosition, final int childPosition) {
        return adapter.getSection(parentPosition, childPosition);
    }


    public void setOnExpandableSectionListener(
            final ExpandableSectionAdapter.OnExpandableSectionListener listener) {
        this.listener = listener;
        adapter.setOnExpandableSectionListener(listener);
    }


    private ItemTouchHelper.SimpleCallback itemTouchCb =
            new ItemTouchHelper.SimpleCallback(ItemTouchHelper.RIGHT, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                              RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            if (listener != null) {
                final int position = viewHolder.getAdapterPosition();
                listener.onItemSwiped(position);
                adapter.removeItem(position);
            }
        }
    };
}
