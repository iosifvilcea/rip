package blankthings.rip.navigation.section;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;

import blankthings.rip.R;
import blankthings.rip.tools.Utility;

/**
 * Created by iosif on 4/8/17.
 */

public class SectionChildViewHolder extends ChildViewHolder {

    private View view;
    private TextView titleTv;
    private View marker;
    private View divider;


    public SectionChildViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView;
        titleTv = (TextView) itemView.findViewById(R.id.section_title);
        marker = itemView.findViewById(R.id.section_marker);
        divider = itemView.findViewById(R.id.drawer_divider);
    }


    public void bind(final Section child, final View.OnClickListener onClickListener) {
        if (child == null) {
            return;
        }

        configureMarker(child.isSelected());

        view.setOnClickListener(onClickListener);

        if (!TextUtils.isEmpty(child.getDisplayName())) {
            titleTv.setText(child.getDisplayName());
        }
    }


    public void showDivider(boolean show) {
        final int VISIBILITY = (show) ? View.VISIBLE : View.INVISIBLE;
        divider.setVisibility(VISIBILITY);
    }


    private void configureMarker(boolean selected) {
        if (selected) {
            final int black = ContextCompat.getColor(itemView.getContext(), R.color.black);
            marker.setBackgroundColor(black);

        } else {
            marker.setBackground(null);
        }
    }

}
