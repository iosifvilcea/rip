package blankthings.rip.sections.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Used to load terms, copyright.
 *
 * Created by iosifvilcea on 1/26/17.
 */

public class BaseWebViewFragment extends Fragment {

    public static final String TAG = BaseWebViewFragment.class.getSimpleName();
    private static final String DEFAULT_URL = "";
    private static final String URL_KEY = "url_key";
    private WebView webView;

    public static BaseWebViewFragment newInstance() {
        return newInstance(DEFAULT_URL);
    }


    public static BaseWebViewFragment newInstance(final String url) {
        Bundle args = new Bundle();
        args.putString(URL_KEY, url);

        BaseWebViewFragment fragment = new BaseWebViewFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        webView = new WebView(getContext());
        return webView;
    }

    
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments().get(URL_KEY) != null) {
            final String url = (String) getArguments().get(URL_KEY);
            webView.loadUrl(url);
        } else {
            webView.loadUrl(DEFAULT_URL);
        }

        final WebChromeClient client = new WebChromeClient();
        webView.setWebChromeClient(client);
    }


    @Override
    public void onPause() {
        super.onPause();
        webView.onPause();
    }


    @Override
    public void onResume() {
        webView.onResume();
        super.onResume();
    }


    @Override
    public void onDestroy() {
        if (webView != null) {
            webView.destroy();
        }
        super.onDestroy();
    }
}
