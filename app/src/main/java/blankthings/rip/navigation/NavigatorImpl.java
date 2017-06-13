package blankthings.rip.navigation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import blankthings.rip.MainActivity;
import blankthings.rip.R;
import blankthings.rip.api.Endpoints;
import blankthings.rip.sections.album.AlbumFragment;
import blankthings.rip.sections.album.detail.DetailFragment;
import blankthings.rip.sections.base.BaseFragment;
import blankthings.rip.sections.base.BaseWebViewFragment;
import blankthings.rip.sections.base.OnBackPressedListener;
import blankthings.rip.sections.home.HomeFragment;
import blankthings.rip.sections.imageviewpager.ImgViewPagerFragment;
import blankthings.rip.sections.search.SearchFragment;

/**
 * Created by iosif on 6/11/17.
 */

public class NavigatorImpl implements NavigationContract.Navigator {

    private View loadingView;

    private MainActivity activity;

    public NavigatorImpl(final MainActivity mainActivity) {
        activity = mainActivity;
    }


    private void ensureValidFragment(final Fragment fragment) {
        if (!(fragment instanceof BaseFragment)) {
            throw new IllegalArgumentException("Fragment is not an instance of BaseFragment.");
        }
    }


    private void addFragment(final Fragment fragment, final String tag) {
        ensureValidFragment(fragment);

        final FragmentManager manager = activity.getSupportFragmentManager();
        final FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.content, fragment, tag);
        transaction.addToBackStack(tag);
        transaction.commit();
    }


    private void replaceFragment(final Fragment fragment, final String tag) {
        ensureValidFragment(fragment);

        final FragmentManager manager = activity.getSupportFragmentManager();
        final FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content, fragment, tag);
        transaction.addToBackStack(tag);
        transaction.commit();
    }


    @Override
    public void goToFragment(final String tag) {
//        switch (id) {
//            case R.id.search:
//                toSearch();
//                break;
//
//            case R.id.saved:
//                toImageVP();
//                break;
//
//            case R.id.home:
//            default:
//                toHome();
//                break;
//        }
    }


    @Override
    public boolean goBack() {
        final FragmentManager fragmentManager = activity.getSupportFragmentManager();
        boolean backPressHandled = isHandledByFragment();

        if (!backPressHandled) {
            if (fragmentManager.getBackStackEntryCount() > 1) {
                fragmentManager.popBackStackImmediate();
                backPressHandled = true;
            } else {
                fragmentManager.popBackStackImmediate();
                backPressHandled = false;
            }
        }

        return backPressHandled;
    }


    @Override
    public void goBackToFragment(final String tag) {
        final FragmentManager fragmentManager = activity.getSupportFragmentManager();
        fragmentManager.popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }


    private boolean isHandledByFragment() {
        final FragmentManager fragmentManager = activity.getSupportFragmentManager();
        boolean backPressHandled = false;

        /** Notifies visible fragment of backpress. */
        final Fragment fragment = fragmentManager.findFragmentById(R.id.content);
        if (fragment != null && fragment instanceof OnBackPressedListener) {
            backPressHandled = ((OnBackPressedListener) fragment).onBackPressed();
        }

        return backPressHandled;
    }



    public void toHome() {
        replaceFragment(HomeFragment.newInstance(), HomeFragment.TAG);
    }


    public void toSingleSub(final String sub) {
        replaceFragment(AlbumFragment.newInstance(sub), AlbumFragment.TAG);
    }


    public void toDetail(final Bundle bundle) {
        addFragment(DetailFragment.newInstance(bundle), DetailFragment.TAG);
    }


    public void toSearch() {
        replaceFragment(SearchFragment.newInstance(), SearchFragment.TAG);
    }


    public void toImageVP() {
        replaceFragment(ImgViewPagerFragment.newInstance(), ImgViewPagerFragment.TAG);
    }


    public void toPrivacyPolicy() {
        toWebView(Endpoints.PRIVACY_URL);
    }


    public void toTerms() {
        toWebView(Endpoints.TERMS_URL);
    }


    public void toCopyright() {
        toWebView(Endpoints.COPYRIGHT_URL);
    }


    public void toWebView(final String url) {
        replaceFragment(BaseWebViewFragment.newInstance(url), BaseWebViewFragment.TAG);
    }


    @Override
    public void startLoading() {
        crossfadeLoading(true);
    }


    @Override
    public void stopLoading() {
        crossfadeLoading(false);
    }


    private void crossfadeLoading(boolean startLoading) {
        int loadingAnimDuration = 400;
        final float startAlpha, endingAlpha;
        final int stopVisibility;
        if (startLoading) {
            startAlpha = 0f;
            endingAlpha = 1f;
            stopVisibility = View.VISIBLE;
        } else {
            startAlpha = 1f;
            endingAlpha = 0f;
            stopVisibility = View.GONE;
        }

        loadingView.setAlpha(startAlpha);
        loadingView.setVisibility(View.VISIBLE);

        loadingView.animate()
                .alpha(endingAlpha)
                .setDuration(loadingAnimDuration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        loadingView.setVisibility(stopVisibility);
                    }
                });
    }
}
