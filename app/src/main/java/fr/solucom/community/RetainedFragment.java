package fr.solucom.community;

import android.app.Fragment;
import android.os.Bundle;

import fr.solucom.communitylibrary.GeneralData;
import fr.solucom.communitylibrary.Home;

/**
 * Created by wasier on 03/07/2015.
 */

/**
 * This fragment is used to retain data when the orientation is changed
 */
public class RetainedFragment extends Fragment {
    // General data we want to retain
    private GeneralData GeneralDataRetained;
    // Home data we want to retain
    private Home HomeRetained;

    // this method is only called once for this fragment
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // retain this fragment
        setRetainInstance(true);
    }

    /**
     * @param GeneralDataRetained The general data object to be retained
     */
    public void RetainGeneralData(GeneralData GeneralDataRetained) {
        this.GeneralDataRetained = GeneralDataRetained;
    }

    /**
     *
     * @param HomeRetained The home data to be retained
     */
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
