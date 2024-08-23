@file:Suppress("unused")

package org.bpsbits.kt.rest.utils.brh

import jakarta.ws.rs.core.NewCookie
import org.bpsbits.kt.rest.commons.QuarkusApp
import org.bpsbits.kt.rest.commons.Tomcat
import org.bpsbits.kt.rest.data.obj.AppInfo
import org.bpsbits.kt.rest.i18n.AcceptedLanguage
import org.bpsbits.kt.rest.prototypes.rest.BasicRequestHandler
import org.bpsbits.kt.rest.utils.CookieUtil
import org.bpsbits.kt.rest.utils.string.parseAcceptedLanguagesISO6391

/**
 * Returns the IP address of the client making the request.
 * @return IP address of the client.
 * @see [BasicRequestHandler]
 */
val BasicRequestHandler.clientIP: String
    get() {
        return this.request?.remoteAddress()?.hostAddress().toString()
    }

/**
 * Returns the user agent information from the request.
 * @return The user agent information.
 * @see [BasicRequestHandler]
 */
val BasicRequestHandler.clientUserAgent: String
    get() {
        return this.headerValue("User-Agent")
    }

/**
 * Retrieves the value of the given cookie.
 * @param name The cookie's name.
 * @param default The value to return if the cookie does not exist.
 * @return The value of the cookie. If no cookie is provided, an empty string is returned instead.
 * @see [BasicRequestHandler]
 */
fun BasicRequestHandler.cookieValue(name: String, default: String = ""): String {
    return request?.getCookie(name)?.value ?: default
}

/**
 * Creates an entirely new cookie by utilising the given name and parameters.
 * It is automatically decided if the cookie should be only transmitted
 * over an encrypted request using the HTTPS protocol.
 * @param name The cookie's name.
 * @param value The value of the cookie.
 * @param httpOnly Determines if JavaScript is prohibited from accessing the value of a cookie.
 * @return The newly created cookie.
 * @see [BasicRequestHandler]
 */
fun BasicRequestHandler.newCookie(name: String, value: String, httpOnly: Boolean = false): NewCookie {
    return CookieUtil.new(name, value, httpOnly, this.isSSL)
}

/**
 * Creates a new expired cookie with the given name.
 * A cookie's expiration date is a signal to the browser that it should remove the cookie.
 * @param name The cookie's name.
 * @param httpOnly Determines if JavaScript is prohibited from accessing the value of a cookie.
 * @return A new expired cookie.
 * @see [BasicRequestHandler]
 */
fun BasicRequestHandler.newExpiredCookie(name: String, httpOnly: Boolean = false): NewCookie {
    return CookieUtil.expired(name, httpOnly, this.isSSL)
}

/**
 * Returns the value for the given header.
 * @param name The header's name.
 * @param default The value to return if the header isn't given
 * @return The value for the given header.If no header is provided, an empty string is returned instead.
 * @see [BasicRequestHandler]
 */
fun BasicRequestHandler.headerValue(name: String, default: String = ""): String {
    return this.request?.getHeader(name) ?: default
}

/**
 * Returns the list of accepted languages (ISO639 1 code) from the request.
 * @return The list of accepted languages.
 * @see [BasicRequestHandler]
 */
val BasicRequestHandler.acceptedISO6391Languages: List<AcceptedLanguage>
    get() {
        return this.request?.getHeader("Accept-Language").toString().parseAcceptedLanguagesISO6391()
    }

/**
 * Returns the ISO639 1 code of the first accepted language from the request.
 * @return ISO639 1 code of first accepted language.
 * @see [BasicRequestHandler]
 */
val BasicRequestHandler.firstAcceptedISO6391Language: String
    get() = this.acceptedISO6391Languages.first().language

/**
 * Returns value of the Tomcat session cookie.
 * @return Value of the Tomcat session cookie or an empty string if not set.
 * @see [BasicRequestHandler]
 */
val BasicRequestHandler.tomcatSessionId: String
    get() = this.cookieValue(Tomcat.SESSION_COOKIE)

/**
 * Returns application information object.
 * @see [AppInfo]
 * @see [QuarkusApp]
 * @see [BasicRequestHandler]
 */
val BasicRequestHandler.appInfo: AppInfo
    get() {
        return AppInfo(
            name = QuarkusApp.name,
            description = QuarkusApp.description,
            version = QuarkusApp.version,
            dev = QuarkusApp.isDev,
            ssl = isSSL
        )
    }