package blankthings.rip.sections.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import blankthings.rip.navigation.NavigationContract;

/**
 * BaseFragment
 *
 * Created by iosifvilcea on 6/18/16.
 */
public abstract class BaseFragment
        extends android.support.v4.app.Fragment
        implements OnBackPressedListener {

    protected NavigationContract.Navigator navigator;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupNavigation();
    }


    private void setupNavigation() {
        if (!(getActivity() instanceof NavigationContract.HasNavigator)) {
            throw new IllegalArgumentException("MainActivity needs to implement HasNavigator.");

        } else {
            navigator = ((NavigationContract.HasNavigator)getActivity()).getNavigator();
        }
    }


    @Override
    public boolean onBackPressed() {
        return false;
    }
}
