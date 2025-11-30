package com.rezai237.projecteink.ui.utils

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter

object IconUtils {
    fun getGrayscaleFilter(): ColorMatrixColorFilter {
        val matrix = ColorMatrix()
        matrix.setSaturation(0f) // Remove color
        
        // High contrast matrix
        // We want to make it look like E-Ink: mostly black and white
        val contrast = 1.2f
        val scale = contrast
        val translate = (-.5f * scale + .5f) * 255f
        val contrastMatrix = ColorMatrix(floatArrayOf(
            scale, 0f, 0f, 0f, translate,
            0f, scale, 0f, 0f, translate,
            0f, 0f, scale, 0f, translate,
            0f, 0f, 0f, 1f, 0f
        ))
        
        matrix.postConcat(contrastMatrix)
        
        return ColorMatrixColorFilter(matrix)
    }
}
