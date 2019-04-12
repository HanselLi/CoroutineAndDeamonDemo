package com.hansel.coroutine

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.android.synthetic.main.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "liyang"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getName().let {
            Log.d(TAG, " it : $it")
        }
        getName().run {
            Log.d(TAG, " === $this")
        }
        getName().apply {
            var num = when (this) {
                "hansel" -> 999
                else -> -1
            }
            Log.d(TAG, "num is $num")
        }

        getName().also {
            Log.d(TAG, " also : $it")
        }

        with(User()) {
            name
            age
            Log.d(TAG, "$name and $age")
        }
        var result = getName().takeUnless {
            it.equals("ha")
        }

        repeat(3) {
            Log.d(TAG, " it $it")
        }

        Log.d(TAG, " result : $result")
        val intent = Intent("com.hansel.action")
//        intent.setClass(this, Main2Activity::class.java)
        btn.setOnClickListener {
            startActivityForResult(intent, 100)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(TAG, " result : ${data?.toURI()}  resultCode : $resultCode")
    }


    override fun getIntent(): Intent {
        return super.getIntent()
    }


    fun getName(): String {
        return "hansel"
    }

}

class User {
    var name: String = "lilei";
    val age: Int = 11

}