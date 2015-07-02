package fr.solucom.communitylibrary;

/**
 * Created by wasier on 22/06/2015.
 */

/**
 * This java class represents a specific home (i.e. Data concerning a specific floor) on the server side.
 * Home data represents all data concerning a specific floor.
 * Home possesses:
 * two Events,
 * two News,
 *
 * @see Event
 * @see News
 */

public class Home {

    //The title displayed on the home (i.e. floor) page
    private String title;
    //The floor number (e.g 17, 21, 26)
    private int floor;

    //The title of the events part on the home page
    private String eventstitle;
    //The location of the events part picture on the server
    private String pictureeventsURL;
    //The first event for the specific floor
    private Event event1;
    //The second event for the specific floor
    private Event event2;

    //The title of the news part on the home page
    private String newstitle;
    //The location of the news part picture on the server
    private String picturenewsURL;
    //The first news for the specific floor
    private News news1;
    //The second news for the specific floor
    private News news2;


    public String getTitle() {
        return title;
    }

    public int getFloor() {
        return floor;
    }

    public String getPictureNews() {
        return picturenewsURL;
    }

    public String getNewsTitle() {
        return newstitle;
    }

    public String getEventsTitle() {
        return eventstitle;
    }

    public String getPictureEvents() {
        return pictureeventsURL;
    }

    public News getNews1() {
        return news1;
    }

    public News getNews2() {
        return news2;
    }

    public Event getEvent1() {
        return event1;
    }

    public Event getEvent2() {
        return event2;
    }
}

