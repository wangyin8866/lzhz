package com.example.user.liangzi.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by User on 2017/5/8.
 */

public class NetworkConnectivityReceiver extends BroadcastReceiver {



    @Override
    public IBinder peekService(Context myContext, Intent service) {
        return super.peekService(myContext, service);
    }

    @Override
    public void onReceive(Context context, Intent intent) {

    }
}
