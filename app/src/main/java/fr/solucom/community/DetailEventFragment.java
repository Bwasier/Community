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
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import fr.solucom.communitylibrary.ApplicationController;
import fr.solucom.communitylibrary.Event;

/**
 * Detail Event fragment is used to display details on event when events page is selected by the user.
 * This fragment displays the title, the picture, the description of the event passed as argument
 */
public class DetailEventFragment extends Fragment {
    private static final String EVENT = "event";
    private static final String TAG = "DetailEventFragment";
    //Initializes the event that will be use to inflate the view
    private Event event;
    //Initializes the listener
    private OnFragmentInteractionListener mListener;

    public DetailEventFragment() {
        // Required empty public constructor
    }

    /**
     * This method is used to create a new instance of DetailEventFragment.
     * The event is passed as argument and the new fragment is returned
     *
     * @param event the event object sent to the method.
     * @return fragment the DetailEventFragment inflated with the event passed as argument
     * @see fr.solucom.communitylibrary.Event
     */
    public static DetailEventFragment newInstance(Event event) {
        // creates the new frag
        DetailEventFragment fragment = new DetailEventFragment();
        //creates the new bundle
        Bundle args = new Bundle();
        //puts the event as arg in the bundle
        args.putString(EVENT, new Gson().toJson(event));
        //set the bundle as arg in the fragment
        fragment.setArguments(args);
        return fragment;

    }

    /**
     * Method called when the fragment is created.
     * The event passed in the argument is caught by the instance
     *
     * @param savedInstanceState The savedInstance passed. This argument should contains the bundle passed as argument when the new instance is declared.
     * @see DetailEventFragment#newInstance(Event)
     * @see Gson#fromJson(JsonElement, Class)
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //get the event in the arg using GSON
            this.event = new Gson().fromJson(getArguments().getString(EVENT), Event.class);
        }
    }

    /**
     * Method called when the view is created.
     *
     * @param inflater           The layout inflater of the application. Used to instantiate the layout XML file into its corresponding View objects.
     * @param container          The viewGroup of the application. Special view that can contain other views
     * @param savedInstanceState The bundle passed as argument. Should be null so far.
     * @return rootView             The view of the DetailEventFragment inflated with the correct resources
     * @see LayoutInflater
     * @see ViewGroup
     * @see Bundle
     * @see fr.solucom.communitylibrary.Event
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // get the server url in the string resources
        String url = getString(R.string.Server_URL);
        // Creates the rootView with the corresponding layout inflated by the container
        View rootView = inflater.inflate(R.layout.fragment_detail_event, container, false);
        //Gets the Imageview in order to inflate it with the image
        final ImageView mImageView = (ImageView) rootView.findViewById(R.id.imageDetailEvent);
        //Verifies the event is not null
        if (event != null) {
            // Retrieves an image specified by the URL, displays it in the UI.
            ImageRequest request = new ImageRequest(url + event.getPicture(),
                    new Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap bitmap) {
                            //when the response is sent by the server, set the event image to the view
                            mImageView.setImageBitmap(bitmap);
                        }
                    }, 0, 0, null,
                    new Response.ErrorListener() {
                        public void onErrorResponse(VolleyError error) {
                            mImageView.setImageResource(R.drawable.ic_drawer);
                            Log.e(TAG, "Image request error: " + error);
                        }
                    });

            //Call the application controller and add the request to queue
            ApplicationController.getInstance().getRequestQueue().add(request);
            //LOG
            Log.d(TAG, "URL send for the picture: " + url + event.getPicture());
        }
        //Gets the Title textView and inflates it with the event title
        TextView Title = (TextView) rootView.findViewById(R.id.TitleDetailEvent);
        Title.setText(event.getTitle());

        //Gets the description textView and inflates it with the event description
        TextView description = (TextView) rootView.findViewById(R.id.Event_Description);
        description.setText(event.getDescription());

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
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

}
