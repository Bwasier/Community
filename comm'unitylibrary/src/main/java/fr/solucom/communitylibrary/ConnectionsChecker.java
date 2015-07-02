package fr.solucom.communitylibrary;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.Toast;

/**
 * Created by wasier on 30/06/2015.
 */
public class ConnectionsChecker {
    /**
     * This method is used when the activity is launched.
     *
     * Checks if the bluetooth is supported by the device
     * Checks if the bluetooth is enabled on the device
     *
     * Checks if the internet connection is enabled
     * Checks if the internet connection is active
     *
     */
    /**
     * This method allows to check if the bluetooth is supported on the device and if it is turned on
     *
     * @param mainActivity The main activity of the application
     */
    public static void checkBluetoothAdapter(Activity mainActivity) {
        // get the bluetooth adpater on the device
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        // if the bluetooth adapter is null, bluetooth is not supported  by the device. Close the activity
        if (mBluetoothAdapter == null) {
            Toast.makeText(mainActivity.getApplicationContext(), "Ce support ne supporte pas l'application.", Toast.LENGTH_LONG).show();
            mainActivity.finish();
            // if the bluetooth is disabled, informs the user and close the activity
        } else {
            if (!mBluetoothAdapter.isEnabled()) {
                Toast.makeText(mainActivity.getApplicationContext(), "Pour recevoir les notifications des balises, merci d'activer le  Bluetooth", Toast.LENGTH_LONG).show();
                mainActivity.finish();
            }
        }
    }

    /**
     * This method is used to check if the internet connection of the device is enabled
     *
     * @param mainActivity The main activity of the application
     */
    public static void checkInternetAccess(Activity mainActivity) {
        // get the connectivity manager on the device
        ConnectivityManager connectivityManager = (ConnectivityManager) mainActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        //if the connection is active, check the network connection
        if (connectivityManager.getActiveNetworkInfo() != null) {
            //if the network connection is not active, informs the user and closes the app
            if (!connectivityManager.getActiveNetworkInfo().isConnected()) {
                Toast.makeText(mainActivity.getApplicationContext(), "Votre smartphone n'est pas connecté à internet", Toast.LENGTH_LONG).show();
                mainActivity.finish();
            }
        } else {
            //If the getActiveNetworkInfo returns null, the internet connection is not active. Informs the user then closes the app
            Toast.makeText(mainActivity.getApplicationContext(), "L'application nécessite un accès à internet. Merci de l'activer", Toast.LENGTH_LONG).show();
            mainActivity.finish();
        }

    }

}
