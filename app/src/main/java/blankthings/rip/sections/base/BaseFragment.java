package blankthings.rip.sections.base;

import blankthings.rip.navigation.DrawerManager;
import blankthings.rip.navigation.Navigator;
import blankthings.rip.navigation.ToolbarManager;

/**
 * BaseFragment
 *
 * Created by iosifvilcea on 6/18/16.
 */
public class BaseFragment extends android.support.v4.app.Fragment implements OnBackPressedListener {

    protected final Navigator navigator = Navigator.INSTANCE;
    protected final DrawerManager drawerManager = DrawerManager.INSTANCE;
    protected final ToolbarManager toolbarManager = ToolbarManager.INSTANCE;


    @Override
    public void onBackPressed() {
    }

}
