package com.loc8r.tipz.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.Observable
import android.databinding.PropertyChangeRegistry

abstract class ObservableViewModel(app: Application): AndroidViewModel(app), Observable {

    // This was created to replicate the work done in BaseObservable.  This makes mCallbacks the
    // class responsible for managing Observable callbacks for our viewmodel.
    // By lazy, a Kotlin ability returns the lambda function the first time it's requested
    // and then
    @delegate:Transient
    private val mCallbacks: PropertyChangeRegistry by lazy {
        PropertyChangeRegistry()
    }

    /**
     * Adds a callback to listen for changes to the Observable.
     * @param callback The callback to start listening.
     */
    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        mCallbacks.add(callback)
    }

    /**
     * Removes a callback from those listening for changes.
     * @param callback The callback that should stop listening.
     */
    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        mCallbacks.remove(callback)
    }

    // This is our custom function to replace the BaseObservable one.  BR._all comes from
    // a generated class created by the databinding library.  _all is the proper static 0 reference
    fun notifyChange(){
        mCallbacks.notifyChange(this,com.loc8r.tipz.BR._all)
    }
}