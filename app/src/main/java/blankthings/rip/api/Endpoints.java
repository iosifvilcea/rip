package blankthings.rip.api;

import blankthings.rip.api.redditmodels.Thing;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Endpoints for our Retrofit Client.
 *
 * Created by iosifvilcea on 2/12/17.
 */

public interface Endpoints {

    String BASE_URL = "http://reddit.com/";

    @GET("/r/{subreddit}/{sort}.json")
    Call<Thing> getTopListings(@Path("subreddit") String subreddit,
                               @Path("sort") String sort,
                               @Query("limit") String listingPerPage,
                               @Query("t") String howFarBack,
                               @Query("after") String after);

    /**
     * Get list of subreddits endpoints.
     */

    @GET("subreddits/default.json")
    Call<Thing> getDefaultSubreddits();


    @GET("subreddits/default.json")
    Call<Thing> getDefaultSubreddits(@Query("after") String after);


    /**
     * Popular sorts based on the activity of the subreddit.
     *   Note: the position of the subreddits can shift around.
     */
    @GET("subreddits/popular.json")
    Call<Thing> getPopularSubreddits();


    @GET("subreddits/popular.json")
    Call<Thing> getPopularSubreddits(@Query("after") String after);


    /**
     * New sorts the subreddits based on their creation date, newest first.
     */
    @GET("subreddits/new.json")
    Call<Thing> getNewSubreddits();


    @GET("subreddits/new.json")
    Call<Thing> getNewSubreddits(@Query("after") String after);
}
