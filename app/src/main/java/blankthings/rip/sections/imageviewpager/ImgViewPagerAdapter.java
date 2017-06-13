package blankthings.rip.sections.imageviewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import blankthings.rip.api.redditmodels.Child;
import blankthings.rip.sections.album.AlbumView;

/**
 * Image ViewPager Adapter
 * Created by iosifvilcea on 1/17/17.
 */

public class ImgViewPagerAdapter extends PagerAdapter {

    private Context context;
    private final List<String> tabNames = new ArrayList<>();
    private final List<Child> children = new ArrayList<>();


    public ImgViewPagerAdapter(final Context context) {
        this.context = context;
        tabNames.add("Tab Num 1");
        tabNames.add("Tab Num 2");
        tabNames.add("Tab Num 3");
        tabNames.add("Tab Num 4");
        tabNames.add("Tab Num 5");
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final AlbumView albumView = new AlbumView(context);
        albumView.addCardList((ArrayList<Child>) children);

        container.addView(albumView);
        return albumView;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }


    @Override
    public int getCount() {
        return tabNames.size();
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return tabNames.get(position);
    }
}
