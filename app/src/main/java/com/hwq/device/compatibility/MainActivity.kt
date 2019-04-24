package com.hwq.device.compatibility

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.widget.TextView
import java.lang.StringBuilder
import android.util.DisplayMetrics


class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
    }

    private lateinit var screenInfo: TextView
    private lateinit var metricsInfo: TextView
    private lateinit var textSize1: TextView
    private lateinit var textSize2: TextView
    private lateinit var textSize3: TextView
    private lateinit var textSize4: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        screenInfo = findViewById(R.id.tv_screen_info)
        metricsInfo = findViewById(R.id.tv_metrics_info)

        textSize1 = findViewById(R.id.text_size1)
        textSize2 = findViewById(R.id.text_size2)
        textSize3 = findViewById(R.id.text_size3)
        textSize4 = findViewById(R.id.text_size4)

        setScreenInfo()
        getMetrics()
        setTextSize()
    }

    /**
     * 设置屏幕信息
     */
    private fun setScreenInfo() {
        val builder = StringBuilder()
        builder.append("屏幕信息").append("\n")
        builder.append(ScreenInfoUtil.getXydpi(this)).append("\n")
        builder.append(ScreenInfoUtil.getDpi(resources.displayMetrics.xdpi)).append("\n")
        builder.append(ScreenInfoUtil.getBaseScreenInfo(this)).append("\n")
        builder.append("字体大小:" + ScreenInfoUtil.getFontScale(this)).append("\n")
        screenInfo.text = builder.toString()
    }

    /**
     * 获取当前Metrics的数值
     */
    private fun getMetrics() {
        val mMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(mMetrics)
        metricsInfo.text = "Metrics:$mMetrics"

        Log.d(TAG, "getMetrics:$mMetrics")
    }

    private fun setTextSize() {
        //转化单位是用sp，最终 px = sp *density
        textSize1.textSize = 18f
        //输出: getDimension = font1 * density 且转化单位使用sp
        textSize2.textSize = resources.getDimension(R.dimen.font1)
        //输出：getDimension = font1 * density 转化单位为px
        textSize3.setTextSize(TypedValue.COMPLEX_UNIT_PX, resources.getDimension(R.dimen.font1))
        //默认单位为sp，最终 px = sp *density
        textSize4.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)

        Log.d(TAG, "size:" + resources.getDimension(R.dimen.font1))
    }
}
