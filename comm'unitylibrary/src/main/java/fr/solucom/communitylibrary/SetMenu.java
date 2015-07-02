package fr.solucom.communitylibrary;

/**
 * This java class represents a restaurant menu on the server side.
 * Two restaurant menus are hosted on the server side (one for each restaurant)
 *
 * @see GeneralData
 */
public class SetMenu {
    //The restaurant's name
    private String title;
    //The menus
    private String description;
    //The location of the menu's picture on the server
    private String picturemenuURL;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPicture() {
        return picturemenuURL;
    }
}