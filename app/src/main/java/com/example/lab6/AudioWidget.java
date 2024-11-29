package com.example.lab6;

import android.Manifest;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import java.util.ArrayList;

/**
 * Implementation of App Widget functionality.
 */
public class AudioWidget extends AppWidgetProvider {


    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

         Log.i("Loggi","dfefefe");
        // Construct the RemoteViews object
        //RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.audio_widget);
       // views.setTextViewText(R.id.MainList, widgetText);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.audio_widget);
        Intent intent = new Intent(context, AudioWidgetService.class);
        views.setRemoteAdapter(R.id.MainList, intent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId,R.id.MainList);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        super.onUpdate(context,appWidgetManager,appWidgetIds);
        for (int appWidgetId : appWidgetIds) {
            Log.i("Loggi","Апдейтимся");
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

        private AudioContentObserver audioContentObserver;

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        Handler handler = new Handler(Looper.getMainLooper());
        if(audioContentObserver == null)
        {
            audioContentObserver = new AudioContentObserver(handler, context);
            context.getContentResolver().registerContentObserver(
                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    true,
                    audioContentObserver
            );
        }
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        if (audioContentObserver != null) {
            context.getContentResolver().unregisterContentObserver(audioContentObserver);
            Log.d("ImageListWidget", "ContentObserver регистрация была сброшенаА.");
            audioContentObserver = null;
        }
        //context.stopService(serviceAdapterIntent);
        // Enter relevant functionality for when the last widget is disabled
    }
    @Override
    public void onDeleted(Context context, int[] appWidgetsIds) {
        super.onDeleted(context, appWidgetsIds);

        if (audioContentObserver != null) {
            context.getContentResolver().unregisterContentObserver(audioContentObserver);
            audioContentObserver = null;
        }
    }
}