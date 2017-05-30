package blankthings.rip.sections.album;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import blankthings.rip.R;
import blankthings.rip.api.redditmodels.Child;
import blankthings.rip.views.OnItemClickListener;

/**
 * Created by iosif on 4/8/17.
 */

public class AlbumViewHolder extends RecyclerView.ViewHolder {


    public ImageView image;


    public AlbumViewHolder(final View itemView, final OnItemClickListener onItemClickListener) {
        super(itemView);

        image = (ImageView) itemView.findViewById(R.id.image_view);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(v, getLayoutPosition());
                }
            }
        });
    }


    public void bind(final Child child) {
        if (child == null) {
            return;
        }

        final String url = child.getData().getUrl();
        if (url.contains(".gif")) {
            Glide.with(itemView.getContext())
                    .load(url)
                    .asGif()
                    .into(image);

        } else {
            Glide.with(itemView.getContext())
                    .load(url)
                    .into(image);
        }
    }
}
