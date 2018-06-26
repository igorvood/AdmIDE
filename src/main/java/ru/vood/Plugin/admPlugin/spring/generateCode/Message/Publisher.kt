package ru.vood.Plugin.admPlugin.spring.generateCode.Message

import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity

class Publisher : ApplicationContextAware {

    private lateinit var ctx: ApplicationContext

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        this.ctx = applicationContext
    }

    fun publish(source: VBdObjectEntity, fullNameClass: String) {
        this.ctx.publishEvent(AddImportEvent(source, fullNameClass));
    }
}