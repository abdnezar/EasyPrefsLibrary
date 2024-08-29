package com.abdnezar.easyprefs


//  Interface for saving or getting data from Preferences.
internal interface EasyPrefs {
    fun store(key: String, toStore: Any)

    // Getting data from Preferences using key as id.
    fun <T> get(key: String, defaultObj: T): T

    //  This deletes all the data previously saved in the Preferences.
    fun reset()
}
