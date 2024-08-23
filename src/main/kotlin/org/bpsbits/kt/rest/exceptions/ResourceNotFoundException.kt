package org.bpsbits.kt.rest.exceptions

import jakarta.ws.rs.WebApplicationException
import jakarta.ws.rs.core.Response

/**
 * 404 - Not found.
 *
 * The server cannot find the requested resource.
 *
 * Helps to specify more accurately the cause of problem instead of using generic [WebApplicationException].
 */
@Suppress("unused")
class ResourceNotFoundException(message: String = "The requested resource was not found.") :
    WebApplicationException(message, Response.Status.NOT_FOUND)