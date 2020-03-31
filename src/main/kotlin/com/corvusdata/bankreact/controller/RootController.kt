package com.corvusdata.bankreact.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class RootController {

    @Value("\${msg.title}")
    lateinit var title: String

    @GetMapping("/", "/index")
    fun index(model: Model): String {
        model.addAttribute("title", title)
        return "index"
    }
}