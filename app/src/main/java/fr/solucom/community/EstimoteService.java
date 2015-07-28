package fr.solucom.community;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

/**
 * This service is used to launch the Estimote manager when the device's bluetooth is turned on.
 *
 * @see EstimoteReceiver
 * @see EstimoteManager
 */
public class EstimoteService extends Service {
    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    /**
     * This method is called when the service is started. When the service is turned on, it creates the Estimote Manager
     *
     * @param intent
     * @param flags   Additional data about this start request
     * @param startId A unique integer representing this specific request to start
     * @return START_STICKY An integer that indicates the service is started and stopped as needed
     * @see EstimoteService
     * @see EstimoteManager
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            //creates the Estimote Manager
            EstimoteManager.Create((NotificationManager) this
                    .getSystemService(Context.NOTIFICATION_SERVICE), this);
        } catch (Exception e) {

        }
        // the service is explicitly started and stopped as needed
        return START_STICKY;
    }

    /**
     * This method is used when the "onDestroy" method is called by the Estimote Receiver
     *
     * @see EstimoteService
     * @see EstimoteManager#stop()
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        //stops the Estimote Manager
        EstimoteManager.stop();
    }
}
