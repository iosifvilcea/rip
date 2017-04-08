package blankthings.rip.api.redditmodels;

import java.util.ArrayList;

/**
 * Created by iosifvilcea on 1/22/17.
 */

public class ThingWrapper {


    private final Thing thing;
    private final ArrayList<Child> children = new ArrayList<>();


    public ThingWrapper(final Thing thing) {
        if (thing == null) {
            throw new NullPointerException("ThingWrapper cannot have null parameters.");
        }

        this.thing = thing;
        setupThingWrapper();
    }


    private void setupThingWrapper() {
        if (thing.getListing() == null || thing.getListing().getChildren() == null) {
            return;
        }

        children.addAll(thing.getListing().getChildren());
    }


    public String getBefore() {
        return thing.getListing().getBefore();
    }


    public String getAfter() {
        return thing.getListing().getAfter();
    }


    public ArrayList<Child> getChildren() {
        return children;
    }


    public String getChildTitle(final int position) {
        if (children != null
                && children.size() > 0
                && children.get(position).getData() != null
                && children.get(position).getData().getTitle() != null) {

            return children.get(position).getData().getTitle();
        }

        return "";
    }

}
