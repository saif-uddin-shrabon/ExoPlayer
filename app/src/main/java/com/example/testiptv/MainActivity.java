package com.example.testiptv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText url,ch;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        url = findViewById(R.id.geturl);
        btn = findViewById(R.id.urlbtn);
        ch = findViewById(R.id.getch);

//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String Url = url.getText().toString().trim();
//                String chnnel = ch.getText().toString();
//
//
////                String url = "https://iptv-org.github.io/iptv/countries/br.m3u";
//
//                try {
//                    URL urlObj = new URL(Url);
//                    HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
//                    conn.setRequestMethod("GET");
//                    conn.connect();
//
//                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//                    String line;
//                    List<Map<String, String>> channels = new ArrayList<>();
//                    Map<String, String> currentChannel = new HashMap<>();
//
//                    while ((line = reader.readLine()) != null) {
//                        line = line.trim();
//
//                        if (line.startsWith("#EXTINF:")) {
//                            String[] info = line.split(",(.+)", 2);
//                            currentChannel.put("name", info[1]);
//                            String logo = line.replaceAll(".tvg-logo=\"([^\"])\".*", "$1");
//                            currentChannel.put("logo", logo);
//                        } else if (line.startsWith("http")) {
//                            currentChannel.put("url", line);
//                            channels.add(currentChannel);
//                            currentChannel = new HashMap<>();
//                        }
//                    }
//                    reader.close();
//                    conn.disconnect();
//
//                    Log.d("Channels", channels.toString());
//
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//
//
//
//            }
//        });

//                new AsyncTask<String, Void, List<String>>() {
//                    @Override
//                    protected List<String> doInBackground(String... params) {
//                        String Url = params[0];
//                        try {
//                            URL url = new URL(Url);
//                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                            conn.setRequestMethod("GET");
//                            conn.connect();
//
//                            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//                            String line;
//                            List<String> links = new ArrayList<>();
//                            while ((line = reader.readLine()) != null) {
//                                if (line.startsWith("http")) {
//                                    links.add(line);
//                                }
//                            }
//                            reader.close();
//                            conn.disconnect();
//
//                            return links;
//
//                        } catch (IOException e) {
//                            throw new RuntimeException(e);
//                        }
//                    }
//
//                    @Override
//                    protected void onPostExecute(List<String> links) {
//                        Log.d("Links", links.toString());
//                    }
//                }.execute(Url);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Url = url.getText().toString().trim();
                String chnnel = ch.getText().toString();
                new GetChannelsTask().execute(Url);
            }
        });




    }
    private class GetChannelsTask extends AsyncTask<String, Void, List<Map<String, String>>> {

        @Override
        protected List<Map<String, String>> doInBackground(String... urls) {
            String urlString = urls[0];
            List<Map<String, String>> channels = new ArrayList<>();
            Map<String, String> currentChannel = new HashMap<>();

            try {
                URL urlObj = new URL(urlString);
                HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
                conn.setRequestMethod("GET");
                conn.connect();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;

                while ((line = reader.readLine()) != null) {
                    line = line.trim();

                    if (line.startsWith("#EXTINF:")) {
                        String[] info = line.split(",(.+)", 2);
                        currentChannel.put("name", info[1]);
                        String logo = line.replaceAll(".tvg-logo=\"([^\"])\".*", "$1");
                        currentChannel.put("logo", logo);
                    } else if (line.startsWith("http")) {
                        currentChannel.put("url", line);
                        channels.add(currentChannel);
                        currentChannel = new HashMap<>();
                    }
                }
                reader.close();
                conn.disconnect();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return channels;
        }

        @Override
        protected void onPostExecute(List<Map<String, String>> channels) {

            for (Map<String, String> channel : channels) {
                String name = channel.get("name");
                String logo = channel.get("logo");
                String url = channel.get("url");
                Log.d("Channel", "Name: " + name + ", Logo: " + logo + ", URL: " + url);
            }

//            Log.d("Channels", channels.toString());

        }
    }
}



