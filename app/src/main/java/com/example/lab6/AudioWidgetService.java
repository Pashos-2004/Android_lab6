package com.example.lab6;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViewsService;

public class AudioWidgetService extends  RemoteViewsService {
    public AudioWidgetService() {
    }
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new AudioRemoteViewsFactory(this.getApplicationContext());
    }



}