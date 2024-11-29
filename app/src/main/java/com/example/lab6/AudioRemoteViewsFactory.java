package com.example.lab6;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import java.util.ArrayList;
import java.util.List;

public class AudioRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private final Context mContext;
    private List<AudioItem> audioItems = new ArrayList<>();



    public AudioRemoteViewsFactory(Context context) {
        mContext = context;
    }

    @Override
    public void onCreate() {
        Log.i("Loggi","Тут");
        loadAudioData();
    }

    @Override
    public void onDataSetChanged() {
        Log.i("Loggi","Здесь");
        loadAudioData();
    }

    @Override
    public void onDestroy() {
        audioItems.clear();
    }

    @Override
    public int getCount() {
        return audioItems.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {


        AudioItem item = audioItems.get(position);
        RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.audio_widget_list_item);

        views.setTextViewText(R.id.audio_title, item.title);
        views.setTextViewText(R.id.audio_artist, item.artist);

        return views;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null; // Можно указать макет загрузки
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    private void loadAudioData() {
        audioItems.clear();
        ContentResolver resolver = mContext.getContentResolver();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST
        };

        Log.i("Loggi","GETDATA");

        //Cursor cursor = resolver.query(uri, projection, null, null, null);
        Cursor cursor = mContext.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Audio.Media.DISPLAY_NAME,
                        MediaStore.Audio.Media.ARTIST},
                null,
                null,
                MediaStore.Audio.Media.ARTIST);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME));
                String artist = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
                audioItems.add(new AudioItem(title, artist));
                Log.i("log",title+" "+artist);
            }
            cursor.close();
        }
    }

    static class AudioItem {
        String title;
        String artist;

        AudioItem(String title, String artist) {
            this.title = title;
            this.artist = artist;
        }
    }
}

