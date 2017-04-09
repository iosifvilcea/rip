package blankthings.rip.navigation.section;

import android.support.v7.view.menu.MenuView;
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
    private View.OnLongClickListener onLongClicked;


    public SectionParentViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        this.itemView.setOnLongClickListener(onLongClickListener);
        titleTv = (TextView) itemView.findViewById(R.id.sectionTitle);
    }


    public void bind(ParentSubSection parent, final View.OnLongClickListener onLongClickListener,
                     final View.OnClickListener onClickListener) {
        if (parent == null) {
            return;
        }

        if (!TextUtils.isEmpty(parent.getDisplayName())) {
            titleTv.setText(parent.getDisplayName());
        }

        onLongClicked = onLongClickListener;
        itemView.setOnClickListener(onClickListener);
    }


    private final View.OnLongClickListener onLongClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {
            if (isExpanded()) {
                collapseView();
            } else {
                expandView();
            }

            if (onLongClicked != null) {
                onLongClicked.onLongClick(view);
            }

            return true;
        }
    };
}
