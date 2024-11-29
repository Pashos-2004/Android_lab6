package com.example.lab6;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.util.Log;



import java.util.Arrays;

public class AudioContentObserver extends ContentObserver {
    private Context context;

    public AudioContentObserver(Handler handler, Context context) {
        super(handler);
        this.context = context;
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        Log.d("AudioContentObserver", "onChange: selfChange = " + selfChange);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        ComponentName thisWidget = new ComponentName(context, AudioWidget.class);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
        Log.d("AudioContentObserver", "Изменения в media store. AppWidgetIds: " + Arrays.toString(appWidgetIds));
        if (appWidgetIds.length > 0) {
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.MainList);

            Log.d("AudioContentObserver", "Вызвано оповещение обновления: " + Arrays.toString(appWidgetIds));
        } else {
            Log.d("AudioContentObserver", "Нечего обновлять.");
        }
    }
}