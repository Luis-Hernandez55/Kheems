package mx.itson.kheems.persistencia

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class KheemsDB(context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int)
    : SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(sqLiteDatebase: SQLiteDatabase) {
        try {
            //Creación de la base de datos
            sqLiteDatebase.execSQL("CREATE TABLE Ganador "+
            "(id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, intentos TEXT, partidas TEXT)")

        }catch (ex: Exception){
            Log.e("Ocurrio un error al crear la base de datos", ex.toString())
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}