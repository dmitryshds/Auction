package biz.bagira.auction.entities;

/**
 * Created by Dmitriy on 23.02.2017.
 */
public enum Title {
    MR("Mr") , MRS("Mrs");

    private String title;

    Title(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
