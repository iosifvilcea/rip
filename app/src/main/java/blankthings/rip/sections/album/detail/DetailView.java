package blankthings.rip.sections.album.detail;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import blankthings.rip.R;
import blankthings.rip.api.redditmodels.Data;
import it.sephiroth.android.library.imagezoom.ImageViewTouch;
import it.sephiroth.android.library.imagezoom.ImageViewTouchBase;

/**
 * Created by iosif on 4/15/17.
 */

public class DetailView extends FrameLayout {


    public static final String TAG = DetailView.class.getSimpleName();
    protected LinearLayout bottomsheet;

    protected TextView headerTxt;
    protected ImageViewTouch detailImg;
    protected ImageButton saveBtn;
    protected ImageButton shareBtn;
    protected ImageButton downloadBtn;

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
    }


    private void configureBottomsheet() {
        headerTxt = (TextView) findViewById(R.id.detail_header);
        bottomsheet = (LinearLayout) findViewById(R.id.detail_bs_view);
        saveBtn = (ImageButton) findViewById(R.id.detail_option_save);
        shareBtn = (ImageButton) findViewById(R.id.detail_option_share);
        downloadBtn = (ImageButton) findViewById(R.id.detail_option_download);

        bottomSheetBehavior = BottomSheetBehavior.from(bottomsheet);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
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


    public void displaySubredditDetail(@Nullable Data data) {
        if (data == null) {
            return;
        }

        if (!TextUtils.isEmpty(data.getTitle())) {
            headerTxt.setText(data.getTitle());
        }

        // TODO: 4/16/17 set proper drawables.
        Glide.with(getContext())
                .load(data.getUrl())
                .placeholder(android.R.drawable.ic_menu_mylocation)
                .error(android.R.drawable.stat_notify_error)
                .into(detailImg);
    }
}
