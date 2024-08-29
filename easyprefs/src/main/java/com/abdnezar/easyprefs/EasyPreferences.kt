package com.abdnezar.easyprefs

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

class EasyPreferences @JvmOverloads constructor(
    context: Context,
    name: String?,
    type: Int = Context.MODE_PRIVATE
) : EasyPrefs {
    private var mSp: SharedPreferences? = null
    private val mGson = Gson()

    init {
        mSp = context.applicationContext.getSharedPreferences(name, type)
    }

    override fun store(key: String, toStore: Any) {
        val editor = mSp!!.edit()
        storeObject(editor, key, toStore)
        editor.apply()
    }

    override fun <T> get(key: String, defaultObj: T): T {
        val result: Any?
        when (defaultObj) {
            is String -> {
                result = mSp!!.getString(key, defaultObj as String)
            }

            is Boolean -> {
                result = mSp!!.getBoolean(key, (defaultObj as Boolean))
            }

            is Float -> {
                result = mSp!!.getFloat(key, (defaultObj as Float))
            }

            is Long -> {
                result = mSp!!.getLong(key, (defaultObj as Long))
            }

            is Int -> {
                result = mSp!!.getInt(key, (defaultObj as Int))
            }

            else -> {
                val `object` = deserializeFromSp(key, defaultObj as Class<T>)
                result = (`object` ?: defaultObj)
            }
        }
        // the API for Sets is not used due to compatibility reasons
        // and to provide a nicer API. In fact, to use it, two separate method
        // would be necessary.
        return result as T
    }

    private fun storeObject(editor: SharedPreferences.Editor, key: String, toStore: Any) {
        if (toStore is String) {
            editor.putString(key, toStore)
        } else if (toStore is Boolean) {
            editor.putBoolean(key, toStore)
        } else if (toStore is Float) {
            editor.putFloat(key, toStore)
        } else if (toStore is Long) {
            editor.putLong(key, toStore)
        } else if (toStore is Int) {
            editor.putInt(key, toStore)
        } else {
            val json = serializeObject(editor, key, toStore)
            editor.putString(key, json)
        }
    }

    override fun reset() {
        mSp!!.edit().clear().apply()
    }

    private fun serializeObject(
        editor: SharedPreferences.Editor,
        key: String,
        toStore: Any
    ): String {
        return mGson.toJson(toStore)
    }

    private fun <T> deserializeFromSp(key: String, defaultObj: Class<T>): T? {
        val json = mSp!!.getString(key, null) ?: return null
        return mGson.fromJson(json, defaultObj::class.java) as T
    }
}
