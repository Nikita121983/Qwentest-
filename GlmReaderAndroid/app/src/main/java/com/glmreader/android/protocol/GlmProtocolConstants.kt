package com.glmreader.android.protocol

import java.util.UUID

object GlmProtocolConstants {
    // Bosch GLM 50 C Service & Characteristic UUIDs
    val SERVICE_UUID: UUID = UUID.fromString("02A6C0D0-0451-4000-B000-FB3210111989")
    val CHARACTERISTIC_UUID: UUID = UUID.fromString("02A6C0D1-0451-4000-B000-FB3210111989")

    // Commands (for later)
    const val CMD_INIT = "C0 55 02 40 00"
}
