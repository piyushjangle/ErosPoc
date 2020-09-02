package com.logituit.erospoc.plugin;

import android.content.Context;
import android.net.Uri;
import android.view.View;

import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.logituit.logixsdk.logixplayer.LogixPlaybackException;
import com.logituit.logixsdk.logixplayer.core.LogixPlayerImpl;
import com.logituit.logixsdk.logixplayer.interfaces.LogixPlayerEventListener;
import com.logituit.logixsdk.logixplayer.ui.LogixPlayerView;
import com.logituit.logixsdk.logixplayer.util.C;

import java.io.InvalidObjectException;

/** Logixplayer plugin to handle simple playback
 * Usage:
 * Define below in xml
 * <com.logituit.logixsdk.logixplayer.ui.LogixPlayerView
 *             android:layout_width="match_parent"
 *             android:id="@+id/play_container"
 *             app:use_controller="false"
 *             android:visibility="visible"
 *             android:layout_height="match_parent"/>
 * //initialization
 * LogixPlayerPlugin logixPlayer = new LogixPlayerPlugin((LogixPlayerView) view, 0, view.getContext(), this);
 * logixPlayer.initializePlayer(videoUrl, true);
 * //pause
 * logixPlayer.pausePlayer();
 * //resume
 * logixPlayer.playPlayer();
 * //mute/unmute
 * logixPlayer.setMute(true/false);
 * //IMP: Release the player when going out of view
 * logixPlayer.releasePlayer();
 * //To get simple player state implement LogixPlayerPluginListener interface.
 *
 **/
public class LogixPlayerPlugin implements LogixPlayerEventListener {
    private LogixPlayerImpl logixPlayerImpl;
    private LogixPlayerView logixPlayerView;
    private int position = -1;
    private Context context;
    private LogixPlayerPluginListener pluginListener;

    public LogixPlayerPlugin(LogixPlayerView logixPlayerView, int position, Context context, LogixPlayerPluginListener playerPluginListener) {
        this.logixPlayerView = logixPlayerView;
        this.position = position;
        this.context = context;
        this.pluginListener = playerPluginListener;

        java.util.concurrent.CopyOnWriteArrayList<LogixPlayerEventListener> logixPlayerEventListeners = new java.util.concurrent.CopyOnWriteArrayList<LogixPlayerEventListener>();

        logixPlayerEventListeners.add(this);
        this.logixPlayerImpl = new LogixPlayerImpl(this.context, this.logixPlayerView, logixPlayerEventListeners, null);
    }

    public void initializePlayer(String videoUrl, boolean autoPlay) {
        LogixPlayerImpl.LogixPlayerBuilder logixPlayerBuilder = new LogixPlayerImpl.LogixPlayerBuilder(new Uri[]{Uri.parse(videoUrl)})
                .autoPlay(autoPlay);
        if (logixPlayerImpl != null) {
            try {
                logixPlayerImpl.initialize(logixPlayerBuilder);
            } catch (InvalidObjectException e) {
                e.printStackTrace();
            }
            logixPlayerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FILL);
            logixPlayerImpl.setVideoScalingMode(C.VIDEO_SCALING_MODE_SCALE_TO_FIT);
            logixPlayerImpl.setVolume(1);
        }
    }

    public void pausePlayer() {
        if (logixPlayerImpl != null) {
            logixPlayerImpl.playPause(false);
        }
    }

    public void playPlayer() {
        if (logixPlayerImpl != null) {
            logixPlayerImpl.playPause(true);
        }
    }

    public void releasePlayer() {
        if (logixPlayerImpl != null) {
            logixPlayerImpl.releasePlayer();
            logixPlayerImpl = null;
        }
    }


    public void setMute(boolean mute) {
        logixPlayerImpl.toggleMute(mute);
    }


    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        if (playbackState == STATE_ENDED && pluginListener != null) {
            pluginListener.onPlaybackEnded(position);
            logixPlayerView.setVisibility(View.GONE);
        } else if (playbackState == STATE_READY && playWhenReady) {
            logixPlayerView.setVisibility(View.VISIBLE);
            pluginListener.onPlaybackStarted(position);
        }
    }

    public void setPlayerVisibility(int visibility) {
        logixPlayerView.setVisibility(visibility);
    }

    @Override
    public void onPlayerError(boolean isBehindLiveWindow, LogixPlaybackException logixPlaybackException) {
        logixPlayerView.setVisibility(View.GONE);
        if (pluginListener != null) {
            pluginListener.onPlayerError(position, logixPlaybackException);
        }
    }
}
