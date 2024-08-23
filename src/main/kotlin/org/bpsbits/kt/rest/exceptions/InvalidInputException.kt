package org.bpsbits.kt.rest.exceptions

import jakarta.ws.rs.WebApplicationException
import jakarta.ws.rs.core.Response

/**
 * 400 - Invalid input.
 *
 * The request cannot be fulfilled due to the given invalid data.
 *
 * Helps to specify more accurately the cause of problem instead of using generic [WebApplicationException].
 */
@Suppress("unused")
class InvalidInputException(
    message: String = "The request cannot be fulfilled due to the given invalid data."
) :
    WebApplicationException(message, Response.Status.BAD_REQUEST)