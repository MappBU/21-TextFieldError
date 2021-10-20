package com.example.material

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import com.example.material.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(),View.OnKeyListener {

    var binding:ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding?.enterCity?.setOnKeyListener(this)

        // При нажатии на иконку вопроса вызывается снизу снейкбар с текстом... и кнопкой...
        binding?.sectionEnterCity?.setEndIconOnClickListener{
            Snackbar.make(it, R.string.headerSnack, Snackbar.LENGTH_LONG)
                .setBackgroundTint(ContextCompat.getColor (this, R.color.orange))
                .setAction(R.string.headerActionSnack){}
                .show()
        }

        // Метож с условиями о выводе ошибки
        binding?.sectionEnterCity?.editText?.doOnTextChanged { text, start, before, count ->

            when {
                count>7 -> binding?.sectionEnterCity?.error = getString(R.string.maxError)
                count<1 -> binding?.sectionEnterCity?.helperText = getString(R.string.minError)
            }

            binding?.error?.text = text

        }


    }

    // С прдыдущего урока
    override fun onKey(view: View, i: Int, keyEvent: KeyEvent): Boolean {
        when (view.id) {
            R.id.enterCity -> {
                if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {
                    binding?.result?.text = binding?.enterCity?.text
                    binding?.enterCity?.setText("")
                    return true
                }

            }
        }

        return false
    }


}