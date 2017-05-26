package blankthings.rip.views;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.ScaleGestureDetector;

import it.sephiroth.android.library.imagezoom.ImageViewTouch;

/**
 * Created by iosif on 5/26/17.
 */

public class ImageZoomView extends ImageViewTouch {

    private ColorDrawable colorDrawable;

    public ImageZoomView(Context context) {
        super(context);
        initView();
    }

    public ImageZoomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }


    private void initView() {
        colorDrawable = new ColorDrawable(ContextCompat.getColor(getContext(), android.R.color.holo_red_dark));
    }


    @Override
    protected ScaleGestureDetector.OnScaleGestureListener getScaleListener() {
        return new ScaleListener() {
            @Override
            public boolean onScale(ScaleGestureDetector detector) {
                // TODO: 5/26/17  
                setImageDrawable(colorDrawable);
                return super.onScale(detector);
            }
        };
    }
}
