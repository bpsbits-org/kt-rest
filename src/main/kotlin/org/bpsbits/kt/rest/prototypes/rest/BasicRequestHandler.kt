package org.bpsbits.kt.rest.prototypes.rest

import io.quarkus.runtime.annotations.RegisterForReflection
import io.vertx.core.http.HttpServerRequest
import io.vertx.mutiny.sqlclient.Pool
import io.vertx.mutiny.sqlclient.Tuple
import jakarta.ws.rs.core.Response
import org.bpsbits.kt.rest.commons.QuarkusApp
import org.bpsbits.kt.rest.i18n.ISO6391Code
import org.bpsbits.kt.rest.utils.brh.acceptedISO6391Languages
import org.bpsbits.kt.rest.utils.brh.cookieValue
import org.bpsbits.kt.rest.utils.brh.headerValue
import org.bpsbits.kt.rest.utils.pgpool.functionQueryAsString
import org.bpsbits.kt.rest.utils.pgpool.procedureQuery

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
     * Behavior depends on `app.lang.*` configuration properties.
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
     * Builds a response with the provided entity.
     * @param entity the entity to be included in the response.
     */
    fun buildResponse(entity: Any): Response {
        return Response.ok().entity(entity).build()
    }

    /**
     * Calls a PostgreSQL function and returns the result as a [Response].
     *
     * @param pgPool The database connection used to execute the function.
     * @param function The name of the PostgreSQL function to call.
     * @param tuple Optional input values for the function.
     * @param defaultResult A fallback value to return if the PostgreSQL function does not give any result.
     *
     * @return A [Response] that contains the result of the function call.
     */
    fun pgFunctionResponse(
        pgPool: Pool,
        function: String,
        tuple: Tuple? = null,
        defaultResult: String = "[]"
    ): Response {
        val fnResult = pgPool.functionQueryAsString(function, tuple, defaultResult)
        return buildResponse(fnResult)
    }

    /**
     * Executes a PostgreSQL procedure and returns a response indicating success.
     *
     * @param pgPool The database connection pool used to call the procedure.
     * @param procedure The name of the PostgreSQL procedure to execute.
     * @param tuple Optional parameters to pass to the procedure.
     *
     * @return A response object confirming the successful execution of the procedure.
     */
    fun pgProcedureResponse(pgPool: Pool, procedure: String, tuple: Tuple? = null): Response {
        pgPool.procedureQuery(procedure, tuple)
        return buildResponse(true)
    }

}