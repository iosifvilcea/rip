package blankthings.rip.api;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import blankthings.rip.api.redditmodels.Thing;
import blankthings.rip.navigation.Navigator;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * ApiController
 *   Manages requests.
 *
 * Created by iosifvilcea on 7/20/16.
 */
public class ApiController {

    private static final String TAG = ApiController.class.getSimpleName();

    private static ApiController apiController;
    private static final ApiService apiService = new ApiService();

    /**
     * Reddit sorting types.
     */
    public enum SortType {
        TOP,
        CONTROVERSIAL,
        HOT,
        NEW,
        RANDOM,
        RISING
    }


    /**
     * @return ApiController
     */
    public static ApiController getInstance() {
        if (apiController == null) {
            apiController = new ApiController();
        }

        return apiController;
    }


    /**
     * Retrieves a list of top subreddits
     *   Default is Top, past 24hrs, 25 listings.
     *
     * @param subreddit - subreddit to retrieve
     * @param sortType (Required) - sorts by type (Hot, New, Rising, Top)
     * @param howFarBack (Optional) - length created (past hour, 24hr, week, month, year, all)
     * @param listingPerPage (Optional) - (max: 100)
     * @param after (Optional) - next page
     * @param listingCallback - Thing callback
     */
    public void listingRequest(final String subreddit,
                               final SortType sortType,
                               int listingPerPage,
                               @Nullable String howFarBack,
                               @Nullable String after,
                               final Callback<Thing> listingCallback) {

        if (TextUtils.isEmpty(subreddit)) {
            Log.e(TAG, "Sub-reddit parameter cannot be empty.");
            Navigator.INSTANCE.stopLoading();
            return;
        }

        /** TOP and CONTROVERSIAL can also query for how far back to look for a query,
         *   represented by the howFarBack parameter. */
        if (SortType.TOP == sortType || SortType.CONTROVERSIAL == sortType) {
            howFarBack = null;
        }

        if (listingPerPage == 0) {
            listingPerPage = 25;
        }


//        final String sort = (null == sortType) ? SortType.TOP.name() + "/" : sortType.name() + "/";
        final String sort = "";
        final Call<Thing> listCall = apiService
                .make()
                .getTopListings(subreddit, sort, String.valueOf(listingPerPage), howFarBack, after);
        listCall.enqueue(listingCallback);
    }


    /**
     * @param after (Optional) - next page string for pagination.
     * @param listingCallback - callback.
     */
    public void defaultSubredditsRequest(@Nullable final String after, final Callback<Thing> listingCallback) {
        Call<Thing> listCall;
        if (!TextUtils.isEmpty(after)) {
            listCall = apiService.make().getDefaultSubreddits(after);
        } else {
            listCall = apiService.make().getDefaultSubreddits();
        }
        listCall.enqueue(listingCallback);
    }

}
