package com.example.gcamfinder.ui.utils

import android.content.Context
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager
import android.os.Build

data class DiagnosticResult(
    val hardwareLevel: String,
    val isLevel3OrFull: Boolean,
    val rawSupported: Boolean,
    val statusMessage: String
)

object CameraDiagnostics {

    fun checkCameraCapabilities(context: Context): DiagnosticResult {
        return try {
            val cameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
            val cameraIds = cameraManager.cameraIdList

            if (cameraIds.isEmpty()) {
                return DiagnosticResult(
                    hardwareLevel = "Unbekannt",
                    isLevel3OrFull = false,
                    rawSupported = false,
                    statusMessage = "Keine Kamerasensoren im System gefunden."
                )
            }

            // Find first back camera to diagnose
            var backCameraId: String? = null
            for (id in cameraIds) {
                val chars = cameraManager.getCameraCharacteristics(id)
                val facing = chars.get(CameraCharacteristics.LENS_FACING)
                if (facing == CameraCharacteristics.LENS_FACING_BACK) {
                    backCameraId = id
                    break
                }
            }

            val targetId = backCameraId ?: cameraIds[0]
            val characteristics = cameraManager.getCameraCharacteristics(targetId)

            // 1. Hardware Level check
            val hwLevel = characteristics.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL)
            val (levelStr, isHighLevel) = when (hwLevel) {
                CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL_3 -> "LEVEL_3 (Exzellent)" to true
                CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL_FULL -> "FULL (Optimal)" to true
                CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL_LIMITED -> "LIMITED (Eingeschränkt)" to false
                CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL_LEGACY -> "LEGACY (Veraltet)" to false
                else -> "UNBEKANNT" to false
            }

            // 2. RAW support check
            val capabilities = characteristics.get(CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES)
            val supportsRaw = capabilities?.contains(
                CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES_RAW
            ) ?: false

            val statusMsg = if (isHighLevel && supportsRaw) {
                "Dein Gerät ist zu 100% kompatibel für GCam mit allen Linsen und XML-Optimierungen!"
            } else if (supportsRaw) {
                "Eingeschränkte Unterstützung: GCam läuft, aber eventuell nicht mit allen Custom-Konfigurationen."
            } else {
                "Warnung: RAW-Aufnahme wird nicht unterstützt. GCam-Bilder weisen eventuell Artefakte auf."
            }

            DiagnosticResult(
                hardwareLevel = levelStr,
                isLevel3OrFull = isHighLevel,
                rawSupported = supportsRaw,
                statusMessage = statusMsg
            )
        } catch (e: Exception) {
            DiagnosticResult(
                hardwareLevel = "Fehler",
                isLevel3OrFull = false,
                rawSupported = false,
                statusMessage = "Diagnose fehlgeschlagen: ${e.localizedMessage ?: "Unbekannter Fehler"}"
            )
        }
    }
}
