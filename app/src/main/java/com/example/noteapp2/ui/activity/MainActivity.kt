package com.example.noteapp2.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.noteapp2.R
import com.example.noteapp2.databinding.ActivityMainBinding
import com.example.noteapp2.utils.PreferenceHelper

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment

        navController = navHostFragment.navController

        val sharedPreference = PreferenceHelper()
        sharedPreference.unit(this)

        if (!sharedPreference.isOnBoardShown){
             navController.navigate(R.id.onBoardFragment)
        }else{
            navController.navigate(R.id.noteFragment)
        }
    }
}