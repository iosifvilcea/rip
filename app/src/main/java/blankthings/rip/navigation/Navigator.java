package blankthings.rip.navigation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

import java.lang.ref.WeakReference;

import blankthings.rip.MainActivity;
import blankthings.rip.R;
import blankthings.rip.sections.BaseWebViewFragment;
import blankthings.rip.sections.ImgViewPagerFragment;
import blankthings.rip.sections.album.AlbumFragment;
import blankthings.rip.sections.album.detail.DetailFragment;
import blankthings.rip.sections.base.BaseFragment;
import blankthings.rip.sections.base.OnBackPressedListener;
import blankthings.rip.sections.home.HomeFragment;
import blankthings.rip.sections.search.SearchFragment;
import blankthings.rip.sections.settings.SettingsFragment;

/**
 * Navigator
 *   - Manages fragment transitions.
 *
 * Created by iosifvilcea on 7/9/16.
 */
public enum Navigator {

    INSTANCE;

    private static final String TAG = Navigator.class.getSimpleName();
    private static final String privacyUrl = "https://www.reddit.com/help/privacypolicy/";
    private static final String termsUrl = "https://www.reddit.com/help/useragreement";
    private static final String copyrightUrl = "https://www.reddit.com/help/contentpolicy/";

    private WeakReference<MainActivity> mainActWeakRef = null;
    private View loadingView;


    /**
     * @param mainActivity - main activity where this Navigator is being initialized.
     */
    public void initializeNavigator(final MainActivity mainActivity) {
        if (null == mainActivity) {
            Log.e(TAG, "Cannot initialize Navigator. MainActivity cannot be null.");
            return;
        }

        mainActWeakRef = new WeakReference<>(mainActivity);
        loadingView = mainActivity.findViewById(R.id.loadingView);
        loadingView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             // DO NOTHING.
            }
        });
    }


    private void ensureValidFragment(final Fragment fragment) {
        final MainActivity mainActivity = mainActWeakRef.get();
        if (mainActivity == null) {
            throw new IllegalStateException("Activity Reference cannot be null.");
        }

        if (!(fragment instanceof BaseFragment)) {
            throw new IllegalArgumentException("Fragment is not an instance of BaseFragment.");
        }
    }


    public void addFragment(final Fragment fragment, final String tag) {
        ensureValidFragment(fragment);

        final FragmentManager manager = mainActWeakRef.get().getSupportFragmentManager();
        final FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.content, fragment, tag);
        transaction.addToBackStack(tag);
        transaction.commit();
    }


    public void replaceFragment(final Fragment fragment, final String tag) {
        ensureValidFragment(fragment);

        final FragmentManager manager = mainActWeakRef.get().getSupportFragmentManager();
        final FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content, fragment, tag);
        transaction.addToBackStack(tag);
        transaction.commit();
    }


    public void toFragmentWithId(final int id) {
        switch (id) {
            case R.id.explore:
                toExplore();
                break;

            case R.id.search:
                toSearch();
                break;

            case R.id.settings:
                toSettings();
                break;

            case R.id.home:
            default:
                toHome();
                break;
        }
    }


    public void toHome() {
        replaceFragment(HomeFragment.newInstance(), HomeFragment.TAG);
    }


    public void toExplore() {
        // TODO: 4/15/17 - Impl. Explore.
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


    public void toSettings() {
        replaceFragment(SettingsFragment.newInstance(), SettingsFragment.TAG);
    }


    public void toPrivacyPolicy() {
        toWebView(privacyUrl);
    }


    public void toTerms() {
        toWebView(termsUrl);
    }


    public void toCopyright() {
        toWebView(copyrightUrl);
    }


    public void toWebView(final String url) {
        replaceFragment(BaseWebViewFragment.newInstance(url), BaseWebViewFragment.TAG);
    }


    public void startLoading() {
        loadingView.setVisibility(View.VISIBLE);
    }


    public void stopLoading() {
        loadingView.setVisibility(View.GONE);
    }


    public boolean back() {
        return onBackPressed();
    }


    private boolean onBackPressed() {
        final MainActivity mainActivity = mainActWeakRef.get();
        if (mainActivity == null) {
            return false;
        }

        final FragmentManager fragmentManager = mainActivity.getSupportFragmentManager();
        boolean backPressHandled = false;

        /** Notifies visible fragment of backpress. */
        final Fragment fragment = fragmentManager.findFragmentById(R.id.content);
        if (fragment != null && fragment instanceof OnBackPressedListener) {
            backPressHandled = ((OnBackPressedListener) fragment).onBackPressed();
        }

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
}
