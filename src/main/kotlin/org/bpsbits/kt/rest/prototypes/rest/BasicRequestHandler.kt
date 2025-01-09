package org.bpsbits.kt.rest.prototypes.rest

import io.quarkus.runtime.annotations.RegisterForReflection
import io.vertx.core.http.HttpServerRequest
import io.vertx.mutiny.pgclient.PgPool
import io.vertx.mutiny.sqlclient.Tuple
import jakarta.ws.rs.core.Response
import org.bpsbits.kt.rest.commons.QuarkusApp
import org.bpsbits.kt.rest.i18n.ISO6391Code
import org.bpsbits.kt.rest.utils.brh.acceptedISO6391Languages
import org.bpsbits.kt.rest.utils.brh.cookieValue
import org.bpsbits.kt.rest.utils.brh.headerValue
import org.bpsbits.kt.rest.utils.pgpool.functionQueryAsString

/**
 * Provides some basic functionality for handling HTTP requests.
 *
 * Use it with [org.bpsbits.kt.rest.utils.brh] extensions to expand functionality.
 */
@Suppress("unused")
@RegisterForReflection
interface BasicRequestHandler {

    /**
     * Override this property in extended classes.
     */
    var request: HttpServerRequest?

    /**
     * Determines whether the connection is secure or not.
     */
    val isSSL: Boolean
        get() {
            return request?.isSSL ?: false
        }

    /**
     * Tries to determine the language of the request.
     *
     * Behaviour depends on `app.lang.*` configuration properties.
     */
    val detectedLanguage: ISO6391Code
        get() {
            val detectQuery = QuarkusApp.langDetectQuery
            if (detectQuery.isNotEmpty()) {
                val langFromQuery: String = request?.getParam(detectQuery) ?: ""
                if (QuarkusApp.isSupportedLang(langFromQuery)) {
                    return ISO6391Code.resolve(langFromQuery)
                }
            }
            val detectCookie = QuarkusApp.langDetectCookie
            if (detectCookie.isNotEmpty()) {
                val langFromCookie: String = cookieValue(detectCookie)
                if (QuarkusApp.isSupportedLang(langFromCookie)) {
                    return ISO6391Code.resolve(langFromCookie)
                }
            }
            val detectHeader = QuarkusApp.langDetectHeader
            if (detectHeader.isNotEmpty()) {
                val langFromHeader: String = headerValue(detectHeader)
                if (QuarkusApp.isSupportedLang(langFromHeader)) {
                    return ISO6391Code.resolve(langFromHeader)
                }
            }
            val acceptedLangFromHeader = acceptedISO6391Languages
            if (acceptedLangFromHeader.isNotEmpty() && QuarkusApp.isSupportedLang(acceptedLangFromHeader.first().language)) {
                return ISO6391Code.resolve(acceptedLangFromHeader.first().language)
            }
            return ISO6391Code.resolve(QuarkusApp.primaryLang)
        }

    /**
     * Retrieves the identity token from the request.
     */
    val identityToken: String
        get() {
            val tokenName = QuarkusApp.identityTokenName
            return listOf(cookieValue(tokenName), headerValue(tokenName))
                .firstOrNull { it.isNotBlank() } ?: ""
        }

    /**
     * Builds a response with the provided entity.
     * @param entity the entity to be included in the response.
     */
    fun buildResponse(entity: Any): Response {
        return Response.ok().entity(entity).build()
    }

    /**
     * Executes a PostgreSQL function and returns the result as a [Response].
     * @param pgPool the PostgreSQL pool to use.
     * @param function the name of the function to execute.
     * @param tuple the optional parameters for the function.
     * @param defaultResult the default value to return if the function returns null
     */
    fun pgFunctionResponse(
        pgPool: PgPool,
        function: String,
        tuple: Tuple?,
        defaultResult: String = "[]"
    ): Response {
        val fnResult = pgPool.functionQueryAsString(function, tuple, defaultResult)
        return buildResponse(fnResult)
    }

}