
package blankthings.rip.api.redditmodels;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Thing implements Parcelable {

    @SerializedName("kind")
    private String kind;

    @SerializedName("data")
    private Listing listing;


    public String getKind() {
        return kind;
    }


    public void setKind(String kind) {
        this.kind = kind;
    }


    public Listing getListing() {
        return listing;
    }


    public void setListing(Listing listing) {
        this.listing = listing;
    }


    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.kind);
        dest.writeParcelable(this.listing, flags);
    }


    public Thing() {}


    protected Thing(Parcel in) {
        this.kind = in.readString();
        this.listing = in.readParcelable(Listing.class.getClassLoader());
    }


    public static final Parcelable.Creator<Thing> CREATOR = new Parcelable.Creator<Thing>() {
        @Override
        public Thing createFromParcel(Parcel source) {
            return new Thing(source);
        }

        @Override
        public Thing[] newArray(int size) {
            return new Thing[size];
        }
    };
}
