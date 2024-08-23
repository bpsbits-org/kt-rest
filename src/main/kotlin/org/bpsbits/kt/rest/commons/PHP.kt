package org.bpsbits.kt.rest.commons

/**
 * Here are some specific PHP constants that may be required for constructing
 * PHP-related REST API services or REST API clients.
 */
class PHP {

    /**
     * Static methods and properties.
     */
    companion object {
        /**
         * Default identifier that PHP uses for session cookies.
         * Please note that some servers might be configured to use a different cookie name.
         */
        const val SESSION_COOKIE: String = "PHPSESSID"
    }

}