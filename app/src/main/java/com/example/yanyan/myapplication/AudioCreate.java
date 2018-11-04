package com.example.yanyan.myapplication;

import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class AudioCreate extends AppCompatActivity {

    private Button start;
    private ListView listView;

    private ExecutorService mExecutorService;
    private MediaRecorder mMediaRecorder;
    private long startTime, endTime;
    private File mAudioFile;
    private List<FileReader> dataList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio_create);

        MediaRecorder recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        //recorder.setOutputFile();
        //recorder.prepare();
        recorder.start();
        recorder.stop();
        recorder.reset();
        recorder.release();
    }
}
