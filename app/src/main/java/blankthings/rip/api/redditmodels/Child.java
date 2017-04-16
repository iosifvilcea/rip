
package blankthings.rip.api.redditmodels;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Child implements Parcelable {

    @SerializedName("kind")
    private String kind;

    @SerializedName("data")
    private Data data;


    public String getKind() {
        return kind;
    }


    public void setKind(String kind) {
        this.kind = kind;
    }


    public Data getData() {
        return data;
    }


    public void setData(Data data) {
        this.data = data;
    }


    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.kind);
        dest.writeParcelable(this.data, flags);
    }


    public Child() {
    }


    protected Child(Parcel in) {
        this.kind = in.readString();
        this.data = in.readParcelable(Data.class.getClassLoader());
    }


    public static final Parcelable.Creator<Child> CREATOR = new Parcelable.Creator<Child>() {
        @Override
        public Child createFromParcel(Parcel source) {
            return new Child(source);
        }

        @Override
        public Child[] newArray(int size) {
            return new Child[size];
        }
    };
}
