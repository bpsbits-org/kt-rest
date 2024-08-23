package org.bpsbits.kt.rest.exceptions

import jakarta.ws.rs.WebApplicationException
import jakarta.ws.rs.core.Response

/**
 * 401 - Unauthorized.
 *
 * The server failed to identify the user.
 *
 * Helps to specify more accurately the cause of problem instead of using generic [WebApplicationException].
 */
@Suppress("unused")
class UnauthorizedException(
    message: String = "The server failed to identify the user."
) :
    WebApplicationException(message, Response.Status.UNAUTHORIZED)