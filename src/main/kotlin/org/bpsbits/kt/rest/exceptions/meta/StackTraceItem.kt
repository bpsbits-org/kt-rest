package org.bpsbits.kt.rest.exceptions.meta

import io.quarkus.runtime.annotations.RegisterForReflection
import kotlinx.serialization.Serializable

/**
 * Structured representation of [StackTraceElement] for serialization.
 */
@Suppress("unused")
@Serializable
@RegisterForReflection
data class StackTraceItem(
    val className: String = "",
    val fileName: String? = "",
    val lineNumber: Int = 0,
    val methodName: String = "",
    val nativeMethod: Boolean = false
) {

    /**
     * Static methods and properties.
     */
    companion object {

        /**
         * Creates a new [StackTraceItem] from a [StackTraceElement]
         * @param element the [StackTraceElement]
         */
        @Suppress("MemberVisibilityCanBePrivate")
        fun fromElement(element: StackTraceElement): StackTraceItem {
            return StackTraceItem(
                className = element.className,
                fileName = element.fileName,
                lineNumber = element.lineNumber,
                methodName = element.methodName,
                nativeMethod = element.isNativeMethod
            )
        }

        /**
         * Creates a new array of [StackTraceItem]s from a [List] of [StackTraceElement]s
         * @param trace the [List] of [StackTraceElement]s
         */
        fun fromTrace(trace: Array<StackTraceElement>): List<StackTraceItem> {
            val mutableList = mutableListOf<StackTraceItem>()
            trace.forEach { elem ->
                mutableList.add(fromElement(elem))
            }
            return mutableList
        }

    }
}
