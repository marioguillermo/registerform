package com.example.registerform.ui.kotlin

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.registerform.R
import com.example.registerform.domain.User

class SuccessActivityKt : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_success)
        intent.getParcelableExtra<User>(MainActivityKt.USER_EXTRA)?.let { user ->
            bindUserToView(user)
        }
    }

    private fun bindUserToView(user: User){
        val textView = findViewById<TextView>(R.id.tvName)
        textView.text = user.name
    }
}