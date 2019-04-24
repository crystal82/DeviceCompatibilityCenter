package com.hwq.device.compatibility

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.util.Log

object ScreenInfoUtil {

    private const val TAG: String = "ScreenInfoUtil"

    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     * @param context
     * @param pxValue
     * @return
     */
    fun px2dip(context: Context, pxValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    fun sp2px(context: Context, spValue: Float): Int {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (spValue * fontScale + 0.5f).toInt()
    }

    fun px2sp(context: Context, pxValue: Float): Int {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (pxValue / fontScale + 0.5f).toInt()
    }

    fun dip2px(context: Context, dipValue: Int): Int {
        val scale = context.resources.displayMetrics.density
        return (dipValue * scale + 0.5f).toInt()
    }

    /**
     * 获取x、y的dpi,得到的数据，基本上是一致的
     */
    fun getXydpi(context: Context): String {
        val xdpi = context.resources.displayMetrics.xdpi
        val ydpi = context.resources.displayMetrics.ydpi

        return "xdpi:$xdpi ydpi:$ydpi"
    }

    /**
     * 匹配得到手机的dpi
     */
    fun getDpi(xdpi: Float): String {
        var dpi = ""
        if (xdpi >= 0 && xdpi < 120) {
            dpi = "ldpi"
        } else if (xdpi >= 120 && xdpi < 160) {
            dpi = "mdpi"
        } else if (xdpi >= 160 && xdpi < 240) {
            dpi = "hdpi"
        } else if (xdpi >= 240 && xdpi < 320) {
            dpi = "xhdpi"
        } else if (xdpi >= 320 && xdpi < 480) {
            dpi = "xxhdpi"
        } else if (xdpi >= 480 && xdpi < 640) {
            dpi = "xxxhdpi"
        }

        return dpi
    }

    /**
     * 获取屏幕的基本信息
     */
    fun getBaseScreenInfo(activity: Activity): String {
        val metric = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(metric)
        // 屏幕宽度（像素）
        val width = metric.widthPixels
        // 屏幕高度（像素）
        val height = metric.heightPixels
        // 屏幕密度（0.75 / 1.0 / 1.5）
        val density = metric.density
        // 屏幕密度DPI（120 / 160 / 240）
        val densityDpi = metric.densityDpi

        val info = ("width:" + width
                + "  height:" + height
                + "  density:" + density
                + "  densityDpi:" + densityDpi)
        Log.d("FitScreen", info)

        return info
    }


    /**
     * 获取当前手机屏幕的尺寸(单位:像素)
     */
    fun getPingMuSize(mContext: Context): Double {
        val densityDpi = mContext.resources.displayMetrics.densityDpi
        val scaledDensity = mContext.resources.displayMetrics.scaledDensity
        val density = mContext.resources.displayMetrics.density
        val xdpi = mContext.resources.displayMetrics.xdpi
        val ydpi = mContext.resources.displayMetrics.ydpi
        val width = mContext.resources.displayMetrics.widthPixels
        val height = mContext.resources.displayMetrics.heightPixels

        // 这样可以计算屏幕的物理尺寸
        val width2 = (width / xdpi) * (width / xdpi)
        val height2 = (height / ydpi) * (width / xdpi)

        return Math.sqrt((width2 + height2).toDouble())
    }

    fun getFontScale(context: Context): Float {
        val configuration = context.resources.configuration
        Log.d(TAG, "FontScale:" + configuration.fontScale)

        return configuration.fontScale
    }
}
