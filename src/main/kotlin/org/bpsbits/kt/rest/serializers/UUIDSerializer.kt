package org.bpsbits.kt.rest.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.util.*

/**
 * Serializes a [UUID].
 */
@Suppress("unused")
object UUIDSerializer : KSerializer<UUID> {

    override val descriptor = PrimitiveSerialDescriptor("UUID", PrimitiveKind.STRING)

    /**
     * Deserializes [UUID]
     */
    override fun deserialize(decoder: Decoder): UUID {
        return UUID.fromString(decoder.decodeString())
    }

    /**
     * Serializes [UUID]
     */
    override fun serialize(encoder: Encoder, value: UUID) {
        encoder.encodeString(value.toString())
    }
}