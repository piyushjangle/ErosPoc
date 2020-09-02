package com.logituit.erospoc;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.logituit.erospoc.plugin.LogixPlayerPlugin;
import com.logituit.erospoc.plugin.LogixPlayerPluginListener;
import com.logituit.logixsdk.logixplayer.LogixPlaybackException;
import com.logituit.logixsdk.logixplayer.ui.LogixPlayerView;

public class MainActivity extends AppCompatActivity implements LogixPlayerPluginListener {

    /*UI elements*/
    private LogixPlayerView logixPlayerView;
    private LogixPlayerView playerView;
    private LogixPlayerPlugin logixPlayer;
    private LogixPlayerPluginListener logixPlayerPluginListener;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        logixPlayerView = findViewById(R.id.play_container);
        logixPlayerPluginListener = this;


        logixPlayer = new LogixPlayerPlugin(logixPlayerView, 0, this, logixPlayerPluginListener);
        logixPlayer.initializePlayer("https://www.youtube.com/watch?v=5X7WWVTrBvM", true);

    }

    @Override
    public void onPlaybackEnded(int position) {

    }

    @Override
    public void onPlayerError(int position, LogixPlaybackException logixPlaybackException) {

    }

    @Override
    public void onPlaybackStarted(int position) {

    }
}