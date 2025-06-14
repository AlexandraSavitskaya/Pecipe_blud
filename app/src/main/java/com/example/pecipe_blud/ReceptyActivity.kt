package com.example.pecipe_blud

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.pecipe_blud.databinding.ActivityReceptyBinding


class ReceptyActivity : AppCompatActivity() {

    //Binding для работы с ActivityReceptyBinding
    private lateinit var receptyBinding: ActivityReceptyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        receptyBinding = ActivityReceptyBinding.inflate(layoutInflater)
        setContentView(receptyBinding.root)
        val navView: BottomNavigationView = receptyBinding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_recepty)
//        val appBarConfiguration = AppBarConfiguration(setOf(
//            R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
//        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}