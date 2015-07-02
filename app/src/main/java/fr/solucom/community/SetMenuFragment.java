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
import fr.solucom.communitylibrary.SetMenu;


public class SetMenuFragment extends Fragment {

    private static final String MENU = "menu";
    private static final String TAG = "SetMenuFragment";
    // initializes the home that will be used to inflate the view
    private SetMenu menu;
    private OnFragmentInteractionListener mListener;

    public SetMenuFragment() {
        // Required empty public constructor
    }

    /**
     * This method is used to create a new instance of SetMenuFragment.
     * The menu is passed as argument and the new fragment is returned
     *
     * @param  menu     The menu object used to inflate the view
     *
     * @return fragment The SetMenuFragment created with the home passed as argument
     *
     * @see fr.solucom.communitylibrary.SetMenu
     *
     */
    public static SetMenuFragment newInstance(SetMenu menu) {
        //creates the new fragment
        SetMenuFragment fragment = new SetMenuFragment();
        //create the associated bundle
        Bundle args = new Bundle();
        //put the menu passed as argument in the bundle
        args.putString(MENU, new Gson().toJson(menu));
        //set the bundle as arg in the fragment
        fragment.setArguments(args);
        return fragment;

    }

    /**
     * Method called when the fragment is created.
     * The menu passed in the argument is caught by the instance
     *
     * @param savedInstanceState The savedInstance passed. This argument should contains the bundle passed as argument when the new instance is declared.
     * @see SetMenuFragment#newInstance(SetMenu)
     * @see Gson#fromJson(JsonElement, Class)
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.menu = new Gson().fromJson(getArguments().getString(MENU), SetMenu.class);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // get the server url in the string resources
        String url = getString(R.string.Server_URL);

        // Creates the rootView with the corresponding layout inflated by the container
        View rootView = inflater.inflate(R.layout.fragment_setmenu, container, false);

        //Gets the Image view in order to inflate it with the image
        final ImageView mImageView = (ImageView) rootView.findViewById(R.id.imageDetailMenu);

        if (menu != null) {
            // Retrieves an image specified by the URL, displays it in the UI.
            ImageRequest request = new ImageRequest(url + menu.getPicture(),
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
            Log.d(TAG, "URL send for the picture: " + url + menu.getPicture());
        }

        //Gets the Title textView and inflates it with the menu title
        TextView Title = (TextView) rootView.findViewById(R.id.TitleDetailMenu);
        Title.setText(menu.getTitle());

        //Gets the description textView and inflates it with the menu description
        TextView description = (TextView) rootView.findViewById(R.id.Menu_Description);
        description.setText(menu.getDescription());

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
