package blankthings.rip.tools;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Created by iosifvilcea on 7/9/16.
 */
public class Utility {


    private Utility() {
    }


    public static float dpToPixels(final Context context, final int dp) {
        final Resources r = context.getResources();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
    }
}
