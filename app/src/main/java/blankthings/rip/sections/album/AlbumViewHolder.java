package blankthings.rip.sections.album;

import android.support.v7.widget.RecyclerView;

import android.view.View;
import android.widget.ImageView;

import blankthings.rip.R;

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
}
