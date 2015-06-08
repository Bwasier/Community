package fr.solucom.community;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wasier on 28/05/2015.
 */

//This class is used to present all activites
    //so for an activity has just a title and a picture

public class ActivityInfo {
    protected String title;
    protected int pictureId;

    //constuctor
    ActivityInfo(String title, int pictureId){
        this.title=title;
        this.pictureId=pictureId;
    }
}



