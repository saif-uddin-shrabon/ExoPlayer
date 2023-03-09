package com.example.testiptv;

import static android.content.ContentValues.TAG;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;




public class videoplayer extends AppCompatActivity {

    protected PlayerView playerView;

    SimpleExoPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoplayer);



        playerView = findViewById(R.id.player_view);
        plavideo();
    }

    private void plavideo() {

        //String videouri = getIntent().getStringExtra("keyName");
        String videouri = "https://stmv.video.expressolider.com.br/ghostv/ghostv/playlist.m3u8";

        try {

            Uri uri = Uri.parse(videouri);
//            player = new SimpleExoPlayer.Builder(this).build();
            player = new SimpleExoPlayer(videoplayer.this);

            playerView.setPlayer(player);

            // Create an HTTPS data source factory
            DataSource.Factory dataSourceFactory = new DefaultHttpDataSourceFactory(
                    "exoplayer-codelab",
                    DefaultHttpDataSource.DEFAULT_CONNECT_TIMEOUT_MILLIS,
                    DefaultHttpDataSource.DEFAULT_READ_TIMEOUT_MILLIS,
                    true /* allowCrossProtocolRedirects */,
                    DefaultHttpDataSourceFactory.DEFAULT_SSL_SOCKET_FACTORY);

            // Create a media source using the data source factory
//            MediaSource mediaSource = new HlsMediaSource.Factory(dataSourceFactory).createMediaSource(uri);
            MediaSource mediaSource = new HlsMediaSource.Factory(dataSourceFactory).createMediaSource(uri);
            Log.d(TAG, "Media source created: " + (mediaSource != null));


            // Set the media source for the player
            player.setMediaSource(mediaSource);

            // Prepare the player
            player.prepare();

            // Start playing the video
            player.setPlayWhenReady(true);

        } catch (Exception e) {
            Log.e(TAG, "Error initializing player", e);
        }
    }



//    private void plavideo() {
//
//        String videouri = getIntent().getExtras().getString("keyName","defaultKey");
//
//        try {
//
//            Uri ui = Uri.parse(videouri);
//            player = new SimpleExoPlayer.Builder(this).build();
//
//            playerView.setPlayer(player);
//
////             add the following lines to create the MediaSource and attach it to the player
//            DataSource.Factory dataSourceFactory = new DefaultHttpDataSourceFactory(ui);
//            MediaSource mediaSource = new HlsMediaSource.Factory(dataSourceFactory).createMediaSource(ui);
//            player.setMediaSource(mediaSource);
//            player.prepare();
//            player.play();
//        }catch (Exception e){
//
//        }
//
//
//
//
//    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        player.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        player.pause();
    }
}