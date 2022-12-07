package com.arysugiarto.netplix

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val thread:Unit=object:Thread(){
            override fun run() {
                try {
                    sleep(3000)
                    finish()
                }catch (e:Exception){
                    e.printStackTrace()
                }finally {
                    val intent=Intent(this@SplashScreen,MainActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
                }
            }
        }.start()
    }
}