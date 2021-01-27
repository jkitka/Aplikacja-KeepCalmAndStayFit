package com.example.keepcalmand

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQuery
import android.database.sqlite.SQLiteQueryBuilder
import android.widget.Toast
import java.security.AccessControlContext

class DbManager{

    val dbName="MyLists"
    val dbTable="Lists"
    val colID="ID"
    val colTitle="Title"
    val colMon="Monday"
    val colTue="Tuesday"
    val colWen="Wensday"
    val colThur="Thursday"
    val colFri="Friday"
    val colSat="Saturday"
    val colSun="Sunday"

    val dbVersion=1
    //CREATE TABLE IF NOT EXISTS MyLists (ID INTEGER PRIMARY KEY, title TEXT, Description TEXT);"
    val sqlCreateTable="CREATE TABLE IF NOT EXISTS "+ dbTable +" ("+ colID +" INTEGER PRIMARY KEY,"+
            colTitle + " TEXT,"+ colMon + " TEXT," + colTue + " TEXT," + colWen +" TEXT," + colThur + " TEXT," + colFri + " TEXT," + colSat + " TEXT," + colSun +  " TEXT);"
    var sqlDB:SQLiteDatabase?=null


    constructor(context: Context){

        var db=DatabaseHelperLists(context)
        sqlDB=db.writableDatabase

    }

    inner class DatabaseHelperLists:SQLiteOpenHelper{
        var context:Context?=null
        constructor(context:Context):super(context,dbName,null,dbVersion){
            this.context=context
        }
        override fun onCreate(db: SQLiteDatabase?) {
           db!!.execSQL(sqlCreateTable)
            Toast.makeText(this.context, "Utworzono bazę danych", Toast.LENGTH_LONG).show()
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db!!.execSQL("Drop table IF EXISTS "+ dbTable)
        }


    }



    fun Insert(values:ContentValues):Long{

        val ID=sqlDB!!.insert(dbTable,"",values)
        return ID

    }

    fun Query(projection:Array<String>,selection:String,selectionArgs:Array<String>,sorOrder:String): Cursor {

        val qb=SQLiteQueryBuilder()
        qb.tables=dbTable
        val cursor=qb.query(sqlDB,projection,selection,selectionArgs,null,null,sorOrder)
        return cursor
    }

    fun Delte(selection:String,selectionArgs: Array<String>):Int{

        val count=sqlDB!!.delete(dbTable,selection,selectionArgs)
        return count
    }

}