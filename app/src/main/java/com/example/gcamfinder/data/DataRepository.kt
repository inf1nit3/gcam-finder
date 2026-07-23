package com.example.gcamfinder.data

import com.example.gcamfinder.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

// --- DATA MODELS ---

data class CameraSensor(
    val role: String,
    val sensorModel: String,
    val resolution: String,
    val sensorSize: String,
    val lensSpecs: String
)

data class Device(
    val id: String,
    val name: String,
    val imageResId: Int,
    val displaySpecs: String,
    val cameraSpecs: String,
    val chipset: String,
    val sensors: List<CameraSensor>
)

data class GcamRecommendation(
    val deviceId: String,
    val isVideo: Boolean,
    val gcamName: String,
    val gcamVersion: String,
    val gcamDeveloper: String,
    val gcamDownloadUrl: String,
    val xmlName: String,
    val xmlDownloadUrl: String,
    val xmlDescription: String,
    val recommendedSettings: List<String>
)

data class GuideStep(
    val stepNumber: Int,
    val title: String,
    val description: String
)

// --- REPOSITORY INTERFACE ---

interface DataRepository {
    val devices: Flow<List<Device>>
    fun getRecommendation(deviceId: String, isVideo: Boolean): Flow<GcamRecommendation?>
    fun getInstallationGuide(gcamDeveloper: String): Flow<List<GuideStep>>
}

// --- IMPLEMENTATION ---

class DefaultDataRepository : DataRepository {

    private val devicesList = listOf(
        Device(
            id = "vivo_x300_pro",
            name = "VIVO X300 Pro",
            imageResId = R.drawable.vivo_x300_pro,
            displaySpecs = "6,78\" Flaches LTPO OLED, 4500 Nits, 120 Hz",
            cameraSpecs = "50 MP Sony LYT-828 + 200 MP Zeiss Periskop",
            chipset = "MediaTek Dimensity 9500 (3 nm)",
            sensors = listOf(
                CameraSensor("Hauptkamera (Weitwinkel)", "Sony LYT-828", "50 MP", "1/1.28-Zoll", "f/1.57, Gimbal OIS, Zeiss T* Coated"),
                CameraSensor("Periskop-Telekamera (3.5x)", "Samsung ISOCELL HPB", "200 MP", "1/1.4-Zoll", "f/2.67, 85mm Eq., OIS, Zeiss APO"),
                CameraSensor("Ultraweitwinkel-Kamera", "Samsung ISOCELL JN1", "50 MP", "1/2.76-Zoll", "f/2.0, 15mm Eq., Autofokus/Makro")
            )
        ),
        Device(
            id = "xiaomi_14_ultra",
            name = "XIAOMI 14 ULTRA",
            imageResId = R.drawable.xiaomi_14_ultra,
            displaySpecs = "6,73\" QHD+ LTPO AMOLED, 3000 Nits, 120 Hz",
            cameraSpecs = "50 MP Leica 1\" LYT-900 + Quad-System",
            chipset = "Snapdragon 8 Gen 3 (4 nm)",
            sensors = listOf(
                CameraSensor("Hauptkamera (Weitwinkel)", "Sony LYT-900", "50 MP", "1-Zoll-Typ", "f/1.63 - f/4.0 stufenlose Blende, 23mm Eq., OIS, ALD"),
                CameraSensor("Telekamera (3.2x)", "Sony IMX858", "50 MP", "1/2.51-Zoll", "f/1.8, 75mm Eq., Floating-Linse, OIS"),
                CameraSensor("Periskop-Telekamera (5x)", "Sony IMX858", "50 MP", "1/2.51-Zoll", "f/2.5, 120mm Eq., OIS, Makro"),
                CameraSensor("Ultraweitwinkel-Kamera", "Sony IMX858", "50 MP", "1/2.51-Zoll", "f/1.8, 12mm Eq., 122° FOV, Autofokus/Makro")
            )
        ),
        Device(
            id = "xiaomi_15_ultra",
            name = "XIAOMI 15 ULTRA",
            imageResId = R.drawable.xiaomi_15_ultra,
            displaySpecs = "6,73\" QHD+ LTPO AMOLED, 3200 Nits, 120 Hz",
            cameraSpecs = "50 MP Leica 1\" LYT-900 + 200 MP Periskop",
            chipset = "Snapdragon 8 Elite (3 nm)",
            sensors = listOf(
                CameraSensor("Hauptkamera (Weitwinkel)", "Sony LYT-900", "50 MP", "1-Zoll-Typ", "f/1.63, 23mm Eq., OIS, ALD"),
                CameraSensor("Periskop-Telekamera (4.3x)", "Samsung ISOCELL HP9", "200 MP", "1/1.4-Zoll", "f/2.6, 100mm Eq., OIS"),
                CameraSensor("Telekamera (3x)", "Sony IMX858", "50 MP", "1/2.51-Zoll", "f/1.8, 70mm Eq., OIS"),
                CameraSensor("Ultraweitwinkel-Kamera", "Samsung ISOCELL JN1", "50 MP", "1/2.75-Zoll", "f/2.2, 14mm Eq., 115° FOV")
            )
        ),
        Device(
            id = "xiaomi_17_ultra",
            name = "XIAOMI 17 ULTRA",
            imageResId = R.drawable.xiaomi_17_ultra,
            displaySpecs = "6,9\" HyperRGB LTPO OLED, 3500 Nits, 120 Hz",
            cameraSpecs = "50 MP Leica 1\" + 200 MP 75–100 mm Zoom",
            chipset = "Snapdragon 8 Elite Gen 5 (3 nm)",
            sensors = listOf(
                CameraSensor("Hauptkamera (Weitwinkel)", "Light Fusion 1050L", "50 MP", "1-Zoll-Typ", "f/1.67, 23 mm Eq., LOFIC HDR, OIS"),
                CameraSensor("Periskop-Telekamera (optischer Zoom)", "Samsung ISOCELL HPE", "200 MP", "1/1.4-Zoll", "f/2.39–f/2.96, 75–100 mm Eq. mechanischer Zoom, OIS"),
                CameraSensor("Ultraweitwinkel-Kamera", "Samsung ISOCELL JN5", "50 MP", "1/2.75-Zoll", "f/2.2, 14 mm Eq., 115° FOV")
            )
        ),
        Device(
            id = "samsung_s26_ultra",
            name = "SAMSUNG S26 ULTRA",
            imageResId = R.drawable.samsung_s26_ultra,
            displaySpecs = "6,9\" QHD+ Dynamic AMOLED 2X, 2600 Nits, 120 Hz",
            cameraSpecs = "200 MP Hauptkamera + 50 MP Ultraweit + 50 MP 5x + 10 MP 3x",
            chipset = "Snapdragon 8 Elite Gen 5 for Galaxy",
            sensors = listOf(
                CameraSensor("Hauptkamera (Weitwinkel)", "200-MP-Wide-Sensor", "200 MP", "nicht offiziell genannt", "f/1.4, OIS, 2x Zoom in optischer Qualität"),
                CameraSensor("Periskop-Telekamera (5x)", "50-MP-Tele-Sensor", "50 MP", "nicht offiziell genannt", "f/2.9, 5x optischer Zoom, OIS"),
                CameraSensor("Telekamera (3x)", "10-MP-Tele-Sensor", "10 MP", "nicht offiziell genannt", "f/2.4, 3x optischer Zoom, OIS"),
                CameraSensor("Ultraweitwinkel-Kamera", "50-MP-Ultraweit-Sensor", "50 MP", "nicht offiziell genannt", "f/1.9, Autofokus")
            )
        )
    )

    private fun egoistProfile(deviceId: String): EgoistProfile =
        requireNotNull(EgoistProfiles.forDevice(deviceId)) {
            "Kein EGOIST-Profil für $deviceId konfiguriert."
        }

    private val recommendations = listOf(
        // VIVO X300 Pro - Photo
        GcamRecommendation(
            deviceId = "vivo_x300_pro",
            isVideo = false,
            gcamName = "AGC GCam",
            gcamVersion = "8.4.300.V9.6",
            gcamDeveloper = "BigKaka",
            gcamDownloadUrl = egoistProfile("vivo_x300_pro").apk.directDownloadUrl,
            xmlName = egoistProfile("vivo_x300_pro").config.fileName,
            xmlDownloadUrl = egoistProfile("vivo_x300_pro").config.directDownloadUrl,
            xmlDescription = "Mitgeliefertes EGOIST-Profil für die Kamera-IDs des vivo X300 Pro mit LYT-828-Hauptkamera, HPB-Periskop und Custom-Library.",
            recommendedSettings = listOf(
                "Verwende 'HDR+ Erweitert' für Landschaftsaufnahmen",
                "Aktiviere den 'Zeiss-Modus' im oberen Schnellmenü für natürliche Hauttöne",
                "Setze den Tele-Zoom Multi-Frame-Wert auf 15 Bilder für maximale Detailtreue"
            )
        ),
        // VIVO X300 Pro - Video
        GcamRecommendation(
            deviceId = "vivo_x300_pro",
            isVideo = true,
            gcamName = "Motion Cam",
            gcamVersion = "Play Store · 5-Sekunden-Testversion",
            gcamDeveloper = "MotionCam",
            gcamDownloadUrl = "https://play.google.com/store/apps/details?id=com.motioncam",
            xmlName = "Kein externes Profil erforderlich",
            xmlDownloadUrl = "https://play.google.com/store/apps/details?id=com.motioncam",
            xmlDescription = "MotionCam nutzt die vom vivo-ROM über Camera2 freigegebenen Sensorströme. Verfügbare RAW-Auflösungen und Bildraten müssen direkt auf dem Gerät geprüft werden.",
            recommendedSettings = listOf(
                "Führe nach der Installation zuerst die Objektiv-Erkennung aus",
                "Nutze schnellen internen Speicher für stabile RAW-Aufnahmeraten",
                "Teste Auflösung und Bildrate vor längeren Aufnahmen auf Stabilität"
            )
        ),
        // XIAOMI 14 ULTRA - Photo
        GcamRecommendation(
            deviceId = "xiaomi_14_ultra",
            isVideo = false,
            gcamName = "AGC GCam",
            gcamVersion = "8.4.300.V9.6",
            gcamDeveloper = "BigKaka",
            gcamDownloadUrl = egoistProfile("xiaomi_14_ultra").apk.directDownloadUrl,
            xmlName = egoistProfile("xiaomi_14_ultra").config.fileName,
            xmlDownloadUrl = egoistProfile("xiaomi_14_ultra").config.directDownloadUrl,
            xmlDescription = "Mitgeliefertes EGOIST-Profil für das Xiaomi 14 Ultra mit LYT-900 und drei IMX858-Zusatzkameras.",
            recommendedSettings = listOf(
                "Verwende das 'Leica Vibrant'-Farbprofil für Street-Fotografie",
                "Aktiviere den variablen Blenden-Modus (schaltet automatisch zwischen f/1.63 und f/4.0 je nach Licht)",
                "Setze das Rauschmodell auf 'Benutzerdefiniertes LYT-900 Rauschmodell' für absolut rauschfreie Nachtaufnahmen"
            )
        ),
        // XIAOMI 14 ULTRA - Video
        GcamRecommendation(
            deviceId = "xiaomi_14_ultra",
            isVideo = true,
            gcamName = "Motion Cam",
            gcamVersion = "Play Store · 5-Sekunden-Testversion",
            gcamDeveloper = "MotionCam",
            gcamDownloadUrl = "https://play.google.com/store/apps/details?id=com.motioncam",
            xmlName = "Kein externes Profil erforderlich",
            xmlDownloadUrl = "https://play.google.com/store/apps/details?id=com.motioncam",
            xmlDescription = "MotionCam nutzt die vom Xiaomi-ROM über Camera2 freigegebenen Sensorströme. RAW-Bittiefe, Objektivzugriff und Bildrate hängen von der installierten Firmware ab.",
            recommendedSettings = listOf(
                "Führe nach der Installation zuerst die Objektiv-Erkennung aus",
                "Wähle eine von der aktuellen Firmware stabil unterstützte Bildrate",
                "Nutze Falschfarben und Fokus-Peaking für die manuelle Belichtung"
            )
        ),
        // XIAOMI 15 ULTRA - Photo
        GcamRecommendation(
            deviceId = "xiaomi_15_ultra",
            isVideo = false,
            gcamName = "AGC GCam",
            gcamVersion = "8.4.300.V9.6",
            gcamDeveloper = "BigKaka",
            gcamDownloadUrl = egoistProfile("xiaomi_15_ultra").apk.directDownloadUrl,
            xmlName = egoistProfile("xiaomi_15_ultra").config.fileName,
            xmlDownloadUrl = egoistProfile("xiaomi_15_ultra").config.directDownloadUrl,
            xmlDescription = "Mitgeliefertes EGOIST-Profil für LYT-900, HP9-Periskop, IMX858-Tele und JN1-Ultraweitwinkel des Xiaomi 15 Ultra.",
            recommendedSettings = listOf(
                "Nutze das 'Leica Authentic'-Profil für klassische Schwarz-Weiß-Porträts",
                "Nutze die festen Objektivprofile für Haupt-, Tele- und Periskopkamera",
                "Lade die mitgelieferte Custom-Library vor dem Import der AGC-Datei"
            )
        ),
        // XIAOMI 15 ULTRA - Video
        GcamRecommendation(
            deviceId = "xiaomi_15_ultra",
            isVideo = true,
            gcamName = "Motion Cam",
            gcamVersion = "Play Store · 5-Sekunden-Testversion",
            gcamDeveloper = "MotionCam",
            gcamDownloadUrl = "https://play.google.com/store/apps/details?id=com.motioncam",
            xmlName = "Kein externes Profil erforderlich",
            xmlDownloadUrl = "https://play.google.com/store/apps/details?id=com.motioncam",
            xmlDescription = "MotionCam nutzt die vom Xiaomi-ROM über Camera2 freigegebenen Sensorströme. Tatsächlich verfügbare RAW-Modi sind firmwareabhängig.",
            recommendedSettings = listOf(
                "Führe nach der Installation zuerst die Objektiv-Erkennung aus",
                "Beginne mit einer moderaten Auflösung und prüfe die nachhaltige Bildrate",
                "Aktiviere Fokus-Peaking für die manuelle Fokussierung"
            )
        ),
        // XIAOMI 17 ULTRA - Photo
        GcamRecommendation(
            deviceId = "xiaomi_17_ultra",
            isVideo = false,
            gcamName = "AGC GCam",
            gcamVersion = "8.4.300.V9.6",
            gcamDeveloper = "BigKaka",
            gcamDownloadUrl = egoistProfile("xiaomi_17_ultra").apk.directDownloadUrl,
            xmlName = egoistProfile("xiaomi_17_ultra").config.fileName,
            xmlDownloadUrl = egoistProfile("xiaomi_17_ultra").config.directDownloadUrl,
            xmlDescription = "Mitgeliefertes EGOIST-Profil für Light Fusion 1050L, HPE-Zoomkamera und JN5-Ultraweitwinkel des Xiaomi 17 Ultra.",
            recommendedSettings = listOf(
                "Lade die mitgelieferte Custom-Library vor dem Import der AGC-Datei",
                "Prüfe nach dem Import die Zuordnung aller hinterlegten Kamera-IDs",
                "Verwende HDR+ Erweitert für Szenen mit hohem Kontrast"
            )
        ),
        // XIAOMI 17 ULTRA - Video
        GcamRecommendation(
            deviceId = "xiaomi_17_ultra",
            isVideo = true,
            gcamName = "Motion Cam",
            gcamVersion = "Play Store · 5-Sekunden-Testversion",
            gcamDeveloper = "MotionCam",
            gcamDownloadUrl = "https://play.google.com/store/apps/details?id=com.motioncam",
            xmlName = "Kein externes Profil erforderlich",
            xmlDownloadUrl = "https://play.google.com/store/apps/details?id=com.motioncam",
            xmlDescription = "MotionCam nutzt die vom Xiaomi-ROM über Camera2 freigegebenen Sensorströme. Auflösung, Bittiefe und Bildrate müssen auf der jeweiligen Firmware verifiziert werden.",
            recommendedSettings = listOf(
                "Führe nach der Installation zuerst die Objektiv-Erkennung aus",
                "Nutze Rec.2020 beziehungsweise Direct Log nur bei bestätigter Geräteunterstützung",
                "Teste die nachhaltige Bildrate vor längeren RAW-Aufnahmen"
            )
        ),
        // SAMSUNG S26 ULTRA - Photo
        GcamRecommendation(
            deviceId = "samsung_s26_ultra",
            isVideo = false,
            gcamName = "AGC GCam",
            gcamVersion = "8.4.300.V9.6",
            gcamDeveloper = "BigKaka",
            gcamDownloadUrl = egoistProfile("samsung_s26_ultra").apk.directDownloadUrl,
            xmlName = egoistProfile("samsung_s26_ultra").config.fileName,
            xmlDownloadUrl = egoistProfile("samsung_s26_ultra").config.directDownloadUrl,
            xmlDescription = "Mitgeliefertes EGOIST-Testprofil für die offiziellen Kamera-Auflösungen und Kamera-IDs des Samsung Galaxy S26 Ultra.",
            recommendedSettings = listOf(
                "Use HDR+ Enhanced for scenery shots to recover details",
                "Select custom library shgv913.so in AGC Settings",
                "Double press next to shutter button to load AGC configuration"
            )
        ),
        // SAMSUNG S26 ULTRA - Video
        GcamRecommendation(
            deviceId = "samsung_s26_ultra",
            isVideo = true,
            gcamName = "Motion Cam",
            gcamVersion = "Play Store · 5-Sekunden-Testversion",
            gcamDeveloper = "MotionCam",
            gcamDownloadUrl = "https://play.google.com/store/apps/details?id=com.motioncam",
            xmlName = "Kein externes Profil erforderlich",
            xmlDownloadUrl = "https://play.google.com/store/apps/details?id=com.motioncam",
            xmlDescription = "MotionCam nutzt die vom Samsung-ROM über Camera2 freigegebenen Sensorströme. Objektivzugriff, RAW-Bittiefe und stabile Bildrate sind firmwareabhängig.",
            recommendedSettings = listOf(
                "Führe nach der Installation zuerst die Objektiv-Erkennung aus",
                "Use fast internal storage for a stable RAW stream",
                "Teste Auflösung und Bildrate vor längeren Aufnahmen"
            )
        )
    )

    private val generalGuideSteps = mapOf(
        "BigKaka" to listOf(
            GuideStep(1, "GCam herunterladen", "Lade die empfohlene AGC GCam APK über den Download-Link herunter und installiere sie auf deinem Gerät."),
            GuideStep(2, "AGC-Datei speichern", "Speichere die mitgelieferte .agc-Konfiguration zunächst im Download-Ordner deines Geräts."),
            GuideStep(3, "Custom-Library laden", "Öffne AGC 8.4, gehe zu Einstellungen → Libraries → Load custom library und wähle die zum Gerät gehörende .so-Datei."),
            GuideStep(4, "AGC-Konfiguration importieren", "Doppelklicke auf den schwarzen Bereich neben dem Auslöser, wähle die gespeicherte .agc-Datei und tippe auf Import beziehungsweise Restore."),
            GuideStep(5, "Fertig!", "Die GCam ist nun perfekt für dein Gerät kalibriert! Die Kamera startet automatisch neu und wendet alle Optimierungen an.")
        ),
        "Hasli" to listOf(
            GuideStep(1, "LMC herunterladen", "Lade die empfohlene LMC GCam APK herunter und installiere die App auf deinem Smartphone."),
            GuideStep(2, "LMC-Ordner anlegen", "Erstelle im Hauptverzeichnis deines internen Speichers einen neuen Ordner mit dem Namen 'LMC8.4'."),
            GuideStep(3, "XML abspeichern", "Lade die empfohlene XML-Datei herunter und kopiere/verschiebe diese direkt in den Ordner '/LMC8.4/'."),
            GuideStep(4, "XML laden", "Starte die LMC-App, erteile alle Berechtigungen und mache einen Doppelklick auf die freie schwarze Fläche neben dem runden Shutter-Button. Wähle die XML im Dropdown und klicke auf 'Import'."),
            GuideStep(5, "Fertig!", "Alle Linsen, Videoprofile und Rauschmodelle wurden erfolgreich geladen und eingerichtet. Viel Spaß beim Filmen!")
        ),
        "MotionCam" to listOf(
            GuideStep(1, "Motion Cam installieren", "Klicke im Downloads-Tab auf 'Motion Cam im Play Store öffnen' und installiere die kostenlose App direkt auf deinem Gerät."),
            GuideStep(2, "Berechtigungen erteilen", "Starte die App und erlaube alle Berechtigungen für Kamera, Mikrofon und Speicher, damit auf die rohen Sensorströme zugegriffen werden kann."),
            GuideStep(3, "Sensor-Kalibrierung durchführen", "Beim ersten Start führt die App einen kurzen Rausch- und Sensor-Kalibrierungstest durch. Folge den Anweisungen auf dem Bildschirm, um das optimale Sensorprofil zu erstellen."),
            GuideStep(4, "CinemaDNG Format aktivieren", "Öffne die Einstellungen, navigiere zu den Video-Optionen und stelle das Containerformat auf 'CinemaDNG RAW' oder 'Motion RAW'. Dies umgeht die Rauschunterdrückung deines Herstellers."),
            GuideStep(5, "Manuelle Kontrolle & Filmen", "Nutze die manuellen Schieberegler für Fokus, Verschlusszeit (Shutter) und ISO. Importiere die aufgezeichneten RAW-Frames später in ein Programm wie DaVinci Resolve für perfektes Grading.")
        )
    )

    override val devices: Flow<List<Device>> = flow {
        emit(devicesList)
    }

    override fun getRecommendation(deviceId: String, isVideo: Boolean): Flow<GcamRecommendation?> = flow {
        val match = recommendations.firstOrNull { it.deviceId == deviceId && it.isVideo == isVideo }
        emit(match)
    }

    override fun getInstallationGuide(gcamDeveloper: String): Flow<List<GuideStep>> = flow {
        val steps = generalGuideSteps[gcamDeveloper] ?: generalGuideSteps["BigKaka"]!!
        emit(steps)
    }
}
