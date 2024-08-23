package org.bpsbits.kt.rest.utils

import jakarta.ws.rs.core.NewCookie
import java.time.LocalDate
import java.util.*

/**
 * Functions for generating and managing HTTP cookies.
 * Please note that path of cookies is set to "/" always.
 * @see [NewCookie] See [NewCookie](https://docs.oracle.com/javaee/7/api/javax/ws/rs/core/NewCookie.html) for more information.
 */
@Suppress("unused")
class CookieUtil {

    /**
     * Static methods and properties.
     */
    companion object {

        /**
         * Generates an expired cookie using the provided name and parameters.
         * A cookie's expiration date is a signal to the browser that it should remove the cookie.
         * @param name The cookie's name.
         * @param httpOnly Determines if JavaScript is prohibited from accessing the value of a cookie.
         * @param secure If `true`, the cookie is exclusively transmitted  over an encrypted request using the HTTPS protocol.
         * @see [NewCookie] See [NewCookie](https://docs.oracle.com/javaee/7/api/javax/ws/rs/core/NewCookie.html) for more information.
         */
        fun expired(name: String, httpOnly: Boolean = false, secure: Boolean = false): NewCookie {
            return NewCookie.Builder(name).value("expired").path("/")
                .httpOnly(httpOnly).secure(secure)
                .expiry(Date(LocalDate.parse("1970-01-01").toEpochDay())).build()
        }

        /**
         * Creates an entirely new cookie by utilizing the given name and parameters.
         * @param name The cookie's name.
         * @param value The value of the cookie.
         * @param httpOnly Determines if JavaScript is prohibited from accessing the value of a cookie.
         * @param secure If `true`, the cookie is exclusively transmitted  over an encrypted request using the HTTPS protocol.
         * @see [NewCookie] See [NewCookie](https://docs.oracle.com/javaee/7/api/javax/ws/rs/core/NewCookie.html) for more information.
         */
        fun new(name: String, value: String, httpOnly: Boolean = false, secure: Boolean = false): NewCookie {
            return NewCookie.Builder(name).value(value).path("/").httpOnly(httpOnly).secure(secure).build()
        }

    }
}