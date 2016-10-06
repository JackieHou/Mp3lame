package com.jackiehou.mp3lame;

/**
 * Created by Administrator on 2016/6/1.
 */
public class VoiceConvert {

    public static final int NUM_CHANNELS = 1;
    public static final int SAMPLE_RATE = 8000;
    public static final int BITRATE = 16;
    public static final int MODE = 3;
    public static final int QUALITY = 1;
    //0为小端，非零为大端
    public static final int ENDIAN = 0;

    static {
        System.loadLibrary("mp3lame");
    }

    public boolean convertFile(String sourcePath, String targetPath){
        initEncoder(NUM_CHANNELS, SAMPLE_RATE, BITRATE, MODE, QUALITY,ENDIAN);
        int result = encodeFile(sourcePath, targetPath);
        if (result == 0) {
            //success
            return true;
        }
        destroyEncoder();
        return false;
    }

    private native void initEncoder(int numChannels, int sampleRate, int bitRate, int mode, int quality,int endian);
    private native void destroyEncoder();
    private native int encodeFile(String sourcePath, String targetPath);
}
