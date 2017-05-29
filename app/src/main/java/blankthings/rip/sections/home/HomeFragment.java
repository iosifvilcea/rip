package blankthings.rip.sections.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import blankthings.rip.R;
import blankthings.rip.contentproviders.MediaResolverController;
import blankthings.rip.sections.base.BaseFragment;

/**
 * HomeFragment
 * Created by iosifvilcea on 6/18/16.
 */
public class HomeFragment extends BaseFragment {
    public static final String TAG = HomeFragment.class.getSimpleName();


    public static HomeFragment newInstance() {
        return new HomeFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment_layout, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button button = (Button) view.findViewById(R.id.button);
        button.setText("Query Images");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuerying();
            }
        });

        Button button2 = (Button) view.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.toSearch();
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        toolbarManager.setTitle("ABS");
        toolbarManager.enableToolbarScroll(false);
        toolbarManager.showTabs(false);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        toolbarManager.setSearchView(null /** TODO */);
    }


    private void startQuerying() {
        MediaResolverController controller = new MediaResolverController(getContext());
        controller.findFirstItem();
    }
}
