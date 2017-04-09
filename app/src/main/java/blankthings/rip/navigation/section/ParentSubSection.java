package blankthings.rip.navigation.section;

import com.bignerdranch.expandablerecyclerview.model.Parent;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a multi reddit section composed of all it's children.
 * Created by iosif on 4/8/17.
 */

public class ParentSubSection
        extends Section
        implements Parent<Section> {

    protected boolean initiallyExpanded = false;
    protected boolean hasChildren = false;
    protected List<Section> children = null;



    public ParentSubSection(final String id) {
        super(id);
        children = new ArrayList<>();
    }


    public ParentSubSection(final Object id, final String displayName) {
        super(id, displayName);
        children = new ArrayList<>();
    }


    public ParentSubSection(final String displayName, final List<Section> children) {
        super(displayName);
        this.children = children;
        hasChildren = (children.size() > 0);
        id = generateSubreddit();
    }


    public ParentSubSection(Section section) {
        super(section.id, section.displayName, section.isSelected);
        children = new ArrayList<>();
    }


    protected String generateSubreddit() {
        final StringBuilder sb = new StringBuilder("");
        for (int i=0; i < children.size(); i++) {

            final Section child = children.get(i);
            if (child.getId() instanceof String) {
                sb.append((String) child.id);
                if (i < children.size()-1) {
                    sb.append("+");
                }
            }
        }

        return sb.toString();
    }


    public void setInitiallyExpanded(boolean initiallyExpanded) {
        this.initiallyExpanded = initiallyExpanded;
    }


    @Override
    public List<Section> getChildList() {
        return children;
    }


    @Override
    public boolean isInitiallyExpanded() {
        return initiallyExpanded;
    }


    public boolean hasChildren() {
        return hasChildren;
    }


    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }
}
