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
import fr.solucom.communitylibrary.KMArticle;


public class KnowledgeManagementFragment extends Fragment {

    private static final String KM = "KM";
    private static final String TAG = "KMFragment";
    //Initializes the listener
    public OnFragmentInteractionListener mListener;
    // initializes the KM article that will be used to inflate the view
    private KMArticle KMArticle;

    public KnowledgeManagementFragment() {
        // Required empty public constructor
    }

    /**
     * This method is used to create a new instance of KnowledgeManagementFragment.
     * The KMArticle is passed as argument and the new fragment is returned
     *
     * @param  KMArticle     The KMArticle object used to inflate the view
     *
     * @return fragment      The KnowledgeManagementFragment created with the home passed as argument
     *
     * @see fr.solucom.communitylibrary.KMArticle
     *
     */
    public static KnowledgeManagementFragment newInstance(KMArticle KMArticle) {
        //creates the new fragment
        KnowledgeManagementFragment fragment = new KnowledgeManagementFragment();
        //create the associated bundle
        Bundle args = new Bundle();
        //put the KMArticle passed as argument in the bundle
        args.putString(KM, new Gson().toJson(KMArticle));
        //set the bundle as arg in the fragment
        fragment.setArguments(args);
        return fragment;

    }

    /**
     * Method called when the fragment is created.
     * The KMArticle passed in the argument is caught by the instance
     *
     * @param savedInstanceState The savedInstance passed. This argument should contains the bundle passed as argument when the new instance is declared.
     * @see fr.solucom.communitylibrary.KMArticle
     * @see Gson#fromJson(JsonElement, Class)
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //get the KMArticle in the arg using GSON
            this.KMArticle = new Gson().fromJson(getArguments().getString(KM), KMArticle.class);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // get the server url in the string resources
        String url = getString(R.string.Server_URL);
        // Creates the rootView with the corresponding layout inflated by the container
        View rootView = inflater.inflate(R.layout.fragment_knowledge_management, container, false);
        //Gets the Imageview in order to inflate it with the image
        final ImageView mImageView = (ImageView) rootView.findViewById(R.id.imageDetailKM);

        if (KMArticle != null) {
            // Retrieves an image specified by the URL, displays it in the UI.
            ImageRequest request = new ImageRequest(url + KMArticle.getPicture(),
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
            Log.d(TAG, "URL send for the picture: " + url + KMArticle.getPicture());
        }

        //Gets the Title textView and inflates it with the Events title
        TextView Title = (TextView) rootView.findViewById(R.id.TitleDetailKM);
        if (KMArticle != null) {
            Title.setText(KMArticle.getTitle());
        }

        //Gets the description textView and inflates it with the KM description
        TextView description = (TextView) rootView.findViewById(R.id.KM_Description);
        if (KMArticle != null) {
            description.setText(KMArticle.getDescription());
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
