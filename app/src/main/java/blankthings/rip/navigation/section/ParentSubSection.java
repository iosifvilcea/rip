package blankthings.rip.navigation.section;

import com.bignerdranch.expandablerecyclerview.model.Parent;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a multi reddit section composed of all it's children.
 * Created by iosif on 4/8/17.
 */

public class ParentSubSection implements Parent<ParentSubSection.SubSection> {


    private String displayName;
    private String subreddit;
    private boolean isChecked;
    private boolean initiallyExpanded;
    private List<SubSection> children;


    public ParentSubSection(final String displayName) {
        this.displayName = displayName;
        this.subreddit = displayName;
        children = new ArrayList<>();
    }


    public ParentSubSection(final String displayName, final List<SubSection> children) {
        this.displayName = displayName;
        this.children = children;
        generateSubreddit();
    }


    protected void generateSubreddit() {
        final StringBuilder sb = new StringBuilder("");
        for (int i=0; i < children.size(); i++) {

            final SubSection child = children.get(i);
            sb.append(child.getSubreddit());
            if (i < children.size()-1) {
                sb.append("+");
            }
        }
        subreddit = sb.toString();
    }


    public String getSubreddit() {
        return subreddit;
    }


    public void setSubreddits(String subreddit) {
        this.subreddit = subreddit;
    }


    public String getDisplayName() {
        return displayName;
    }


    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }


    public void setInitiallyExpanded(boolean initiallyExpanded) {
        this.initiallyExpanded = initiallyExpanded;
    }


    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }


    @Override
    public List<SubSection> getChildList() {
        return children;
    }


    @Override
    public boolean isInitiallyExpanded() {
        return initiallyExpanded;
    }



    /**
     * Represents a Subreddit section model.
     */
    public static class SubSection {

        private String displayName;
        private String subreddit;
        private boolean isChecked;

        public SubSection(String displayName, String subreddit) {
            this.displayName = displayName;
            this.subreddit = subreddit;
        }

        public String getSubreddit() {
            return subreddit;
        }


        public void setSubreddit(String subreddit) {
            this.subreddit = subreddit;
        }


        public String getDisplayName() {
            return displayName;
        }


        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }
    }

}
