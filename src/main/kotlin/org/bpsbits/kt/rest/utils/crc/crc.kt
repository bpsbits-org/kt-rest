@file:Suppress("unused")

package org.bpsbits.kt.rest.utils.crc

import jakarta.ws.rs.container.ContainerRequestContext
import jakarta.ws.rs.core.Cookie
import org.bpsbits.kt.rest.commons.PHP
import org.bpsbits.kt.rest.commons.QuarkusApp
import org.bpsbits.kt.rest.commons.Tomcat

/**
 * Extracts cookie value.
 * @param cookieName Name of the cookie.
 * @return Value of given cookie or an empty string if not found.
 * @see oracle.com <a href="https://docs.oracle.com/javaee/7/api/javax/ws/rs/container/ContainerRequestContext.html">ContainerRequestContext</a>
 */
fun ContainerRequestContext.cookieValue(cookieName: String): String {
    val cookies: Map<String, Cookie> = this.cookies
    return cookies[cookieName]?.value ?: ""
}

/**
 * Extracts header value.
 * @param headerName Name of the header.
 * @return Value of given header or an empty string if not found.
 * @see oracle.com <a href="https://docs.oracle.com/javaee/7/api/javax/ws/rs/container/ContainerRequestContext.html">ContainerRequestContext</a>
 */
fun ContainerRequestContext.headerValue(headerName: String): String {
    return this.getHeaderString(headerName) ?: ""
}

/**
 * Returns value of the Tomcat session cookie.
 * @return Value of the Tomcat session cookie or an empty string if not set.
 * @see oracle.com <a href="https://docs.oracle.com/javaee/7/api/javax/ws/rs/container/ContainerRequestContext.html">ContainerRequestContext</a>
 */
val ContainerRequestContext.tomcatSessionId: String
    get() = this.cookieValue(Tomcat.SESSION_COOKIE)

/**
 * Returns value of the PHP session cookie.
 * @return Value of the PHP session cookie or an empty string if not set.
 * @see oracle.com <a href="https://docs.oracle.com/javaee/7/api/javax/ws/rs/container/ContainerRequestContext.html">ContainerRequestContext</a>
 */
val ContainerRequestContext.phpSessionId: String
    get() = this.cookieValue(PHP.SESSION_COOKIE)

/**
 * Returns a boolean indicating whether this request was made using a secure channel, such as HTTPS.
 * @return Returns `true` if this request was made using a secure channel, such as HTTPS.
 * @see oracle.com <a href="https://docs.oracle.com/javaee/7/api/javax/ws/rs/container/ContainerRequestContext.html">ContainerRequestContext</a>
 */
val ContainerRequestContext.isSSL: Boolean
    get() {
        return this.securityContext.isSecure
    }

/**
 * Returns the value of the identity token.
 * If the token is not set, returns an empty string.
 */
val ContainerRequestContext.identityToken: String
    get() {
        val tokenName = QuarkusApp.identityTokenName
        return listOf(cookieValue(tokenName), headerValue(tokenName))
            .firstOrNull { it.isNotBlank() } ?: ""
    }