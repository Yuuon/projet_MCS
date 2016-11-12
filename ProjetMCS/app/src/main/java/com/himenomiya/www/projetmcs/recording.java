package com.himenomiya.www.projetmcs;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;



public class recording extends AppCompatActivity {
    String AudioSavePathInDevice = null;
    MediaRecorder mediaRecorder ;
    public static final int RequestPermissionCode = 1;
    MediaPlayer mediaPlayer ;
    Button buttonStart,buttonStop,buttonPlay,buttonRecStop;
    String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recording);
        Intent intent = getIntent();
        message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        final TextView filename = (TextView) findViewById(R.id.textView3);
        filename.setText(message);
        buttonStart = (Button) findViewById(R.id.buttonStart);
        buttonStop = (Button) findViewById(R.id.buttonstop);
        buttonPlay = (Button) findViewById(R.id.buttonPlay);
        buttonRecStop = (Button) findViewById(R.id.buttonRecStop);
        buttonStart.setEnabled(true);
        buttonStop.setEnabled(false);
        buttonRecStop.setEnabled(false);
        File recExist = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" +
                message + ".3gp");
        AudioSavePathInDevice =
                Environment.getExternalStorageDirectory().getAbsolutePath() + "/" +
                        message + ".3gp";
        if(recExist.exists() && !recExist.isDirectory()) {
            buttonPlay.setEnabled(true);
        } else {
            buttonPlay.setEnabled(false);
        }


        /*
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });
        */
        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaRecorder.stop();
                buttonStop.setEnabled(false);
                buttonPlay.setEnabled(true);
                buttonStart.setEnabled(true);
                buttonRecStop.setEnabled(false);


                Toast.makeText(recording.this, "Recording Completed",
                        Toast.LENGTH_LONG).show();
            }
        });

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) throws IllegalArgumentException,
                    SecurityException, IllegalStateException {

                buttonStop.setEnabled(false);
                buttonStart.setEnabled(false);
                buttonRecStop.setEnabled(true);

                mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(AudioSavePathInDevice);
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                mediaPlayer.start();
                Toast.makeText(recording.this, "Recording Playing",
                        Toast.LENGTH_LONG).show();
            }
        });


    }
    public void MediaRecorderReady(){
        mediaRecorder=new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(AudioSavePathInDevice);
    }
    private void requestPermission() {
        ActivityCompat.requestPermissions(recording.this, new String[]{WRITE_EXTERNAL_STORAGE, RECORD_AUDIO}, RequestPermissionCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case RequestPermissionCode:
                if (grantResults.length> 0) {
                    boolean StoragePermission = grantResults[0] ==
                            PackageManager.PERMISSION_GRANTED;
                    boolean RecordPermission = grantResults[1] ==
                            PackageManager.PERMISSION_GRANTED;

                    if (StoragePermission && RecordPermission) {
                        Toast.makeText(recording.this, "Permission Granted",
                                Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(recording.this,"Permission Denied",Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }

    public boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(),
                WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(),
                RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED &&
                result1 == PackageManager.PERMISSION_GRANTED;
    }
    public String getDiskCacheDir(Context context) {
        String cachePath = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return cachePath;
    }
    public void startRecording(View view) {
        if(checkPermission()) {



            MediaRecorderReady();

            try {
                mediaRecorder.prepare();
                mediaRecorder.start();
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            buttonStart.setEnabled(false);
            buttonStop.setEnabled(true);

            Toast.makeText(recording.this, "Recording started",Toast.LENGTH_LONG).show();
        } else {
            requestPermission();
        }

    }
    public void stopRecording(View view) {
        buttonStop.setEnabled(false);
        buttonStart.setEnabled(true);
        buttonRecStop.setEnabled(false);
        buttonPlay.setEnabled(true);

        if(mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
            MediaRecorderReady();
        }
    }
}


