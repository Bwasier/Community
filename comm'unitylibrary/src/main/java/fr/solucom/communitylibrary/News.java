package fr.solucom.communitylibrary;

/**
 * Created by wasier on 22/06/2015.
 */

/**
 * This java class represents an news hosted in the server. Each floor possesses two news.
 *
 * @see Home
 */
public class News {

    //The title of the news
    private String title;
    //The news' description
    private String description;
    //The news' picture location on the server
    private String picturenewsURL;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPicture() {
        return picturenewsURL;
    }
}
