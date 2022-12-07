package com.arysugiarto.netplix

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.arysugiarto.netplix.databinding.ActivityMainBinding
import com.arysugiarto.netplix.utils.CheckInternet

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var navicontroller:NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.cumstomToolbar)
        CheckInternet(this).observe(this){
            if (it){
                binding.progressbar.visibility=View.GONE
                binding.networkNotification.visibility=View.GONE
            }else{
                binding.progressbar.visibility=View.VISIBLE
                binding.networkNotification.visibility=View.VISIBLE
            }
        }
        navicontroller=findNavController(R.id.fragmentContainerView)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        navicontroller.navigate(R.id.searchFragment)
        return super.onOptionsItemSelected(item)
    }
}