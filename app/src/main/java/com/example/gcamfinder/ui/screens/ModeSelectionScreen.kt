package com.example.gcamfinder.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gcamfinder.data.Device
import com.example.gcamfinder.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModeSelectionScreen(
    device: Device,
    onModeSelected: (Boolean, String) -> Unit, // false = Photo, true = Video/Film, second is variant
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(AmoledBlack)
            .statusBarsPadding()
            .verticalScroll(scrollState)
            .padding(horizontal = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Back Navigation Button
        IconButton(
            onClick = onBackClick,
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(50))
                .background(SurfaceCard)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Zurück",
                tint = TextPrimary
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Selected Device Summary Header
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, BorderSlate, RoundedCornerShape(14.dp)),
            colors = CardDefaults.cardColors(containerColor = SurfaceCard)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "GEWÄHLTES GERÄT",
                    style = Typography.labelMedium.copy(color = ZeissCyan)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = device.name,
                    style = Typography.headlineMedium.copy(color = TextPrimary, fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "${device.chipset} | ${device.displaySpecs}",
                    style = Typography.bodyMedium.copy(color = TextSecondary, fontSize = 13.sp)
                )

                Spacer(modifier = Modifier.height(12.dp))
                HorizontalDivider(color = BorderSlate, thickness = 1.dp)
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "EXAKTE SENSOR-SPEZIFIKATIONEN:",
                    style = Typography.labelMedium.copy(color = ApertureGold, fontWeight = FontWeight.Bold, fontSize = 10.sp)
                )
                Spacer(modifier = Modifier.height(8.dp))

                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    device.sensors.forEach { sensor ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .background(AmoledBlack.copy(alpha = 0.4f))
                                .padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1.2f)) {
                                Text(
                                    text = sensor.role.uppercase(),
                                    style = Typography.labelMedium.copy(
                                        color = ZeissCyan,
                                        fontSize = 8.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                                Spacer(modifier = Modifier.height(1.dp))
                                Text(
                                    text = sensor.sensorModel,
                                    style = Typography.bodyMedium.copy(
                                        color = TextPrimary,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 11.sp
                                    )
                                )
                            }
                            Spacer(modifier = Modifier.width(6.dp))
                            Column(
                                modifier = Modifier.weight(1.8f),
                                horizontalAlignment = Alignment.End
                            ) {
                                Text(
                                    text = "${sensor.resolution} | ${sensor.sensorSize}",
                                    style = Typography.bodyMedium.copy(
                                        color = TextPrimary,
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                )
                                Spacer(modifier = Modifier.height(1.dp))
                                Text(
                                    text = sensor.lensSpecs,
                                    style = Typography.bodyMedium.copy(
                                        color = TextSecondary,
                                        fontSize = 9.sp
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Große Auswahlanleitung
        Text(
            text = "Einsatzbereich wählen",
            style = Typography.titleLarge.copy(color = TextPrimary)
        )
        Text(
            text = "Wähle das passende Kamera-Setup für deine Anforderungen:",
            style = Typography.bodyLarge.copy(color = TextSecondary),
            modifier = Modifier.padding(top = 4.dp, bottom = 24.dp)
        )
        // Foto Mode Card
        ModeCard(
            title = "FOTO",
            subtitle = "Beste Bildqualität & Dynamik",
            description = "Optimiert für maximalen Dynamikumfang (HDR+ Erweitert), natürliche Leica/Zeiss-Farben, extreme Tele-Zoom-Details und erstklassige Rauschunterdrückung bei Nacht.",
            tagColor = ApertureGold,
            tagText = "HDR+ & RAW",
            onClick = {
                if (device.id == "xiaomi_17_ultra") {
                    onModeSelected(false, "xiaomi_foto")
                } else {
                    onModeSelected(false, "default")
                }
            }
        )
 
        Spacer(modifier = Modifier.height(16.dp))
 
        // Film Mode Card
        ModeCard(
            title = "FILM / VIDEO",
            subtitle = "Professionelles RAW-Video",
            description = "Ermöglicht verlustfreie CinemaDNG-Aufnahmen (RAW) mit Motion Cam. Umgeht die herstellerseitige Videokompression vollständig für kinoreife Ergebnisse.",
            tagColor = ZeissCyan,
            tagText = "RAW-VIDEO & 4K60",
            onClick = { onModeSelected(true, "default") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Premium Tools Card
        ModeCard(
            title = "PREMIUM-WERKZEUGE & TOOLS",
            subtitle = "Kamera-Diagnose, LUT-Vorschau & Datei-Manager",
            description = "Nativer Camera2 API Sensor-Analyzer, Echtzeit-LUT-Vorschau mit Split-Slider für eigene Bilder, GCam-Dateimanager zur Config-Sicherung und automatischer Update-Checker für deine Profile.",
            tagColor = ApertureGold,
            tagText = "PRO WERKZEUGE",
            onClick = { onModeSelected(false, "tools") }
        )

        if (device.id == "xiaomi_17_ultra") {
            Spacer(modifier = Modifier.height(16.dp))

            // Leica M9 Cloud / LEITZ Mod Card
            ModeCard(
                title = "LEICA M9 / LEITZ MOD",
                subtitle = "Leica M9 Cloud-Processing freischalten",
                description = "Nutze das exklusive Leica M9 Essential Cloud-Processing für echte 50-Megapixel-Aufnahmen direkt in der originalen Kamera-App. Erfordert Magisk (Root) + LSPosed und das dreiteilige Installationspaket.",
                tagColor = Color(0xFFD21F1B), // Leica Crimson Red
                tagText = "MAGISK & LSPOSED (ROOT)",
                onClick = { onModeSelected(false, "leitz") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Xiaomi 17 Ultra DCG Card
            ModeCard(
                title = "14-BIT NATIVES DCG RAW",
                subtitle = "Spezial-Setup für MotionCam Pro (Kein Root!)",
                description = "Schalte echten 14-Bit Dual Conversion Gain RAW-Capture auf der Haupt- und Periskop-Kamera frei. Ausführliche Anleitung zur manuellen Einrichtung der Qualcomm Hardware-Register.",
                tagColor = ZeissCyan,
                tagText = "MOTIONCAM PRO & DCG",
                onClick = { onModeSelected(true, "xiaomi_17_ultra_dcg") }
            )
        }

        if (device.id == "vivo_x300_pro") {
            Spacer(modifier = Modifier.height(16.dp))

            // EGOIST Custom-Profil Card
            ModeCard(
                title = "EGOIST CUSTOM-PROFIL",
                subtitle = "Höchste Bildschärfe & Zeiss-Farben",
                description = "Vollständig abgestimmtes Premium-Profil inkl. XML-Konfiguration und exklusiver Custom-Library (shgv1.2k16.so). Bietet extreme Detailzeichnung, ein integriertes Anleitungsvideo und erstklassiges Rauschverhalten.",
                tagColor = ZeissCyan,
                tagText = "AGC 8.4 & CUSTOM-LIB",
                onClick = { onModeSelected(false, "egoist") }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun ModeCard(
    title: String,
    subtitle: String,
    description: String,
    tagColor: Color,
    tagText: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.96f else 1f,
        label = "mode_press_scale"
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .scale(scale)
            .border(
                width = 1.dp,
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        BorderSlate,
                        tagColor.copy(alpha = 0.3f)
                    )
                ),
                shape = RoundedCornerShape(18.dp)
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            ),
        colors = CardDefaults.cardColors(
            containerColor = SurfaceCard
        ),
        shape = RoundedCornerShape(18.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Mode Tag
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(tagColor.copy(alpha = 0.15f))
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = tagText,
                        style = Typography.labelMedium.copy(
                            color = tagColor,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp
                        )
                    )
                }

                Text(
                    text = "AUSWÄHLEN >",
                    style = Typography.labelLarge.copy(color = TextSecondary, fontSize = 11.sp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Title
            Text(
                text = title,
                style = Typography.titleLarge.copy(color = TextPrimary, fontWeight = FontWeight.Bold)
            )

            // Subtitle
            Text(
                text = subtitle,
                style = Typography.labelLarge.copy(color = tagColor, fontSize = 11.sp, fontWeight = FontWeight.SemiBold),
                modifier = Modifier.padding(top = 2.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Description
            Text(
                text = description,
                style = Typography.bodyMedium.copy(color = TextSecondary, lineHeight = 18.sp)
            )
        }
    }
}
