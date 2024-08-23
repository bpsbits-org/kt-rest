package org.bpsbits.kt.rest.i18n

import org.bpsbits.kt.rest.data.obj.ISO6391CodeOutput
import org.bpsbits.kt.rest.i18n.ISO6391Code.ANY

/**
 * List of ISO 639-1 language codes.
 *
 * Includes [ANY] to support HTTP Accept-Language header.
 */
@Suppress("unused", "SpellCheckingInspection", "MemberVisibilityCanBePrivate")
enum class ISO6391Code(val language: String, val code: String) {
    ABKHAZIAN("Abkhazian", "ab"),
    AFAR("Afar", "aa"),
    AFRIKAANS("Afrikaans", "af"),
    AKAN("Akan", "ak"),
    ALBANIAN("Albanian", "sq"),
    AMHARIC("Amharic", "am"),

    /**
     * Unknown language. Provides compatibility with Accept-Language header.
     */
    ANY("*", "*"),
    ARABIC("Arabic", "ar"),
    ARAGONESE("Aragonese", "an"),
    ARMENIAN("Armenian", "hy"),
    ASSAMESE("Assamese", "as"),
    AVARIC("Avaric", "av"),
    AVESTAN("Avestan", "ae"),
    AYMARA("Aymara", "ay"),
    AZERBAIJANI("Azerbaijani", "az"),
    BAMBARA("Bambara", "bm"),
    BASHKIR("Bashkir", "ba"),
    BASQUE("Basque", "eu"),
    BELARUSIAN("Belarusian", "be"),
    BENGALI("Bengali", "bn"),
    BISLAMA("Bislama", "bi"),
    BOSNIAN("Bosnian", "bs"),
    BRETON("Breton", "br"),
    BULGARIAN("Bulgarian", "bg"),
    BURMESE("Burmese", "my"),
    CATALAN("Catalan, Valencian", "ca"),
    CENTRAL_KHMER("Central Khmer", "km"),
    CHAMORRO("Chamorro", "ch"),
    CHECHEN("Chechen", "ce"),
    CHICHEWA("Chichewa, Chewa, Nyanja", "ny"),
    CHINESE("Chinese", "zh"),
    CHURCH_SLAVONIC("Church Slavonic, Old Slavonic, Old Church Slavonic", "cu"),
    CHUVASH("Chuvash", "cv"),
    CORNISH("Cornish", "kw"),
    CORSICAN("Corsican", "co"),
    CREE("Cree", "cr"),
    CROATIAN("Croatian", "hr"),
    CZECH("Czech", "cs"),
    DANISH("Danish", "da"),
    DIVEHI("Divehi, Dhivehi, Maldivian", "dv"),
    DUTCH("Dutch, Flemish", "nl"),
    DZONGKHA("Dzongkha", "dz"),
    ENGLISH("English", "en"),
    ESPERANTO("Esperanto", "eo"),
    ESTONIAN("Estonian", "et"),
    EWE("Ewe", "ee"),
    FAROESE("Faroese", "fo"),
    FIJIAN("Fijian", "fj"),
    FINNISH("Finnish", "fi"),
    FRENCH("French", "fr"),
    FULAH("Fulah", "ff"),
    GAELIC("Gaelic, Scottish Gaelic", "gd"),
    GALICIAN("Galician", "gl"),
    GANDA("Ganda", "lg"),
    GEORGIAN("Georgian", "ka"),
    GERMAN("German", "de"),
    GREEK("Greek, Modern (1453–)", "el"),
    GUARANI("Guarani", "gn"),
    GUJARATI("Gujarati", "gu"),
    HAITIAN("Haitian, Haitian Creole", "ht"),
    HAUSA("Hausa", "ha"),
    HEBREW("Hebrew", "he"),
    HERERO("Herero", "hz"),
    HINDI("Hindi", "hi"),
    HIRI_MOTU("Hiri Motu", "ho"),
    HUNGARIAN("Hungarian", "hu"),
    ICELANDIC("Icelandic", "is"),
    IDO("Ido", "io"),
    IGBO("Igbo", "ig"),
    INDONESIAN("Indonesian", "id"),
    INTERLINGUA("Interlingua (International Auxiliary Language Association)", "ia"),
    INTERLINGUE("Interlingue, Occidental", "ie"),
    INUKTITUT("Inuktitut", "iu"),
    INUPIAQ("Inupiaq", "ik"),
    IRISH("Irish", "ga"),
    ITALIAN("Italian", "it"),
    JAPANESE("Japanese", "ja"),
    JAVANESE("Javanese", "jv"),
    KALAALLISUT("Kalaallisut, Greenlandic", "kl"),
    KANNADA("Kannada", "kn"),
    KANURI("Kanuri", "kr"),
    KASHMIRI("Kashmiri", "ks"),
    KAZAKH("Kazakh", "kk"),
    KIKUYU("Kikuyu, Gikuyu", "ki"),
    KINYARWANDA("Kinyarwanda", "rw"),
    KIRGHIZ("Kirghiz, Kyrgyz", "ky"),
    KOMI("Komi", "kv"),
    KONGO("Kongo", "kg"),
    KOREAN("Korean", "ko"),
    KUANYAMA("Kuanyama, Kwanyama", "kj"),
    KURDISH("Kurdish", "ku"),
    LAO("Lao", "lo"),
    LATIN("Latin", "la"),
    LATVIAN("Latvian", "lv"),
    LIMBURGAN("Limburgan, Limburger, Limburgish", "li"),
    LINGALA("Lingala", "ln"),
    LITHUANIAN("Lithuanian", "lt"),
    LUBA_KATANGA("Luba-Katanga", "lu"),
    LUXEMBOURGISH("Luxembourgish, Letzeburgesch", "lb"),
    MACEDONIAN("Macedonian", "mk"),
    MALAGASY("Malagasy", "mg"),
    MALAY("Malay", "ms"),
    MALAYALAM("Malayalam", "ml"),
    MALTESE("Maltese", "mt"),
    MANX("Manx", "gv"),
    MAORI("Maori", "mi"),
    MARATHI("Marathi", "mr"),
    MARSHALLESE("Marshallese", "mh"),
    MONGOLIAN("Mongolian", "mn"),
    NAURU("Nauru", "na"),
    NAVAJO("Navajo, Navaho", "nv"),
    NDONGA("Ndonga", "ng"),
    NEPALI("Nepali", "ne"),
    NORTHERN_SAMI("Northern Sami", "se"),
    NORTH_NDEBELE("North Ndebele", "nd"),
    NORWEGIAN("Norwegian", "no"),
    NORWEGIAN_BOKMAL("Norwegian Bokmål", "nb"),
    NORWEGIAN_NYNORSK("Norwegian Nynorsk", "nn"),
    OCCITAN("Occitan", "oc"),
    OJIBWA("Ojibwa", "oj"),
    ORIYA("Oriya", "or"),
    OROMO("Oromo", "om"),
    OSSETIAN("Ossetian, Ossetic", "os"),
    PALI("Pali", "pi"),
    PASHTO("Pashto, Pushto", "ps"),
    PERSIAN("Persian", "fa"),
    POLISH("Polish", "pl"),
    PORTUGUESE("Portuguese", "pt"),
    PUNJABI("Punjabi, Panjabi", "pa"),
    QUECHUA("Quechua", "qu"),
    ROMANIAN_MOLDAVIAN("Romanian, Moldavian, Moldovan", "ro"),
    ROMANSH("Romansh", "rm"),
    RUNDI("Rundi", "rn"),
    RUSSIAN("Russian", "ru"),
    SAMOAN("Samoan", "sm"),
    SANGO("Sango", "sg"),
    SANSKRIT("Sanskrit", "sa"),
    SARDINIAN("Sardinian", "sc"),
    SERBIAN("Serbian", "sr"),
    SHONA("Shona", "sn"),
    SICHUAN_YI("Sichuan Yi, Nuosu", "ii"),
    SINDHI("Sindhi", "sd"),
    SINHALA("Sinhala, Sinhalese", "si"),
    SLOVAK("Slovak", "sk"),
    SLOVENIAN("Slovenian", "sl"),
    SOMALI("Somali", "so"),
    SOUTHERN_SOTHO("Southern Sotho", "st"),
    SOUTH_NDEBELE("South Ndebele", "nr"),
    SPANISH("Spanish, Castilian", "es"),
    SUNDANESE("Sundanese", "su"),
    SWAHILI("Swahili", "sw"),
    SWATI("Swati", "ss"),
    SWEDISH("Swedish", "sv"),
    TAGALOG("Tagalog", "tl"),
    TAHITIAN("Tahitian", "ty"),
    TAJIK("Tajik", "tg"),
    TAMIL("Tamil", "ta"),
    TATAR("Tatar", "tt"),
    TELUGU("Telugu", "te"),
    THAI("Thai", "th"),
    TIBETAN("Tibetan", "bo"),
    TIGRINYA("Tigrinya", "ti"),
    TONGA("Tonga (Tonga Islands)", "to"),
    TSONGA("Tsonga", "ts"),
    TSWANA("Tswana", "tn"),
    TURKISH("Turkish", "tr"),
    TURKMEN("Turkmen", "tk"),
    TWI("Twi", "tw"),
    UIGHUR("Uighur, Uyghur", "ug"),
    UKRAINIAN("Ukrainian", "uk"),
    URDU("Urdu", "ur"),
    UZBEK("Uzbek", "uz"),
    VENDA("Venda", "ve"),
    VIETNAMESE("Vietnamese", "vi"),
    VOLAPUK("Volapük", "vo"),
    WALLOON("Walloon", "wa"),
    WELSH("Welsh", "cy"),
    WESTERN_FRISIAN("Western Frisian", "fy"),
    WOLOF("Wolof", "wo"),
    XHOSA("Xhosa", "xh"),
    YIDDISH("Yiddish", "yi"),
    YORUBA("Yoruba", "yo"),
    ZHUANG("Zhuang, Chuang", "za"),
    ZULU("Zulu", "zu");

    /**
     * Returns [ISO6391Code] as [ISO6391CodeOutput].
     */
    fun toISO6391CodeOutput(): ISO6391CodeOutput {
        return ISO6391CodeOutput(language = this.language, code = this.code)
    }

    /**
     * Static methods and properties.
     */
    companion object {

        @OptIn(ExperimentalStdlibApi::class)
        val codeArray: Array<String>
            get() {
                return entries.map { it.code }.toTypedArray()
            }

        /**
         * Resolves theg given string to ISO 639-1 language code.
         * @param locale The string to resolve.
         * @return The ISO 639-1 language code. If the given string is not a valid ISO 639-1 language code, ANY is returned
         */
        @OptIn(ExperimentalStdlibApi::class)
        fun resolve(locale: String): ISO6391Code {
            if (locale.trim().isNotEmpty()) {
                val regex = Regex("/[^a-zA-Z_]/")
                val code = locale.trim().lowercase().replace(regex, "").substring(0, 2)
                for (lang in entries) {
                    if (lang.code.equals(code, ignoreCase = true)) {
                        return lang
                    }
                }
            }
            return ANY
        }

    }
}