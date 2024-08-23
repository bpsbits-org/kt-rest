package org.bpsbits.kt.rest.commons

/**
 * Tomcat related additions.
 */
class Tomcat {

    /**
     * Static methods and properties.
     */
    companion object {
        /**
         * Default identifier that Tomcat uses for session cookies.
         * Please note that some servers might be configured to use a different cookie name.
         */
        const val SESSION_COOKIE: String = "JSESSIONID"
    }

}