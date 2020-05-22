package com.example.timerdemo11;

import android.media.MediaPlayer;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import static android.widget.SeekBar.*;

public class MainActivity extends AppCompatActivity {
    SeekBar timerSeek;
    TextView timerText;
    ImageView btnTest;
    int c=1;
    MediaPlayer over;
    public void controlTimer(View view)
    {timerSeek.setEnabled(false);


        new CountDownTimer(timerSeek.getProgress()*1000+100,1000)
        {
            @Override
            public void onTick(long millisUntilFinished)
            {
updateTimer(millisUntilFinished/1000);
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

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
