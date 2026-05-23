package com.example.gcamfinder

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.gcamfinder.data.DefaultDataRepository
import com.example.gcamfinder.ui.screens.DeviceSelectionScreen
import com.example.gcamfinder.ui.screens.ModeSelectionScreen
import com.example.gcamfinder.ui.screens.RecommendationHubScreen

@Composable
fun MainNavigation() {
    val repository = remember { DefaultDataRepository() }
    val backStack = rememberNavBackStack(Main)
    
    // Globally collect devices to resolve matching objects quickly
    val devices by repository.devices.collectAsState(initial = emptyList())

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            // Screen 1: Device Selection List
            entry<Main> {
                DeviceSelectionScreen(
                    devices = devices,
                    onDeviceSelected = { id ->
                        backStack.add(ModeSelection(id))
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }

            // Screen 2: Mode Selection (Foto vs Film)
            entry<ModeSelection> { key ->
                val device = remember(key.deviceId, devices) {
                    devices.firstOrNull { it.id == key.deviceId }
                }
                if (device != null) {
                    ModeSelectionScreen(
                        device = device,
                        onModeSelected = { isVideo, variant ->
                            backStack.add(RecommendationHub(device.id, isVideo, variant))
                        },
                        onBackClick = {
                            backStack.removeLastOrNull()
                        },
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            // Screen 3: Matched GCam version/XML recommendations and installation guide
            entry<RecommendationHub> { key ->
                val device = remember(key.deviceId, devices) {
                    devices.firstOrNull { it.id == key.deviceId }
                }
                val recommendation by repository.getRecommendation(key.deviceId, key.isVideo)
                    .collectAsState(initial = null)
                
                val developer = recommendation?.gcamDeveloper ?: "BigKaka"
                val guideSteps by repository.getInstallationGuide(developer)
                    .collectAsState(initial = emptyList())

                if (device != null) {
                    RecommendationHubScreen(
                        device = device,
                        isVideo = key.isVideo,
                        variant = key.variant,
                        recommendation = recommendation,
                        guideSteps = guideSteps,
                        onBackClick = {
                            backStack.removeLastOrNull()
                        },
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    )
}
