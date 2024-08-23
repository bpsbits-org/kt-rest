package org.bpsbits.kt.rest.data.obj

import kotlinx.serialization.Serializable
import org.bpsbits.kt.rest.serializers.UUIDSerializer
import org.bpsbits.kt.rest.utils.UUIDv7
import java.util.*

/**
 * Object containing UUID.
 * By default, UUID is generated using [UUIDv7].
 * @property uuid [UUID]
 */
@Suppress("unused")
@Serializable
data class UUIDOutput(
    @Serializable(with = UUIDSerializer::class)
    var uuid: UUID = UUIDv7.new()
)
