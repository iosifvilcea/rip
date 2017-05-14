package blankthings.rip.sections.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import blankthings.rip.api.redditmodels.ThingWrapper;
import blankthings.rip.sections.album.AlbumViewHolder;
import blankthings.rip.sections.album.AlbumView;
import blankthings.rip.views.OnItemClickListener;

/**
 * SearchAdapter
 *   Manages a list of horizontally scrollable subreddits.
 *
 * Created by iosifvilcea on 2/11/17.
 */
public class SearchAdapter extends RecyclerView.Adapter<AlbumViewHolder> {

    public static final String TAG = SearchAdapter.class.getSimpleName();
    private Context context;

    private ArrayList<ThingWrapper> categories = new ArrayList<>();
    private OnItemClickListener onItemClickListener;


    public SearchAdapter(final Context context) {
        this.context = context;
    }


    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        final View view = new AlbumView(context);
        return new AlbumViewHolder(view, onItemClickListener);
    }


    @Override
    public void onBindViewHolder(AlbumViewHolder holder, int position) {
        if (holder == null) {
            return;
        }

        // TODO 02/15/17

    }


    public void setOnItemClickListener(final OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    @Override
    public int getItemCount() {
        return categories.size();
    }


    //
    public void clearItems() {
        categories.clear();
        notifyDataSetChanged();
    }


    /**
    * Card SectionVh
    */
//    public static class CategoryViewHolder extends RecyclerView.SectionVh {
//
//        public TextView categoryTitle;
//        public View subscribeButton;
//        public RecyclerView recyclerView;
//
//        public CategoryViewHolder(View itemView) {
//            super(itemView);
//
//            categoryTitle = (TextView) itemView.findViewById(R.id.categoryTitle);
//            subscribeButton = itemView.findViewById(R.id.categorySubscribeButton);
//            recyclerView = (RecyclerView) itemView.findViewById(R.id.categoryRecyclerView);
//        }
//    }
}
