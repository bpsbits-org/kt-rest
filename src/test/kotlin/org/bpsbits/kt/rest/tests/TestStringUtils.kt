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

}