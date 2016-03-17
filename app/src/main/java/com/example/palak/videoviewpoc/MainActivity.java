package com.example.palak.videoviewpoc;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.VideoView;

import com.danikula.videocache.Cache;
import com.danikula.videocache.HttpProxyCacheServer;
import com.danikula.videocache.HttpUrlSource;
import com.danikula.videocache.ProxyCacheException;
import com.danikula.videocache.file.FileCache;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private HttpProxyCacheServer proxy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /* try {
            Cache cache = new FileCache(new File(getExternalCacheDir(), VIDEO_CACHE_NAME));
            HttpUrlSource source = new HttpUrlSource(VIDEO_URL);
            proxyCache = new HttpProxyCache(source, cache);
            ((VideoView)findViewById(R.id.video)).setVideoPath(proxyCache.getUrl());
            ((VideoView)findViewById(R.id.video)).start();
        } catch (ProxyCacheException e) {
            Log.e(LOG_TAG, "Error playing video", e);
        }
    }*/

        HttpProxyCacheServer proxy = getProxy();
        String proxyUrl = proxy.getProxyUrl("https://archive.org/download/ksnn_compilation_master_the_internet/ksnn_compilation_master_the_internet_512kb.mp4");
        ((VideoView)findViewById(R.id.video)).setVideoPath(proxyUrl);
        ((VideoView)findViewById(R.id.video)).start();


    }

    private HttpProxyCacheServer getProxy() {
        return new HttpProxyCacheServer(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
