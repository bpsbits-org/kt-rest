package org.bpsbits.kt.rest.data.obj

import kotlinx.serialization.Serializable

/**
 * ISO 6391 language code information object.
 * @property language Language name.
 * @property code ISO 6391 language code.
 */
@Suppress("unused")
@Serializable
data class ISO6391CodeOutput(
    val language: String,
    val code: String
)

