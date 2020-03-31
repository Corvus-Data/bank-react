package com.corvusdata.bankreact.controller

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class RootController {

    private val logger = LoggerFactory.getLogger(javaClass)

    @Value("\${msg.title}")
    lateinit var title: String

    @GetMapping("/", "/index")
    fun index(model: Model): String {
        logger.debug("Inicia la aplicacion")
        model.addAttribute("title", title)
        return "index"
    }
}