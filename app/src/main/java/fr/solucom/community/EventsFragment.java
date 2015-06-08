package fr.solucom.community;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "width";
    private static final String ARG_PARAM2 = "height";

    // TODO: Rename and change types of parameters
    private  int width;
    private  int height;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param width Parameter 1.
     * @param height Parameter 2.
     * @return A new instance of fragment NewsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewsFragment newInstance(int width, int height) {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, width);
        args.putInt(ARG_PARAM2, height);
        fragment.setArguments(args);
        return fragment;
    }

    public EventsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            width = getArguments().getInt(ARG_PARAM1);
            height = getArguments().getInt(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment and start the Recycle view
        View rootView= inflater.inflate(R.layout.fragment_news, container, false);
        //TODO
        this.height= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,this.height, getResources().getDisplayMetrics());
        RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,this.height);
        rootView.setLayoutParams(p);
        //Configure the recycle view
        RecyclerView mainListrv = (RecyclerView) rootView.findViewById(R.id.eventsList);
        mainListrv.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        mainListrv.setLayoutManager(llm);
        //TODO ADD Data (as example some data are implemented)
        RecyclerviewAdapter adapter = new RecyclerviewAdapter(DataExtractor.getCreateList());
        mainListrv.setAdapter(adapter);

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
    public void resizeFragment(int newWidth, int newHeight) {
        if (this != null) {
            View view = this.getView();
            RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(newWidth, newHeight);
            view.setLayoutParams(p);
            view.requestLayout();
        }
    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }



}
