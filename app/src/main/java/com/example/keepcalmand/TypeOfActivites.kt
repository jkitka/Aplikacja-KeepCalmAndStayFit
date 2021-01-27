package com.example.keepcalmand

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_amount_of_calories.*
import kotlinx.android.synthetic.main.activity_type_of_activites.*
import java.nio.channels.FileLock

class TypeOfActivites : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_type_of_activites)

        val extras = intent.extras
        val calories = extras!!.getInt("Kalorie")//getDouble("Kalorie")
        val extrasNSS = intent.extras
        val nameSC = extrasNSS!!.getString("Scenariusz")
        spr.text = "Kalorie: $calories"

        confirm_b_type.setOnClickListener{

            if(setSports().size==0 || setSports().size>6)
            {
                var info_sports_wrong = Toast.makeText(applicationContext, "Wybrano błędna ilość aktywności", Toast.LENGTH_LONG)
                info_sports_wrong.show()

            }
            else
            {


                var info_sports = Toast.makeText(applicationContext, "Zapisano", Toast.LENGTH_LONG)
                info_sports.show()
                val choosen_sports = setSports()
                val actv_with_numbers = get_Divide(calories)

                val newActivity3 = Intent(applicationContext, Result::class.java)
                newActivity3.putExtra("ListaS", choosen_sports)
                newActivity3.putExtra("ListaK", actv_with_numbers)
                newActivity3.putExtra("Scenariusz", nameSC)
                startActivity(newActivity3)

            }


        }
    }

    fun setSports(): ArrayList<String> {
        var ListofSports = ArrayList<String>()

        if(koszykowka_CHB.isChecked){
            ListofSports.add("Koszykówka")
        }
        if(Rower_CHB.isChecked){
            ListofSports.add("Jazda na Rowerze")
        }
        if(nozna_CHB.isChecked){
            ListofSports.add("Piłka nożna")
        }
        if(Bieg_CHB.isChecked){
            ListofSports.add("Bieganie")
        }
        if(siatkowka_CHB.isChecked){
            ListofSports.add("Siatkówka")
        }
        if(silownia_CHB.isChecked){
            ListofSports.add("Siłownia")
        }
        if(plywanie_CHB.isChecked){
            ListofSports.add("Pływanie")
        }
        if(skakanka_CHB.isChecked){
            ListofSports.add("Skakanie na skakance")
        }
        if(squash_CHB.isChecked){
            ListofSports.add("Squash")
        }
        if(kon_CHB.isChecked){
            ListofSports.add("Jazda konna")
        }


            return ListofSports


        }

        fun get_Divide(cal: Int): ArrayList<Int> {

            val ListS = setSports()
            var Divide_sports: ArrayList<Int> = arrayListOf()
            //var Divide_sports = ArrayList<Int>()

            if( ListS.contains("Koszykówka")) {

                val result_basket: Int = (cal/138)*15
                Divide_sports.add(result_basket)

            }
            if(ListS.contains("Jazda na Rowerze")){

                val result_bike: Int = (cal/75)*15
                Divide_sports.add(result_bike)
            }
            if(ListS.contains("Piłka nożna")){

                val result_foot: Int = (cal/163)*14
                Divide_sports.add(result_foot)
            }
            if(ListS.contains("Bieganie")){

                val result_run: Int = (cal/240)*15
                Divide_sports.add(result_run)
            }
            if(ListS.contains("Siatkówka")){

                val result_volley: Int = (cal/113)*14
                Divide_sports.add(result_volley)
            }
            if(ListS.contains("Siłownia")){

                val result_gym: Int = (cal/113)*12
                Divide_sports.add(result_gym)
            }
            if(ListS.contains("Pływanie")){

                val result_swim: Int = ((cal/100)*15)
                Divide_sports.add(result_swim)
            }
            if(ListS.contains("Skakanie na skakance")){

                val result_jump: Int = (cal/144)*11
                Divide_sports.add(result_jump)
            }
            if(ListS.contains("Squash")){

                val result_squash: Int = (cal/124)*11
                Divide_sports.add(result_squash)
            }
            if(ListS.contains("Jazda konna")){

                val result_horse: Int = (cal/163)*13
                Divide_sports.add(result_horse)
            }

            return Divide_sports

        }







}