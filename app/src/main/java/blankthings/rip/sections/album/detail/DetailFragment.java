package blankthings.rip.sections.album.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import blankthings.rip.api.redditmodels.Child;
import blankthings.rip.api.redditmodels.Data;
import blankthings.rip.sections.base.BaseFragment;

/**
 * Created by iosif on 4/15/17.
 */

public class DetailFragment extends BaseFragment {


    public static final String TAG = DetailFragment.class.getSimpleName();
    public static final String DETAIL_KEY = "DETAIL_KEY";

    protected DetailView detailView;


    public static DetailFragment newInstance(final Bundle bundle) {
        final DetailFragment fragment = new DetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        detailView = new DetailView(getContext());
        return detailView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Child child = (Child) getArguments().get(DETAIL_KEY);
        detailView.setSubredditData(child.getData());
    }


    @Override
    public void onResume() {
        super.onResume();
        toolbarManager.hideToolbar();
    }


    @Override
    public void onPause() {
        toolbarManager.showToolbar();
        super.onPause();
    }
}
