# GCam & XML Finder · Ultimate Camera Toolbox 📸✨

Eine native, hochperformante Android-Anwendung, speziell entwickelt für Smartphone-Kamera-Enthusiasten. Die App bietet maßgeschneiderte Google Camera (GCam) APKs, XML-Konfigurationen und hochentwickelte Werkzeuge für Premium-Smartphones wie das **Xiaomi 17 Ultra** und das **Vivo X300 Pro**.

Designtechnisch besticht die App durch ein kompromissloses, edge-to-edge **AMOLED-Deep-Black-Interface**, veredelt mit kuratierten **Zeiss-Cyan (0x00D2FF)** und **Aperture-Gold (0xFFD700)** Farbströmen.

---

## 🚀 Kern-Features

### 1. ⚙️ Exklusives Profil- & Download-Management
* **Maßgeschneiderte Builds:** Direktabgleich von optimierten GCam-Versionen und XMLs (z. B. *EGOIST v16/v17*, *j0qz*, *Rifanda Port*).
* **Automatisches Directory-Routing:** Automatisches Herunterladen und Kopieren von XMLs in die gerätespezifischen GCam-Zielordner (z. B. `/sdcard/LMC8.3/` oder `/sdcard/LMC8.4/`) zur sofortigen Verwendung in der Kamera.
* **LSposed & Magisk-Guides:** Interaktive, hochauflösende Anleitungen zur Einrichtung exklusiver Features wie dem Leica M9 Cloud-Processing oder Qualcomm DCG Hardware-Registern.

### 2. ⚡ Standalone Premium-Tools Dashboard

#### 📐 Camera2 API & Sub-Sensor Scanner
* **Tiefen-API-Diagnose:** Liest nativ alle physischen Linseneigenschaften aus (API-Level `LEVEL_3`, `FULL`, `LIMITED`, Sensor-Größen, physikalische Brennweiten, Megapixel-Arrays).
* **Aux-Lens Exposer:** Umgeht die standardmäßigen Android-Restriktionen auf Vivo/Xiaomi-Geräten und deckt über `physicalCameraIds` alle versteckten Weitwinkel-, Ultraweitwinkel- und Periskop-Telesensoren auf.
* **One-Tap Lens Copier:** Formatiert alle erkannten Linsen-IDs in ein kopierbereites Schema für MotionCam Pro und die GCam-Zusatzeinstellungen.

#### 📊 LUT & Simulation Visualizer
* **Echtzeit Split-Screen Simulator:** Ermöglicht das Laden eigener Galeriebilder über Android `GetContent` und vergleicht diese live über einen flüssigen Gesten-Schieberegler mit Cinematic-LUTs (Teal & Orange, Leica Mono, Warm Film).
* **JWB Farb-Tuning:** Simuliert per Switch in Echtzeit das Verhalten des JWB-Farbprofils durch eine dynamische 25 % Sättigungs- & Kontrastanpassung auf der Farbmatrix.
* **Prozedurales Rauschen (Noise Model):** Zeichnet über eine hardwarebeschleunigte Jetpack Compose Canvas feines Filmkorn auf die farbgraduierte Seite, stufenlos regelbar von `0.10` bis `0.40` mit automatischen Sicherheitswarnungen vor Artefakten.

#### 📂 Integrierter GCam File Manager
* **Direktzugriff:** Auflistung und Verwaltung aller `.xml` Profile in `/LMC8.3/`, `/LMC8.4/` und `/GCam/Configs8.4/`.
* **Backup & Recovery:** Erstellung von Sicherheitskopien im internen App-Dateiverzeichnis und One-Tap-Dateibereinigung.

#### 🔄 Config Auto-Update Checker
* **Cloud-Vergleich:** Vergleicht lokale Configs mit den neuesten Releases auf dem Google Drive Server und ermöglicht Updates mit nur einem Klick.

#### 🎥 MotionCam Color LUTs Integration
* **Click-Interception:** Ein injiziertes JavaScript-Interface im systemeigenen WebView fängt Download- und "Add to Motion Cam" Links auf der offiziellen Webpräsenz ab, löst relative URLs auf und lädt sie direkt über den systemeigenen `DownloadManager` herunter.

---

## 🛠️ Tech-Stack

* **UI / Framework:** Kotlin & Jetpack Compose (Modernes deklaratives UI)
* **Navigation:** Navigation3 (Flexible, typensichere Routen-Steuerung)
* **Media / Player:** Media3 ExoPlayer (Für in App eingebundene Tutorial-Videos)
* **Hardware-Schnittstelle:** Android `Camera2` API (`CameraManager`, `CameraCharacteristics`)
* **WebView:** Android Hardware-Accelerated WebView mit JS-Injektion & Custom JavaScriptInterface.
* **IO / Storage:** Kotlin Coroutines, Java Stream-Copying, SAF (Storage Access Framework)

---

## 📁 Projektstruktur (Auszug)

```text
app/src/main/java/com/example/gcamfinder/
│
├── data/
│   ├── Device.kt                  # Datenklassen für Sensoren und Telefone
│   └── DefaultDataRepository.kt   # Lokales Daten-Repository für APKs/Configs
│
├── theme/
│   ├── Color.kt                   # AMOLED-Black, ZeissCyan & ApertureGold Token
│   ├── Theme.kt                   # Compose Theme-Initialisierung
│   └── Type.kt                    # Outfit & Inter Schrift-Konfiguration
│
├── ui/screens/
│   ├── DeviceSelectionScreen.kt   # Startbildschirm (Geräteauswahl)
│   ├── ModeSelectionScreen.kt     # Modusauswahl (Foto, Video, Pro Tools)
│   └── RecommendationHubScreen.kt # Das Herzstück (Downloads, Guides, Pro-Werkzeuge)
│
├── MainActivity.kt                # Application-Einstiegspunkt
└── Navigation.kt                  # Typensichere Navigation3 Routen-Definition
```

---

## 🔨 Build & Installation

### Voraussetzungen
* Android SDK 28+ (Empfohlen API 33+)
* JDK 17
* Android Studio (Koala oder neuer)

### Befehle

1. **Projekt kompilieren und Syntax überprüfen:**
   ```bash
   ./gradlew compileDebugKotlin
   ```

2. **Finale Debug-APK paketieren:**
   ```bash
   ./gradlew assembleDebug
   ```
   Die fertige Installationsdatei liegt anschließend im Verzeichnis:
   `app/build/outputs/apk/debug/app-debug.apk`
