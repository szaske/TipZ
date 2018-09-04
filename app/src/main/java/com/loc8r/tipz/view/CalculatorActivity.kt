package com.loc8r.tipz.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import com.loc8r.tipz.R
import com.loc8r.tipz.databinding.CalcActivityBinding
import com.loc8r.tipz.viewmodel.CalculatorViewModel

class CalculatorActivity : AppCompatActivity() {

    lateinit var binding: CalcActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This is the dataabinding replacement for the standard setContentView call
        // it associates and instantiates the layout, creates the binding and returns it
        binding = DataBindingUtil.setContentView(this, R.layout.calc_activity)

        // And this binds the views to the viewmodel
        binding.vm = CalculatorViewModel(application)

        setSupportActionBar(binding.toolbar)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
