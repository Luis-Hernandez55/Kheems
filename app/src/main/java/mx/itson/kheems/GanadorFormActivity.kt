package mx.itson.kheems

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import mx.itson.kheems.entidades.Ganador

class GanadorFormActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var txtNombre :EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ganador_form)

        txtNombre=findViewById(R.id.txtNombre)

        val btnGuardar=findViewById<Button>(R.id.btnGuardar)
        btnGuardar.setOnClickListener(this)

        /*val bundle = intent.extras
        val dato = bundle?.getInt("intentos")
        Toast.makeText(this, "Numero de intentos: "+dato, Toast.LENGTH_LONG).show()*/


    }

    override fun onClick(v: View?) {
        //Aqui recibimos la cantidad de intenos y partidas realizadas
        val bundle = intent.extras
        val intentos = bundle?.getInt("intentos")
        Toast.makeText(this, "Numero de intentos: "+intentos, Toast.LENGTH_LONG).show()

        val partidas = bundle?.getInt("partidas")
        Toast.makeText(this, "Numero de partidas: "+partidas, Toast.LENGTH_LONG).show()

        when(v?.id){
            (R.id.btnGuardar) -> {
                Ganador.guardar(applicationContext, ""+txtNombre.getText().toString(), ""+intentos,
                    ""+partidas)
            }
        }
    }
}