package com.example.lab6;


import android.database.Cursor;
import android.provider.MediaStore;

import java.util.ArrayList;

public class Audio {
    public int id;
    public String name ;
    public String artist;
    public String duration;
    public String genre;

    public Audio(int id,String name,String artist){
        this.id=id;
        this.name=name;
        this.artist=artist;
    }

    public String getStringFromFields(){
        String res="";

        res+=Consts.AUDIO_NAME+": "+name+"\n";
        res+=Consts.AUDIO_ARTIST+": "+artist+"\n";

        return res;
    }
    /*
    ArrayList<String> listic;
    public  ArrayList<String> getData(){
        ArrayList<String> list = new ArrayList<String >();

        Cursor cursor = this.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Audio.Media._ID,MediaStore.Audio.Media.DISPLAY_NAME,
                        MediaStore.Audio.Media.ARTIST},
                null,
                null,
                MediaStore.Audio.Media.ARTIST);

        while (cursor.moveToNext()){
            int id = cursor.getColumnIndex(MediaStore.Audio.Media._ID);
            int name = cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME);
            int artis = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int duration = cursor.getColumnIndex(MediaStore.Audio.Media.DURATION);
            int genre = cursor.getColumnIndex(MediaStore.Audio.Media.GENRE);

            long dur = Long.parseLong(cursor.getString(duration));
            String minutes = String.valueOf(dur / 60000);
            String seconds = String.valueOf((dur % 60000) / 1000);
            if(minutes.equals("0") && seconds.equals("0")){
                seconds="1";
            }

            listic.add(new Audio(cursor.getInt(id), cursor.getString(name),cursor.getString(artis),minutes+":"+ (seconds.length() < 2 ? "0" : "") + seconds,cursor.getString(genre)).getStringFromFields());


        }

        return list;
    }

*/
}