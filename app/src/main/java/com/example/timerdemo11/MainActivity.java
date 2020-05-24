package com.example.timerdemo11;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import static android.widget.SeekBar.*;

public class MainActivity extends AppCompatActivity {
    SeekBar timerSeek;
    TextView timerText;
    ImageView btnTest;
    ImageView pause;
    ImageView play;
    CountDownTimer yourCountDownTimer;
    MediaPlayer over;
    ProgressBar pg;
    public void controlTimer(View view)
    {timerSeek.setEnabled(false);
    play.setEnabled(false);
    pause.setEnabled(true);


        yourCountDownTimer=new CountDownTimer(timerSeek.getProgress()*1000+100,1000)
        {

            @Override
            public void onTick(final long millisUntilFinished)
            {
updateTimer(millisUntilFinished/1000);
                Handler progressBarHandler = new Handler();

                //ProgressBar bar = (ProgressBar) findViewById(R.id.progressBar1);;

                progressBarHandler .post(new Runnable() {

                    @RequiresApi(api = Build.VERSION_CODES.N)
                    public void run() {
                        pg.setMax(Math.toIntExact(timerSeek.getProgress()*1000+100));
                        pg.setProgress(Math.toIntExact(millisUntilFinished));
                    }
                });
            }

            @Override
            public void onFinish()
            {over.start();
                timerSeek.setEnabled(true);



            }
        }.start();

    }
    public void updateTimer(long secondsLeft)
    {

        long minutes=secondsLeft/60;
        long seconds=secondsLeft-minutes*60;
        if(seconds<10)
            timerText.setText(minutes+":0"+seconds);
        else
            timerText.setText(minutes+":"+seconds);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
over=MediaPlayer.create(this,R.raw.cha);
        timerSeek=findViewById(R.id.timerSeek);
        timerSeek.setMax(30*60);
        timerText=findViewById(R.id.timerText);
        btnTest =findViewById(R.id.imageView);
        play=findViewById(R.id.imageView);
        pause=findViewById(R.id.imageButton);
        pause.setEnabled(false);
        pg=findViewById(R.id.progressBar);

        timerSeek.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                // TODO Auto-generated method stub
                int minutes=progress/60;
                int seconds=progress-minutes*60;
                if(seconds<10)
                timerText.setText(minutes+":0"+seconds);
                else
                    timerText.setText(minutes+":"+seconds);





            }
        });


        //Using handler timer
        /*final Handler hs=new Handler();
        Runnable run=new Runnable() {
            @Override
            public void run() {
                //Log.i("hanlerpost","1 second");
                hs.postDelayed(this,1000);
            }
        };
hs.post(run);
//Log.i("hanlerpost","post ko run");*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public void pauseClick(View view)
    {
        timerSeek.setEnabled(true);
        play.setEnabled(true);
        pause.setEnabled(false);
        yourCountDownTimer.cancel();
        over.pause();


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
