package org.bpsbits.kt.rest.exceptions

import jakarta.ws.rs.WebApplicationException
import jakarta.ws.rs.core.Response

/**
 * 403 - Forbidden.
 *
 * Lack of required rights to access or interact with the given resource.
 *
 * Helps to specify more accurately the cause of problem instead of using generic [WebApplicationException].
 */
@Suppress("unused")
class ForbiddenException(
    message: String = "Lack of required rights to access or interact with the given resource."
) :
    WebApplicationException(message, Response.Status.FORBIDDEN)