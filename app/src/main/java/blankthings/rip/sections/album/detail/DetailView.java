package blankthings.rip.sections.album.detail;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import blankthings.rip.R;
import blankthings.rip.api.redditmodels.Data;
import it.sephiroth.android.library.imagezoom.ImageViewTouch;
import it.sephiroth.android.library.imagezoom.ImageViewTouchBase;

/**
 * Created by iosif on 4/15/17.
 */

public class DetailView extends LinearLayout {


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
        configureImageZoom();
        configureBottomsheet();
    }


    private void configureParentView() {
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);
        setBackgroundColor(ContextCompat.getColor(getContext(), R.color.transparent_black));
        inflate(getContext(), R.layout.detail_layout, this);
    }


    private void configureImageZoom() {
        detailImg = (ImageViewTouch) findViewById(R.id.detail_image_view_touch);
        detailImg.setDisplayType(ImageViewTouchBase.DisplayType.FIT_TO_SCREEN);
        detailImg.setSingleTapListener(new ImageViewTouch.OnImageViewTouchSingleTapListener() {
            @Override
            public void onSingleTapConfirmed() {
                if (bottomSheetBehavior == null) {
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
            @Override
            public void onDoubleTap() {
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
    }


    public void showBottomsheet() {
        if (bottomsheet != null) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }
    }


    public void hideBottomsheet() {
        if (bottomsheet != null) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
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


}
