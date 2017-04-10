package blankthings.rip.navigation.section;

import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ParentViewHolder;

import blankthings.rip.R;

/**
 * Created by iosif on 4/8/17.
 */

public class SectionParentViewHolder extends ParentViewHolder {


    private View itemView;
    private TextView titleTv;
    private View marker;
    private View divider;


    public SectionParentViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        titleTv = (TextView) itemView.findViewById(R.id.section_title);
        marker = itemView.findViewById(R.id.section_marker);
        divider = itemView.findViewById(R.id.drawer_divider);

    }


    public void bind(final ParentSubSection parent,
                     final View.OnLongClickListener onLongClickListener,
                     final View.OnClickListener onClickListener) {
        if (parent == null) {
            return;
        }

        showDivider(true);
        showMarker(false);
        if (!TextUtils.isEmpty(parent.getDisplayName())) {
            titleTv.setText(parent.getDisplayName());
        }

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMarker(true);
                onClickListener.onClick(v);
            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                if (parent.hasChildren()) {
                    if (isExpanded()) {
                        collapseView();
                        showDivider(true);
                    } else {
                        expandView();
                        showDivider(false);
                    }
                }

                if (onClickListener != null) {
                    onLongClickListener.onLongClick(v);
                }

                return true;
            }
        });
    }


    public void showDivider(boolean show) {
        if (itemView == null || divider == null) {
            return;
        }

        final int color;
        if (show) {
            color = ContextCompat.getColor(itemView.getContext(), R.color.divider);
        } else {
            color = ContextCompat.getColor(itemView.getContext(), R.color.softDivider);
        }
        divider.setBackgroundColor(color);
    }


    public void showMarker(boolean show) {
        if (itemView == null || marker == null) {
            return;
        }

        if (show) {
            final int black = ContextCompat.getColor(itemView.getContext(), R.color.black);
            marker.setBackgroundColor(black);
        } else {
            marker.setBackground(null);
        }
    }
}
