package org.bpsbits.kt.rest.utils

import com.fasterxml.uuid.Generators
import java.util.*

/**
 * Wrapper for fasterxml [com.fasterxml.uuid.Generators.timeBasedEpochGenerator].
 */
@Suppress("unused", "MemberVisibilityCanBePrivate")
class UUIDv7 {

    /**
     * Static methods and properties.
     */
    companion object {

        /**
         * Generates a new v7 UUID.
         * @return a new v7 [UUID].
         */
        fun new(): UUID {
            return Generators.timeBasedEpochGenerator().generate()
        }

        /**
         * Generates a new v7 UUID string.
         * @return a new v7 string.
         */
        fun newString(): String {
            return new().toString()
        }

    }

}