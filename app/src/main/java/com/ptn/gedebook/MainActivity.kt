package com.ptn.gedebook

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.ptn.gedebook.book.presentation.fragment.BookFragment
import com.ptn.gedebook.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).root)

        with (supportFragmentManager.beginTransaction()) {
            add(R.id.fragment_container, BookFragment(), BookFragment.TAG)
            addToBackStack(null)
            commit()
        }
    }
}
