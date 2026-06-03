package at.fhooe.sail.mc.icc

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import at.fhooe.sail.mc.icc.databinding.ActivityBBinding
import kotlin.random.Random


    const val AR_ACTIVITY_B_STRING_KEY: String = "ActivityBKey"
class Activity_B : AppCompatActivity() {
    lateinit var binding: ActivityBBinding
    var txt: String = "undefined"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // lambdas statt oben onclicklistener
        binding.activityBButtonRollDice.setOnClickListener {
            val rnd: Random = Random(System.currentTimeMillis()) // zufallszahlengenerator von kotlin holen
            val v: Int = rnd.nextInt(100)
            txt = "Activity B Message ($v)"
            binding.activityBTvOutput.text = txt        // text aus variable soll in der TEXT View dargestellt werden
        }

        binding.activityBButtonSend.setOnClickListener {
            val i: Intent = Intent()
            i.putExtra(AR_ACTIVITY_B_STRING_KEY, txt)
            setResult(RESULT_OK, i)
            finish()
        }
    }
}