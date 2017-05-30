package blankthings.rip.contentproviders;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

/**
 * Controls a ContentResolver that accesses the MediaStore ContentProvider.
 * Created by iosif on 5/28/17.
 */

public class MediaResolverController {

    public static final String TAG = MediaResolverController.class.getSimpleName();

    final ContentResolver contentResolver;

    private static final Uri CONTENT_URI = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;


    public MediaResolverController(final Context context) {
        contentResolver = context.getContentResolver();
    }


    public void findFirstItem() {
        // Query for all images on external storage
        String[] projection = { MediaStore.Images.Media._ID, MediaStore.Images.Media.DATA };
        String selection = "";
        String [] selectionArgs = null;

        final Cursor cursor = contentResolver.query(CONTENT_URI, projection, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
            String data = cursor.getString(id);

            Log.e(TAG, "Id: " + id + " Data: " + data);

            while (cursor.moveToNext()) {
                id = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
                data = cursor.getString(id);
                Log.e(TAG, "Id: " + id + " Data: " + data);
            }

            cursor.close();
        }

    }
}
