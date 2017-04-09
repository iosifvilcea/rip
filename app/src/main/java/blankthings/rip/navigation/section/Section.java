package blankthings.rip.navigation.section;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.google.gson.internal.Primitives;

import java.lang.ref.SoftReference;
import java.util.List;

/**
 * Describes a Section object.
 * Created by iosif on 4/9/17.
 */

public class Section {

    protected Object id = null;
    protected String displayName = "";
    protected boolean isSelected = false;


    public Section(Object id) {
        this(id, null);
    }


    public Section(Object id, String displayName) {
        this(id, displayName, false);
    }


    public Section(final Object id, final String displayName, final boolean isSelected) {
        this.id = id;

        if (id instanceof String) {
            this.displayName = (String) id;

        } else if (!TextUtils.isEmpty(displayName)) {
            this.displayName = displayName;
        }

        this.isSelected = isSelected;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
