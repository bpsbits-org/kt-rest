package org.bpsbits.kt.rest.tests

import org.bpsbits.kt.rest.utils.string.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TestStringUtils {

    @Test
    fun testMatchesTomcatCookie() {
        Assertions.assertTrue("JSESSIONID".matchesTomcatCookie)
        Assertions.assertFalse("BLA".matchesTomcatCookie)
    }

    @Test
    fun testParseAcceptedLanguages() {
        val langList = " en;q=0.8, fi-FI;q=0, ".parseAcceptedLanguages()
        Assertions.assertTrue(langList.size == 2)
        Assertions.assertTrue(langList[0].language == "en")
        Assertions.assertTrue(langList[0].q == 0.8f)
        Assertions.assertTrue(langList[1].language == "fi-FI")
        Assertions.assertTrue(langList[1].q == 0f)
    }

    @Test
    fun testParseAcceptedLanguagesISO() {
        val langList = " en;q=0.8, fi-FI;q=0, ".parseAcceptedLanguagesISO6391()
        Assertions.assertTrue(langList.size == 2)
        Assertions.assertTrue(langList[0].language == "en")
        Assertions.assertTrue(langList[0].q == 0.8f)
        Assertions.assertTrue(langList[1].language == "fi")
        Assertions.assertTrue(langList[1].q == 0f)
    }

    @Test
    fun testISODateFormat() {
        Assertions.assertTrue("2012-01-01".matchesISODateFormat)
        Assertions.assertTrue("2012-12-31".matchesISODateFormat)
        Assertions.assertFalse("2012-00-00".matchesISODateFormat)
        Assertions.assertFalse("2012-01-00".matchesISODateFormat)
        Assertions.assertFalse("2012-13-32".matchesISODateFormat)
        Assertions.assertTrue("0000-01-01".matchesISODateFormat)
    }

    @Test
    fun testIsValidISODate() {
        Assertions.assertTrue("2012-01-01".isValidISODate())
        Assertions.assertFalse("2012-02-31".isValidISODate())
        Assertions.assertFalse("2012-04-31".isValidISODate())
        Assertions.assertFalse("2023-02-29".isValidISODate())
        Assertions.assertTrue("2024-02-29".isValidISODate())
        Assertions.assertTrue("2023-12-31".isValidISODate())
        Assertions.assertTrue("2023-03-01".isValidISODate())
    }

    @Test
    fun testValidateISODate() {
        try {
            "2023-02-29".validateISODate()
        } catch (e: IllegalArgumentException) {
            println(e.message)
            Assertions.assertTrue(e.javaClass.simpleName == "IllegalArgumentException")
        }
        try {
            require(!"2023-03-01".isValidISODate()) {
                "2023-03-01 passed validation"
            }
        } catch (e: IllegalArgumentException) {
            println(e.message)
            Assertions.assertTrue(e.javaClass.simpleName == "IllegalArgumentException")
        }
    }

    @Test
    fun testToPgFunctionQuery() {
        val query = "api.getUserNames".intoPgFunctionQuery(2, "::text")
        Assertions.assertTrue(query == "select api.\"getUserNames\"($1, $2)::text;")
    }

    @Test
    fun testToPgProcedureQuery() {
        val query = "api.markFileAs".intoPgProcedureQuery(2)
        Assertions.assertTrue(query == "call api.\"markFileAs\"($1, $2);")
    }

}