package blankthings.rip.navigation;

/**
 * Created by iosifvilcea on 1/26/17.
 */

public class SectionItem {

    public SectionItem(final int id, final String title, final int icon, final boolean isChecked) {
        this.id = id;
        this.title = title;
        this.icon = icon;
        this.isChecked = isChecked;
    }

    private int id;
    private String title;
    private int icon;
    private boolean isChecked;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
