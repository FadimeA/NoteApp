package com.example.noteapp2.ui.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.noteapp2.ui.fragments.onboard.OnBoardFragment
import com.example.noteapp2.ui.fragments.onboard.OnBoardPagerFragment
import com.example.noteapp2.ui.fragments.onboard.OnBoardPagerFragment.Companion.ARG_ONBOARD_POSITION

class OnBoardAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): OnBoardPagerFragment =
        OnBoardPagerFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_ONBOARD_POSITION, position)
            }
        }
}


