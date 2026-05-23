package com.example.gcamfinder

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable data object Main : NavKey

@Serializable data class ModeSelection(val deviceId: String) : NavKey

@Serializable data class RecommendationHub(val deviceId: String, val isVideo: Boolean, val variant: String = "default") : NavKey
