package com.aaron.clase

import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.youtube.player.YoutubeBaseActivity
import com.google.android.youtube.player.YoutubeInitializationResult
import com.google.android.youtube.player.YoutubePlayer


class Activity2 : YoutubeBaseActivity(), Youtubeplayer.OnInitializedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)
    }
    override fun OnInitializationSucces(
        p0: YoutubePlayer.Provider?,
        p1: YoutubePlayer?,
        p2: Boolean?,
        )
    {
        
    }
}