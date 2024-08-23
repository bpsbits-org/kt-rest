@file:Suppress("unused")

package org.bpsbits.kt.rest.utils.string

import org.bpsbits.kt.rest.commons.Tomcat
import org.bpsbits.kt.rest.i18n.AcceptedLanguage
import org.bpsbits.kt.rest.i18n.ISO6391Code
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

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
 * Checks if the current string matches the ISO data format (yyyy-mm-dd).
 * Doesn't check if the data is a valid date.
 */
val String.matchesISODateFormat
    get():Boolean {
        return Regex("""^\d{4}-(0[1-9]|1[0-2])-(0[1-9]|1[0-9]|2[0-9]|3[0-1])${'$'}""").matches(this)
    }

/**
 * Checks if the current string matches the ISO data format and is also a valid date.
 */
fun String.isValidISODate(): Boolean {
    if (!this.matchesISODateFormat) return false
    return try {
        LocalDate.parse(this)
        true
    } catch (e: DateTimeParseException) {
        false
    }
}

/**
 * Validates if the current string is valid ISO date.
 * @throws [IllegalArgumentException] if the string is not a valid ISO date.
 */
fun String.validateISODate() {
    require(this.matchesISODateFormat) {
        "Given string doesn't match the required date format! (Example: 2012-01-01)."
    }
    require(this.isValidISODate()) {
        "Given string `$this` is not a valid date!"
    }
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

/**
 * If string contains valid ISO date, then returns a [LocalDate].
 * @see [LocalDate]
 */
fun String.parseIsoDate(): LocalDate? {
    if (!this.matchesISODateFormat) {
        return null
    }
    val formatter = DateTimeFormatter.ISO_DATE
    return LocalDate.parse(this, formatter)
}

/**
 * If sting contains uppercase letters, wraps it with double quotes.
 * This is useful when the string is used as name of an SQL column, table, or function.
 * It also strips all existing double quotes.
 */
fun String.doubleQuoteIfContainsUppercase(): String {
    var str: String = this.replace("\"", "")
    if (str.any { it.isUpperCase() }) {
        str = "\"$str\""
    }
    return str
}

/**
 * Sanitizes the given string, so it is safer to use in an SQL query as function name.
 * For example, converts `api.getUserNames` into `api."getUserNames"`.
 */
fun String.sanitizePgFunctionName(): String {
    val str: String = this.replace(
        Regex("[^a-zA-Z0-9_.]"),
        ""
    ).replace(Regex("\\.+"), ".")
    var (schema, fnName) = if (str.contains(".")) str.split('.').take(2) + "" else listOf("", str)
    schema = schema.doubleQuoteIfContainsUppercase()
    fnName = fnName.doubleQuoteIfContainsUppercase()
    return if (schema.isNotEmpty()) "$schema.$fnName" else fnName
}

/**
 * Converts the given string into a valid PostgreSQL function query with placeholders for parameters.
 * For example, the string `api.getUserNames` could be converted to `select api."getUserNames"($1);`.
 * @param argsCount Count of arguments to be used in the query.
 * @param suffix String to be added to the end of the query. You can use this to add conversion of a function result to a specific data type.
 * @sample org.bpsbits.kt.rest.tests.TestStringUtils.testToPgFunctionQuery
 */
fun String.intoPgFunctionQuery(argsCount: Int = 0, suffix: String = ""): String {
    val fnName = this.sanitizePgFunctionName()
    val varPlaceHolders = (1..argsCount).joinToString { "\$$it" }
    val sanSuffix = suffix.trim()
    return "select $fnName($varPlaceHolders)$sanSuffix;"
}

/**
 * Converts the given string into a valid PostgreSQL procedure call query with placeholders for parameters.
 * For example, the string `api.markFileExpired` could be converted to `call api."markFileExpired"($1);`.
 * @param argsCount Count of arguments to be used in the query.
 * @sample org.bpsbits.kt.rest.tests.TestStringUtils.testToPgProcedureQuery
 */
fun String.intoPgProcedureQuery(argsCount: Int = 0): String {
    val fnName = this.sanitizePgFunctionName()
    val varPlaceHolders = (1..argsCount).joinToString { "\$$it" }
    return "call $fnName($varPlaceHolders);"
}