package com.example.tests.base

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.tests.R

abstract class ABaseActivity : AppCompatActivity() {
    open fun getContainer(): Int = R.id.container

    init {
        inject()
    }

    open fun inject() {}

    fun replace(fragment: Fragment, backStack: String? = null, tag: String? = null) {

        supportFragmentManager.beginTransaction()
            .replace(getContainer(), fragment, tag).apply {
                backStack?.let { addToBackStack(it) }
            }
            .commit()
    }

    fun toast(@StringRes stringId: Int) {
        Toast.makeText(this, stringId, Toast.LENGTH_LONG).show()
    }

    fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}