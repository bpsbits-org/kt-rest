@file:Suppress("unused")

package org.bpsbits.kt.rest.utils.hsr

import io.vertx.core.http.HttpServerRequest
import org.bpsbits.kt.rest.commons.QuarkusApp
import org.bpsbits.kt.toolbox.utils.string.md5AsUUID
import org.bpsbits.kt.toolbox.utils.uuid.UUIDv7
import java.util.UUID

/**
 * Gets the client's IP address from the request headers or connection
 * @return Remote IP address as a string
 */
val HttpServerRequest.clientIP: String
    get() {
        return getHeader("X-Forwarded-For")?.split(',')?.first()?.trim()
            ?: getHeader("X-Real-IP")
            ?: remoteAddress()?.host() ?: "unknown"
    }

/**
 * Gets the client's User-Agent string from request headers
 * @return User-Agent string or "unknown" if the header is missing/empty
 */
val HttpServerRequest.userAgent: String
    get() {
        return getHeader("User-Agent") ?: ""
    }

/**
 * Client request metadata including IP and User-Agent
 */
val HttpServerRequest.clientInfo: Map<String, String>
    get() {
        return mapOf(
            "ip" to clientIP,
            "agent" to userAgent
        )
    }

/**
 * Extracts and validates session hash from an identity token
 * @return UUID from a valid token, null if missing or invalid
 */
val HttpServerRequest.sessionHash: UUID?
    get() = run {
        val tokenName = QuarkusApp.identityTokenName
        (getHeader(tokenName)?.trim() ?: getCookie(tokenName)?.value?.trim())
            ?.takeIf { UUIDv7.isStringUUIDv7(it) }?.md5AsUUID()
    }

/**
 * Gets the owner ID from session headers if a valid session exists
 * @return UUID of a session owner if present, and the session is valid, null otherwise
 */
val HttpServerRequest.sessionOwnerId: UUID?
    get() = sessionHash?.let {
        getHeader(QuarkusApp.SESSION_OWNER_HEADER)
            ?.trim()
            ?.takeIf { it.isNotEmpty() }
            ?.let { UUID.fromString(it) }
    }