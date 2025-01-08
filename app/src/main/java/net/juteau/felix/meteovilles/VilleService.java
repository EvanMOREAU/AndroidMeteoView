package net.juteau.felix.meteovilles;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class VilleService extends Service {
    public VilleService() {


    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}