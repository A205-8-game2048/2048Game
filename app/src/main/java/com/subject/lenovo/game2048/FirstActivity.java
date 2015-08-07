package com.subject.lenovo.game2048;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by lenovo on 2015/8/1.
 */
public class FirstActivity extends Activity {
    private ImageButton game5x5,game4x4,game404,Help;
    private SoundPool Sound;
    private HashMap<Integer,Integer> Sounds=new HashMap<>();
    int of=1;
    private MediaPlayer player;
    private ImageButton Soundfo;
    static SharedPreferences preferences;
    static SharedPreferences.Editor editor;

    private int[] soundon={R.drawable.yx_on,R.drawable.yx_off};


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        game5x5=(ImageButton)findViewById(R.id.game5x5);
        game4x4=(ImageButton)findViewById(R.id.game4x4);
        game404=(ImageButton)findViewById(R.id.game4x4_0);
        Help=(ImageButton)findViewById(R.id.help);
        Soundfo=(ImageButton)findViewById(R.id.Sound);
        Intent firstintent=getIntent();
        player=MediaPlayer.create(this,R.raw.firstbeijing);
        player.setLooping(true);
        preferences= getSharedPreferences("file", MODE_APPEND);
        editor=preferences.edit();
        of=firstintent.getIntExtra("Soundfo",1);
        if (of == 0){
            Soundfo.setImageResource(soundon[1]);
        }
        else {
            Soundfo.setImageResource(soundon[0]);
        }
        if(of==1){
            player.start();
        }

        Sound=new SoundPool(1, AudioManager.STREAM_MUSIC,0);
        setSound();


        Soundfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(of==0){
                    Sound.play(Sounds.get(1), 1, 1, 0, 0, 1);}
                if (of == 0){
                    Soundfo.setImageResource(soundon[0]);
                    of = 1;
                    player.start();
                }
                else {
                    Soundfo.setImageResource(soundon[1]);
                    of = 0;
                player.stop();
                try {
                    player.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            }
        });
        Help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(of==1){
                Sound.play(Sounds.get(1), 1, 1, 0, 0, 1);}
                Intent intent=new Intent(FirstActivity.this,HelpActivity.class);
                intent.putExtra("Soundfo",of);
                startActivity(intent);
            }
        });
        game404.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(of==1){
                    Sound.play(Sounds.get(1), 1, 1, 0, 0, 1);}
                Intent intent=new Intent(FirstActivity.this,Game404Activiy.class);
                intent.putExtra("Soundfo",of);
                player.stop();
                try {
                    player.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                startActivity(intent);
            }
        });
        game5x5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(of==1){
                    Sound.play(Sounds.get(1), 1, 1, 0, 0, 1);}
                Intent intent=new Intent(FirstActivity.this,Game5x5Activiy.class);
                intent.putExtra("Soundfo",of);
                player.stop();
                try {
                    player.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                startActivity(intent);
            }
        });
        game4x4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(of==1){
                    Sound.play(Sounds.get(1), 1, 1, 0, 0, 1);
                }
                player.stop();
                try {
                    player.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(FirstActivity.this, Game4x4Activity.class);
                intent.putExtra("Soundfo",of);
                startActivity(intent);

            }
        });

    }
    public void setSound( ){
        Sounds.put(1, Sound.load(this, R.raw.dianji, 1));

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            player.stop();
            finish();
            //此处写处理的事件

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
