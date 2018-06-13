package com.example.dell.controldelabos

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.util.Log
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreen : AppCompatActivity() {
    private val splash_time_out:Long=2000
    var sw=true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        //val sw=checkNetwork()
        //Log.d("VERIF",sw.toString())
        Handler().postDelayed(Runnable {
            val intent:Intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        },splash_time_out)
/*        do {
            if(!sw)
            {
                Snackbar.make(splash,"Verifique su conexion a Internet",Snackbar.LENGTH_LONG).setAction("AGAIN"){

                }
            }else{

            }
        }while (!sw)*/


    }
   /* fun checkNetwork(): Boolean {
        var connect:ConnectivityManager=applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE)as ConnectivityManager
        var info:NetworkInfo=connect.activeNetworkInfo
        var isConnect:Boolean=info.isConnectedOrConnecting
        return isConnect
    }*/
}
