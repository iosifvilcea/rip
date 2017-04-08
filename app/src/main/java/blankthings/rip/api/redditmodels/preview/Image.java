
package blankthings.rip.api.redditmodels.preview;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Image {

    @SerializedName("source")
    private Source source;

    @SerializedName("resolutions")
    private List<Resolution> resolutions = null;

    @SerializedName("id")
    private String id;

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public List<Resolution> getResolutions() {
        return resolutions;
    }

    public void setResolutions(List<Resolution> resolutions) {
        this.resolutions = resolutions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
