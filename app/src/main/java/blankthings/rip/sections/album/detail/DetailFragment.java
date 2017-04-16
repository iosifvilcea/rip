package blankthings.rip.sections.album.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by iosif on 4/15/17.
 */

public class DetailFragment extends Fragment {


    public static final String TAG = DetailFragment.class.getSimpleName();

    protected DetailView detailView;


    public static DetailFragment newInstance() {
        return new DetailFragment();
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
        // TODO: 4/15/17 Do stuff.
    }
}
