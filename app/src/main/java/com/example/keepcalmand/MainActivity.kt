package com.example.keepcalmand

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val TAG:String = "TAG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


      BuRoz.setOnClickListener(){

          var message = Toast.makeText(applicationContext, "Witaj", Toast.LENGTH_LONG)
          message.show()

          var nowaAktywnosc: Intent = Intent(applicationContext, AmountOfCalories::class.java)
          startActivity(nowaAktywnosc)
      }

        Scen_Button.setOnClickListener(){

            var newActivity4:Intent = Intent(applicationContext, YourLists::class.java)
            startActivity(newActivity4)

        }
        Info_Button.setOnClickListener{
            var newActivity5:Intent = Intent(applicationContext, MapsActivity::class.java)
            startActivity(newActivity5)
        }

        Log.d(TAG,"OnCreate! MainActivity!")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,"OnStart! MainActivity!")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"OnResume! MainActivity!")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"OnPause! MainActivity!")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"OnStop! MainActivity!")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"OnDestroy! MainActivity!")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG,"OnRestart! MainActivity!")
    }
}