package org.bpsbits.kt.rest.data.obj

import kotlinx.serialization.Serializable

/**
 * Application information object.
 * @property name Name of the application.
 * @property description Short description of the application.
 * @property version Version of the application.
 * @property dev `True` if the application is running in development mode.
 * @property ssl `True` if the request is made in HTTPS mode.
 * @see [org.bpsbits.kt.rest.commons.QuarkusApp]
 */
@Serializable
data class AppInfo(
    val name: String,
    val description: String,
    val version: String,
    val dev: Boolean,
    val ssl: Boolean = false
)
