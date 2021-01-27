package com.example.keepcalmand

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_amount_of_calories.*
import kotlinx.android.synthetic.main.activity_type_of_activites.*

class AmountOfCalories : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_amount_of_calories)


        Apply_button.setOnClickListener{
            var info = Toast.makeText(applicationContext, "Zapisano", Toast.LENGTH_LONG)
            var info_wrong = Toast.makeText(applicationContext, "Błędna ilość kalorii", Toast.LENGTH_LONG)
            val calories = Integer.parseInt(Amount_editText.text.toString())//.toDouble()
            val name_s = name_s_txt.text.toString()

            if(calories > 0){
                info.show()
                val nowaAktywnosc2: Intent = Intent(applicationContext, TypeOfActivites::class.java)
                nowaAktywnosc2.putExtra("Kalorie", calories)
                nowaAktywnosc2.putExtra("Scenariusz", name_s)
                startActivity(nowaAktywnosc2)
            }
            else{
               info_wrong.show()
            }
        }
    }
}