package com.example.salman.tiome;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<SongInfo>  _songs=new ArrayList<SongInfo>();
    RecyclerView recyclerView;
    SeekBar seekBar;
    SongAdapter songAdapter;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView) findViewById(R.id.recyclerview);
        seekBar=(SeekBar) findViewById(R.id.seekBar);
        SongInfo s=new SongInfo("arijit sings","link to website","http://hd.djring.com/320/484709/Pyaar%20ka%20nagma%20-%20Arijit%20Singh%20ft%20AC%20Music%20(DJJOhAL.Com).mp3");
        _songs.add(s);
        songAdapter=new SongAdapter(this,_songs);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(recyclerView.getContext(),linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(songAdapter);
        songAdapter.setOnitemClickListener(new SongAdapter.OnitemClickListener(){
            @Override
            public void onItemClick(final Button b, View v, SongInfo obj, int position) {
                try {
                    if(b.getText().toString().equals("Stop")){
                        b.setText("Play");
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                        mediaPlayer.release();
                        mediaPlayer=null;
                    }else {
                        mediaPlayer = new MediaPlayer();
                        mediaPlayer.setDataSource(obj.getSongUrl());
                        mediaPlayer.prepareAsync();
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                                b.setText("Stop");
                            }
                        });
                    }
                }catch (IOException e){}
            }
        });


    }
}