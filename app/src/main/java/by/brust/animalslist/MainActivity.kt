package by.brust.animalslist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

var sortSetting : String = "name"
var isUseRoom : Boolean = true

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}