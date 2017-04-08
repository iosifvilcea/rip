package blankthings.rip.sections.album;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import blankthings.rip.R;
import blankthings.rip.api.redditmodels.Child;

/**
 * Created by iosifvilcea on 10/20/16.
 */
public class AlbumAdapter
        extends RecyclerView.Adapter<AlbumViewHolder> {


    public static final String TAG = AlbumAdapter.class.getSimpleName();

    private Context context;
    private ViewType albumViewType;

    private OnItemClickListener onItemClickListener;
    private ArrayList<Child> items = new ArrayList<>();


    public enum ViewType {
        CARD_VIEW(0),
        FULL_VIEW(1),
        GRID_VIEW(2);

        int value;
        ViewType(final int val) {
            value = val;
        }
    }


    public AlbumAdapter(final Context context) {
        this(context, null);
    }


    public AlbumAdapter(final Context context, @Nullable final ViewType viewType) {
        this.context = context;
        albumViewType = (viewType == null) ? ViewType.CARD_VIEW : viewType;
    }


    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        final LayoutInflater inflater = LayoutInflater.from(context);

        View view;
        switch (albumViewType) {
            case GRID_VIEW:
                view = inflater.inflate(R.layout.grid_item, parent, false);
                break;

            case FULL_VIEW:
                view = inflater.inflate(R.layout.full_view_item, parent, false);
                break;

            case CARD_VIEW:  // intentional fall-through.
            default:
                view = inflater.inflate(R.layout.card_item, parent, false);
                break;
        }

        return new AlbumViewHolder(view, onItemClickListener);
    }


    @Override
    public void onBindViewHolder(AlbumViewHolder holder, int position) {
        if (holder == null) {
            return;
        }

        final Child child = items.get(position);
        final String url = child.getData().getUrl();
        Glide.with(context)
                .load(url)
                .into(holder.image);
    }


    @Override
    public int getItemViewType(int position) {
        return albumViewType.value;
    }


    @Override
    public int getItemCount() {
        return items.size();
    }


    public void setAlbumViewType(ViewType viewType) {
        albumViewType = viewType;
        notifyDataSetChanged();
    }


    public void addItems(final ArrayList<Child> list) {
        if (list != null && !list.isEmpty()) {
            this.items.addAll(list);
            notifyDataSetChanged();
        }
    }


    public void addItem(final Child item) {
        if (item != null) {
            items.add(item);
            final int lastPosition = items.size() - 1;
            notifyItemInserted(lastPosition);
        }
    }


    public void removeItem(final Child item) {
        if (item != null) {
            items.remove(item);
            final int lastPosition = items.size() - 1;
            notifyItemRemoved(lastPosition);
        }
    }


    public void clearItems() {
        items.clear();
        notifyDataSetChanged();
    }


    public void setOnItemClickListener(final OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
