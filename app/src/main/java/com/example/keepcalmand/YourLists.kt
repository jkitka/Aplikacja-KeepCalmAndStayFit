package com.example.keepcalmand

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_your_lists.*
import kotlinx.android.synthetic.main.ticket.view.*

class YourLists : AppCompatActivity() {

        var listAct=ArrayList<List>()
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_your_lists)

        LoadQuery("%")

    }

    fun LoadQuery(title:String){

        var dbManager=DbManager(this)
        val projections = arrayOf("ID","Title", "Monday","Tuesday","Wensday","Thursday","Friday","Saturday","Sunday")
        val selectionArgs= arrayOf(title)
        val cursor=dbManager.Query(projections,"Title like ?",selectionArgs,"Title")
        listAct.clear()
        if(cursor.moveToFirst()){

            do{
                val ID=cursor.getInt(cursor.getColumnIndex("ID"))
                val Title=cursor.getString(cursor.getColumnIndex("Title"))
                val Monday=cursor.getString(cursor.getColumnIndex("Monday"))
                val Tuesday=cursor.getString(cursor.getColumnIndex("Tuesday"))
                val Wensday=cursor.getString(cursor.getColumnIndex("Wensday"))
                val Thursday=cursor.getString(cursor.getColumnIndex("Thursday"))
                val Friday=cursor.getString(cursor.getColumnIndex("Friday"))
                val Saturday=cursor.getString(cursor.getColumnIndex("Saturday"))
                val Sunday=cursor.getString(cursor.getColumnIndex("Sunday"))

                //val ID=cursor.getInt(cursor.getColumnIndex("ID"))
                    listAct.add(List(ID,Title,Monday,Tuesday,Wensday,Thursday,Friday,Saturday,Sunday))

            }while (cursor.moveToNext())

        }
        var myListAdapter=MyListAdapter(this, listAct)
        lvLists.adapter=myListAdapter
    }

    //

    inner class MyListAdapter:BaseAdapter{

        var listActAdapter=ArrayList<List>()
        var context:Context?=null
        constructor(context:Context, listActAdapter:ArrayList<List>):super(){

            this.listActAdapter=listActAdapter
            this.context=context
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var myView=layoutInflater.inflate(R.layout.ticket,null)
            var myList=listActAdapter[position]
            myView.Title_txt.text=myList.listName
            myView.mon_txt.text=myList.listMon
            myView.tue_txt.text=myList.listTue
            myView.wen_txt.text=myList.listWen
            myView.thur_txt.text=myList.listThur
            myView.fri_txt.text=myList.listFri
            myView.sat_txt.text=myList.listSat
            myView.sun_txt.text=myList.listSun
            myView.delete_img.setOnClickListener(View.OnClickListener {

                var dbManager=DbManager(this.context!!)
                val selectionArgs= arrayOf(myList.listID.toString())
                dbManager.Delte("ID=?",selectionArgs)
                LoadQuery("%")
            })
            return myView
        }

        override fun getItem(position: Int): Any {
            return listActAdapter[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
           return listActAdapter.size
        }

    }


}