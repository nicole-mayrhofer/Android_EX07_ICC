package at.fhooe.sail.mc.icc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import at.fhooe.sail.mc.icc.databinding.ActivityDBinding
import kotlin.random.Random


class Activity_D : AppCompatActivity() {

    var txt: String? = null

    lateinit var binding: ActivityDBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // lambdas statt oben onclicklistener
        binding.activityDButtonRollDice.setOnClickListener {
            val rnd: Random = Random(System.currentTimeMillis()) // zufallszahlengenerator von kotlin holen
            val v: Int = rnd.nextInt(100)
            txt = "Activity D Message ($v)"
            binding.activityDTvOutput.text = txt        // text aus variable soll in der TEXT View dargestellt werden
        }

        binding.activityDButtonSend.setOnClickListener {
            IccSingleton.mailBox = txt
            finish()
        }
    }
}