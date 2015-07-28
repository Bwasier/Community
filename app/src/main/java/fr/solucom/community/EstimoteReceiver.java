package fr.solucom.community;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * This class is used to monitor the bluetooth state on the device.
 * When the bluetooth is turned on, it starts the Estimote service.
 * When the bluetooth is turned off, it stops the Estimote service.
 */
public class EstimoteReceiver extends BroadcastReceiver {
    private Intent estimoteServiceIntent;

    /**
     * Method used to check the bluetooth state on the device. If the bluetooth is turned on, start the Estimote Service,
     * if the bluetooth is turned off, stop the Estimote service
     *
     * @param context
     * @param intent  The intent received by the broadcast receiver.
     * @see EstimoteService
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        final String action = intent.getAction();
        if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
            final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE,
                    BluetoothAdapter.ERROR);
            switch (state) {
                case BluetoothAdapter.STATE_TURNING_OFF:
                    // When bluetooth is turning off, stop the estimote service
                    if (estimoteServiceIntent != null) {
                        context.stopService(estimoteServiceIntent);
                        estimoteServiceIntent = null;
                    }
                    break;
                case BluetoothAdapter.STATE_ON:
                    // When bluethooth is turned on, start the estimote service
                    if (estimoteServiceIntent == null) {
                        estimoteServiceIntent = new Intent(context,
                                EstimoteService.class);
                        context.startService(estimoteServiceIntent);
                    }
                    break;
            }
        }
    }
}
