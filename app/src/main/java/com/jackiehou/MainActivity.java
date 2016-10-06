package com.jackiehou;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.jackiehou.mp3lame.VoiceConvert;

import java.io.File;

public class MainActivity extends Activity {

    Handler handler = new Handler();

    String pcmPath = "/sdcard/temp.pcm";
    File myfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myfile = new File(pcmPath);

        new Thread(){
            @Override
            public void run() {

                VoiceConvert convert = new VoiceConvert();

                String mp3Path = "/sdcard/convert.mp3";
                final boolean isSuccess = convert.convertFile(pcmPath,mp3Path);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this,isSuccess+"",Toast.LENGTH_LONG).show();
                    }
                });
            }
        }.start();
    }
}
