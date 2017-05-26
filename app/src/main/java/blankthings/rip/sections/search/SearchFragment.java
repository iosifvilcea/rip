package blankthings.rip.sections.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import blankthings.rip.R;
import blankthings.rip.sections.base.BaseFragment;

/**
 * SearchFragment
 * Created by iosifvilcea on 6/19/16.
 */
public class SearchFragment extends BaseFragment {

    public static final String TAG = SearchFragment.class.getSimpleName();


    public static SearchFragment newInstance() {
        Bundle args = new Bundle();

        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private SearchView searchView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        searchView = new SearchView(getContext());
        return searchView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // TODO 02/15/17
//        searchView.setOnItemClickListener(onSearchedItemClick);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);

        // TODO 02/15/17
//        toolbarManager.setSearchView(queryTextListener);
    }


    // TODO 02/15/17
//    //
//    private final SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
//        @Override
//        public boolean onQueryTextSubmit(String query) {
//            return true;
//        }
//
//        @Override
//        public boolean onQueryTextChange(String newText) {
//            return false;
//        }
//    };
//
//
//    //
//    private final SearchAdapter.OnItemClickListener onSearchedItemClick = new SearchAdapter.OnItemClickListener() {
//        @Override
//        public void onItemClick(View itemView, int position) {
//            // TODO
//        }
//    };
}
