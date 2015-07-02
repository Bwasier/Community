package fr.solucom.communitylibrary;

/**
 * Created by wasier on 17/06/2015.
 */

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * This class is used to initialize all Volley core objects
 */
public class ApplicationController extends Application {

    private static ApplicationController sInstance;
    private RequestQueue mRequestQueue;

    public synchronized static ApplicationController getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //when the application controller is created, creates a new Volley request queue
        mRequestQueue = Volley.newRequestQueue(this);
        sInstance = this;
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

}