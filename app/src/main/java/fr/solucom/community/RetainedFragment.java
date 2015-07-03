package fr.solucom.community;

import android.app.Fragment;
import android.os.Bundle;

import fr.solucom.communitylibrary.GeneralData;
import fr.solucom.communitylibrary.Home;

/**
 * Created by wasier on 03/07/2015.
 */
public class RetainedFragment extends Fragment {
    // data object we want to retain
    private GeneralData GeneralDataRetained;
    private Home HomeRetained;

    // this method is only called once for this fragment
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // retain this fragment
        setRetainInstance(true);
    }

    public void RetainGeneralData(GeneralData GeneralDataRetained) {
        this.GeneralDataRetained = GeneralDataRetained;
    }

    public void RetainHomeData(Home HomeRetained) {
        this.HomeRetained = HomeRetained;
    }

    public GeneralData getGeneralData() {
        return GeneralDataRetained;
    }

    public Home getHomeData() {
        return HomeRetained;
    }

}
