package com.loc8r.tipz.view

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.util.Log
import android.view.View
import android.widget.EditText
import com.loc8r.tipz.R

class SaveDialogFragment : DialogFragment() {

    interface Callback {
        fun onSaveTip(name: String)
    }

    // Creating a variable to hold our callback.  It is nullable because we don't have access
    // to it until we attach to the activity can get a context.
    private var saveTipCallBack: SaveDialogFragment.Callback? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // This (as?) is called a 'safe' cast, and is a tricky way to instantiate sTC.
        // I want it to be a Callback, but it will cause an except to cast it as such if the
        // context is null.  This makes me wait for context to not be null before I cast
        // see: https://kotlinlang.org/docs/reference/typecasts.html
        saveTipCallBack = context as? Callback
    }

    override fun onDetach() {
        super.onDetach()
        // Make sure I delete the callback when the Dialog is destroyed
        saveTipCallBack = null
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)

        // unique way to create a dialog in Kotlin
        val saveDialog = context?.let { ctx ->
            val editText = EditText(ctx)
            editText.id = viewId
            editText.hint = getString(R.string.save_hint)

            // setPositive looks strange, but that is only because Kotlin asks that you place
            // lambdas outside of parenthesis'
            AlertDialog.Builder(ctx)
                    .setView(editText)
                    .setNegativeButton(R.string.action_cancel,null)
                    .setPositiveButton(R.string.action_save) { _, _ -> onSave(editText)}
                    .create()
        }
        // !! assures me that null cannot be returned, but a NPE can occur
        // see: https://kotlinlang.org/docs/reference/null-safety.html
        return saveDialog!!
    }

    private fun onSave(et: EditText){
        Log.d("Zaske","OnSave invoked")
        val text = et.text.toString()
        if(text.isNotEmpty()){
            Log.d("Zaske","text not empty")
            saveTipCallBack?.onSaveTip(text)
        }
    }

    /**
     * an object that is common to all instances of that class. similar to static fields in Java.
     * I'm using a companion object because the ID needs to be static, so that during phone
     * rotations Android will automatically save state for me.     *
     */
    companion object {
        val viewId = View.generateViewId()
    }
}