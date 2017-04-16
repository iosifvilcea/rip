
package blankthings.rip.api.redditmodels;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Listing implements Parcelable {

    @SerializedName("modhash")
    private String modhash;

    @SerializedName("children")
    private List<Child> children = null;

    @SerializedName("after")
    private String after;

    @SerializedName("before")
    private String before;


    public String getModhash() {
        return modhash;
    }


    public void setModhash(String modhash) {
        this.modhash = modhash;
    }


    public List<Child> getChildren() {
        return children;
    }


    public void setChildren(List<Child> children) {
        this.children = children;
    }


    public String getAfter() {
        return after;
    }


    public void setAfter(String after) {
        this.after = after;
    }


    public String getBefore() {
        return before;
    }


    public void setBefore(String before) {
        this.before = before;
    }


    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.modhash);
        dest.writeTypedList(this.children);
        dest.writeString(this.after);
        dest.writeString(this.before);
    }


    public Listing() {
    }


    protected Listing(Parcel in) {
        this.modhash = in.readString();
        this.children = in.createTypedArrayList(Child.CREATOR);
        this.after = in.readString();
        this.before = in.readString();
    }


    public static final Parcelable.Creator<Listing> CREATOR = new Parcelable.Creator<Listing>() {
        @Override
        public Listing createFromParcel(Parcel source) {
            return new Listing(source);
        }

        @Override
        public Listing[] newArray(int size) {
            return new Listing[size];
        }
    };
}
