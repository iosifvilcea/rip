
package blankthings.rip.api.redditmodels.preview;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Preview {

    @SerializedName("images")
    @Expose
    private List<Image> images = null;

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

}
