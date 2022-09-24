package com.ptn.gedebook

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.ptn.gedebook.databinding.ActivityMainBinding

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).root)
    }
}