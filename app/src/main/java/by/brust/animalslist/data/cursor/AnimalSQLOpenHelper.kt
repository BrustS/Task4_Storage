package by.brust.animalslist.data.cursor

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import by.brust.animalslist.DATABASE_NAME
import by.brust.animalslist.DATABASE_VERSION
import by.brust.animalslist.SQL_CREATE_TABLE
import by.brust.animalslist.SQL_DELETE_TABLE

class AnimalSQLOpenHelper(context: Context) : SQLiteOpenHelper(
    context, DATABASE_NAME,null, DATABASE_VERSION
) {
    val openHelperDao by lazy { AnimalOpenHelperDao(this) }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
       db?.execSQL(SQL_DELETE_TABLE)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

}