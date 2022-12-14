package com.example.pr_bg2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

public class std1_sub1_videos_maths extends AppCompatActivity {
    SimpleExoPlayerView exoPlayerView;


    // creating a variable for exoplayer
    SimpleExoPlayer exoPlayer;

    // url of video which we are loading.
    String videoURL = "https://firebasestorage.googleapis.com/v0/b/pr-bg2.appspot.com/o/Normal_std%2Fstd_01%2Fmaths%2Fstd1_Maths_ch1.mp4?alt=media&token=8f62014a-3d23-44a4-957a-6ad8143ef2e5";

    @Override
    public void onBackPressed() {
        exoPlayer.stop();
        Intent currentIntent = new Intent(std1_sub1_videos_maths.this, std1_sub1_videos.class);
        startActivity(currentIntent);
        finish();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_std1_sub1_videos_maths);

        // creating a variable for exoplayerview.

            exoPlayerView = findViewById(R.id.idExoPlayerVIew);
            try {

                // bandwisthmeter is used for
                // getting default bandwidth
                BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();

                // track selector is used to navigate between
                // video using a default seekbar.
                TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));

                // we are adding our track selector to exoplayer.
                exoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector);

                // we are parsing a video url
                // and parsing its video uri.
                Uri videouri = Uri.parse(videoURL);

                // we are creating a variable for datasource factory
                // and setting its user agent as 'exoplayer_view'
                DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("exoplayer_video");

                // we are creating a variable for extractor factory
                // and setting it to default extractor factory.
                ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

                // we are creating a media source with above variables
                // and passing our event handler as null,
                MediaSource mediaSource = new ExtractorMediaSource(videouri, dataSourceFactory, extractorsFactory, null, null);

                // inside our exoplayer view
                // we are setting our player
                exoPlayerView.setPlayer(exoPlayer);

                // we are preparing our exoplayer
                // with media source.
                exoPlayer.prepare(mediaSource);

                // we are setting our exoplayer
                // when it is ready.
                exoPlayer.setPlayWhenReady(true);

            } catch (Exception e) {
                // below line is used for
                // handling our errors.
                Log.e("TAG", "Error : " + e.toString());
            }
        }
    }
