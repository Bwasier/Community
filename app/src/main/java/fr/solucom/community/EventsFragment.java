package fr.solucom.community;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import fr.solucom.communitylibrary.ApplicationController;
import fr.solucom.communitylibrary.Home;

/**
 * Events fragment is used to display events information on the home page.
 * This fragment displays the title of the event part for the floor detected (the beacon),
 * the event part's picture, and a short description of the  events.
 */
public class EventsFragment extends Fragment {
    private static final String HOME = "home";
    private static final String TAG = "EventsFragment";
    // initializes the home that will be used to inflate the view
    private Home home;
    //Initializes the listener
    private OnFragmentInteractionListener mListener;

    public EventsFragment() {
        // Required empty public constructor
    }

    /**
     * This method is used to create a new instance of EventsFragment.
     * The home is passed as argument and the new fragment is returned
     *
     * @param  home     The home object used to inflate the view
     *
     * @return fragment the EventsFragment created with the home passed as argument
     *
     * @see fr.solucom.communitylibrary.Home
     * @see fr.solucom.communitylibrary.Event

     *
     */
    public static EventsFragment newInstance(Home home) {
        //creates the new fragment
        EventsFragment fragment = new EventsFragment();
        //create the associated bundle
        Bundle args = new Bundle();
        //put the event passed as argument in the bundle
        args.putString(HOME, new Gson().toJson(home));
        //set the bundle as arg in the fragment
        fragment.setArguments(args);
        return fragment;

    }

    /**
     * Method called when the fragment is created.
     * The home passed in the argument is caught by the instance
     *
     * @param savedInstanceState The savedInstance passed. This argument should contains the bundle passed as argument when the new instance is declared.
     * @see EventsFragment#newInstance(Home)
     * @see Gson#fromJson(JsonElement, Class)
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.home = new Gson().fromJson(getArguments().getString(HOME), Home.class);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //get the URL from the resources
        String url = getString(R.string.Server_URL);
        // Creates the rootView with the corresponding layout inflated by the container
        View rootView = inflater.inflate(R.layout.fragment_events, container, false);
        //Gets the Imageview in order to inflate it with the image
        final ImageView mImageView = (ImageView) rootView.findViewById(R.id.imageEvents);

        if (home != null) {
            // Retrieves an image specified by the URL, displays it in the UI.
            ImageRequest request = new ImageRequest(url + home.getPictureEvents(),
                    new Response.Listener<Bitmap>() {

                        @Override
                        public void onResponse(Bitmap bitmap) {
                            //when the response is sent by the server, set the  image to the view
                            mImageView.setImageBitmap(bitmap);
                        }
                    }, 0, 0, null,
                    new Response.ErrorListener() {
                        public void onErrorResponse(VolleyError error) {
                            mImageView.setImageResource(R.drawable.ic_drawer);
                            Log.e(TAG, "Image request error: " + error);
                        }
                    });
            //Calls the application controller and add the request to queue
            ApplicationController.getInstance().getRequestQueue().add(request);
            Log.d(TAG, "URL send for the picture: " + url + home.getPictureEvents());
        }

        //Gets the Title textView and inflates it with the Events title
        TextView Title = (TextView) rootView.findViewById(R.id.TitleEvents);
        Title.setText(home.getEventsTitle());

        //Gets the Title1 textView and inflates it with the event 1 title
        TextView Title1 = (TextView) rootView.findViewById(R.id.Events_Title_1);
        if (home != null && home.getEvent1() != null) {
            Title1.setText(home.getEvent1().getTitle());
        }
        //Gets the Title2 textView and inflates it with the event 2 title
        TextView Title2 = (TextView) rootView.findViewById(R.id.Events_Title_2);
        if (home != null && home.getEvent2() != null) {
            Title2.setText(home.getEvent2().getTitle());
        }
        Button EventsButton = (Button) rootView.findViewById(R.id.Events_buttom);

        //set the listener for the events button
        EventsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity activity = (MainActivity) getActivity();
                //when the button is clicked, the events page should be displayed
                activity.onNavigationDrawerItemSelected(2);
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
