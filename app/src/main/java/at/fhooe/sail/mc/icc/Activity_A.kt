package at.fhooe.sail.mc.icc

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import at.fhooe.sail.mc.icc.databinding.ActivityABinding
import kotlin.random.Random

const val SP_ACTIVITY_A_STRING_KEY: String = "ActivityAKey"
class Activity_A : AppCompatActivity() {

    var txt: String = "undefined"
    lateinit var binding: ActivityABinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityABinding.inflate(layoutInflater)
        setContentView(binding.root)

        // lambdas statt oben onclicklistener
        binding.activityAButtonRollDice.setOnClickListener {
            val rnd: Random = Random(System.currentTimeMillis()) // zufallszahlengenerator von kotlin holen
            val v: Int = rnd.nextInt(100)
            txt = "Activity A Message ($v)"
            binding.activityATvOutput.text = txt        // text aus variable soll in der TEXT View dargestellt werden
        }

        binding.activityAButtonSend.setOnClickListener {
            val sp: SharedPreferences = getSharedPreferences(SP_NAME, MODE_PRIVATE) // nicht für andere Apps
            val edt: SharedPreferences.Editor = sp.edit()
            edt.putString(SP_ACTIVITY_A_STRING_KEY, txt)
            edt.commit()
            finish()
        }
    }
}