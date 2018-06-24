package ru.vood.Plugin.admPlugin.spring.generateCode.impl

import org.springframework.stereotype.Component

@Component
class KotkinTest {

    fun toCamelCase(init: String?): String? {
        if (init == null)
            return null

        var ret = StringBuilder(init.length)

        for (word in init.split("_".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()) {
            if (!word.isEmpty()) {
                ret.append(word.substring(0, 1).toUpperCase())
                ret.append(word.substring(1).toLowerCase())
            }
            /* if (!(ret.length()==init.length()))
            ret.append(" ");*/
        }

        return ret.toString()
    }
}