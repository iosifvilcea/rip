package blankthings.rip.sections.album.detail;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by iosif on 4/15/17.
 */

public class DetailView extends FrameLayout {


    public static final String TAG = DetailView.class.getSimpleName();


    public DetailView(@NonNull Context context) {
        this(context, null);
    }

    public DetailView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DetailView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    protected void init() {
        // TODO: 4/15/17 init stuff.
    }
}
