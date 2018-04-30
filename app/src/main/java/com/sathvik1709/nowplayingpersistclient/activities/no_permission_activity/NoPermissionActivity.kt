package com.sathvik1709.nowplayingpersistclient.activities.no_permission_activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.sathvik1709.nowplayingpersistclient.R
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
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                startActivityForResult(Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS),343)
            }
        }

        SkipBtn.setOnClickListener {
            finish()
        }
    }
}
