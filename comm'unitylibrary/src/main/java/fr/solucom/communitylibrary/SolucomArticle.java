package fr.solucom.communitylibrary;

/**
 * Created by wasier on 22/06/2015.
 */

/**
 * This java class represents a general Solucom article on the server side.
 * Two Solucom articles are hosted on the server side
 *
 * @see GeneralData
 */
public class SolucomArticle {
    // The Solucom article title
    private String title;
    //The Solucom article description
    private String description;
    //The location of the Solucom article's picture on the server side
    private String picturesolucomURL;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPicture() {
        return picturesolucomURL;
    }
}
