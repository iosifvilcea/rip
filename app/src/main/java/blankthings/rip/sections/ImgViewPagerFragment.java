package blankthings.rip.sections;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;

import blankthings.rip.R;
import blankthings.rip.api.ApiController;
import blankthings.rip.api.redditmodels.Thing;
import blankthings.rip.navigation.Navigator;
import blankthings.rip.sections.base.BaseFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by iosifvilcea on 1/17/17.
 */

public class ImgViewPagerFragment extends BaseFragment {

    public static final String TAG = ImgViewPagerFragment.class.getSimpleName();

    public static ImgViewPagerFragment newInstance() {
        ImgViewPagerFragment fragment = new ImgViewPagerFragment();
        return fragment;
    }


    private ImgViewPagerAdapter imgViewPagerAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fetchListings();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.image_vp_fragment, container, false);

        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.image_vp);
        imgViewPagerAdapter = new ImgViewPagerAdapter(getContext());
        viewPager.setAdapter(imgViewPagerAdapter);

        final TabLayout tabLayout = toolbarManager.getTabLayout();
        tabLayout.setVisibility(View.VISIBLE);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }


    private void fetchListings() {
        Navigator.INSTANCE.startLoading();
        ApiController.getInstance().listingRequest(
                "EarthPorn",
                ApiController.SortType.HOT,
                10,
                null,
                null,
                listingCallback);
    }


    private Callback<Thing> listingCallback = new Callback<Thing>() {
        @Override
        public void onResponse(Call<Thing> call, Response<Thing> response) {
            Navigator.INSTANCE.stopLoading();
            if (response.isSuccessful()) {
                Log.e(TAG, "Successful.");
                Thing listings = response.body();
            } else {

                String err = "Network Error.";
                try {
                    err = response.errorBody().string();
                } catch (IOException e) {
                    Log.e(TAG, e.getMessage());
                } finally {
                    Log.e(TAG, err);
                }
            }
        }

        @Override
        public void onFailure(Call<Thing> call, Throwable t) {
            Navigator.INSTANCE.stopLoading();
            Log.e(TAG, t.getMessage());
        }
    };


    @Override
    public void onResume() {
        super.onResume();
        toolbarManager.setTitle("IVPF");
        toolbarManager.enableToolbarScroll(true);
        toolbarManager.showTabs(true);
    }


    @Override
    public void onPause() {
        super.onPause();
        toolbarManager.enableToolbarScroll(false);
        toolbarManager.showTabs(false);
    }
}
