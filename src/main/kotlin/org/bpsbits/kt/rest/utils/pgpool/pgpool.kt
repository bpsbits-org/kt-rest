@file:Suppress("unused")

package org.bpsbits.kt.rest.utils.pgpool

import io.vertx.mutiny.pgclient.PgPool
import io.vertx.mutiny.sqlclient.Tuple
import org.bpsbits.kt.rest.utils.string.intoPgFunctionQuery
import kotlin.text.ifEmpty

/**
 * Executes a PostgreSQL function and returns the result as a string.
 * @param function Name of the PostgreSQL function.
 * @param tuple Optional parameters for the function.
 * @param defaultResult Default result to be returned if the function returns null.
 */
fun PgPool.functionQueryAsString(
    function: String,
    tuple: Tuple?,
    defaultResult: String = "[]"
): String {
    val size = tuple?.size() ?: 0
    val query = function.intoPgFunctionQuery(size, "::text as res;")
    var res = if (tuple != null) {
        this.preparedQuery(query).execute(tuple).await().indefinitely().first().getString(0)
    } else {
        this.preparedQuery(query).execute().await().indefinitely().first().getString(0)
    }
    return res.ifEmpty { defaultResult }
}