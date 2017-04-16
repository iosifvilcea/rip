
package blankthings.rip.api.redditmodels;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import blankthings.rip.api.redditmodels.preview.Preview;

public class Data implements Parcelable {

    @SerializedName("contest_mode")
    private Boolean contestMode;

    @SerializedName("banned_by")
    private String bannedBy;

    @SerializedName("domain")
    private String domain;

    @SerializedName("subreddit")
    private String subreddit;

    @SerializedName("selftext_html")
    private String selftextHtml;

    @SerializedName("selftext")
    private String selftext;

    @SerializedName("likes")
    private Boolean likes;

    // TODO: 4/16/17 - Needs concrete obj.
//    @SerializedName("suggested_sort")
//    private Object suggestedSort;
//
//    @SerializedName("user_reports")
//    private List<Object> userReports = null;
//
//    @SerializedName("secure_media")
//    private Object secureMedia;

    @SerializedName("saved")
    private Boolean saved;

    @SerializedName("id")
    private String id;

    @SerializedName("gilded")
    private Integer gilded;

    @SerializedName("clicked")
    private Boolean clicked;

    // TODO: 4/16/17 - Needs concrete obj.
//    @SerializedName("report_reasons")
//    private Object reportReasons;

    @SerializedName("author")
    private String author;

    @SerializedName("media")
    private Object media;

    @SerializedName("name")
    private String name;

    @SerializedName("score")
    private Integer score;

    @SerializedName("approved_by")
    private Object approvedBy;

    @SerializedName("over_18")
    private Boolean over18;

    @SerializedName("removal_reason")
    private Object removalReason;

    @SerializedName("hidden")
    private Boolean hidden;

    @SerializedName("preview")
    private Preview preview;

    @SerializedName("thumbnail")
    private String thumbnail;

    @SerializedName("subreddit_id")
    private String subredditId;

    // TODO: 4/16/17 - Needs concrete obj.
//    @SerializedName("edited")    /**     NOTE: "Edited" sometimes returns as Boolean,      */
//    private Object edited;       /**     NOTE:   sometimes as Integer.                      */

    @SerializedName("link_flair_css_class")
    private String linkFlairCssClass;

    @SerializedName("author_flair_css_class")
    private String authorFlairCssClass;

    @SerializedName("downs")
    private Integer downs;

    // TODO: 4/16/17 - Needs concrete obj.
//    @SerializedName("mod_reports")
//    private List<Object> modReports = null;

    @SerializedName("archived")
    private Boolean archived;

    @SerializedName("is_self")
    private Boolean isSelf;

    @SerializedName("hide_score")
    private Boolean hideScore;

    @SerializedName("spoiler")
    private Boolean spoiler;

    @SerializedName("permalink")
    private String permalink;

    @SerializedName("locked")
    private Boolean locked;

    @SerializedName("stickied")
    private Boolean stickied;

    @SerializedName("created")
    private Integer created;

    @SerializedName("url")
    private String url;

    @SerializedName("author_flair_text")
    private String authorFlairText;

    @SerializedName("quarantine")
    private Boolean quarantine;

    @SerializedName("title")
    private String title;

    @SerializedName("created_utc")
    private Long createdUtc;

    @SerializedName("link_flair_text")
    private String linkFlairText;

    @SerializedName("distinguished")
    private String distinguished;

    @SerializedName("num_comments")
    private Integer numComments;

    @SerializedName("visited")
    private Boolean visited;

    @SerializedName("num_reports")
    private Integer numReports;

    @SerializedName("ups")
    private Integer ups;

    public Boolean getSelf() {
        return isSelf;
    }

    public void setSelf(Boolean self) {
        isSelf = self;
    }

    public Preview getPreview() {
        return preview;
    }

    public void setPreview(Preview preview) {
        this.preview = preview;
    }

    public void setContestMode(Boolean contestMode) {
        this.contestMode = contestMode;
    }

    public Boolean getContestMode() {
        return contestMode;
    }

    public String getBannedBy() {
        return bannedBy;
    }

    public void setBannedBy(String bannedBy) {
        this.bannedBy = bannedBy;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getSubreddit() {
        return subreddit;
    }

    public void setSubreddit(String subreddit) {
        this.subreddit = subreddit;
    }

    public String getSelftextHtml() {
        return selftextHtml;
    }

    public void setSelftextHtml(String selftextHtml) {
        this.selftextHtml = selftextHtml;
    }

    public String getSelftext() {
        return selftext;
    }

    public void setSelftext(String selftext) {
        this.selftext = selftext;
    }

    public Boolean getLikes() {
        return likes;
    }

    public void setLikes(Boolean likes) {
        this.likes = likes;
    }

//    public Object getSuggestedSort() {
//        return suggestedSort;
//    }
//
//    public void setSuggestedSort(Object suggestedSort) {
//        this.suggestedSort = suggestedSort;
//    }
//
//    public List<Object> getUserReports() {
//        return userReports;
//    }
//
//    public void setUserReports(List<Object> userReports) {
//        this.userReports = userReports;
//    }
//
//    public Object getSecureMedia() {
//        return secureMedia;
//    }
//
//    public void setSecureMedia(Object secureMedia) {
//        this.secureMedia = secureMedia;
//    }

    public Boolean getSaved() {
        return saved;
    }

    public void setSaved(Boolean saved) {
        this.saved = saved;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getGilded() {
        return gilded;
    }

    public void setGilded(Integer gilded) {
        this.gilded = gilded;
    }

    public Boolean getClicked() {
        return clicked;
    }

    public void setClicked(Boolean clicked) {
        this.clicked = clicked;
    }

//    public Object getReportReasons() {
//        return reportReasons;
//    }
//
//    public void setReportReasons(Object reportReasons) {
//        this.reportReasons = reportReasons;
//    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Object getMedia() {
        return media;
    }

    public void setMedia(Object media) {
        this.media = media;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Object getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(Object approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Boolean getOver18() {
        return over18;
    }

    public void setOver18(Boolean over18) {
        this.over18 = over18;
    }

    public Object getRemovalReason() {
        return removalReason;
    }

    public void setRemovalReason(Object removalReason) {
        this.removalReason = removalReason;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getSubredditId() {
        return subredditId;
    }

    public void setSubredditId(String subredditId) {
        this.subredditId = subredditId;
    }

//    public Object getEdited() {
//        return edited;
//    }
//
//    public void setEdited(Object edited) {
//        this.edited = edited;
//    }

    public String getLinkFlairCssClass() {
        return linkFlairCssClass;
    }

    public void setLinkFlairCssClass(String linkFlairCssClass) {
        this.linkFlairCssClass = linkFlairCssClass;
    }

    public String getAuthorFlairCssClass() {
        return authorFlairCssClass;
    }

    public void setAuthorFlairCssClass(String authorFlairCssClass) {
        this.authorFlairCssClass = authorFlairCssClass;
    }

    public Integer getDowns() {
        return downs;
    }

    public void setDowns(Integer downs) {
        this.downs = downs;
    }

//    public List<Object> getModReports() {
//        return modReports;
//    }
//
//    public void setModReports(List<Object> modReports) {
//        this.modReports = modReports;
//    }

    public Boolean getArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public Boolean getIsSelf() {
        return isSelf;
    }

    public void setIsSelf(Boolean isSelf) {
        this.isSelf = isSelf;
    }

    public Boolean getHideScore() {
        return hideScore;
    }

    public void setHideScore(Boolean hideScore) {
        this.hideScore = hideScore;
    }

    public Boolean getSpoiler() {
        return spoiler;
    }

    public void setSpoiler(Boolean spoiler) {
        this.spoiler = spoiler;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Boolean getStickied() {
        return stickied;
    }

    public void setStickied(Boolean stickied) {
        this.stickied = stickied;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthorFlairText() {
        return authorFlairText;
    }

    public void setAuthorFlairText(String authorFlairText) {
        this.authorFlairText = authorFlairText;
    }

    public Boolean getQuarantine() {
        return quarantine;
    }

    public void setQuarantine(Boolean quarantine) {
        this.quarantine = quarantine;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCreatedUtc() {
        return createdUtc;
    }

    public void setCreatedUtc(Long createdUtc) {
        this.createdUtc = createdUtc;
    }

    public String getLinkFlairText() {
        return linkFlairText;
    }

    public void setLinkFlairText(String linkFlairText) {
        this.linkFlairText = linkFlairText;
    }

    public String getDistinguished() {
        return distinguished;
    }

    public void setDistinguished(String distinguished) {
        this.distinguished = distinguished;
    }

    public Integer getNumComments() {
        return numComments;
    }

    public void setNumComments(Integer numComments) {
        this.numComments = numComments;
    }

    public Boolean getVisited() {
        return visited;
    }

    public void setVisited(Boolean visited) {
        this.visited = visited;
    }

    public Integer getNumReports() {
        return numReports;
    }

    public void setNumReports(Integer numReports) {
        this.numReports = numReports;
    }

    public Integer getUps() {
        return ups;
    }

    public void setUps(Integer ups) {
        this.ups = ups;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.contestMode);
        dest.writeString(this.bannedBy);
        dest.writeString(this.domain);
        dest.writeString(this.subreddit);
        dest.writeString(this.selftextHtml);
        dest.writeString(this.selftext);
        dest.writeValue(this.likes);
//        dest.writeParcelable(this.suggestedSort, flags);
//        dest.writeList(this.userReports);
//        dest.writeParcelable(this.secureMedia, flags);
        dest.writeValue(this.saved);
        dest.writeString(this.id);
        dest.writeValue(this.gilded);
        dest.writeValue(this.clicked);
//        dest.writeParcelable(this.reportReasons, flags);
        dest.writeString(this.author);
//        dest.writeParcelable(this.media, flags);
        dest.writeString(this.name);
        dest.writeValue(this.score);
//        dest.writeParcelable(this.approvedBy, flags);
        dest.writeValue(this.over18);
//        dest.writeParcelable(this.removalReason, flags);
        dest.writeValue(this.hidden);
//        dest.writeParcelable(this.preview, flags);
        dest.writeString(this.thumbnail);
        dest.writeString(this.subredditId);
//        dest.writeParcelable(this.edited, flags);
        dest.writeString(this.linkFlairCssClass);
        dest.writeString(this.authorFlairCssClass);
        dest.writeValue(this.downs);
//        dest.writeList(this.modReports);
        dest.writeValue(this.archived);
        dest.writeValue(this.isSelf);
        dest.writeValue(this.hideScore);
        dest.writeValue(this.spoiler);
        dest.writeString(this.permalink);
        dest.writeValue(this.locked);
        dest.writeValue(this.stickied);
        dest.writeValue(this.created);
        dest.writeString(this.url);
        dest.writeString(this.authorFlairText);
        dest.writeValue(this.quarantine);
        dest.writeString(this.title);
        dest.writeValue(this.createdUtc);
        dest.writeString(this.linkFlairText);
        dest.writeString(this.distinguished);
        dest.writeValue(this.numComments);
        dest.writeValue(this.visited);
        dest.writeValue(this.numReports);
        dest.writeValue(this.ups);
    }

    public Data() {
    }

    protected Data(Parcel in) {
        this.contestMode = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.bannedBy = in.readString();
        this.domain = in.readString();
        this.subreddit = in.readString();
        this.selftextHtml = in.readString();
        this.selftext = in.readString();
        this.likes = (Boolean) in.readValue(Boolean.class.getClassLoader());
//        this.suggestedSort = in.readParcelable(Object.class.getClassLoader());
//        this.userReports = new ArrayList<Object>();
//        in.readList(this.userReports, Object.class.getClassLoader());
//        this.secureMedia = in.readParcelable(Object.class.getClassLoader());
        this.saved = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.id = in.readString();
        this.gilded = (Integer) in.readValue(Integer.class.getClassLoader());
        this.clicked = (Boolean) in.readValue(Boolean.class.getClassLoader());
//        this.reportReasons = in.readParcelable(Object.class.getClassLoader());
        this.author = in.readString();
        this.media = in.readParcelable(Object.class.getClassLoader());
        this.name = in.readString();
        this.score = (Integer) in.readValue(Integer.class.getClassLoader());
        this.approvedBy = in.readParcelable(Object.class.getClassLoader());
        this.over18 = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.removalReason = in.readParcelable(Object.class.getClassLoader());
        this.hidden = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.preview = in.readParcelable(Preview.class.getClassLoader());
        this.thumbnail = in.readString();
        this.subredditId = in.readString();
//        this.edited = in.readParcelable(Object.class.getClassLoader());
        this.linkFlairCssClass = in.readString();
        this.authorFlairCssClass = in.readString();
        this.downs = (Integer) in.readValue(Integer.class.getClassLoader());
//        this.modReports = new ArrayList<Object>();
//        in.readList(this.modReports, Object.class.getClassLoader());
        this.archived = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.isSelf = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.hideScore = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.spoiler = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.permalink = in.readString();
        this.locked = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.stickied = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.created = (Integer) in.readValue(Integer.class.getClassLoader());
        this.url = in.readString();
        this.authorFlairText = in.readString();
        this.quarantine = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.title = in.readString();
        this.createdUtc = (Long) in.readValue(Long.class.getClassLoader());
        this.linkFlairText = in.readString();
        this.distinguished = in.readString();
        this.numComments = (Integer) in.readValue(Integer.class.getClassLoader());
        this.visited = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.numReports = (Integer) in.readValue(Integer.class.getClassLoader());
        this.ups = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<Data> CREATOR = new Parcelable.Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel source) {
            return new Data(source);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };
}
