package com.example.github_search_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.github_search_app.databinding.ActivityMainBinding
import com.example.github_search_app.utils.APP_ACTIVITY

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private val mBinding get() = binding!!
    lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        APP_ACTIVITY = this
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)



    }

    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }
}