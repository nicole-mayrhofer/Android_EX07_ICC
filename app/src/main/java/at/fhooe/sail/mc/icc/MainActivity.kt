package at.fhooe.sail.mc.icc

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import at.fhooe.sail.mc.icc.databinding.ActivityMainBinding

const val TAG: String = "ICC-Test"
const val SP_NAME: String = "ICC-Test-SharedPreferences"

class MainActivity : AppCompatActivity(), View.OnClickListener, SharedPreferences.OnSharedPreferenceChangeListener {

    lateinit var binding: ActivityMainBinding

    val launcher: ActivityResultLauncher<Intent> =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { res ->
            if (res.resultCode == RESULT_OK) {
            val txt: String? = res.data?.getStringExtra(AR_ACTIVITY_B_STRING_KEY)
                binding.activityMainTvOutput.text = txt
            } else {
                Log.e(TAG, "MainActivity::ActivityResultLauncher::Listener: --> no valid result received")
            }

        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
            activityMainButtonA.setOnClickListener(this@MainActivity)
            activityMainButtonB.setOnClickListener(this@MainActivity)
            activityMainButtonC.setOnClickListener(this@MainActivity)
            activityMainButtonD.setOnClickListener(this@MainActivity)
        }
        val sp: SharedPreferences = getSharedPreferences(SP_NAME, MODE_PRIVATE)
        sp.registerOnSharedPreferenceChangeListener(this)

    }

    override fun onResume() {
       super.onResume()

        val app: IccApplication = application as IccApplication
        app.mailBox?.also { txt ->
            binding.activityMainTvOutput.text = txt
        }

        /* Mailbox "leeren" - sonst bleibt alter Wert erhalten */
       app.mailBox = null

        IccSingleton.mailBox?.also {
            binding.activityMainTvOutput.text = it
        }
        IccSingleton.mailBox = null


        /*
        val sp: SharedPreferences = getSharedPreferences(SP_NAME, MODE_PRIVATE)
         val txt = sp.getString(SP_ACTIVITY_A_STRING_KEY, "undefined")     // fallback wenn es nicht existiert
         binding.activityMainTvOutput.text = txt
         */
    }

    override fun onClick(v: View?) {
        var i: Intent? = null
        when (v?.id) {
            R.id.activity_main_button_a -> { i = Intent(this, Activity_A::class.java) } // expliziter INTENT
            R.id.activity_main_button_b -> {    /* launcher starts working here*/
                val j = Intent(this, Activity_B::class.java)
                launcher.launch(j)
            }
            R.id.activity_main_button_c -> {i = Intent(this, Activity_C::class.java)}
            R.id.activity_main_button_d -> {i = Intent(this, Activity_D::class.java)}

            else -> {
                Log.e(TAG, "Main Activity::onClick: unexpected ID encountered")
            }
        }  // when

        i?.let {
            startActivity(it)
        }
    }

    override fun onSharedPreferenceChanged(
        sp: SharedPreferences?,
        key: String?
    ) {
        Log.i(TAG, "MainActivity::onSharedPreferencesChanged() --> $key value changed ...")
        if (key.equals(SP_ACTIVITY_A_STRING_KEY)) {
            val txt = sp?.getString(
                SP_ACTIVITY_A_STRING_KEY,
                "undefined"     // fallback wenn es nicht existiert
            )
            binding.activityMainTvOutput.text = txt
        }
    }
}