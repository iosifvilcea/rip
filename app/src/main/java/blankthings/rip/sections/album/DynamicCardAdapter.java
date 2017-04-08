package blankthings.rip.sections.album;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import blankthings.rip.R;
import blankthings.rip.api.redditmodels.Child;

/**
 * Created by iosifvilcea on 10/20/16.
 */
public class DynamicCardAdapter
        extends RecyclerView.Adapter<DynamicCardAdapter.DynamicViewHolder> {

    public static final String TAG = DynamicCardAdapter.class.getSimpleName();
    public static final int VERTICAL = 0;
    public static final int HORIZONTAL = 0;

    private Context context;
    private int scrollDirection;

    private ViewType cardListViewType;
    public enum ViewType {
        CARD_VIEW(0),
        LIST_VIEW(1),
        THUMBS_VIEW(2);

        int value;
        ViewType(final int val) {
            value = val;
        }
    }

    private static OnItemClickListener onItemClickListener;
    private ArrayList<Child> items = new ArrayList<>();


    public DynamicCardAdapter(final Context context) {
        this(context, VERTICAL, ViewType.CARD_VIEW);
    }

    public DynamicCardAdapter(final Context context, final int scrollDirection) {
        this(context, scrollDirection, ViewType.CARD_VIEW);
    }

    public DynamicCardAdapter(final Context context, final int scrollDirection, final ViewType viewType) {
        this.context = context;
        cardListViewType = viewType;
        this.scrollDirection = scrollDirection;
    }


    @Override
    public DynamicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        final LayoutInflater inflater = LayoutInflater.from(context);
        View view;

        // TODO - SEPARATE VIEW HOLDERS.
        switch (cardListViewType) {
            case LIST_VIEW:
                view = inflater.inflate(R.layout.drawer_item, parent, false);
                break;
            case THUMBS_VIEW:
                view = inflater.inflate(R.layout.thumbnail_item, parent, false);
                break;
            case CARD_VIEW:
                // intentional fall-through.

                // TODO - GRID VIEW.

            default:
                view = inflater.inflate(R.layout.card_item, parent, false);
                break;
        }

        return new DynamicViewHolder(view);
    }


    @Override
    public void onBindViewHolder(DynamicViewHolder holder, int position) {
        if (holder == null) {
            return;
        }

        final Child child = items.get(position);
        setupCardView(holder, child);
    }


    protected void setupCardView(final DynamicViewHolder holder, final Child child) {
        final String url = child.getData().getUrl();
        Glide.with(context)
                .load(url)
                .into(holder.image);
    }


    @Override
    public int getItemViewType(int position) {
        return cardListViewType.value;
    }


    @Override
    public int getItemCount() {
        return items.size();
    }


    //
    public void addItems(final ArrayList<Child> list) {
        if (list != null && !list.isEmpty()) {
            this.items.addAll(list);
            notifyDataSetChanged();
        }
    }


    //
    public void addItem(final Child item) {
        if (item != null) {
            items.add(item);
            final int lastPosition = items.size() - 1;
            notifyItemInserted(lastPosition);
        }
    }


    //
    public void removeItem(final Child item) {
        if (item != null) {
            items.remove(item);
            final int lastPosition = items.size() - 1;
            notifyItemRemoved(lastPosition);
        }
    }


    //
    public void clearItems() {
        items.clear();
        notifyDataSetChanged();
    }


    //
    public void setOnItemClickListener(final OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    /**
     * SectionVh
     */
    public static class DynamicViewHolder extends RecyclerView.ViewHolder {

        public View view;
        public ImageView image;

        public DynamicViewHolder(View itemView) {
            super(itemView);

            view = itemView.findViewById(R.id.cardItem);
            image = (ImageView) itemView.findViewById(R.id.cardImage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(v, getLayoutPosition());
                    }
                }
            });
        }
    }
}
