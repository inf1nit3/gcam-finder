package com.example.gcamfinder.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.platform.LocalContext
import com.example.gcamfinder.data.Device
import com.example.gcamfinder.theme.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeviceSelectionScreen(
    devices: List<Device>,
    onDeviceSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }

    // --- APP AUTO-UPDATER STATES ---
    var showUpdateDialog by remember { mutableStateOf(false) }
    var latestVersion by remember { mutableStateOf("") }
    var releaseNotes by remember { mutableStateOf("") }
    var downloadUrl by remember { mutableStateOf("") }
    
    var downloadProgress by remember { mutableFloatStateOf(0f) }
    var downloadStatus by remember { mutableStateOf("IDLE") } // "IDLE", "DOWNLOADING", "SUCCESS", "FAILED"

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch(Dispatchers.IO) {
            try {
                // Get local app version
                val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
                val currentVersion = packageInfo.versionName ?: "1.0.0"

                // Hit GitHub API for latest release
                val url = java.net.URL("https://api.github.com/repos/inf1nit3/gcam-finder/releases/latest")
                val connection = url.openConnection() as java.net.HttpURLConnection
                connection.requestMethod = "GET"
                connection.setRequestProperty("Accept", "application/vnd.github.v3+json")
                connection.connectTimeout = 5000
                connection.readTimeout = 5000
                connection.connect()

                if (connection.responseCode == java.net.HttpURLConnection.HTTP_OK) {
                    val responseText = connection.inputStream.bufferedReader().use { it.readText() }
                    
                    // Direct Regex Parsing
                    val tagPattern = java.util.regex.Pattern.compile("\"tag_name\"\\s*:\\s*\"([^\"]+)\"")
                    val tagMatcher = tagPattern.matcher(responseText)
                    val tagVal = if (tagMatcher.find()) tagMatcher.group(1) else ""

                    val bodyPattern = java.util.regex.Pattern.compile("\"body\"\\s*:\\s*\"([^\"]+)\"")
                    val bodyMatcher = bodyPattern.matcher(responseText)
                    val rawBody = if (bodyMatcher.find()) bodyMatcher.group(1) else ""
                    val bodyVal = rawBody.replace("\\n", "\n").replace("\\r", "").replace("\\\"", "\"")

                    // Extract the browser_download_url of the APK asset (matching .apk)
                    val assetPattern = java.util.regex.Pattern.compile("\"browser_download_url\"\\s*:\\s*\"([^\"]+\\.apk)\"")
                    val assetMatcher = assetPattern.matcher(responseText)
                    val apkUrl = if (assetMatcher.find()) assetMatcher.group(1) else ""

                    // Compare tag name (stripping 'v' prefix if present)
                    val cleanTag = tagVal.trim().lowercase().removePrefix("v")
                    val cleanLocal = currentVersion.trim().lowercase().removePrefix("v")

                    if (cleanTag.isNotBlank() && cleanTag != cleanLocal) {
                        latestVersion = tagVal
                        releaseNotes = bodyVal.ifBlank { "Systemoptimierungen und neue Sensorunterstützung." }
                        downloadUrl = apkUrl
                        withContext(Dispatchers.Main) {
                            showUpdateDialog = true
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    val filteredDevices = remember(searchQuery, devices) {
        if (searchQuery.isBlank()) {
            devices
        } else {
            devices.filter { it.name.contains(searchQuery, ignoreCase = true) }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(AmoledBlack)
            .statusBarsPadding()
            .padding(horizontal = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        // Professional Camera-Inspired Header
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "GCAM",
                    style = Typography.displayLarge.copy(color = TextPrimary, fontWeight = FontWeight.Black)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "FINDER",
                    style = Typography.displayLarge.copy(color = ApertureGold, fontWeight = FontWeight.Black)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 2.dp)
            ) {
                Text(
                    text = "BY",
                    style = Typography.labelMedium.copy(color = TextSecondary, fontWeight = FontWeight.Bold, fontSize = 9.sp),
                    modifier = Modifier.padding(bottom = 1.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "scheisssewasser",
                    style = Typography.labelLarge.copy(color = ZeissCyan, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                )
            }
        }

        Text(
            text = "OPTIMIERTE VERSIONEN & XML SETUP",
            style = Typography.labelLarge.copy(color = TextSecondary, fontSize = 11.sp),
            modifier = Modifier.padding(top = 4.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Sleek Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("Smartphone suchen...", color = TextSecondary) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = ApertureGold) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = TextPrimary,
                unfocusedTextColor = TextPrimary,
                focusedContainerColor = SurfaceCard,
                unfocusedContainerColor = SurfaceCard,
                focusedBorderColor = ApertureGold,
                unfocusedBorderColor = BorderSlate,
                cursorColor = ApertureGold
            ),
            shape = RoundedCornerShape(14.dp),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Device Scrollable List
        if (filteredDevices.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Keine Geräte gefunden.",
                    style = Typography.bodyLarge.copy(color = TextSecondary)
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(bottom = 24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(filteredDevices, key = { it.id }) { device ->
                    DeviceCard(
                        device = device,
                        onClick = { onDeviceSelected(device.id) }
                    )
                }
            }
        }

        // --- PREMIUM AUTO-UPDATE DIALOG ---
        if (showUpdateDialog) {
            androidx.compose.ui.window.Dialog(
                onDismissRequest = { 
                    if (downloadStatus != "DOWNLOADING") showUpdateDialog = false 
                },
                properties = androidx.compose.ui.window.DialogProperties(
                    dismissOnBackPress = downloadStatus != "DOWNLOADING",
                    dismissOnClickOutside = false
                )
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, ApertureGold.copy(alpha = 0.5f), RoundedCornerShape(18.dp)),
                    colors = CardDefaults.cardColors(containerColor = SurfaceCard),
                    shape = RoundedCornerShape(18.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Premium Header Icon
                        Box(
                            modifier = Modifier
                                .size(56.dp)
                                .clip(RoundedCornerShape(50))
                                .background(ApertureGold.copy(alpha = 0.15f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "🔄",
                                fontSize = 28.sp
                            )
                        }
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        Text(
                            text = "UPDATE VERFÜGBAR",
                            style = Typography.labelMedium.copy(color = ApertureGold, fontWeight = FontWeight.Bold, fontSize = 10.sp)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Neue Version $latestVersion",
                            style = Typography.titleLarge.copy(color = TextPrimary, fontWeight = FontWeight.Bold)
                        )
                        
                        Spacer(modifier = Modifier.height(12.dp))
                        
                        // Release Notes Container
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(max = 180.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(AmoledBlack)
                                .border(0.5.dp, BorderSlate, RoundedCornerShape(8.dp))
                                .padding(12.dp)
                        ) {
                            Text(
                                text = "ÄNDERUNGSPROTOKOLL:",
                                style = Typography.labelMedium.copy(color = ZeissCyan, fontWeight = FontWeight.Bold, fontSize = 8.sp)
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Box(modifier = Modifier.verticalScroll(rememberScrollState())) {
                                Text(
                                    text = releaseNotes,
                                    style = Typography.bodyMedium.copy(color = TextSecondary, fontSize = 11.sp, lineHeight = 16.sp)
                                )
                            }
                        }
                        
                        Spacer(modifier = Modifier.height(20.dp))
                        
                        // Status / Download Bar
                        if (downloadStatus == "DOWNLOADING") {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                LinearProgressIndicator(
                                    progress = { downloadProgress },
                                    color = ZeissCyan,
                                    trackColor = BorderSlate,
                                    modifier = Modifier.fillMaxWidth().height(6.dp).clip(RoundedCornerShape(3.dp))
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = String.format(java.util.Locale.US, "Herunterladen... %.0f%%", downloadProgress * 100),
                                    style = Typography.bodyMedium.copy(color = ZeissCyan, fontWeight = FontWeight.Bold, fontSize = 11.sp)
                                )
                            }
                        } else {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                // Later Button
                                Button(
                                    onClick = { showUpdateDialog = false },
                                    colors = ButtonDefaults.buttonColors(containerColor = BorderSlate, contentColor = TextPrimary),
                                    shape = RoundedCornerShape(10.dp),
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(
                                        text = "Später",
                                        style = Typography.labelLarge.copy(color = TextPrimary, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                    )
                                }
                                
                                // Download & Install Button
                                Button(
                                    onClick = {
                                        downloadStatus = "DOWNLOADING"
                                        downloadProgress = 0f
                                        coroutineScope.launch(Dispatchers.IO) {
                                            try {
                                                val connection = java.net.URL(downloadUrl).openConnection() as java.net.HttpURLConnection
                                                connection.requestMethod = "GET"
                                                connection.connect()
                                                
                                                if (connection.responseCode == java.net.HttpURLConnection.HTTP_OK) {
                                                    val fileLength = connection.contentLength
                                                    val input = connection.inputStream
                                                    val apkFile = java.io.File(context.cacheDir, "app-update.apk")
                                                    val output = java.io.FileOutputStream(apkFile)
                                                    
                                                    val data = ByteArray(4096)
                                                    var total: Long = 0
                                                    var count: Int
                                                    while (input.read(data).also { count = it } != -1) {
                                                        total += count
                                                        if (fileLength > 0) {
                                                            downloadProgress = total.toFloat() / fileLength.toFloat()
                                                        }
                                                        output.write(data, 0, count)
                                                    }
                                                    output.flush()
                                                    output.close()
                                                    input.close()
                                                    
                                                    downloadStatus = "SUCCESS"
                                                    withContext(Dispatchers.Main) {
                                                        // Trigger Installation!
                                                        try {
                                                            val authority = "com.example.gcamfinder.fileprovider"
                                                            val apkUri = androidx.core.content.FileProvider.getUriForFile(context, authority, apkFile)
                                                            val intent = android.content.Intent(android.content.Intent.ACTION_VIEW).apply {
                                                                setDataAndType(apkUri, "application/vnd.android.package-archive")
                                                                flags = android.content.Intent.FLAG_ACTIVITY_NEW_TASK or android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
                                                            }
                                                            context.startActivity(intent)
                                                        } catch (instEx: Exception) {
                                                            instEx.printStackTrace()
                                                            android.widget.Toast.makeText(context, "Installationsfehler: ${instEx.localizedMessage}", android.widget.Toast.LENGTH_LONG).show()
                                                        }
                                                        showUpdateDialog = false
                                                        downloadStatus = "IDLE"
                                                    }
                                                } else {
                                                    downloadStatus = "FAILED"
                                                    withContext(Dispatchers.Main) {
                                                        android.widget.Toast.makeText(context, "Download fehlgeschlagen (HTTP ${connection.responseCode})", android.widget.Toast.LENGTH_LONG).show()
                                                        downloadStatus = "IDLE"
                                                    }
                                                }
                                            } catch (e: Exception) {
                                                e.printStackTrace()
                                                downloadStatus = "FAILED"
                                                withContext(Dispatchers.Main) {
                                                    android.widget.Toast.makeText(context, "Fehler: ${e.localizedMessage}", android.widget.Toast.LENGTH_LONG).show()
                                                    downloadStatus = "IDLE"
                                                }
                                            }
                                        }
                                    },
                                    colors = ButtonDefaults.buttonColors(containerColor = ApertureGold, contentColor = AmoledBlack),
                                    shape = RoundedCornerShape(10.dp),
                                    modifier = Modifier.weight(1.2f)
                                ) {
                                    Text(
                                        text = "Aktualisieren",
                                        style = Typography.labelLarge.copy(color = AmoledBlack, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DeviceCard(
    device: Device,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.97f else 1f,
        label = "press_scale"
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .scale(scale)
            .border(
                width = 1.dp,
                brush = Brush.verticalGradient(
                    colors = listOf(
                        BorderSlate,
                        Color.Transparent
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
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // High-Quality Smartphone Image Thumbnail
                Image(
                    painter = painterResource(id = device.imageResId),
                    contentDescription = device.name,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(84.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(AmoledBlack)
                        .padding(4.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                // Spec / Details Column
                Column(modifier = Modifier.weight(1f)) {
                    // Technische Kennzeichnung
                    Text(
                        text = "KAMERA-FLAGGSCHIFF",
                        style = Typography.labelMedium.copy(color = ZeissCyan)
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    // Device Name
                    Text(
                        text = device.name,
                        style = Typography.titleLarge.copy(color = TextPrimary, fontWeight = FontWeight.Bold)
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    // Mini specs
                    Text(
                        text = device.cameraSpecs,
                        style = Typography.bodyMedium.copy(color = TextPrimary, fontSize = 12.sp)
                    )
                    Text(
                        text = device.chipset,
                        style = Typography.bodyMedium.copy(color = TextSecondary, fontSize = 11.sp),
                        modifier = Modifier.padding(top = 2.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // A small interactive toggle button for sensor details
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(BorderSlate.copy(alpha = 0.8f))
                            .clickable { expanded = !expanded }
                            .padding(horizontal = 10.dp, vertical = 6.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = if (expanded) "Sensoren verbergen ▲" else "Sensoren anzeigen ▼",
                            style = Typography.labelMedium.copy(
                                color = ApertureGold,
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.sp
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.width(8.dp))

                // Aperture Chevron Indicator
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(RoundedCornerShape(50))
                        .background(BorderSlate),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = ">",
                        style = Typography.labelLarge.copy(
                            color = ApertureGold,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    )
                }
            }

            // Expanded sensor details list
            if (expanded) {
                HorizontalDivider(
                    color = BorderSlate,
                    thickness = 1.dp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    device.sensors.forEach { sensor ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(10.dp))
                                .background(AmoledBlack.copy(alpha = 0.4f))
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1.2f)) {
                                Text(
                                    text = sensor.role.uppercase(),
                                    style = Typography.labelMedium.copy(
                                        color = ZeissCyan,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 9.sp
                                    )
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = sensor.sensorModel,
                                    style = Typography.bodyMedium.copy(
                                        color = TextPrimary,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 12.sp
                                    )
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Column(
                                modifier = Modifier.weight(1.8f),
                                horizontalAlignment = Alignment.End
                            ) {
                                Text(
                                    text = "${sensor.resolution} | ${sensor.sensorSize}",
                                    style = Typography.bodyMedium.copy(
                                        color = TextPrimary,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = sensor.lensSpecs,
                                    style = Typography.bodyMedium.copy(
                                        color = TextSecondary,
                                        fontSize = 10.sp
                                    )
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
