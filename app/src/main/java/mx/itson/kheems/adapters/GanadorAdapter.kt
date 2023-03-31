package mx.itson.kheems.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import mx.itson.kheems.R
import mx.itson.kheems.entidades.Ganador

class GanadorAdapter(context: Context?, ganadores:List<Ganador>) : BaseAdapter() {

    var context : Context? = context
    var ganadores : List<Ganador> = ganadores

    override fun getCount(): Int {
        return ganadores.size
    }

    override fun getItem(i: Int): Any {
        return ganadores[i]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(i: Int, View: View?, p2: ViewGroup?): View {
        var elemento = LayoutInflater.from(context).inflate(R.layout.item_ganador, null)

        try {
            val u =getItem(i) as Ganador

            val txtNombre : TextView = elemento.findViewById(R.id.txtNombre)
            txtNombre.setText(u.nombre)

            val txtIntentos : TextView = elemento.findViewById(R.id.txtIntentos)
            txtIntentos.setText(u.intentos)

            val txtPartidas : TextView = elemento.findViewById(R.id.txtPartidas)
            txtPartidas.setText(u.partidas)
        }catch (ex: Exception){
            Log.e("Ocurrio un error al cargar la lista", ex.toString())
        }
        return elemento
    }
}