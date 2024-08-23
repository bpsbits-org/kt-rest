package org.bpsbits.kt.rest.exceptions

import jakarta.ws.rs.WebApplicationException
import jakarta.ws.rs.core.Response

/**
 * 400 - No cookie found.
 *
 * Client failed to provide the necessary cookie to the server.
 *
 * Helps to specify more accurately the cause of problem instead of using generic [WebApplicationException].
 */
@Suppress("unused")
class InvalidCookieException(
    message: String = "The server did not get the required cookie from the client."
) :
    WebApplicationException(message, Response.Status.BAD_REQUEST)