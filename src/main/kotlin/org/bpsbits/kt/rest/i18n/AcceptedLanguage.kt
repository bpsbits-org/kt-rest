package org.bpsbits.kt.rest.i18n

import kotlinx.serialization.Serializable

/**
 * Object that represents an HTTP Accept-Language header element.
 * @property language Language code.
 * @property q Q-factor weighting. Determines the order of priority.
 * @see [org.bpsbits.kt.rest.utils.string.parseAcceptedLanguages]
 * @see mozilla.org <a href="https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Accept-Language" targer="_blank">Accept-Language</a>
 */
@Serializable
data class AcceptedLanguage(
    val language: String = "*",
    val q: Float = 1.0f
) {
    override fun toString(): String = "$language;q=$q"
}
