package com.loc8r.tipz.view

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.loc8r.tipz.R
import com.loc8r.tipz.databinding.CalcActivityBinding
import com.loc8r.tipz.viewmodel.CalculatorViewModel

class CalculatorActivity : AppCompatActivity(), SaveDialogFragment.Callback {
    override fun onSaveTip(name: String) {
        Snackbar.make(binding.root, "Saved $name", Snackbar.LENGTH_SHORT).show()
    }

    lateinit var binding: CalcActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This is the dataabinding replacement for the standard setContentView call
        // it associates and instantiates the layout, creates the binding and returns it
        binding = DataBindingUtil.setContentView(this, R.layout.calc_activity)

        // And this creates a new viewmodel when it's launched
        // and then returns that same viewmodel between screen rotations
        binding.vm = ViewModelProviders.of(this)
                .get(CalculatorViewModel::class.java)

        setSupportActionBar(binding.toolbar)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_tip_calculator, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_save -> {
                showSaveDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showSaveDialog() {
        val saveFragment = SaveDialogFragment()
        Log.d("Zaske", "")
        saveFragment.show(supportFragmentManager, "SaveDialog")
    }
}
