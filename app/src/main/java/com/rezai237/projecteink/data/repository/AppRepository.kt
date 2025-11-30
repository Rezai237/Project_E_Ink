package com.rezai237.projecteink.data.repository

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import com.rezai237.projecteink.domain.model.AppInfo
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AppRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    suspend fun getInstalledApps(): List<AppInfo> = withContext(Dispatchers.IO) {
        val pm = context.packageManager
        val intent = Intent(Intent.ACTION_MAIN, null).apply {
            addCategory(Intent.CATEGORY_LAUNCHER)
        }
        
        // Query all launcher activities
        val activities = pm.queryIntentActivities(intent, 0)
        
        activities.mapNotNull { resolveInfo ->
            val packageName = resolveInfo.activityInfo.packageName
            
            // Filter out our own app to avoid showing it in the list
            if (packageName == context.packageName) return@mapNotNull null
            
            val label = resolveInfo.loadLabel(pm).toString()
            val icon = resolveInfo.loadIcon(pm)
            
            AppInfo(
                label = label,
                packageName = packageName,
                icon = icon
            )
        }.sortedBy { it.label.lowercase() }
    }
}
