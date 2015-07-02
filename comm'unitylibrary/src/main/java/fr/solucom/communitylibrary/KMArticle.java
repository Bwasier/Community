package fr.solucom.communitylibrary;

/**
 * This java class represents a Knowledge management article on the server side.
 * Two Knowledge management articles are hosted on the server side
 *
 * @see GeneralData
 */

public class KMArticle {
    // The KM article title
    private String title;
    //The KM article description
    private String description;
    //The location of the KM article's picture on the server side
    private String pictureKMURL;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPicture() {
        return pictureKMURL;
    }
}