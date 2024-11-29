package com.example.lab6;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {

                } else {
                    Toast toast = Toast.makeText(this,"Я не могу работать без разрешения",Toast.LENGTH_LONG);
                    toast.show();

                }
            });

    public  void CheckPermission(){
        if(checkSelfPermission(Manifest.permission.READ_MEDIA_AUDIO)!= PackageManager.PERMISSION_GRANTED){
            requestPermissionLauncher.launch(Manifest.permission.READ_MEDIA_AUDIO);
        }
        Log.i("lab6+","Есть разрешение");


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        CheckPermission();
    }
}