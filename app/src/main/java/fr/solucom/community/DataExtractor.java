package fr.solucom.community;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wasier on 01/06/2015.
 */
public class DataExtractor {
    //TODO Remove this part, just for testing
    private static List<ActivityInfo> createList() {
        List<ActivityInfo> activityInfos = new ArrayList<ActivityInfo>();
        activityInfos.add(new ActivityInfo("titre1", R.drawable.titre1));
        activityInfos.add(new ActivityInfo("titre2", R.drawable.titre2));
        activityInfos.add(new ActivityInfo("titre3", R.drawable.titre3));
        activityInfos.add(new ActivityInfo("titre1", R.drawable.titre1));
        activityInfos.add(new ActivityInfo("titre2", R.drawable.titre2));
        activityInfos.add(new ActivityInfo("titre3", R.drawable.titre3));
        activityInfos.add(new ActivityInfo("titre1", R.drawable.titre1));
        activityInfos.add(new ActivityInfo("titre2", R.drawable.titre2));
        activityInfos.add(new ActivityInfo("titre3", R.drawable.titre3));
        return activityInfos;
    }
    public static  List<ActivityInfo> getCreateList(){
        List<ActivityInfo> ActivityList = DataExtractor.createList();
        return ActivityList;
    }
}
