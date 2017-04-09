package blankthings.rip.navigation.section;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;

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


    public SectionView(Context context) {
        super(context);
        inflate(context, R.layout.recycler_layout, this);
        setupViews();
    }


    protected void setupViews() {
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        final List list = new ArrayList();
        adapter = new ExpandableSectionAdapter(getContext(), list);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }


    public void setSections(final List<ParentSubSection> items) {
        if (adapter != null) {
            adapter.setParentList(items, false);
        }
    }


    public ParentSubSection getSection(final int position) {
        return adapter.getSection(position);
    }


    public void setOnItemClickListener(final OnItemClickListener onItemClickListener) {
        adapter.setOnItemClickListener(onItemClickListener);
    }
}
