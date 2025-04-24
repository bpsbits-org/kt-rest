@file:Suppress("unused")

package org.bpsbits.kt.rest.utils.crc

import jakarta.ws.rs.container.ContainerRequestContext
import jakarta.ws.rs.core.Cookie
import org.bpsbits.kt.rest.commons.PHP
import org.bpsbits.kt.rest.commons.QuarkusApp
import org.bpsbits.kt.rest.commons.Tomcat
import org.bpsbits.kt.toolbox.utils.string.md5AsUUID
import org.bpsbits.kt.toolbox.utils.uuid.UUIDv7
import java.util.UUID

/**
 * Extracts cookie value.
 * @param cookieName Name of the cookie.
 * @return Value of a given cookie or an empty string if not found.
 * @see <a href="https://docs.oracle.com/javaee/7/api/javax/ws/rs/container/ContainerRequestContext.html">ContainerRequestContext</a>
 */
fun ContainerRequestContext.cookieValue(cookieName: String): String {
    val cookies: Map<String, Cookie> = this.cookies
    return cookies[cookieName]?.value ?: ""
}

/**
 * Extracts header value.
 * @param headerName Name of the header.
 * @return Value of a given header or an empty string if not found.
 * @see <a href="https://docs.oracle.com/javaee/7/api/javax/ws/rs/container/ContainerRequestContext.html">ContainerRequestContext</a>
 */
fun ContainerRequestContext.headerValue(headerName: String): String {
    return this.getHeaderString(headerName) ?: ""
}

/**
 * Returns value of the Tomcat session cookie.
 * @return Value of the Tomcat session cookie or an empty string if not set.
 * @see <a href="https://docs.oracle.com/javaee/7/api/javax/ws/rs/container/ContainerRequestContext.html">ContainerRequestContext</a>
 */
val ContainerRequestContext.tomcatSessionId: String
    get() = this.cookieValue(Tomcat.SESSION_COOKIE)

/**
 * Returns value of the PHP session cookie.
 * @return Value of the PHP session cookie or an empty string if not set.
 * @see <a href="https://docs.oracle.com/javaee/7/api/javax/ws/rs/container/ContainerRequestContext.html">ContainerRequestContext</a>
 */
val ContainerRequestContext.phpSessionId: String
    get() = this.cookieValue(PHP.SESSION_COOKIE)

/**
 * Returns a boolean indicating whether this request was made using a secure channel, such as HTTPS.
 * @return Returns `true` if this request was made using a secure channel, such as HTTPS.
 * @see <a href="https://docs.oracle.com/javaee/7/api/javax/ws/rs/container/ContainerRequestContext.html">ContainerRequestContext</a>
 */
val ContainerRequestContext.isSSL: Boolean
    get() {
        return this.securityContext.isSecure
    }

/**
 * Extracts and validates session hash from an identity token
 * @return UUID from a valid token, null if missing or invalid
 * @see <a href="https://docs.oracle.com/javaee/7/api/javax/ws/rs/container/ContainerRequestContext.html">ContainerRequestContext</a>
 */
val ContainerRequestContext.sessionHash: UUID?
    get() {
        val tokenName = QuarkusApp.identityTokenName
        val tokenValue = (getHeaderString(tokenName)?.trim()
            ?: cookies[tokenName]?.value?.trim())
            ?.also { token ->
                headers.putSingle(tokenName, token)
            } ?: ""
        if (UUIDv7.isStringUUIDv7(tokenValue)) {
            return tokenValue.md5AsUUID()
        }
        return null
    }

/**
 * Extracts and validates session hash from an identity token
 * @return UUID as string from a valid token, empty string if missing or invalid
 * @see <a href="https://docs.oracle.com/javaee/7/api/javax/ws/rs/container/ContainerRequestContext.html">ContainerRequestContext</a>
 */
val ContainerRequestContext.identityToken: String
    get() {
        return this.sessionHash?.toString() ?: ""
    }

/**
 * Gets the client's IP address.
 * Falls back to localhost (127.0.0.1) if the request is from localhost, otherwise returns "unknown".
 * Checks headers in order: X-Forwarded-For, X-Real-IP, Proxy-Client-IP, HTTP_X_FORWARDED_FOR, HTTP_CLIENT_IP
 * @see <a href="https://docs.oracle.com/javaee/7/api/javax/ws/rs/container/ContainerRequestContext.html">ContainerRequestContext</a>
 */
val ContainerRequestContext.clientIP: String
    get() {
        val headers = listOfNotNull(
            getHeaderString("X-Forwarded-For")?.split(",")?.firstOrNull()?.trim(),
            getHeaderString("X-Real-IP")?.trim(),
            getHeaderString("Proxy-Client-IP")?.trim(),
            getHeaderString("HTTP_X_FORWARDED_FOR")?.trim(),
            getHeaderString("HTTP_CLIENT_IP")?.trim()
        )
        headers.forEachIndexed { index, value -> println("Header[$index]: $value") }
        return headers.firstOrNull { it.isNotBlank() && it != "unknown" }
            ?: if (getHeaderString("Host")?.contains("localhost") == true) "127.0.0.1" else "unknown"
    }

/**
 * Gets the client's User-Agent string or returns "unknown" if not present.
 * @see <a href="https://docs.oracle.com/javaee/7/api/javax/ws/rs/container/ContainerRequestContext.html">ContainerRequestContext</a>
 */
val ContainerRequestContext.userAgent: String
    get() = getHeaderString("User-Agent") ?: "unknown"

/**
 * Client request metadata including IP and User-Agent.
 * @see <a href="https://docs.oracle.com/javaee/7/api/javax/ws/rs/container/ContainerRequestContext.html">ContainerRequestContext</a>
 */
val ContainerRequestContext.clientInfo
    get() = mapOf(
        "ip" to clientIP,
        "agent" to userAgent
    )

/**
 * Sets or removes the identity owner ID in request headers.
 * @see <a href="https://docs.oracle.com/javaee/7/api/javax/ws/rs/container/ContainerRequestContext.html">ContainerRequestContext</a>
 */
fun ContainerRequestContext.setIdentityOwner(ownerId: UUID?) = ownerId.also { id ->
    if (id != null) headers.putSingle(QuarkusApp.SESSION_OWNER_HEADER, id.toString())
    else headers.remove(QuarkusApp.SESSION_OWNER_HEADER)
}