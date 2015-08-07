package com.subject.lenovo.game2048;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Created by lenovo on 2015/8/3.
 */
public class GameOverActivity extends Activity {
    private Button restart;
    public int play;
    public int of=1;
    private TextView overscore;
    public int score;
    private Button first;
    private SoundPool Sound;
    private HashMap<Integer,Integer> Sounds=new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);
        restart=(Button)findViewById(R.id.OverRestart);
        Intent intent1=getIntent();
        play=intent1.getIntExtra("play",0);
        of=intent1.getIntExtra("Soundfo",1);
        score=intent1.getIntExtra("OverScore",0);
        overscore=(TextView)findViewById(R.id.OverScore);
        overscore.setText(score+"");
        first=(Button)findViewById(R.id.first);
        Sound=new SoundPool(1, AudioManager.STREAM_MUSIC,0);
        setSound();

        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(of==1){
                    Sound.play(Sounds.get(1), 1, 1, 0, 0, 1);}
                Intent intent = new Intent(GameOverActivity.this, FirstActivity.class);
                intent.putExtra("Soundfo", of);
                startActivity(intent);
                finish();
            }
        });



        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(of==1){
                    Sound.play(Sounds.get(1), 1, 1, 0, 0, 1);}
                if (play == 1) {
                    Intent intent = new Intent(GameOverActivity.this, Game4x4Activity.class);
                    intent.putExtra("choose", 0);
                    intent.putExtra("Soundfo", of);
                    startActivity(intent);
                    finish();
                } else if (play == 2) {
                    Intent intent = new Intent(GameOverActivity.this, Game5x5Activiy.class);
                    intent.putExtra("choose", 0);
                    intent.putExtra("Soundfo", of);
                    startActivity(intent);
                    finish();
                } else if (play == 3) {
                    Intent intent = new Intent(GameOverActivity.this, Game404Activiy.class);
                    intent.putExtra("choose", 0);
                    intent.putExtra("Soundfo", of);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
    public void setSound( ){
        Sounds.put(1, Sound.load(this, R.raw.dianji, 1));

    }
}
