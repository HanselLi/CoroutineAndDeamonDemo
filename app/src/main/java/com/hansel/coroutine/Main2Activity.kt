package com.hansel.coroutine

import android.app.Activity
import android.content.Intent
import android.os.Bundle

class Main2Activity : Activity() {
//    var  intent:Intent = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent.putExtra("name","wangsicong")
    }

    override fun onBackPressed() {

//        super.onBackPressed()
        setResult(RESULT_OK,intent)
        finish()
    }

}