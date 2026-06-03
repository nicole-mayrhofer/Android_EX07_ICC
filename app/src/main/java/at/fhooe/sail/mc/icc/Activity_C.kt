package at.fhooe.sail.mc.icc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import at.fhooe.sail.mc.icc.databinding.ActivityBBinding
import at.fhooe.sail.mc.icc.databinding.ActivityCBinding
import kotlin.random.Random


class Activity_C : AppCompatActivity() {
    lateinit var binding: ActivityCBinding
    var txt: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // lambdas statt oben onclicklistener
        binding.activityCButtonRollDice.setOnClickListener {
            val rnd: Random = Random(System.currentTimeMillis()) // zufallszahlengenerator von kotlin holen
            val v: Int = rnd.nextInt(100)
            txt = "Activity C Message ($v)"
            binding.activityCTvOutput.text = txt        // text aus variable soll in der TEXT View dargestellt werden
        }

        binding.activityCButtonSend.setOnClickListener {
            val app: IccApplication = application as IccApplication
            // hier casten nach unserem Applikations-Typ
            app.mailBox = txt
            finish()
        }
    }
}