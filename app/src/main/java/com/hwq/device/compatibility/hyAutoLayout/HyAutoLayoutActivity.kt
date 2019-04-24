package com.hwq.device.compatibility.hyAutoLayout

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import com.hwq.device.compatibility.R
import com.zhy.autolayout.AutoLayoutActivity

class HyAutoLayoutActivity : AutoLayoutActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hy_auto_layout)

        findViewById<TextView>(R.id.later_pay).setOnClickListener {
            val intent = Intent(this, NormalLayoutActivity::class.java)
            startActivity(intent)
        }
    }
}
