package mx.itson.kheems

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import kotlin.random.Random

class MainActivity : AppCompatActivity(), View.OnClickListener{
    var ubicacion = 0
    var n = 0
    var intentos = 0
    var partidas = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        iniciar()
        val btnIniciar =findViewById<View>(R.id.btnReiniciar) as Button
        btnIniciar.setOnClickListener(this)
        val btnConsulta=findViewById<View>(R.id.btnConsulta) as Button
        btnConsulta.setOnClickListener(this)

        for (i in 1..12){
            val btnSeleccion = findViewById<View>(resources.getIdentifier("opcion$i", "id", this.packageName))
            as ImageButton
            btnSeleccion.setOnClickListener(this)
        }
    }

    fun iniciar(){
        findViewById<View>(R.id.opcion1).setBackgroundResource(R.drawable.icon_pregunta)
        findViewById<View>(R.id.opcion2).setBackgroundResource(R.drawable.icon_pregunta)
        findViewById<View>(R.id.opcion3).setBackgroundResource(R.drawable.icon_pregunta)
        findViewById<View>(R.id.opcion4).setBackgroundResource(R.drawable.icon_pregunta)
        findViewById<View>(R.id.opcion5).setBackgroundResource(R.drawable.icon_pregunta)
        findViewById<View>(R.id.opcion6).setBackgroundResource(R.drawable.icon_pregunta)
        findViewById<View>(R.id.opcion7).setBackgroundResource(R.drawable.icon_pregunta)
        findViewById<View>(R.id.opcion8).setBackgroundResource(R.drawable.icon_pregunta)
        findViewById<View>(R.id.opcion9).setBackgroundResource(R.drawable.icon_pregunta)
        findViewById<View>(R.id.opcion10).setBackgroundResource(R.drawable.icon_pregunta)
        findViewById<View>(R.id.opcion11).setBackgroundResource(R.drawable.icon_pregunta)
        findViewById<View>(R.id.opcion12).setBackgroundResource(R.drawable.icon_pregunta)

        ubicacion = Random.nextInt(1, 12)
    }

    fun destapar(opcion: Int){
        n++
        //Se cuentan la cantidad de intentos
        intentos++
        if (opcion == ubicacion){
            n = 0
            //Ya perdio
            Toast.makeText(this, "¡PERDISTE!", Toast.LENGTH_LONG).show()
            val vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator
            val wavePatron = longArrayOf(0, 250, 70, 250, 70, 250)
            vibrator.vibrate(VibrationEffect.createWaveform(wavePatron, -1))
            for (i in 1..12){
                val btnSeleccion = findViewById<View>(resources.getIdentifier("opcion$i", "id", this.packageName))
                as ImageButton
                if (i == opcion){
                    //Se destapa la carta del cheems llorando
                    btnSeleccion.setBackgroundResource(R.drawable.icon_cheems_llora)
                }else{
                    //Todas las cartas se destapan con el cheems normal
                    btnSeleccion.setBackgroundResource(R.drawable.icon_cheems)
                }
            }
        }else if(n == 11){
            //Si el usuario da 11 tap a cartas sin perder entonces se considera ganador
            val vibrator =getSystemService(VIBRATOR_SERVICE) as Vibrator
            vibrator.vibrate(VibrationEffect.createOneShot(300, VibrationEffect.DEFAULT_AMPLITUDE))
            Toast.makeText(this, "¡GANASTE!", Toast.LENGTH_LONG).show()
            for (i in 1..12){
                //Iteramos cada carta
                val btnSeleccion = findViewById<View>(resources.getIdentifier("opcion$i", "id", this.packageName))
                as ImageButton
                if (i == ubicacion){
                    //La carta que originalmente tenia al cheems llorando se convierte en cheems win
                    btnSeleccion.setBackgroundResource(R.drawable.cheems_win)
                }else{
                    //Se destapa la carta donde se dio el ultimo tap
                    btnSeleccion.setBackgroundResource(R.drawable.icon_cheems)
                }
                n = 0
            }

            //Se abre el activity_ganador_form
            val intentFormulario = Intent(this, GanadorFormActivity::class.java)
            //Aqui se envia la cantidad en intentos y partidas a la clase GanadorFormActivity
            intentFormulario.putExtra("intentos", intentos)
            intentFormulario.putExtra("partidas", partidas)
            startActivity(intentFormulario)


        }else{
            val vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator
            vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE))
            //No perdió todavia, por lo tanto se destapa esa carta con el cheems normal
            val btnSeleccion =findViewById<View>(resources.getIdentifier("opcion$opcion", "id", this.packageName))
            as ImageButton
            btnSeleccion.setBackgroundResource(R.drawable.icon_cheems)
        }
    }

    override fun onClick(v: View?) {

        when(v?.id){
            (R.id.opcion1) -> {
                destapar(1)
            }
            (R.id.opcion2) -> {
                destapar(2)
            }
            (R.id.opcion3) -> {
                destapar(3)
            }
            (R.id.opcion4) -> {
                destapar(4)
            }
            (R.id.opcion5) -> {
                destapar(5)
            }
            (R.id.opcion6) -> {
                destapar(6)
            }
            (R.id.opcion7) -> {
                destapar(7)
            }
            (R.id.opcion8) -> {
                destapar(8)
            }
            (R.id.opcion9) -> {
                destapar(9)
            }
            (R.id.opcion10) -> {
                destapar(10)
            }
            (R.id.opcion11) -> {
                destapar(11)
            }
            (R.id.opcion12) -> {
                destapar(12)
            }
            (R.id.btnReiniciar) -> {
                iniciar()
                n = 0
                partidas ++
                Toast.makeText(this, "Numero partidas: "+partidas, Toast.LENGTH_LONG).show()
            }
            (R.id.btnConsulta) -> {
                val intentLista = Intent(this, GanadorListActivity::class.java)
                startActivity(intentLista)
            }
        }

    }
}