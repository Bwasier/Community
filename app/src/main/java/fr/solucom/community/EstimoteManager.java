package fr.solucom.community;

/**
 * Estimote Manager is used to launch the main activity with the detected beacon's parameters.
 * When a beacon is detected, a notification is displayed on the device's screen.
 * By clicking on it, the main activity for the specific beacon is launched
 * <p/>
 * The Estimote manager is also used to manage the beacon interface :
 * Defines the beacon regions to be monitored
 * Defines the scanning period
 *
 * @see fr.solucom.community.MainActivity
 * @see fr.solucom.community.EstimoteReceiver
 * @see fr.solucom.community.EstimoteService
 */

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.BeaconManager.MonitoringListener;
import com.estimote.sdk.Region;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class EstimoteManager {

    // TAG used for the debug mode
    private static final String TAG = "Estimote Manager";
    private static final int NOTIFICATION_ID = 123;
    //Generated Solucom UUID from ITU-T generator used as UUID for each Solucom's beacon
    private static final String SOLUCOM_PROXIMITY_UUID = "2fc8cec0-1667-11e5-9245-0002a5d5c51b";
    // A region is a a unique UUID-Major_Minor value (i.e. a beacon).
    // Major and minor are set to null in order to monitoring all Solucom's beacons
    private static final Region Solucom = new Region("Solucom", SOLUCOM_PROXIMITY_UUID, null, null);
    //initializes the beacon Manager
    private static BeaconManager beaconManager;
    //initializes the notification manager
    private static NotificationManager notificationManager;
    private static Context currentContext;

    /**
     * @param notificationMngr The notification manager used to push notification (Notification Service of the device)
     * @param context          The context used to lanch the estimote manager (Estimote service)
     * @see EstimoteService
     */
    public static void Create(NotificationManager notificationMngr,
                              Context context) {
        try {
            // Get the notification manager
            notificationManager = notificationMngr;
            currentContext = context;

            // Create a beacon manager
            beaconManager = new BeaconManager(currentContext);

            // Scanning period (2 sec) for the beacon detection
            beaconManager.setBackgroundScanPeriod(TimeUnit.SECONDS.toMillis(2), 0);

            // Method called when a beacon is detected
            beaconManager.setMonitoringListener(new MonitoringListener() {
                // When the device is in a region
                @Override
                public void onEnteredRegion(Region region, List<Beacon> beacons) {
                    //get the beacon (the first object in the beacons list)
                    Beacon beacon = beacons.get(0);
                    //post the notification
                    postNotificationIntent("Comm'unity", "Bienvenue à l\'étage " + beacon.getMinor() + " =)", beacon.getProximityUUID(), beacon.getMajor(),
                            beacon.getMinor());
                }

                // When the device exits a region
                @Override
                public void onExitedRegion(Region region) {
                    postNotificationIntent("Comm'unity",
                            "Au revoir et merci de votre visite =)", region.getProximityUUID(), 0, 0);
                }
            });

            // Beacon manager callback service. Used to launch the scan when the service is ready
            beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
                @Override
                public void onServiceReady() {
                    try {
                        // start the region monitoring
                        beaconManager.startMonitoring(Solucom);
                    } catch (Exception e) {
                        Log.d(TAG, "unable to start the monitoring when callback is caught : " + e);
                    }
                }
            });
        } catch (Exception e) {
            Log.d(TAG, "unable to create the Estimote manager: " + e);
        }
    }

    /**
     * This method is used to pop a notification on the device task bar
     * When the user clicks on the notification, the main activity is launched with a bundle containing all beacon information (UUID, Major, Minor)
     *
     * @param title The title displayed on the task bar (the app name)
     * @param msg   The text displayed on the task bar
     * @param UUID  The UUID of the beacon detected
     * @param major The major of the beacon detected
     * @param minor The minor of the beacon detected
     */
    public static void postNotificationIntent(String title, String msg, String UUID, int major, int minor) {
        //the notification should launch the main activity
        Intent notificationIntent = new Intent(currentContext, MainActivity.class);
        //the activity will not be launched if it is already running
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        //put the beacon information (UUID, major and minor) in the intent
        notificationIntent.putExtra("UUID", UUID);
        notificationIntent.putExtra("major", major);
        notificationIntent.putExtra("minor", minor);

        PendingIntent pendingIntent = PendingIntent.getActivity(
                currentContext, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new Notification.Builder(currentContext)
                .setSmallIcon(R.drawable.ic_bluetooth_searching_white_18dp).setContentTitle(title)
                .setContentText(msg).setAutoCancel(true)
                .setContentIntent(pendingIntent).build();
        notification.defaults |= Notification.DEFAULT_SOUND;
        notification.defaults |= Notification.DEFAULT_LIGHTS;
        notificationManager.notify(NOTIFICATION_ID, notification);
    }

    /**
     * This method is used to stop the estimote manager when the Estimote service is destroyed
     *
     * @see EstimoteService#onDestroy()
     */
    public static void stop() {
        try {
            beaconManager.stopMonitoring(Solucom);
            beaconManager.disconnect();
        } catch (Exception e) {
            Log.d(TAG, "unable to stop the Estimote manager: " + e);
        }
    }
}