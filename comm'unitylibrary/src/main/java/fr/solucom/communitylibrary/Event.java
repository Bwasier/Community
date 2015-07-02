package fr.solucom.communitylibrary;

/**
 * Created by wasier on 22/06/2015.
 */

/**
 * This java class represents an event hosted in the server. Each floor possesses two events.
 *
 * @see Home
 */
public class Event {

    //The title of the event (e.g. "New ASI event next Friday")
    private String title;
    //The event's description
    private String description;
    //The event's picture location on the server
    private String pictureeventURL;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPicture() {
        return pictureeventURL;
    }
}
