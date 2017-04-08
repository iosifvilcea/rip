package blankthings.rip.navigation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import blankthings.rip.R;
import blankthings.rip.sections.album.OnItemClickListener;

/**
 * Created by iosifvilcea on 5/17/16.
 */
public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.SectionVh> {

    private final static String TAG = SectionAdapter.class.getSimpleName();

    private final Context context;
    private ArrayList<SectionItem> sections;
    private OnItemClickListener onItemClickListener;


    public SectionAdapter(final Context context) {
        if (context == null) {
            Log.e(TAG, "Context cannot be null.");
        }

        this.context = context;
        sections = new ArrayList<>();
    }


    @Override
    public SectionVh onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.drawer_item, parent, false);
        return new SectionVh(view);
    }


    @Override
    public void onBindViewHolder(SectionVh holder, final int position) {
        final SectionItem item = sections.get(position);
        final String title = item.getTitle();

        holder.titleTv.setText(title);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(v, position);
            }
        });
    }


    public void setOnItemClickListener(final OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public void setSections(final ArrayList<SectionItem> sections) {
        if (sections != null && !sections.isEmpty()){
            this.sections.clear();
            this.sections.addAll(sections);
            notifyDataSetChanged();
        }
    }


    public SectionItem getSection(final int position) {
        return sections.get(position);
    }


    @Override
    public int getItemCount() {
        return sections.size();
    }


    /**
     * Describes a Section View.
     */
    public static class SectionVh extends RecyclerView.ViewHolder {

        public TextView titleTv;

        public SectionVh(View itemView) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(R.id.sectionTitle);
        }
    }

}
