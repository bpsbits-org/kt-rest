package org.bpsbits.kt.rest.commons

import org.bpsbits.kt.rest.i18n.ISO6391Code
import org.eclipse.microprofile.config.ConfigProvider

/**
 * Miscellaneous Quarkus application utility functions.
 */
class QuarkusApp {

    /**
     * Static methods and properties.
     */
    @Suppress("MemberVisibilityCanBePrivate", "unused")
    companion object {

        const val CONF_DESC: String = "app.description"
        const val CONF_LOG_INC_DIR: String = "app.log.incident.dir"
        const val CONF_NAME: String = "app.name"
        const val CONF_TH_HIDE_STACK: String = "app.mappers.exceptions.throwable.stack.hide"
        const val CONF_VERS: String = "quarkus.application.version"
        const val CONF_LANG_SUPPORTED: String = "app.lang.supported"
        const val CONF_LANG_DET_HEADER: String = "app.lang.detect.header"
        const val CONF_LANG_DET_COOKIE: String = "app.lang.detect.cookie"
        const val CONF_LANG_DET_QUERY: String = "app.lang.detect.query"
        const val CONF_UPLOADS_DIR: String = "app.uploads.dir"
        const val CONF_IDENTITY_TOKEN: String = "app.identity.token.name"
        const val DEFAULT_IDENTITY_TOKEN: String = "X-API-Identity-Token"

        /**
         * Name of the application.
         *
         * Value is read from the `app.name` configuration property. You have to set this property.
         */
        val name: String
            get() {
                return try {
                    ConfigProvider.getConfig().getValue(CONF_NAME, String::class.java)
                } catch (e: Exception) {
                    ""
                }
            }

        /**
         * Short description of the application.
         *
         * Value is read from the `app.description` configuration property. You have to set this property.
         */
        val description: String
            get() {
                return try {
                    ConfigProvider.getConfig().getValue(CONF_DESC, String::class.java)
                } catch (e: Exception) {
                    ""
                }
            }

        /**
         * Returns the version of the application.
         */
        val version: String
            get() {
                return try {
                    ConfigProvider.getConfig().getValue(CONF_VERS, String::class.java)
                } catch (e: Exception) {
                    ""
                }
            }

        /**
         * Returns `true` if the application is running in development mode.
         */
        val isDev: Boolean
            get() {
                return io.quarkus.runtime.LaunchMode.current().isDevOrTest
            }

        /**
         * Determines whether to hide the stack trace information in the exception message.
         *
         * If Quarkus runs in development mode, this value is always `false`.
         * Value is read from the `app.mappers.exceptions.throwable.stack.hide` configuration property.
         */
        val hideThrowableStackTraces: Boolean
            get() {
                return try {
                    !isDev && ConfigProvider.getConfig().getValue(CONF_TH_HIDE_STACK, Boolean::class.java)
                } catch (e: Exception) {
                    true
                }
            }

        /**
         * Returns the path of directory where incident files are stored.
         */
        val incidentLogsDirPath: String
            get() {
                return try {
                    val path = ConfigProvider.getConfig().getValue(CONF_LOG_INC_DIR, String::class.java).trim()
                    if (isDev && !path.startsWith("/")) {
                        return System.getProperty("user.dir") + "/" + path
                    }
                    return path
                } catch (e: Exception) {
                    ""
                }
            }

        /**
         * Returns the path of directory where uploads are stored.
         */
        val uploadsDirPath: String
            get() {
                return try {
                    val path = ConfigProvider.getConfig().getValue(CONF_UPLOADS_DIR, String::class.java).trim()
                    if (isDev && !path.startsWith("/")) {
                        return System.getProperty("user.dir") + "/" + path
                    }
                    return path
                } catch (e: Exception) {
                    ""
                }
            }

        /**
         * Returns the list of supported languages.
         */
        val supportedLanguages: Array<String>
            get() {
                return try {
                    val confSupportedLang = ConfigProvider.getConfig()
                        .getValue(CONF_LANG_SUPPORTED, String::class.java).trim()
                    var langArray = confSupportedLang.split(",").filterNot { it.isEmpty() }.distinct()
                        .filter { it in ISO6391Code.codeArray }
                        .toTypedArray()
                    if (langArray.isEmpty()) {
                        langArray = arrayOf(ISO6391Code.ENGLISH.code)
                    }
                    return langArray
                } catch (e: Exception) {
                    arrayOf(ISO6391Code.ENGLISH.code)
                }
            }

        /**
         * Returns the primary language.
         */
        val primaryLang: String
            get() {
                return supportedLanguages.firstOrNull() ?: ISO6391Code.ENGLISH.code
            }

        /**
         * Determines whether the application supports the given language.
         */
        fun isSupportedLang(lang: String): Boolean {
            return supportedLanguages.contains(lang.trim().lowercase())
        }

        /**
         * Returns the name of the HTTP Header, that passes the language code to the application.
         */
        val langDetectHeader: String
            get() {
                return try {
                    ConfigProvider.getConfig().getValue(CONF_LANG_DET_HEADER, String::class.java)
                } catch (e: Exception) {
                    ""
                }
            }

        /**
         * Returns the name of the cookie, that passes the language code to the application.
         */
        val langDetectCookie: String
            get() {
                return try {
                    ConfigProvider.getConfig().getValue(CONF_LANG_DET_COOKIE, String::class.java)
                } catch (e: Exception) {
                    ""
                }
            }

        /**
         * Returns the name of the query variable, that passes the language code to the application.
         */
        val langDetectQuery: String
            get() {
                return try {
                    ConfigProvider.getConfig().getValue(CONF_LANG_DET_QUERY, String::class.java)
                } catch (e: Exception) {
                    ""
                }
            }

        /**
         * Name of the identity token.
         */
        val identityTokenName: String
            get() {
                return try {
                    ConfigProvider.getConfig()
                        .getValue(CONF_IDENTITY_TOKEN, String::class.java)
                        .takeIf { it.isNotBlank() } ?: DEFAULT_IDENTITY_TOKEN
                } catch (e: Exception) {
                    DEFAULT_IDENTITY_TOKEN
                }
            }

    }
}