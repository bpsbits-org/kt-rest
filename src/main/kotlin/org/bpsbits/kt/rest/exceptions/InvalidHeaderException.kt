package org.bpsbits.kt.rest.exceptions

import jakarta.ws.rs.WebApplicationException
import jakarta.ws.rs.core.Response

/**
 * 400 - Header not found.
 *
 * Client failed to provide the necessary header to the server.
 *
 * Helps to specify more accurately the cause of problem instead of using generic [WebApplicationException].
 */
@Suppress("unused")
class InvalidHeaderException(
    message: String = " Client failed to provide the necessary header to the server."
) :
    WebApplicationException(message, Response.Status.BAD_REQUEST)