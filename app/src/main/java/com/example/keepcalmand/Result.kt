package com.example.keepcalmand


import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_result.*

class Result : AppCompatActivity() {
    val dbTable="Lists"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val extrasS = intent.extras
        val choosen_spotrs = extrasS!!.getStringArrayList("ListaS")
        val extrasD = intent.extras
        val actv_with_numbers = extrasD!!.getIntegerArrayList("ListaK")
        val extrasNS = intent.extras
        val nameSCC = extrasNS!!.getString("Scenariusz")





        if (choosen_spotrs != null) {
            if (actv_with_numbers != null) {
                final_result(choosen_spotrs,actv_with_numbers)
            }
        }






        final_confirm_button.setOnClickListener(){
            var dbManager=DbManager(this)




            var values=ContentValues()
            values.put("Title",nameSCC.toString())
            if (choosen_spotrs != null) {
                if(choosen_spotrs.size==6){
                    values.put("Monday","Poniedziałek: ${choosen_spotrs!![0]}    Czas:  ${actv_with_numbers!![0]}min")
                    values.put("Tuesday","Wtorek: ${choosen_spotrs!![1]}    Czas: ${actv_with_numbers!![1]}min")
                    values.put("Wensday","Środa: ${choosen_spotrs!![2]}    Czas: ${actv_with_numbers!![2]}min")
                    values.put("Thursday", "Czwartek: ${choosen_spotrs!![3]}    Czas: ${actv_with_numbers!![3]}min")
                    values.put("Friday","Piątek: ${choosen_spotrs!![4]}    Czas: ${actv_with_numbers!![4]}min")
                    values.put("Saturday","Sobota: ${choosen_spotrs!![5]}    Czas: ${actv_with_numbers!![5]}min")
                    values.put("Sunday","Niedziela: ODPOCZYNEK!")
                }
            }
            if (choosen_spotrs != null) {
                if(choosen_spotrs.size==5){
                    values.put("Monday","Poniedziałek: ${choosen_spotrs!![0]}    Czas:  ${actv_with_numbers!![0]}min")
                    values.put("Tuesday","Wtorek: ${choosen_spotrs!![1]}    Czas: ${actv_with_numbers!![1]}min")
                    values.put("Wensday","Środa: ${choosen_spotrs!![2]}    Czas: ${actv_with_numbers!![2]}min")
                    values.put("Thursday", "Czwartek: ${choosen_spotrs!![3]}    Czas: ${actv_with_numbers!![3]}min")
                    values.put("Friday","Piątek: ${choosen_spotrs!![4]}    Czas: ${actv_with_numbers!![4]}min")
                    values.put("Saturday","Sobota: ${choosen_spotrs!![0]}    Czas: ${actv_with_numbers!![0]}min")
                    values.put("Sunday","Niedziela: ODPOCZYNEK!")
                }
            }
            if (choosen_spotrs != null) {
                if(choosen_spotrs.size==4){
                    values.put("Monday","Poniedziałek: ${choosen_spotrs!![0]}    Czas:  ${actv_with_numbers!![0]}min")
                    values.put("Tuesday","Wtorek: ${choosen_spotrs!![1]}    Czas: ${actv_with_numbers!![1]}min")
                    values.put("Wensday","Środa: ${choosen_spotrs!![2]}    Czas: ${actv_with_numbers!![2]}min")
                    values.put("Thursday", "Czwartek: ${choosen_spotrs!![3]}    Czas: ${actv_with_numbers!![3]}min")
                    values.put("Friday","Piątek: ${choosen_spotrs!![0]}    Czas: ${actv_with_numbers!![0]}min")
                    values.put("Saturday","Sobota: ${choosen_spotrs!![1]}    Czas: ${actv_with_numbers!![1]}min")
                    values.put("Sunday","Niedziela: ODPOCZYNEK!")
                }
            }
            if (choosen_spotrs != null) {
                if(choosen_spotrs.size==3){
                    values.put("Monday","Poniedziałek: ${choosen_spotrs!![0]}    Czas:  ${actv_with_numbers!![0]}min")
                    values.put("Tuesday","Wtorek: ${choosen_spotrs!![1]}    Czas: ${actv_with_numbers!![1]}min")
                    values.put("Wensday","Środa: ${choosen_spotrs!![2]}    Czas: ${actv_with_numbers!![2]}min")
                    values.put("Thursday", "Czwartek: ${choosen_spotrs!![0]}    Czas: ${actv_with_numbers!![0]}min")
                    values.put("Friday","Piątek: ${choosen_spotrs!![1]}    Czas: ${actv_with_numbers!![1]}min")
                    values.put("Saturday","Sobota: ${choosen_spotrs!![2]}    Czas: ${actv_with_numbers!![2]}min")
                    values.put("Sunday","Niedziela: ODPOCZYNEK!")
                }
            }
            if (choosen_spotrs != null) {
                if(choosen_spotrs.size==2){
                    values.put("Monday","Poniedziałek: ${choosen_spotrs!![0]}    Czas:  ${actv_with_numbers!![0]}min")
                    values.put("Tuesday","Wtorek: ${choosen_spotrs!![1]}    Czas: ${actv_with_numbers!![1]}min")
                    values.put("Wensday","Środa: ${choosen_spotrs!![0]}    Czas: ${actv_with_numbers!![0]}min")
                    values.put("Thursday", "Czwartek: ${choosen_spotrs!![1]}    Czas: ${actv_with_numbers!![1]}min")
                    values.put("Friday","Piątek: ${choosen_spotrs!![0]}    Czas: ${actv_with_numbers!![0]}min")
                    values.put("Saturday","Sobota: ${choosen_spotrs!![1]}    Czas: ${actv_with_numbers!![1]}min")
                    values.put("Sunday","Niedziela: ODPOCZYNEK!")
                }
            }
            if (choosen_spotrs != null) {
                if(choosen_spotrs.size==1){
                    values.put("Monday","Poniedziałek: ${choosen_spotrs!![0]}    Czas:  ${actv_with_numbers!![0]}min")
                    values.put("Tuesday","Wtorek: ${choosen_spotrs!![0]}    Czas: ${actv_with_numbers!![0]}min")
                    values.put("Wensday","Środa: ${choosen_spotrs!![0]}    Czas: ${actv_with_numbers!![0]}min")
                    values.put("Thursday", "Czwartek: ${choosen_spotrs!![0]}    Czas: ${actv_with_numbers!![0]}min")
                    values.put("Friday","Piątek: ${choosen_spotrs!![0]}    Czas: ${actv_with_numbers!![0]}min")
                    values.put("Saturday","Sobota: ${choosen_spotrs!![0]}    Czas: ${actv_with_numbers!![0]}min")
                    values.put("Sunday","Niedziela: ODPOCZYNEK!")
                }
            }

           val ID=dbManager.Insert(values)
            if(ID>0){
               Toast.makeText(this,"Dodano scenariusz",Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this,"Nie mozna dodac scenariusza", Toast.LENGTH_LONG).show()
            }
       }


    }

    fun final_result(listS: ArrayList<String>, listW: ArrayList<Int>){

        if (listS != null) {
             if(listS.size==6){

                 activity1_txt.text="Aktywność: ${listS[0]}"
                 result1_txt.text="Czas: ${listW[0]} min"
                 activity2_txt.text="Aktywność: ${listS[1]}"
                 result2_txt.text="Czas: ${listW[1]} min"
                 activity3_txt.text="Aktywność: ${listS[2]}"
                 result3_txt.text="Czas: ${listW[2]} min"
                 activity4_txt.text="Aktywność: ${listS[3]}"
                 result4_txt.text="Czas: ${listW[3]} min"
                 activity5_txt.text="Aktywność: ${listS[4]}"
                 result5_txt.text="Czas: ${listW[4]} min"
                 activity6_txt.text="Aktywność: ${listS[5]}"
                 result6_txt.text="Czas: ${listW[5]} min"

        }
            if(listS.size==5){

                activity1_txt.text="Aktywność: ${listS[0]}"
                result1_txt.text="Czas: ${listW[0]} min"
                activity2_txt.text="Aktywność: ${listS[1]}"
                result2_txt.text="Czas: ${listW[1]} min"
                activity3_txt.text="Aktywność: ${listS[2]}"
                result3_txt.text="Czas: ${listW[2]} min"
                activity4_txt.text="Aktywność: ${listS[3]}"
                result4_txt.text="Czas: ${listW[3]} min"
                activity5_txt.text="Aktywność: ${listS[4]}"
                result5_txt.text="Czas: ${listW[4]} min"
                activity6_txt.text="Aktywność: ${listS[0]}"
                result6_txt.text="Czas: ${listW[0]} min"

            }

            if(listS.size==4){

                activity1_txt.text="Aktywność: ${listS[0]}"
                result1_txt.text="Czas: ${listW[0]} min"
                activity2_txt.text="Aktywność: ${listS[1]}"
                result2_txt.text="Czas: ${listW[1]} min"
                activity3_txt.text="Aktywność: ${listS[2]}"
                result3_txt.text="Czas: ${listW[2]} min"
                activity4_txt.text="Aktywność: ${listS[3]}"
                result4_txt.text="Czas: ${listW[3]} min"
                activity5_txt.text="Aktywność: ${listS[0]}"
                result5_txt.text="Czas: ${listW[0]} min"
                activity6_txt.text="Aktywność: ${listS[1]}"
                result6_txt.text="Czas: ${listW[1]} min"

            }

            if(listS.size==3){

                activity1_txt.text="Aktywność: ${listS[0]}"
                result1_txt.text="Czas: ${listW[0]} min"
                activity2_txt.text="Aktywność: ${listS[1]}"
                result2_txt.text="Czas: ${listW[1]} min"
                activity3_txt.text="Aktywność: ${listS[2]}"
                result3_txt.text="Czas: ${listW[2]} min"
                activity4_txt.text="Aktywność: ${listS[0]}"
                result4_txt.text="Czas: ${listW[0]} min"
                activity5_txt.text="Aktywność: ${listS[1]}"
                result5_txt.text="Czas: ${listW[1]} min"
                activity6_txt.text="Aktywność: ${listS[2]}"
                result6_txt.text="Czas: ${listW[2]} min"

            }

            if(listS.size==2){

                activity1_txt.text="Aktywność: ${listS[0]}"
                result1_txt.text="Czas: ${listW[0]} min"
                activity2_txt.text="Aktywność: ${listS[1]}"
                result2_txt.text="Czas: ${listW[1]} min"
                activity3_txt.text="Aktywność: ${listS[0]}"
                result3_txt.text="Czas: ${listW[0]} min"
                activity4_txt.text="Aktywność: ${listS[1]}"
                result4_txt.text="Czas: ${listW[1]} min"
                activity5_txt.text="Aktywność: ${listS[0]}"
                result5_txt.text="Czas: ${listW[0]} min"
                activity6_txt.text="Aktywność: ${listS[1]}"
                result6_txt.text="Czas: ${listW[1]} min"

            }

            if(listS.size==1){

                activity1_txt.text="Aktywność: ${listS[0]}"
                result1_txt.text="Czas: ${listW[0]} min"
                activity2_txt.text="Aktywność: ${listS[0]}"
                result2_txt.text="Czas: ${listW[0]} min"
                activity3_txt.text="Aktywność: ${listS[0]}"
                result3_txt.text="Czas: ${listW[0]} min"
                activity4_txt.text="Aktywność: ${listS[0]}"
                result4_txt.text="Czas: ${listW[0]} min"
                activity5_txt.text="Aktywność: ${listS[0]}"
                result5_txt.text="Czas: ${listW[0]} min"
                activity6_txt.text="Aktywność: ${listS[0]}"
                result6_txt.text="Czas: ${listW[0]} min"

            }



       }



    }

}