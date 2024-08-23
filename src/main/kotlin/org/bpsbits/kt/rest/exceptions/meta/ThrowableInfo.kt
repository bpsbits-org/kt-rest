package org.bpsbits.kt.rest.exceptions.meta

import io.quarkus.runtime.annotations.RegisterForReflection
import jakarta.ws.rs.WebApplicationException
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.bpsbits.kt.rest.utils.UUIDv7
import java.io.File

/**
 * Structured representation of a [Throwable],
 * which can be serialized and sent back to the client or logged.
 */
@Suppress("unused", "MemberVisibilityCanBePrivate")
@Serializable
@RegisterForReflection
class ThrowableInfo(
    var caseId: String,
    var code: Int,
    var codeUrl: String,
    var details: String,
    var strike: String,
    var timeStamp: Long,
    var title: String,
    var stack: List<StackTraceItem>,
    var type: String,
) {

    /**
     * Clones current instance into a new [ThrowableInfo] instance.
     */
    fun copy(): ThrowableInfo {
        return ThrowableInfo(
            caseId = caseId,
            code = code,
            codeUrl = codeUrl,
            details = details,
            strike = strike,
            timeStamp = timeStamp,
            title = title,
            stack = stack,
            type = type,
        )
    }

    /**
     * Returns a JSON representation of this object.
     */
    fun toJson(hideStackTrace: Boolean = false): String {
        val json = Json { prettyPrint = true }
        if (!hideStackTrace) return json.encodeToString(value = this)
        val newItem = this.copy()
        newItem.stack = emptyList()
        newItem.strike = "See log '$caseId' for more details."
        return json.encodeToString(value = newItem)
    }

    /**
     * Saves the error information into a file in the given directory.
     * @param directoryPath The directory where the file should be saved.
     * @return Returns true if the file was saved successfully.
     */
    fun saveInto(directoryPath: String): Boolean {
        try {
            val incidentsDir = File(directoryPath)
            incidentsDir.mkdirs()
            val file = File(incidentsDir.absolutePath + "/$caseId.json")
            file.writeText(this.toJson())
            return file.exists()
        } catch (e: Exception) {
            return false
        }
    }

    /**
     * Static methods and properties.
     */
    companion object {

        /**
         * Creates a new [ThrowableInfo] instance from the given [Throwable].
         * @return Returns a new [ThrowableInfo] instance.
         */
        fun fromException(err: Throwable): ThrowableInfo {
            val firstSTE: StackTraceElement? =
                if (err.stackTrace.isNotEmpty()) err.stackTrace.first() else null
            var strikeStack: List<StackTraceItem> = emptyList()
            var strikeInfo = ""
            if (firstSTE != null) {
                strikeStack = StackTraceItem.fromTrace(err.stackTrace)
                strikeInfo =
                    "${firstSTE.fileName!!}:${firstSTE.lineNumber} [${firstSTE.className}::${firstSTE.methodName}]"
            }
            val res = ThrowableInfo(
                code = 500,
                details = err.message.toString(),
                strike = strikeInfo,
                timeStamp = System.currentTimeMillis() / 1000,
                title = err.javaClass.simpleName.split(Regex("(?=\\p{Upper})")).joinToString(separator = " ").trim(),
                stack = strikeStack,
                type = err.javaClass.simpleName,
                caseId = UUIDv7.newString(),
                codeUrl = "https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/"
            )
            if (err is WebApplicationException) {
                res.code = err.response.status
                res.title = err.response.statusInfo.toEnum().reasonPhrase
            }
            res.codeUrl += res.code
            return res
        }
    }

}