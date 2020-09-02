package com.logituit.erospoc.plugin;

import com.logituit.logixsdk.logixplayer.LogixPlaybackException;

public interface LogixPlayerPluginListener {
    void onPlaybackEnded(int position);
    void onPlayerError(int position, LogixPlaybackException logixPlaybackException);
    void onPlaybackStarted(int position);
}
