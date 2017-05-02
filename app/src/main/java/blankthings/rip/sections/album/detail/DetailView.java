package blankthings.rip.sections.album.detail;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import blankthings.rip.R;
import blankthings.rip.api.redditmodels.Data;
import blankthings.rip.tools.Utility;
import it.sephiroth.android.library.imagezoom.ImageViewTouch;
import it.sephiroth.android.library.imagezoom.ImageViewTouchBase;

/**
 * Created by iosif on 4/15/17.
 */

public class DetailView extends CoordinatorLayout {


    public static final String TAG = DetailView.class.getSimpleName();

    protected Data subredditData;

    protected TextView headerTxt;
    protected ImageViewTouch detailImg;
    protected ImageButton saveBtn;
    protected ImageButton shareBtn;
    protected ImageButton downloadBtn;
    protected LinearLayout bottomsheet;

    protected BottomSheetBehavior bottomSheetBehavior;


    public DetailView(Context context) {
        super(context);
        init();
    }


    public DetailView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
        init();
    }


    protected void init() {
        configureParentView();
        configureBottomsheet();
        configureImageZoom();
    }


    private void configureParentView() {
        inflate(getContext(), R.layout.detail_layout, this);
        setBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.holo_green_light));
    }


    private void configureImageZoom() {
        detailImg = (ImageViewTouch) findViewById(R.id.detail_image_view_touch);
        detailImg.setDisplayType(ImageViewTouchBase.DisplayType.FIT_IF_BIGGER);
        detailImg.setSingleTapListener(new ImageViewTouch.OnImageViewTouchSingleTapListener() {
            @Override
            public void onSingleTapConfirmed() {
                if (bottomSheetBehavior == null
                        || bottomSheetBehavior.STATE_DRAGGING == bottomSheetBehavior.getState()
                        || bottomSheetBehavior.STATE_SETTLING == bottomSheetBehavior.getState()) {
                    return;
                }

                if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    hideBottomsheet();
                } else {
                    showBottomsheet();
                }
            }
        });

        detailImg.setDoubleTapListener(new ImageViewTouch.OnImageViewTouchDoubleTapListener() {
            @Override public void onDoubleTap() {}
        });
    }


    private void configureBottomsheet() {
        headerTxt = (TextView) findViewById(R.id.detail_header);
        bottomsheet = (LinearLayout) findViewById(R.id.detail_bs_view);
        saveBtn = (ImageButton) findViewById(R.id.detail_option_save);
        shareBtn = (ImageButton) findViewById(R.id.detail_option_share);
        downloadBtn = (ImageButton) findViewById(R.id.detail_option_download);

        bottomSheetBehavior = BottomSheetBehavior.from(bottomsheet);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        final int peek = headerTxt.getMeasuredHeight() * 2;
        bottomSheetBehavior.setPeekHeight(peek);
        bottomSheetBehavior.setBottomSheetCallback(bottomSheetCallback);
    }


    public void showBottomsheet() {
        if (bottomsheet != null) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }
    }


    public void hideBottomsheet() {
        if (bottomsheet != null) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        }
    }



    public void setSubredditData(@Nullable Data data) {
        subredditData = data;

        if (data == null) {
            showEmptyView();
        } else {
            populateViews();
        }
    }


    private void populateViews() {
        if (!TextUtils.isEmpty(subredditData.getTitle())) {
            headerTxt.setText(subredditData.getTitle());
        }

        // TODO: 4/16/17 set proper drawables.
        Glide.with(getContext())
                .load(subredditData.getUrl())
//                .placeholder(...)
//                .error(...)
                .into(detailImg);
    }


    private void showEmptyView() {
        // TODO: 4/16/17 show empty view.
    }


    private BottomSheetBehavior.BottomSheetCallback bottomSheetCallback =
            new BottomSheetBehavior.BottomSheetCallback() {
                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {
                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                    detailImg.animate()
                            .translationY(1 - slideOffset)
                            .setDuration(0)
                            .start();
                }
            };
}
