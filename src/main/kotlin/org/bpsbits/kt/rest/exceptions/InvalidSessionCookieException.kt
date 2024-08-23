package org.bpsbits.kt.rest.exceptions

import jakarta.ws.rs.WebApplicationException
import jakarta.ws.rs.core.Response

/**
 * 401 - No session cookie found.
 *
 * Client failed to provide the necessary session cookie to the server.
 *
 * Helps to specify more accurately the cause of problem instead of using generic [WebApplicationException].
 */
@Suppress("unused")
class InvalidSessionCookieException(
    message: String = "No session cookie was sent to the server."
) :
    WebApplicationException(message, Response.Status.UNAUTHORIZED)