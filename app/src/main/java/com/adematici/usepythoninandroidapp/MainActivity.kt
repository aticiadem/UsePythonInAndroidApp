package com.adematici.usepythoninandroidapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.adematici.usepythoninandroidapp.databinding.ActivityMainBinding
import com.chaquo.python.PyObject
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!Python.isStarted()) {
            Python.start(AndroidPlatform(this))
        }

        val py = Python.getInstance()
        val pyObj = py.getModule("script")

        binding.buttonCalculate.setOnClickListener {
            val number = binding.editTextNumber.text.toString().toInt()

            val obj: PyObject = pyObj.callAttr(
                "detectOddAndEven",
                number
            )

            binding.textViewResult.text = obj.toString()
        }

    }
}