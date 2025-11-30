package com.rezai237.projecteink.domain.manager

interface SystemSettingsManager {
    fun canWriteSecureSettings(): Boolean
    fun setMonochromeMode(enabled: Boolean): Boolean
    fun isMonochromeEnabled(): Boolean
}
