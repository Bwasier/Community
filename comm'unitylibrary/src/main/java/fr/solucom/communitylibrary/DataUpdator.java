package fr.solucom.communitylibrary;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

public class DataUpdator {

    // TAG used for the debug mode
    private static final String TAG = "DataUpdator";

    /**
     * Method used to catch the server response for a specific beacon (i.e. for a specific floor)
     * When the response is received, the home of this activity should be updated
     *
     * @param JsonArray JSON Object representing the "home" sent by the server
     * @see
     * @see Gson
     * @see fr.solucom.communitylibrary.Home
     */
    public static Home updateData(JSONArray JsonArray) {

        //Create a new GSON to parce the JSON
        Gson gson = new Gson();
        //create a new Home
        Home home = new Home();
        try {
            JSONObject JsonObject = JsonArray.getJSONObject(0);
            // catch the home from the JSON Object using GSON method
            home = gson.fromJson(JsonObject.toString(), Home.class);
            //log the received object
            Log.d(TAG, "JSON object received: " + JsonObject.toString());
            //refresh the app
        } catch (Exception exception) {
            //log the exception
            Log.e(TAG, "GSON error: " + exception.getMessage());
        }
        return home;
    }

    /**
     * Method used to catch the server response for general data (i.e. Common data for all floors
     * When the response is received, generalData of the activity should be updated
     *
     * @param JsonArray JSON Object representing the "General data" sent by the server
     * @see Gson
     * @see GeneralData
     */
    public static GeneralData updateGeneralData(JSONArray JsonArray) {
        //Create a new GSON to parce the JSON

        Gson gson = new Gson();
        //create a new General Data
        GeneralData generalData = new GeneralData();
        //Log debug mode
        Log.d(TAG, "UpdateGeneralData called with object: " + JsonArray);


        try {
            JSONObject JsonObject = JsonArray.getJSONObject(0);
            // catch the general data from the JSON Object using the GSON method
            generalData = gson.fromJson(JsonObject.toString(), GeneralData.class);
            //log the received object
            Log.d(TAG, "JSON object received: " + JsonObject.toString());
        } catch (Exception exception) {
            //log the exception
            Log.e(TAG, "GSON error: " + exception.getMessage());
        }
        return generalData;
    }

}
