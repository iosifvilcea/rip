package blankthings.rip.sections.settings;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import blankthings.rip.R;

/**
 * Created by iosifvilcea on 1/27/17.
 */

public class SettingsView extends FrameLayout {
    public SettingsView(Context context) {
        this(context, null);
    }

    public SettingsView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SettingsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }


    private CardView terms;
    private CardView copyright;


    private void init() {
        View parent = inflate(getContext(), R.layout.settings_view, this);
        terms = (CardView) parent.findViewById(R.id.terms);
        copyright = (CardView) parent.findViewById(R.id.copyright);
    }


    public void setOnButtonClick(final OnClickListener onButtonClick) {
        terms.setOnClickListener(onButtonClick);
        copyright.setOnClickListener(onButtonClick);
    }
}
