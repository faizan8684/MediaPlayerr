package com.example.android.mediaplayerr

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.media.MediaPlayer
import android.support.design.widget.FloatingActionButton
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.os.Handler
import android.os.PersistableBundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var mediaPlay: MediaPlayer
    private lateinit var runnable:Runnable
    private var handler: Handler = Handler()
    private lateinit var starttime : TextView
    private lateinit var endtime: TextView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mediaPlay = MediaPlayer.create(this,R.raw.despacito)

        starttime = findViewById<TextView>(R.id.start_time)
        endtime = findViewById<TextView>(R.id.end_time)

        var position = 0

        val button1 = findViewById<FloatingActionButton>(R.id.play_button)
        val button2 = findViewById<FloatingActionButton>(R.id.stop_button)

        button1.setOnClickListener{
            if (mediaPlay.isPlaying.not()) {
                mediaPlay.seekTo (position)
                mediaPlay.start ()
                button1.setImageResource(R.drawable.pause1)

            }
            else {
                if(mediaPlay.isPlaying) {
                    position = mediaPlay.currentPosition
                    button1.setImageResource(R.drawable.playyy)
                    mediaPlay.pause()
                }
            }
        }

        button2.setOnClickListener{
            mediaPlay.pause ()
            position = 0
            mediaPlay.seekTo (0)
            button1.setImageResource(R.drawable.playyy)
        }



        val seekbaar = findViewById<View>(R.id.seekBar) as SeekBar

        seekBar.max = mediaPlay.duration

        var thread = Thread {
            while (true) {
                Thread.sleep(1000)
                seekBar.progress = mediaPlay.currentPosition
            }
        }
        thread.start()
pk 
        runnable = Runnable {
            seekbaar.progress = mediaPlay.currentSeconds

            //  handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)


        seekbaar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener
        {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                //if (b) {
                  //  mediaPlay.seekTo(i * 1000)
                // }
                }


            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {


            }

        })



}

    private val MediaPlayer.seconds:Int
        get() {
            return this.duration / 1000
        }


    private val MediaPlayer.currentSeconds:Int
        get() {
            return this.currentPosition/1000
        }


        override fun onDestroy () {
        super.onDestroy ()
        mediaPlay.release ()
    }

}

