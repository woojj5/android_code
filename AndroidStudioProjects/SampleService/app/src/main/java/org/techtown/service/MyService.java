package org.techtown.service;

import android.app.Service;
import android.content.Intent;
import android.nfc.Tag;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private static final String TAG = "MyService";
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "OnCreate 호출");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "OnStartCommand 호출");
        if(intent == null) return Service.START_STICKY;
        else processCommand(intent);
        return super.onStartCommand(intent, flags, startId);
    }
    private  void processCommand(Intent intent){
        String command = intent.getStringExtra("command");
        String name = intent.getStringExtra("name");

        Log.d(TAG,"Command " + command + " , name :" + name  );
        for(int i = 0; i<5; i++){
            try{
                Thread.sleep(1000);
            }catch (Exception e){};
            Log.d(TAG, "waiting" + i + "seconds");
        }
        Intent showintent = new Intent(getApplicationContext(), MainActivity.class);

        showintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK| intent.FLAG_ACTIVITY_SINGLE_TOP| intent.FLAG_ACTIVITY_CLEAR_TOP);
        showintent.putExtra("command", "show");
        showintent.putExtra("name", name + "from service");
        startActivity(showintent);
    }
}
