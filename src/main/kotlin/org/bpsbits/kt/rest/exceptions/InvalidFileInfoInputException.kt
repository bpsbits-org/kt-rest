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
class InvalidFileInfoInputException(
    message: String = "Given invalid data prevents file request fulfilment."
) :
    WebApplicationException(message, Response.Status.BAD_REQUEST)