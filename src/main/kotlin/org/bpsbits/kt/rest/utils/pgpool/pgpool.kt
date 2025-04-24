@file:Suppress("unused")

package org.bpsbits.kt.rest.utils.pgpool

import io.vertx.mutiny.sqlclient.Pool
import io.vertx.mutiny.sqlclient.Tuple
import org.bpsbits.kt.toolbox.utils.string.toPgFunctionQuery
import org.bpsbits.kt.toolbox.utils.string.toPgProcedureQuery
import kotlin.text.ifEmpty

/**
 * Executes a PostgreSQL function and returns the result as a string.
 * @param function The name of the PostgreSQL function you wish to call.
 * @param tuple Optional parameters to pass to the function.
 * @param defaultResult A fallback value to return if the function doesn’t provide a result.
 *
 * @return The result from the database functions as a string, or the default value if no result is available.
 */
fun Pool.functionQueryAsString(
    function: String,
    tuple: Tuple? = null,
    defaultResult: String = "null"
): String {
    val size = tuple?.size() ?: 0
    val query = function.toPgFunctionQuery(size, "::text as res;")
    val res = if (tuple != null) {
        this.preparedQuery(query).execute(tuple).await().indefinitely().first().getString(0)
    } else {
        this.preparedQuery(query).execute().await().indefinitely().first().getString(0)
    }
    return res.ifEmpty { defaultResult }
}

fun Pool.procedureQuery(procedure: String, tuple: Tuple? = null) {
    val size = tuple?.size() ?: 0
    val query = procedure.toPgProcedureQuery(size)
    if (tuple != null) {
        this.preparedQuery(query).execute(tuple).await().indefinitely()
    } else {
        this.preparedQuery(query).execute().await().indefinitely()
    }
}