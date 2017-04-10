package blankthings.rip.navigation.section;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.ParentViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import blankthings.rip.R;
import blankthings.rip.sections.album.OnItemClickListener;
import blankthings.rip.tools.Utility;

/**
 * Contains a list of ParentSubSections which is either
 *   - multi-subreddit composed of children subreddits.
 *   - subreddit as parent.
 *
 * Created by iosif on 4/8/17.
 */

public class ExpandableSectionAdapter
        extends ExpandableRecyclerAdapter<ParentSubSection, Section,
                                            SectionParentViewHolder, SectionChildViewHolder> {

    public static final String TAG = ExpandableSectionAdapter.class.getSimpleName();

    private final LayoutInflater inflater;
    private final Context context;
    private List<ParentSubSection> sections;
    private Map<ParentSubSection, SectionParentViewHolder> parentViewHolders;
    private OnExpandableSectionListener onExpandableSectionListener;
    private int lastCheckedPosition = -1;


    public interface OnExpandableSectionListener {
        void onItemClicked(final ParentSubSection section);
        void onItemLongClicked(int parentPosition);
        void onItemSwiped(int parentPosition, int childPosition);
    }


    public ExpandableSectionAdapter(final Context context,
                                    @NonNull List<ParentSubSection> parentList) {
        super(parentList);
        this.context = context;
        sections = parentList;
        inflater = LayoutInflater.from(context);
        parentViewHolders = new HashMap<>();
    }


    @NonNull
    @Override
    public SectionParentViewHolder onCreateParentViewHolder(@NonNull ViewGroup parentViewGroup,
                                                            int viewType) {
        final View view = inflater.inflate(R.layout.drawer_item, parentViewGroup, false);
        return  new SectionParentViewHolder(view);
    }


    @NonNull
    @Override
    public SectionChildViewHolder onCreateChildViewHolder(@NonNull ViewGroup childViewGroup,
                                                          int viewType) {
        final View view = inflater.inflate(R.layout.drawer_item, childViewGroup, false);
        return new SectionChildViewHolder(view);
    }


    @Override
    public void onBindParentViewHolder(@NonNull SectionParentViewHolder parentViewHolder,
                                       final int parentPosition, @NonNull ParentSubSection parent) {
        parentViewHolders.put(parent, parentViewHolder);
        parentViewHolder.bind(parent,
                new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        if (onExpandableSectionListener != null) {
                            onExpandableSectionListener.onItemLongClicked(parentPosition);
                            return true;
                        }
                        return false;
                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onExpandableSectionListener != null) {
                            final ParentSubSection section = getSection(parentPosition, -1);
                            refreshSelectedSection(parentPosition);
                            onExpandableSectionListener.onItemClicked(section);
                        }
                    }
                });
    }


    @Override
    public void onBindChildViewHolder(@NonNull SectionChildViewHolder childViewHolder, final int parentPosition,
                                      final int childPosition, @NonNull Section child) {
        childViewHolder.bind(child, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onExpandableSectionListener != null) {
                    final ParentSubSection section = getSection(parentPosition, childPosition);
                    refreshSelectedSection(parentPosition);
                    onExpandableSectionListener.onItemClicked(section);
                }
            }
        });

        final int size = sections.get(parentPosition).getChildList().size();
        childViewHolder.showDivider(childPosition == size-1);
    }


    public void setSections(final List<ParentSubSection> sections) {
        this.sections = sections;
        setParentList(sections, false);
    }


    public ParentSubSection getSection(final int parent, final int child) {
        if (child == -1) {
            return sections.get(parent);
        } else {
            List<Section> list = sections.get(parent).getChildList();
            return new ParentSubSection(list.get(child));
        }
    }


    protected void refreshSelectedSection(final int parentPosition) {
        if (lastCheckedPosition > -1) {
            final ParentSubSection previousSelectedSection = sections.get(lastCheckedPosition);
            if (previousSelectedSection != null) {
                previousSelectedSection.setSelected(false);

                final SectionParentViewHolder viewHolder = parentViewHolders.get(previousSelectedSection);
                viewHolder.showMarker(false);
            }
        }
        lastCheckedPosition = parentPosition;

        final ParentSubSection selectedSection = sections.get(parentPosition);
        selectedSection.setSelected(true);

        final SectionParentViewHolder viewHolder = parentViewHolders.get(selectedSection);
        viewHolder.showMarker(true);
    }


    public void setOnExpandableSectionListener(final OnExpandableSectionListener listener) {
        onExpandableSectionListener = listener;
    }
}
