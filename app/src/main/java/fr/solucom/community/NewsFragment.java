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


public class NewsFragment extends Fragment {

    private static final String HOME = "home";
    private static final String TAG = "NewsFragment";
    // initializes the home that will be used to inflate the view
    private Home home;
    private OnFragmentInteractionListener mListener;

    public NewsFragment() {
        // Required empty public constructor
    }

    /**
     * This method is used to create a new instance of NewsFragment.
     * The home is passed as argument and the new fragment is returned
     *
     * @param  home     The home object used to inflate the view
     *
     * @return fragment The NewsFragment created with the home passed as argument
     *
     * @see fr.solucom.communitylibrary.Home
     * @see fr.solucom.communitylibrary.News
     *
     */
    public static NewsFragment newInstance(Home home) {
        //creates the new fragment
        NewsFragment fragment = new NewsFragment();
        //create the associated bundle
        Bundle args = new Bundle();
        //puts the event passed as argument in the bundle
        args.putString(HOME, new Gson().toJson(home));
        //sets the bundle as arg in the fragment
        fragment.setArguments(args);
        return fragment;

    }

    /**
     * Method called when the fragment is created.
     * The home passed in the argument is caught by the instance
     *
     * @param savedInstanceState The savedInstance passed. This argument should contains the bundle passed as argument when the new instance is declared.
     * @see NewsFragment#newInstance(Home)
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
        if (getActivity().getActionBar() != null) {
            getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        // get the server url in the string resources
        String url = getString(R.string.Server_URL);

        // Creates the rootView with the corresponding layout inflated by the container
        View rootView = inflater.inflate(R.layout.fragment_news, container, false);

        //Gets the Image view in order to inflate it with the image
        final ImageView mImageView = (ImageView) rootView.findViewById(R.id.imageNews);

        if (home != null) {
            Log.d(TAG, "home getPicture url" + home.getPictureNews());
            // Retrieves an image specified by the URL, displays it in the UI.
            ImageRequest request = new ImageRequest(url + home.getPictureNews(),
                    new Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap bitmap) {
                            //when the response is sent by the server, set the  image to the view
                            mImageView.setImageBitmap(bitmap);
                        }
                    }, 0, 0, ImageView.ScaleType.CENTER_CROP, null,
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

        //Gets the Title textView and inflates it with the News title
        TextView Title = (TextView) rootView.findViewById(R.id.TitleNews);
        Title.setText(home.getNewsTitle());


        //Gets the Title1 textView and inflates it with the news 1 title
        TextView Title1 = (TextView) rootView.findViewById(R.id.News_Title_1);
        if (home != null && home.getNews1() != null) {
            Title1.setText(home.getNews1().getTitle());
        }

        //Gets the Title2 textView and inflates it with the news 2 title
        TextView Title2 = (TextView) rootView.findViewById(R.id.News_Title_2);
        if (home != null && home.getNews2() != null) {
            Title2.setText(home.getNews2().getTitle());
        }

        Button NewsButton = (Button) rootView.findViewById(R.id.News_buttom);

        //Sets the listener for the events button
        NewsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity activity = (MainActivity) getActivity();
                //when the button is clicked, the news page should be displayed
                activity.onNavigationDrawerItemSelected(1);
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
        void onFragmentInteraction(Uri uri);
    }


}
