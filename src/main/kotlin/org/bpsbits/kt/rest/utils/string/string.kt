package org.bpsbits.kt.rest.utils.string

import org.bpsbits.kt.rest.commons.Tomcat
import org.bpsbits.kt.rest.i18n.AcceptedLanguage
import org.bpsbits.kt.rest.i18n.ISO6391Code

/**
 * Verifies that the current string matches the name of the standard Tomcat session cookie.
 * @see [Tomcat]
 * @see [String]
 */
val String.matchesTomcatCookie
    get():Boolean {
        return this == Tomcat.SESSION_COOKIE
    }

/**
 * If a string is Accept-Language header, parses it and returns a list of accepted languages.
 *
 * Please note that this method does not validate the language codes.
 * Please note that language code could be, for example, en or en-US.
 * The prefix 'en' is an ISO 639-1 language code. The 'US' suffix is a country
 * code that follows the ISO 3166-1 Alpha-2 standard.
 * @see [parseAcceptedLanguagesISO6391]
 * @see [AcceptedLanguage]
 * @see [String]
 */
fun String.parseAcceptedLanguages(): List<AcceptedLanguage> {
    try {
        val rawLangList: List<String> = this.split(",")
            .map { it.trim() }.map { it.trim() }.filter { it.isNotEmpty() }
        if (rawLangList.isEmpty()) return listOf(AcceptedLanguage())
        val rankedLangList = rawLangList.map { lang ->
            val parts = lang.split(";q=")
            when (parts.size) {
                2 -> AcceptedLanguage(parts[0], parts[1].toFloat())
                else -> AcceptedLanguage(parts[0], 1.0f)
            }
        }.sortedByDescending { it.q }
        return rankedLangList
    } catch (e: Exception) {
        return listOf(AcceptedLanguage())
    }
}

/**
 * If a string is Accept-Language header, parses it and returns a list of accepted languages
 * where language codes are ISO 639-1 codes and possible country codes are removed.
 * @see [parseAcceptedLanguages]
 * @see [AcceptedLanguage]
 * @see [String]
 */
fun String.parseAcceptedLanguagesISO6391(): List<AcceptedLanguage> {
    val list = this.parseAcceptedLanguages()
    val newList: MutableList<AcceptedLanguage> = mutableListOf()
    if (list.isEmpty()) return listOf(AcceptedLanguage())
    for (lang in list) {
        val new = AcceptedLanguage(ISO6391Code.resolve(lang.language).code, lang.q)
        newList.add(new)
    }
    return newList
}
