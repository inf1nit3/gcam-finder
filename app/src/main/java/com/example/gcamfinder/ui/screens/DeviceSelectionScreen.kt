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
import com.example.gcamfinder.data.Device
import com.example.gcamfinder.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeviceSelectionScreen(
    devices: List<Device>,
    onDeviceSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }
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
