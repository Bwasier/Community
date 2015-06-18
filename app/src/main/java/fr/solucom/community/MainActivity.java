package fr.solucom.community;

import android.app.Activity;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity
        implements
            NavigationDrawerFragment.NavigationDrawerCallbacks,
            EventsFragment.OnFragmentInteractionListener,
            EventsTitleFragment.OnFragmentInteractionListener,
            NewsFragment.OnFragmentInteractionListener,
            NewsTitleFragment.OnFragmentInteractionListener,
            BlankFragment.OnFragmentInteractionListener,
            KnowledgeManagementFragment.OnFragmentInteractionListener,
            KnowledgeManagementTitleFragment.OnFragmentInteractionListener,
            SolucomFragment.OnFragmentInteractionListener,
            SolucomTitleFragment.OnFragmentInteractionListener,
            FoodFragment.OnFragmentInteractionListener,
            FoodTitleFragment.OnFragmentInteractionListener
        {

    //TODO remove this part and catch the beconID
    public String UUIDbeacon = "B9407F30-F5F8-466E-AFF9-25556B57FE6D";
    public String floor = "connexion...";
    String url ="http://10.100.203.13/floor";
    JSONObject rep = null;
    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //call the dataCollector with the UUID and the home page. When data arrived, update data is call
        collectData(UUIDbeacon, "home");
        setContentView(R.layout.activity_main);
        //Create a new drawer
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));



    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // call the new fragment
        Fragment fragmentTitle1 = null;
        Fragment fragment1= null;
        Fragment fragmentTitle2 = null;
        Fragment fragment2= null;

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch (position) {
            // default is the home so call the home frag and change title on the action bar
            default:
                fragmentTitle1 = NewsTitleFragment.newInstance(floor);
                fragment1= NewsFragment.newInstance(0,240,floor);
                fragmentTitle2 = EventsTitleFragment.newInstance(floor);
                fragment2= EventsFragment.newInstance(0,240);
                mTitle = getString(R.string.title_section1) +" " + floor;
                break;
            // News selected
            case 1:
                fragmentTitle1 = NewsTitleFragment.newInstance(floor);
                fragment1= NewsFragment.newInstance(0,490,"17");
                fragmentTitle2 = new BlankFragment();
                fragment2= new BlankFragment();
                mTitle = getString(R.string.title_section2) +" " + floor;
                break;
            // Events selected on the main
            case 2:
                fragmentTitle1 = EventsTitleFragment.newInstance(floor);
                fragment1= EventsFragment.newInstance(0,490);
                fragmentTitle2 = new BlankFragment();
                fragment2= new BlankFragment();
                mTitle = getString(R.string.title_section3) +" " + floor;
                break;
            //TODO
            case 3:
                fragmentTitle1 = new KnowledgeManagementTitleFragment();
                fragment1= KnowledgeManagementFragment.newInstance(0,490);
                fragmentTitle2 = new BlankFragment();
                fragment2= new BlankFragment();
                mTitle = getString(R.string.title_section4);
                break;
            //TODO
            case 4:
                fragmentTitle1 = new FoodTitleFragment();
                fragment1= FoodFragment.newInstance(0,240);
                fragmentTitle2 = new FoodTitleFragment();
                fragment2= FoodFragment.newInstance(0,240);
                mTitle = getString(R.string.title_section5) ;
                break;
            //TODO
            case 5:
                fragmentTitle1 = new SolucomTitleFragment();
                fragment1= SolucomFragment.newInstance(0,490);
                fragmentTitle2 = new BlankFragment();
                fragment2= new BlankFragment();
                mTitle = getString(R.string.title_section6);
                break;
            //TODO
            case 6:
                break;
        }
        fragmentTransaction.replace(R.id.containerTitle1, fragmentTitle1);
        fragmentTransaction.replace(R.id.container1, fragment1);
        fragmentTransaction.replace(R.id.containerTitle2, fragmentTitle2);
        fragmentTransaction.replace(R.id.container2, fragment2);
        fragmentTransaction.commit();

    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void onFragmentInteraction(Uri uri) {
        //leave it empty
    }

    //TODO put it in other activity
    public void collectData(String idBeacon, String page) {

        url=url+"/"+idBeacon+"/"+page;

        JsonObjectRequest request = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    updateData(jsonObject.getJSONObject("home"));

                    //TODO
                } catch (JSONException volleyError) {
                    Toast.makeText(getApplicationContext(),
                            volleyError.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(getApplicationContext(),
                                volleyError.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        ApplicationController.getInstance().getRequestQueue().add(request);

     }
    public void updateData(JSONObject JsonObject){
        try {
        floor= JsonObject.getString("floor");
        onNavigationDrawerItemSelected(0);
        }catch (JSONException volleyError) {
            Toast.makeText(getApplicationContext(),
                    volleyError.getMessage(), Toast.LENGTH_SHORT).show();
            }
    }
}

