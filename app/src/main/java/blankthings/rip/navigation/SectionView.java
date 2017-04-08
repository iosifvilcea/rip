package blankthings.rip.navigation;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;

import java.util.ArrayList;

import blankthings.rip.R;
import blankthings.rip.sections.album.OnItemClickListener;

/**
 * Created by iosifvilcea on 10/18/16.
 */

public class SectionView extends FrameLayout {


    private static final String TAG = SectionView.class.getSimpleName();
    private SectionAdapter adapter;


    public SectionView(Context context) {
        super(context);
        inflate(context, R.layout.recycler_layout, this);
        setupViews();
    }


    protected void setupViews() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        adapter = new SectionAdapter(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }


    public void setSections(final ArrayList<SectionItem> items) {
        if (adapter != null) {
            adapter.setSections(items);
        }
    }

    public SectionItem getSection(final int position) {
        return adapter.getSection(position);
    }


    public void setOnItemClickListener(final OnItemClickListener onItemClickListener) {
        adapter.setOnItemClickListener(onItemClickListener);
    }
}
