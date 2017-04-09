package blankthings.rip.navigation.section;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;

import blankthings.rip.R;

/**
 * Created by iosif on 4/8/17.
 */

public class SectionChildViewHolder extends ChildViewHolder {

    private View view;
    private TextView titleTv;


    public SectionChildViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView;
        titleTv = (TextView) itemView.findViewById(R.id.sectionTitle);
    }


    public void bind(final ParentSubSection.SubSection child, final View.OnClickListener onClickListener) {
        if (child == null) {
            return;
        }

        view.setOnClickListener(onClickListener);

        if (!TextUtils.isEmpty(child.getDisplayName())) {
            titleTv.setText(child.getDisplayName());
        }
    }

}
