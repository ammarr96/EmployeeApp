package com.amar.employeestestapp.util

import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation

class Util {

    companion object {
        fun setScaleAnimation(view: View) {
            val anim = ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
            anim.duration = 300
            view.startAnimation(anim)
        }
    }

}