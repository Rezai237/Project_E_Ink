package com.rezai237.projecteink.ui.home

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rezai237.projecteink.data.repository.AppRepository
import com.rezai237.projecteink.domain.manager.SystemSettingsManager
import com.rezai237.projecteink.domain.model.AppInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.ceil

@HiltViewModel
class AppViewModel @Inject constructor(
    private val repository: AppRepository,
    private val systemSettingsManager: SystemSettingsManager
) : ViewModel() {

    private val _appPages = MutableStateFlow<List<List<AppInfo>>>(emptyList())
    val appPages: StateFlow<List<List<AppInfo>>> = _appPages.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _isMonochromeEnabled = MutableStateFlow(false)
    val isMonochromeEnabled: StateFlow<Boolean> = _isMonochromeEnabled.asStateFlow()

    private val _showPermissionDialog = MutableStateFlow(false)
    val showPermissionDialog: StateFlow<Boolean> = _showPermissionDialog.asStateFlow()

    // Grid configuration
    private val appsPerPage = 20 // 4 columns * 5 rows

    init {
        loadApps()
        checkMonochromeStatus()
    }

    private fun checkMonochromeStatus() {
        _isMonochromeEnabled.value = systemSettingsManager.isMonochromeEnabled()
    }

    fun toggleMonochrome() {
        if (systemSettingsManager.canWriteSecureSettings()) {
            val newState = !_isMonochromeEnabled.value
            val success = systemSettingsManager.setMonochromeMode(newState)
            if (success) {
                _isMonochromeEnabled.value = newState
            }
        } else {
            _showPermissionDialog.value = true
        }
    }

    fun dismissPermissionDialog() {
        _showPermissionDialog.value = false
    }

    fun loadApps() {
        viewModelScope.launch {
            _isLoading.value = true
            val allApps = repository.getInstalledApps()
            _appPages.value = allApps.chunked(appsPerPage)
            _isLoading.value = false
        }
    }

    fun launchApp(context: Context, packageName: String) {
        val intent = context.packageManager.getLaunchIntentForPackage(packageName)
        intent?.let {
            context.startActivity(it)
        }
    }
}
