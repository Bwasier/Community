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
import fr.solucom.communitylibrary.SolucomArticle;


public class SolucomFragment extends Fragment {

    private static final String SolucomArticle = "SolucomArticle";
    private static final String TAG = "SolucomFragment";
    public OnFragmentInteractionListener mListener;
    private SolucomArticle article;

    public SolucomFragment() {
        // Required empty public constructor
    }

    /**
     * This method is used to create a new instance of SolucomFragment.
     * The Solucom article is passed as argument and the new fragment is returned
     *
     * @param article   The SolucomArticle object used to inflate the view
     * @return fragment The SolucomFragment created with the SolucomArticle passed as argument
     *
     * @see fr.solucom.communitylibrary.SolucomArticle
     */
    public static SolucomFragment newInstance(SolucomArticle article) {
        //creates the new fragment
        SolucomFragment fragment = new SolucomFragment();
        //creates the associated bundle
        Bundle args = new Bundle();
        //puts the event passed as argument in the bundle
        args.putString(SolucomArticle, new Gson().toJson(article));
        //sets the bundle as arg in the fragment
        fragment.setArguments(args);
        return fragment;

    }

    /**
     * Method called when the fragment is created.
     * The Solucom Article passed in the argument is caught by the instance
     *
     * @param savedInstanceState The savedInstance passed. This argument should contains the bundle passed as argument when the new instance is declared.
     * @see SolucomFragment#newInstance(SolucomArticle)
     * @see Gson#fromJson(JsonElement, Class)
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.article = new Gson().fromJson(getArguments().getString(SolucomArticle), SolucomArticle.class);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // gets the server url in the string resources
        String url = getString(R.string.Server_URL);

        // Creates the rootView with the corresponding layout inflated by the container
        View rootView = inflater.inflate(R.layout.fragment_solucom, container, false);

        //Gets the Image view in order to inflate it with the image
        final ImageView mImageView = (ImageView) rootView.findViewById(R.id.imageDetailSolucom);

        if (article != null) {
            // Retrieves an image specified by the URL, displays it in the UI.
            ImageRequest request = new ImageRequest(url + article.getPicture(),
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
            Log.d(TAG, "URL send for the picture: " + url + article.getPicture());
        }

        //Gets the Title textView and inflates it with the Article title
        TextView Title = (TextView) rootView.findViewById(R.id.TitleDetailSolucom);
        if (article != null) {
            Title.setText(article.getTitle());
        }

        //Gets the description textView and inflates it with the article description
        TextView description = (TextView) rootView.findViewById(R.id.Solucom_Description);
        if (article != null) {
            description.setText(article.getDescription());
        }
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
