package mx.itson.kheems.entidades

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import mx.itson.kheems.persistencia.KheemsDB

object Ganador : java.io.Serializable {

    var id = 0
    var nombre:String? = null
    var intentos:String? = null
    var partidas:String? = null

    //Guardamos los datos
    fun guardar(context: Context, nombre: String, intentos: String, partidas: String){
        try {
            val kheemsDB = KheemsDB(context,"Kheems", null, 1)
            val baseDatos : SQLiteDatabase = kheemsDB.writableDatabase
            val valores = ContentValues()
            valores.put("nombre", nombre)
            valores.put("intentos", intentos)
            valores.put("partidas",partidas)
            baseDatos.insert("Ganador", null, valores)
        }catch (ex:Exception){
            Log.e("Ocurrio un error al guardar", ex.toString())
        }
    }

    //Obtenemos los datos
    fun obtener(context: Context) : List<Ganador>{
        var ganadores : MutableList<Ganador> = ArrayList()
        try {
            val kheemsDB = KheemsDB(context, "Kheems", null, 1)
            val baseDatos : SQLiteDatabase = kheemsDB.readableDatabase

            val cursor = baseDatos.rawQuery("SELECT id, nombre, intentos, partidas FROM Ganador",
            null)

            while (cursor.moveToNext()){
                var u = Ganador.apply {
                    nombre = cursor.getString(1)
                intentos = cursor.getString(2)
                partidas = cursor.getString(3)
                }
                ganadores.add(u)
            }
        }catch (ex: Exception){
            Log.e("Ocurrio un error en los usuarios", ex.toString())
        }
        return ganadores
    }

}