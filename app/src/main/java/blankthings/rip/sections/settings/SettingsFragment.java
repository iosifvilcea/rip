package blankthings.rip.sections.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import blankthings.rip.R;
import blankthings.rip.sections.base.BaseFragment;

/**
 * Created by iosifvilcea on 1/26/17.
 */

public class SettingsFragment extends BaseFragment {

    public static SettingsFragment newInstance() {
        Bundle args = new Bundle();

        SettingsFragment fragment = new SettingsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private SettingsView settingsView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        settingsView = new SettingsView(getContext());
        settingsView.setOnButtonClick(onButtonClicked);
        return settingsView;
    }


    private final View.OnClickListener onButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (v.getId() == R.id.terms) {
                navigator.toTerms();

            } else if (v.getId() == R.id.copyright) {
                navigator.toCopyright();
            }

        }
    };


    // TODO - Settings thangs.
    //   - Save in SharedPrefs.
    //   - Show saved / starred images in a saved fragment somewhere.
    //   - Subreddit Filters.
    //   - Hide: NSFW, threshold upvotes / downvotes, already seen posts?.
    //   - Review App.
}
