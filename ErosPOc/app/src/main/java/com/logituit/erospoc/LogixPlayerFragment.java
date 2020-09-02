package com.logituit.erospoc;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.ads.interactivemedia.v3.api.AdErrorEvent;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.logituit.logixsdk.ads.LogixAdEvent;
import com.logituit.logixsdk.ads.LogixAdEventListener;
import com.logituit.logixsdk.logixplayer.core.LogixPlayerImpl;
import com.logituit.logixsdk.logixplayer.interfaces.LogixPlayerEventListener;
import com.logituit.logixsdk.logixplayer.ui.LogixPlayerView;
import com.logituit.logixsdk.logixplayer.util.C;

import java.io.InvalidObjectException;
import java.util.concurrent.CopyOnWriteArrayList;

public class LogixPlayerFragment extends Fragment implements LogixPlayerEventListener, LogixAdEventListener {

    /*UI elements*/
    private LogixPlayerView logixPlayerView;
    private LogixPlayerImpl logixPlayerImpl;
    Context context;

    public LogixPlayerFragment() {
        // Required empty public constructor
    }

    public void initializePlayer(String videoUrl, boolean autoPlay) throws Exception {
        LogixPlayerImpl.LogixPlayerBuilder logixPlayerBuilder =
                new LogixPlayerImpl.LogixPlayerBuilder(new Uri[]{Uri.parse(videoUrl)})
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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_logix_player, container, false);

        logixPlayerView = view.findViewById(R.id.player_view);

        context = getActivity();

        try {
            CopyOnWriteArrayList<LogixPlayerEventListener> logixPlayerEventListeners = new CopyOnWriteArrayList<>();
            logixPlayerEventListeners.add(this);

            logixPlayerImpl = new LogixPlayerImpl(context,
                    logixPlayerView,
                    logixPlayerEventListeners,
                    this);

            LogixPlayerImpl.LogixPlayerBuilder logixPlayerBuilder;

            initializePlayer("https://www.youtube.com/watch?v=5X7WWVTrBvM", true);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }

    @Override
    public void onAdError(AdErrorEvent adErrorEvent) {

    }

    @Override
    public void onAllAdsCompleted(LogixAdEvent logixAdEvent) {

    }

    @Override
    public void onAdClick(LogixAdEvent logixAdEvent) {

    }

    @Override
    public void onAdCompleted(LogixAdEvent logixAdEvent) {

    }

    @Override
    public void onAdCuePointsChanged(LogixAdEvent logixAdEvent) {

    }

    @Override
    public void onAdContentPauseRequested(LogixAdEvent logixAdEvent) {

    }

    @Override
    public void onAdContentResumeRequested(LogixAdEvent logixAdEvent) {

    }

    @Override
    public void onAdFirstQuartile(LogixAdEvent logixAdEvent) {

    }

    @Override
    public void onAdLog(LogixAdEvent logixAdEvent) {

    }

    @Override
    public void onAdBreakReady(LogixAdEvent logixAdEvent) {

    }

    @Override
    public void onAdMidpoint(LogixAdEvent logixAdEvent) {

    }

    @Override
    public void onAdPaused(LogixAdEvent logixAdEvent) {

    }

    @Override
    public void onAdResumed(LogixAdEvent logixAdEvent) {

    }

    @Override
    public void onAdSkippableStateChanged(LogixAdEvent logixAdEvent) {

    }

    @Override
    public void onAdSkipped(LogixAdEvent logixAdEvent) {

    }

    @Override
    public void onAdStarted(LogixAdEvent logixAdEvent) {

    }

    @Override
    public void onAdTapped(LogixAdEvent logixAdEvent) {

    }

    @Override
    public void onAdIconTapped(LogixAdEvent logixAdEvent) {

    }

    @Override
    public void onAdThirdQuartile(LogixAdEvent logixAdEvent) {

    }

    @Override
    public void onAdLoaded(LogixAdEvent logixAdEvent) {

    }

    @Override
    public void onAdProgress(LogixAdEvent logixAdEvent) {

    }

    @Override
    public void onAdBuffering(LogixAdEvent logixAdEvent) {

    }

    @Override
    public void onAdBreakStarted(LogixAdEvent logixAdEvent) {

    }

    @Override
    public void onAdBreakEnded(LogixAdEvent logixAdEvent) {

    }

    @Override
    public void onAdPeriodStarted(LogixAdEvent logixAdEvent) {

    }

    @Override
    public void onAdPeriodEnded(LogixAdEvent logixAdEvent) {

    }
}