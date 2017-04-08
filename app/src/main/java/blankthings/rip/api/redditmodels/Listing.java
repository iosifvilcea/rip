
package blankthings.rip.api.redditmodels;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Listing {

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

}
