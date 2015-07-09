package fr.solucom.communitylibrary;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;

/**
 * This class is used to get JSON object from the server side.
 * It deals with asynchronous response from the server,
 * by calling a main method when the response is caught.
 */

public class DataCollector {
    private static final String TAG = "DataCollector";

    /**
     * This method is used to collect home data from the server side. Since the response is asynchronous,
     * the main method "updateData" is called when the response is returned.
     *
     * @param url      the absolute URL where data are hosted
     * @param idBeacon the UUID of the beacon detected
     * @param major    the major field of the beacon detected
     * @param minor    the minor field of the beacon detected
     * @param page     the page requested by the user
     * @param callback the Volley callback used to call back the main when the response is returned  by the server
     * @see ApplicationController
     */
    public static void collectHomeData(String url, String idBeacon, int major, int minor, final String page, final VolleyCallback callback) {

        //create the URL for the specific beacon. The template follows : server/floor/UUID/major/minor/page
        url += "floor/" + idBeacon + "/" + major + "/" + minor + "/" + page;
        //log the url
        Log.i(TAG, "url used for the request" + url);
        //Creates a new resquest using volley.
        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonObject) {
                try {
                    //when the response is returned, call back the activity
                    callback.onSuccess(jsonObject);
                    Log.d(TAG, "JsonObject received" + jsonObject);
                } catch (Exception e) {
                    Log.e(TAG, "jsonObject update error: " + e.getMessage());
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e(TAG, "volley error in request: " + volleyError.getMessage());
                    }
                });
        //Call the application controller and add the request to queue
        ApplicationController.getInstance().getRequestQueue().add(request);
    }

    /**
     * This method is used to collect general data from the server side. Since the response is asynchronous,
     * the main method "updateGeneralData" is called when the response is returned.
     *
     * @param url      the absolute URL where data are hosted
     * @param callback the Volley callback used to call back the main when the response is returned  by the server
     * @see fr.solucom.communitylibrary.ApplicationController
     * @see JsonObjectRequest
     */
    public static void collectGeneralData(String url, final VolleyCallback callback) {

        //create the URL for the general data. Template follows : server/generalData
        url += "generalData";
        //LOG
        Log.i(TAG, "url used for the request" + url);

        //Creates a new request using volley.
        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonObject) {
                try {
                    //when the response is returned, callback the activity
                    callback.onSuccess(jsonObject);
                    Log.d(TAG, "JsonObject received" + jsonObject);
                } catch (Exception e) {
                    Log.e(TAG, "jsonObject update error: " + e.getMessage());
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e(TAG, "volley error in request: " + volleyError.getMessage());
                    }
                });
        //Call the application controller and add the request to queue
        ApplicationController.getInstance().getRequestQueue().add(request);
    }

    public interface VolleyCallback {
        void onSuccess(JSONArray jsonObject);
    }

}
