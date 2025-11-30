package com.rezai237.projecteink.data.manager

import android.content.ContentResolver
import android.content.Context
import android.provider.Settings
import com.rezai237.projecteink.domain.manager.SystemSettingsManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SystemSettingsManagerImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : SystemSettingsManager {

    private val contentResolver: ContentResolver = context.contentResolver

    override fun canWriteSecureSettings(): Boolean {
        return context.checkCallingOrSelfPermission(android.Manifest.permission.WRITE_SECURE_SETTINGS) == 
                android.content.pm.PackageManager.PERMISSION_GRANTED
    }

    override fun setMonochromeMode(enabled: Boolean): Boolean {
        if (!canWriteSecureSettings()) return false

        return try {
            if (enabled) {
                // Enable Daltonizer and set mode to Monochromacy (0)
                Settings.Secure.putInt(contentResolver, "accessibility_display_daltonizer_enabled", 1)
                Settings.Secure.putInt(contentResolver, "accessibility_display_daltonizer", 0)
            } else {
                // Disable Daltonizer
                Settings.Secure.putInt(contentResolver, "accessibility_display_daltonizer_enabled", 0)
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    override fun isMonochromeEnabled(): Boolean {
        return try {
            val enabled = Settings.Secure.getInt(contentResolver, "accessibility_display_daltonizer_enabled", 0) == 1
            val mode = Settings.Secure.getInt(contentResolver, "accessibility_display_daltonizer", -1)
            enabled && mode == 0
        } catch (e: Exception) {
            false
        }
    }
}
