package mx.itson.kheems

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import mx.itson.kheems.adapters.GanadorAdapter
import mx.itson.kheems.entidades.Ganador

var listaGanadores : ListView? = null

class GanadorListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ganador_list)

        listaGanadores = findViewById(R.id.listGanadores)

        cargarGanadores()

    }

    fun cargarGanadores(){
        val ganadores : List<Ganador> = Ganador.obtener(this)
        val ganadorAdapter = GanadorAdapter(this, ganadores)
        listaGanadores?.adapter = ganadorAdapter
    }

}