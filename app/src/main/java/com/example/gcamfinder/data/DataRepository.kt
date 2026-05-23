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
                CameraSensor("Periskop-Telekamera (3.5x)", "Samsung ISOCELL HPB", "200 MP", "1/1.4-Zoll", "f/2.7, 85mm Eq., OIS, Zeiss APO"),
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
                CameraSensor("Hauptkamera (Weitwinkel)", "Sony LYT-900", "50 MP", "1-Zoll-Typ (1/0.98\")", "f/1.63 - f/4.0 stufenlose Blende, 23mm Eq., OIS, ALD"),
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
                CameraSensor("Periskop-Telekamera (4.3x)", "Samsung ISOCELL HP9", "200 MP", "1/1.4-Zoll", "f/2.6, 100mm Eq., OIS, Zeiss APO"),
                CameraSensor("Telekamera (3x)", "Sony IMX858", "50 MP", "1/2.51-Zoll", "f/1.8, 70mm Eq., OIS"),
                CameraSensor("Ultraweitwinkel-Kamera", "Samsung ISOCELL JN5", "50 MP", "1/2.76-Zoll", "f/2.2, 14mm Eq., Autofokus")
            )
        ),
        Device(
            id = "xiaomi_17_ultra",
            name = "XIAOMI 17 ULTRA",
            imageResId = R.drawable.xiaomi_17_ultra,
            displaySpecs = "6,9\" LTPO AMOLED, 3500 Nits, 120 Hz",
            cameraSpecs = "50 MP LOFIC 1\" + Kontinuierlicher Zoom",
            chipset = "Snapdragon 8 Elite Gen 5 (2 nm)",
            sensors = listOf(
                CameraSensor("Hauptkamera (Weitwinkel)", "OmniVision OVX10500 / Light Fusion 1050L", "50 MP", "1-Zoll-Typ", "f/1.67, LOFIC-Technologie, 109dB Dynamikbereich, OIS"),
                CameraSensor("Periskop-Telekamera (Kontinuierlicher Zoom)", "Samsung ISOCELL HPE", "200 MP", "1/1.4-Zoll", "f/2.6, 75mm - 100mm Eq. stufenloser Zoom, OIS"),
                CameraSensor("Ultraweitwinkel-Kamera", "Samsung ISOCELL JN5", "50 MP", "1/2.75-Zoll", "f/2.2, 14mm Eq., Autofokus")
            )
        ),
        Device(
            id = "samsung_s26_ultra",
            name = "SAMSUNG S26 ULTRA",
            imageResId = R.drawable.samsung_s26_ultra,
            displaySpecs = "6,8\" QHD+ Dynamic AMOLED 2X, 4000 Nits, 120 Hz",
            cameraSpecs = "200 MP HP2 + 50 MP Quad-Tele + 50 MP Periskop",
            chipset = "Snapdragon 8 Elite / Exynos 2600 (3 nm)",
            sensors = listOf(
                CameraSensor("Hauptkamera (Weitwinkel)", "Samsung ISOCELL HP2", "200 MP", "1/1.3-Zoll", "f/1.7, 24mm Eq., OIS, Super Quad Pixel AF"),
                CameraSensor("Periskop-Telekamera (5x)", "Sony IMX858", "50 MP", "1/2.51-Zoll", "f/3.4, 115mm Eq., OIS"),
                CameraSensor("Telekamera (3x)", "Sony IMX854", "50 MP", "1/2.52-Zoll", "f/2.4, 67mm Eq., OIS"),
                CameraSensor("Ultraweitwinkel-Kamera", "Samsung ISOCELL JN3", "50 MP", "1/2.76-Zoll", "f/2.2, 13mm Eq., Autofokus")
            )
        )
    )

    private val recommendations = listOf(
        // VIVO X300 Pro - Photo
        GcamRecommendation(
            deviceId = "vivo_x300_pro",
            isVideo = false,
            gcamName = "AGC GCam",
            gcamVersion = "9.2.038.V9.0",
            gcamDeveloper = "BigKaka",
            gcamDownloadUrl = "https://www.celsoazevedo.com/files/android/google-camera/dev-suggested/",
            xmlName = "ZeissNatural_v3.xml",
            xmlDownloadUrl = "https://www.celsoazevedo.com/files/android/google-camera/f/configs-agc-01/",
            xmlDescription = "Abgestimmt auf authentische Zeiss T*-Farbdarstellung, extremen Dynamikumfang über HDR+ Erweitert und Pixel-Binning-Optimierungen für das 200-MP-Periskop-Objektiv.",
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
            gcamVersion = "v3.1.2-Free",
            gcamDeveloper = "MotionCam",
            gcamDownloadUrl = "https://play.google.com/store/apps/details?id=com.motioncam",
            xmlName = "CinemaDNG_VivoX300_v1.mc",
            xmlDownloadUrl = "https://play.google.com/store/apps/details?id=com.motioncam",
            xmlDescription = "Ermöglicht echte 10-Bit RAW-Videoaufnahmen (CinemaDNG) direkt über den 1-Zoll Sony Sensor. Umgeht die interne Weichzeichnung des vivo-Algorithmus vollständig.",
            recommendedSettings = listOf(
                "Stelle das Video-Format auf 'CinemaDNG RAW' für unkomprimiertes Color Grading",
                "Nutze schnellen internen Speicher (UFS 4.0) für stabile RAW-Aufnahmeraten",
                "Verwende ein flaches LOG-Profil (z.B. Rec.2020 Log) für maximale Dynamik"
            )
        ),
        // XIAOMI 14 ULTRA - Photo
        GcamRecommendation(
            deviceId = "xiaomi_14_ultra",
            isVideo = false,
            gcamName = "AGC GCam",
            gcamVersion = "9.2.038.V9.0",
            gcamDeveloper = "BigKaka",
            gcamDownloadUrl = "https://www.celsoazevedo.com/files/android/google-camera/dev-suggested/",
            xmlName = "LeicaVibrantUltra_v14.xml",
            xmlDownloadUrl = "https://www.celsoazevedo.com/files/android/google-camera/f/configs-agc-01/",
            xmlDescription = "Speziell für den 1-Zoll LYT-900 Sensor des Xiaomi 14 Ultra entwickelt. Bietet die Leica Vibrant-Farbpalette, ultrascharfe Periskop-Profile und perfekt kalibrierte Vignettierungskorrekturen.",
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
            gcamVersion = "v3.1.2-Free",
            gcamDeveloper = "MotionCam",
            gcamDownloadUrl = "https://play.google.com/store/apps/details?id=com.motioncam",
            xmlName = "LeicaCinemaRAW_X14_v2.mc",
            xmlDownloadUrl = "https://play.google.com/store/apps/details?id=com.motioncam",
            xmlDescription = "Entwickelt für die verlustfreie 12-Bit RAW-Aufnahme des Xiaomi 14 Ultra über den 1-Zoll-Sensor LYT-900. Unterstützt nahtlose native Umschaltung auf alle 4 Leica-Objektive.",
            recommendedSettings = listOf(
                "Wähle das 'CinemaDNG' RAW-Aufnahmeformat bei 24 fps oder 30 fps",
                "Stelle die manuelle variable Blende auf f/2.0 für ein weiches, natürliches Hintergrund-Bokeh",
                "Aktiviere die Echtzeit-Falschfarbenanzeige zur perfekten Belichtungskontrolle"
            )
        ),
        // XIAOMI 15 ULTRA - Photo
        GcamRecommendation(
            deviceId = "xiaomi_15_ultra",
            isVideo = false,
            gcamName = "AGC GCam",
            gcamVersion = "9.2.038.V9.0",
            gcamDeveloper = "BigKaka",
            gcamDownloadUrl = "https://www.celsoazevedo.com/files/android/google-camera/dev-suggested/",
            xmlName = "LeicaVibrantUltra_v15.xml",
            xmlDownloadUrl = "https://www.celsoazevedo.com/files/android/google-camera/f/configs-agc-01/",
            xmlDescription = "Speziell kalibriert für den LYT-900 Sensor der zweiten Generation im Xiaomi 15 Ultra. Bietet optimierten Dynamikumfang, präzise Leica-Farbabstimmung und hervorragende Rauschminderung für das neue Periskop-Teleskop.",
            recommendedSettings = listOf(
                "Nutze das 'Leica Authentic'-Profil für klassische Schwarz-Weiß-Porträts",
                "Aktiviere den dualen Blenden-Modus für optimale Tiefenschärfe",
                "Setze die Rauschminderung auf das 'LYT-900 Gen 2'-Modell"
            )
        ),
        // XIAOMI 15 ULTRA - Video
        GcamRecommendation(
            deviceId = "xiaomi_15_ultra",
            isVideo = true,
            gcamName = "Motion Cam",
            gcamVersion = "v3.1.2-Free",
            gcamDeveloper = "MotionCam",
            gcamDownloadUrl = "https://play.google.com/store/apps/details?id=com.motioncam",
            xmlName = "LeicaCinemaRAW_X15_v2.mc",
            xmlDownloadUrl = "https://play.google.com/store/apps/details?id=com.motioncam",
            xmlDescription = "Speziell kalibriert für die 12-Bit CinemaDNG RAW-Aufnahme auf dem Xiaomi 15 Ultra. Optimiert für den Snapdragon 8 Elite ISP und das Quad-Kamerasystem mit 200-MP-Periskop.",
            recommendedSettings = listOf(
                "Nutze 4K @ 24 fps / 30 fps CinemaDNG für kinoreife Textur",
                "Verwende die manuelle Blendensteuerung zur flexiblen Schärfentiefe-Gestaltung",
                "Aktiviere die Fokus-Peaking-Unterstützung für absolut scharfe manuelle Fokussierung"
            )
        ),
        // XIAOMI 17 ULTRA - Photo
        GcamRecommendation(
            deviceId = "xiaomi_17_ultra",
            isVideo = false,
            gcamName = "AGC GCam",
            gcamVersion = "9.4.012.V1.0",
            gcamDeveloper = "BigKaka",
            gcamDownloadUrl = "https://www.celsoazevedo.com/files/android/google-camera/dev-suggested/",
            xmlName = "QuantumOpticPro_v1.xml",
            xmlDownloadUrl = "https://www.celsoazevedo.com/files/android/google-camera/f/configs-agc-01/",
            xmlDescription = "Konfiguration der nächsten Generation, die den 2-nm-Snapdragon-8-Elite-ISP nutzt. Verwendet fortschrittliche KI-Rauschunterdrückung, LOFIC-Ultra-High-Dynamic-Range-Wiederherstellung und RAW-Quad-Binned-Periskop-Mapping.",
            recommendedSettings = listOf(
                "Aktiviere den LOFIC-Modus für extreme Gegenlicht-Situationen",
                "Setze das KI-Rauschunterdrückungsmodell auf das 'Snapdragon 8 Elite'-Profil",
                "Verwende den 50-MP-RAW-Modus für maximale Bildausschnitts-Möglichkeiten"
            )
        ),
        // XIAOMI 17 ULTRA - Video
        GcamRecommendation(
            deviceId = "xiaomi_17_ultra",
            isVideo = true,
            gcamName = "Motion Cam",
            gcamVersion = "v3.1.2-Free",
            gcamDeveloper = "MotionCam",
            gcamDownloadUrl = "https://play.google.com/store/apps/details?id=com.motioncam",
            xmlName = "Quantum8KCinema_X17_v1.mc",
            xmlDownloadUrl = "https://play.google.com/store/apps/details?id=com.motioncam",
            xmlDescription = "Speziell optimiert für High-FPS-RAW-Aufnahmen unter Verwendung der Snapdragon 8 Elite Schnittstelle. Ermöglicht extreme Datenströme bei 4K60 RAW oder 8K30 HDR.",
            recommendedSettings = listOf(
                "Wähle 'CinemaDNG' bei 4K @ 60 fps für atemberaubende Zeitlupen",
                "Aktiviere den erweiterten Farbraum 'Rec.2024 Wide Color'",
                "Nutze die integrierte Gyroskop-Wasserwaage zur perfekten optischen Ausrichtung"
            )
        ),
        // SAMSUNG S26 ULTRA - Photo
        GcamRecommendation(
            deviceId = "samsung_s26_ultra",
            isVideo = false,
            gcamName = "AGC GCam",
            gcamVersion = "8.4.300.V9.6",
            gcamDeveloper = "BigKaka",
            gcamDownloadUrl = "https://www.celsoazevedo.com/files/android/google-camera/dev-suggested/",
            xmlName = "EGOISTv44betaAGC8.4v9.6_S26U_test.agc",
            xmlDownloadUrl = "https://drive.google.com/drive/folders/11iiS5mB63E9YZNdv3lpkI_9gu2l0yO1I?usp=sharing",
            xmlDescription = "EGOIST custom setup calibrated for Samsung ISOCELL HP2 200MP sensor, balancing ultra crisp detail and clean night dynamic range recovery.",
            recommendedSettings = listOf(
                "Use HDR+ Enhanced for scenery shots to recover details",
                "Select custom library shgv1.2k16.so in AGC Settings",
                "Double press next to shutter button to load AGC configuration"
            )
        ),
        // SAMSUNG S26 ULTRA - Video
        GcamRecommendation(
            deviceId = "samsung_s26_ultra",
            isVideo = true,
            gcamName = "Motion Cam",
            gcamVersion = "v3.1.2-Free",
            gcamDeveloper = "MotionCam",
            gcamDownloadUrl = "https://play.google.com/store/apps/details?id=com.motioncam",
            xmlName = "CinemaDNG_S26U_v1.mc",
            xmlDownloadUrl = "https://play.google.com/store/apps/details?id=com.motioncam",
            xmlDescription = "Uncompressed 10-bit DNG RAW video profile designed for ISOCELL HP2 sensor, completely bypassing stock noise processing and shutter-lag.",
            recommendedSettings = listOf(
                "Wähle 'CinemaDNG' Format bei 4K @ 30 fps",
                "Use fast external storage (UFS 4.0) for stable RAW stream",
                "Set white balance to native 5500K for natural daylight scenes"
            )
        )
    )

    private val generalGuideSteps = mapOf(
        "BigKaka" to listOf(
            GuideStep(1, "GCam herunterladen", "Lade die empfohlene AGC GCam APK über den Download-Link herunter und installiere sie auf deinem Gerät."),
            GuideStep(2, "Ordnerstruktur erstellen", "Öffne deine Dateimanager-App und erstelle im Hauptverzeichnis des internen Speichers einen Ordner namens 'AGC.9.2' (bzw. passend zur installierten Hauptversion) und darin einen Unterordner namens 'configs'."),
            GuideStep(3, "XML-Datei kopieren", "Lade die empfohlene XML-Konfigurationsdatei herunter und verschiebe sie in den neu erstellten Ordner '/AGC.9.2/configs/'."),
            GuideStep(4, "XML importieren", "Öffne die installierte AGC-App, doppelklicke auf den schwarzen Bereich neben dem Auslöser, wähle die importierte XML-Datei aus und klicke auf 'Import' bzw. 'Restore'."),
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
