package com.sathvik1709.nowplayingpersistclient.activities.no_permission_activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.sathvik1709.nowplayingpersistclient.R
import com.sathvik1709.nowplayingpersistclient.util.getNotificationsAccessSettings
import kotlinx.android.synthetic.main.activity_no_permission.*

class NoPermissionActivity : AppCompatActivity() {

    lateinit var notificationsAccessBtn : Button
    lateinit var SkipBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_no_permission)

        notificationsAccessBtn = notifications_access_settings_btn
        SkipBtn = skipBtn

        notificationsAccessBtn.setOnClickListener {
            startActivityForResult(Intent().getNotificationsAccessSettings(), 321)
        }

        SkipBtn.setOnClickListener {
            finish()
        }
    }
}