package blankthings.rip.sections.album.detail;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import blankthings.rip.R;
import blankthings.rip.api.redditmodels.Data;

/**
 * Created by iosif on 4/15/17.
 */

public class DetailView extends LinearLayout {


    public static final String TAG = DetailView.class.getSimpleName();

    protected Data subredditData;

    protected TextView headerTxt;
    protected ImageView detailImg;
    protected ImageButton saveBtn;
    protected ImageButton shareBtn;
    protected ImageButton downloadBtn;


    public DetailView(Context context) {
        super(context);
        init();
    }


    public DetailView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
        init();
    }


    protected void init() {
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);
        setBackgroundColor(ContextCompat.getColor(getContext(), R.color.transparent_black));
        inflate(getContext(), R.layout.detail_layout, this);

        headerTxt = (TextView) findViewById(R.id.detail_header);
        detailImg = (ImageView) findViewById(R.id.image_view);
        saveBtn = (ImageButton) findViewById(R.id.detail_option_save);
        shareBtn = (ImageButton) findViewById(R.id.detail_option_share);
        downloadBtn = (ImageButton) findViewById(R.id.detail_option_download);
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

        Glide.with(getContext())
                .load(subredditData.getUrl())
                .into(detailImg);
    }


    private void showEmptyView() {
        // TODO: 4/16/17 show empty view.
    }


}
