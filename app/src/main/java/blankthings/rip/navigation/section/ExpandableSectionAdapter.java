package blankthings.rip.navigation.section;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;

import java.util.List;

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
        extends ExpandableRecyclerAdapter<ParentSubSection, ParentSubSection.SubSection,
                                            SectionParentViewHolder, SectionChildViewHolder> {

    public static final String TAG = ExpandableSectionAdapter.class.getSimpleName();

    private final LayoutInflater inflater;
    private final Context context;
    private List<ParentSubSection> sections;
    private OnItemClickListener onItemClickListener;


    public ExpandableSectionAdapter(final Context context,
                                    @NonNull List<ParentSubSection> parentList) {
        super(parentList);
        this.context = context;
        sections = parentList;
        inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public SectionParentViewHolder onCreateParentViewHolder(@NonNull ViewGroup parentViewGroup,
                                                            int viewType) {
        final View view = inflater.inflate(R.layout.drawer_item, parentViewGroup, false);
        return new SectionParentViewHolder(view);
    }


    @NonNull
    @Override
    public SectionChildViewHolder onCreateChildViewHolder(@NonNull ViewGroup childViewGroup,
                                                          int viewType) {
        final View view = inflater.inflate(R.layout.drawer_item, childViewGroup, false);
        float padding = Utility.dpToPixels(context, 15);
        float paddingLeft = Utility.dpToPixels(context, 30);
        view.setPadding((int)paddingLeft, (int)padding, (int)padding, (int)padding);
        return new SectionChildViewHolder(view);
    }


    @Override
    public void onBindParentViewHolder(@NonNull SectionParentViewHolder parentViewHolder,
                                       final int parentPosition, @NonNull ParentSubSection parent) {
        parentViewHolder.bind(parent,
                new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        // TODO - Show dialog to add to Multi sub?
                        // TODO - Show dialog to add to Multi sub?
                        // TODO - Show dialog to add to Multi sub?
                        Log.d(ExpandableSectionAdapter.class.getSimpleName(), "OnLongClicked.");
                        return false;
                    }
                }, generateOnClickListener(parentPosition));
    }


    @Override
    public void onBindChildViewHolder(@NonNull SectionChildViewHolder childViewHolder, int parentPosition,
                                      final int childPosition, @NonNull ParentSubSection.SubSection child) {
        childViewHolder.bind(child, generateOnClickListener(childPosition));
    }


    private View.OnClickListener generateOnClickListener(final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    Log.e(TAG, "Child pos: " + position);
                    onItemClickListener.onItemClick(v, position);
                }
            }
        };
    }



    public ParentSubSection getSection(final int position) {
        return sections.get(position);
    }


    public void setOnItemClickListener(final OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
